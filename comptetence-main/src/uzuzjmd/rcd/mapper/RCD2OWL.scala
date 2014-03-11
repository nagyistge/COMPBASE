package uzuzjmd.rcd.mapper

import org.apache.log4j.Level
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import uzuzjmd.csv.competence.FilteredCSVCompetence
import uzuzjmd.rcd.competence.RCDFilter
import uzuzjmd.rcd.generated.Rdceo
import scala.collection.mutable.Buffer
import scala.collection.JavaConverters._
import uzuzjmd.rcd.generated.Title
import uzuzjmd.rcd.generated.Langstring
import uzuzjmd.rcd.generated.Description
import uzuzjmd.rcd.generated.Identifier
import uzuzjmd.rcd.generated.Definition
import uzuzjmd.rcd.generated.Model
import uzuzjmd.rcd.generated.Metadata
import uzuzjmd.rcd.generated.Statement
import uzuzjmd.rcd.generated.Statementtext
import uzuzjmd.rcd.generated.Rdceoschema
import uzuzjmd.rcd.generated.Rdceoschemaversion
import uzuzjmd.owl.competence.ontology.CompOntClass
import uzuzjmd.owl.util.MagicStrings
import edu.uci.ics.jung.io.graphml.Metadata
import com.hp.hpl.jena.ontology.OntModel
import com.hp.hpl.jena.ontology.ObjectProperty
import uzuzjmd.owl.util.CompOntologyAccess
import uzuzjmd.owl.competence.ontology.CompObjectProperties
import uzuzjmd.owl.util.CompOntologyManager
import com.hp.hpl.jena.ontology.OntClass
import com.hp.hpl.jena.ontology.Individual
import com.hp.hpl.jena.util.iterator.Filter
import uzuzjmd.console.util.LogStream
import uzuzjmd.rcd.competence.RCDMaps
import uzuzjmd.owl.util.CompFileUtil

object RCD2OWL {

  val logger = LogManager.getLogger(RCD2OWL.getClass().getName());
  logger.setLevel(Level.DEBUG)
  val logStream = new LogStream(logger, Level.DEBUG);

  /**
   * A bunch of useful toString - Methoden, um die Arbeit mit RCDEOs zu vereinfachen
   * Wichtige Annahme: Die formell als Liste definierte Langstring-Liste besteht immer nur aus einem Element
   * Wenn dies anders verwendet werden soll, gelten diese implicits nicht mehr
   *
   */

  implicit def amyString1(x: Langstring): String = {
    return x.getValue()
  }
  implicit def amyString2(x: Buffer[Langstring]): String = {
    return amyString1(x.head)
  }

  implicit def toMyString3(x: Title): String = {
    return x.getLangstring().asScala.head.getValue()
  }

  implicit def toMyString4(x: Statementtext): String = {
    return x.getLangstring().asScala.head.getValue()
  }

  implicit def toMyString5(x: (Title, String, Statementtext)): (RCDFilter.CompetenceTriple) = {
    return (toMyString3(x._1), x._2, toMyString4(x._3))
  }

  implicit def toMyString6(x: Buffer[(Title, String, Statementtext)]): Buffer[RCDFilter.CompetenceTriple] = {
    return x.map(x => toMyString5(x))
  }

  /**
   * special cases
   * _2 == SubCompetenceOf --> should transform to owl:Subclass of Competence
   * 		and should be transitiv property
   * _2 == SubOperatorOf --> should transform to owl:Subclass of Operator
   * 		and should be transitiv property
   * _2 == MetaCatchwordOf --> should transform to owl:Subclass of Catchword
   * 		and should be transitiv property
   * _2 == similarTo should be transitiv and reflexiv property
   * _2 == DescriptionElementOf -> should be linked to the DescriptionElement instead of the Competence directly
   */
  def convert(rcdeos: Buffer[Rdceo], manager: CompOntologyManager): OntModel = {
    val logger = RCD2OWL.logger
    val logStream = RCD2OWL.logStream

    val util = manager.getUtil()
    val ontModel = manager.getM()

    val triples = getStatementTriples(rcdeos).map(RCDMaps.convertTriplesToOperators).filterNot(RCDFilter.isTripleWithBlanc)
    // debugging output
    triples.map(x => logger.trace("Triple: " + x._1 + " " + x._2 + " " + x._3))

    // die mit object property & classes
    val triplesWithObjectProperties = triples.filter(RCDFilter.isObjectPropertyTriple)
    createOntClassesForTitle(util, triplesWithObjectProperties)
    logger.debug("Classes for Titles created")
    createObjectPropertiesForDefaultCases(util, triplesWithObjectProperties)
    logger.debug("ObjectProps for default cases created")

    createSubOperatorRels(util, rcdeos)
    logger.debug("SubOperator rels created")
    createMetacatchwordRels(util, triplesWithObjectProperties)
    logger.debug("metaoperator rels created")

    // data properties
    rcdeos.foreach(x => util.createOntClassForString(x.getTitle()).addLiteral(ontModel.createProperty(MagicStrings.PREFIX, "definition"), x.getDescription().getLangstring().asScala.head.getValue()))

    // for debugging
    val compFileUtil = new CompFileUtil(manager.getM());
    compFileUtil.writeOntologyout(manager.getM());

    // debugging output
    //triples.map(x => println("Triple" + x._1 + " " + x._2 + " " + x._3))
    return ontModel

  }

  /**
   * vgl. RCDFilter.CompetenceTriple
   * jedem CompetenceDescriptionElement wird die ObjectProperty
   * TODO:TestThisMethod
   */
  private def competenceDescriptionToOnt(triple: RCDFilter.CompetenceTriple, util: CompOntologyAccess) {
    //TODO implement
    throw new NotImplementedError
  }

  /**
   * Utility Function
   * links the statements to the specified title
   */
  private def flatStatements(titleStatements: (Title, Buffer[Statement])): Buffer[(Title, Statement)] = {
    return titleStatements._2.map(y => (titleStatements._1, y))
  }

  /**
   *
   * @return triples: _1 KompetenzIndividual _2 (Title), ObjectProperty, _3 Individual related to that ObjectProperty i.e. OperatorIndividual to OperatorOf
   */
  private def getStatementTriples(rcdeos: scala.collection.mutable.Buffer[uzuzjmd.rcd.generated.Rdceo]): scala.collection.mutable.Buffer[RCDFilter.CompetenceTriple] = {
    // transform to pairs of titel and statement 
    val inputStatements = rcdeos.map(x => (x.getTitle(), x.getDefinition().asScala.head.getStatement().asScala)).map(x => flatStatements(x)).flatten
    // triples: _1 KompetenzIndividual _2 (Title), ObjectProperty, _3 Individual related to that ObjectProperty i.e. OperatorIndividual to OperatorOf
    val triples: Buffer[RCDFilter.CompetenceTriple] = inputStatements.map(x => (x._1, x._2.getStatementname(), x._2.getStatementtext()))
    triples
  }

  /**
   *
   *  classes erstellen
   */
  private def createOntClassesForTitle(util: uzuzjmd.owl.util.CompOntologyAccess, triples: scala.collection.mutable.Buffer[(String, String, String)]): Unit = {

    // die mit subcompetence
    val triplesGroupedByTitles2a = triples.filter(RCDFilter.isSubClassTriple).groupBy(x => x._1)
    // we assume, that the triple text contains the super-class
    triplesGroupedByTitles2a.foreach(titleMap => titleMap._2.foreach(triple => util.createOntClassForString(titleMap._1).addSuperClass(util.getOntClassForString(triple._3))))

    // die ohne subcompetence
    val triplesGroupedByTitles2b = triples.filterNot(RCDFilter.isSubClassTriple).groupBy(x => x._1)
    triplesGroupedByTitles2b.foreach(x => util.createOntClassForString(x._1).addSuperClass(util.getClass(CompOntClass.Competence)))

    // finally noch individuals für jede Kompetenz erstellen
    triples.foreach(x => util.createIndividualForString(util.getOntClassForString(x._1), "I" + x._1))
  }

  /**
   * Looks up Competence for each Title
   * Then it searches for all Operators of this Competence
   * Then it makes the operator inherit them
   */
  private def createSubOperatorRels(util: uzuzjmd.owl.util.CompOntologyAccess, rcdeos: Buffer[Rdceo]) {
    rcdeos.foreach(handleRcdeoSubOperators(util))
  }

  /**
   * maps the (sub)operator parts of the rcdeo to a model represantation
   * Hilfsfunktion für createSubOperatorRels
   */
  private def handleRcdeoSubOperators(util: CompOntologyAccess)(rcdeo: Rdceo) {
    // suboperatorTriples.foreach(handleSubOperatorTriple(util))
    val title = rcdeo.getTitle()
    val statements = rcdeo.getDefinition().asScala.head.getStatement().asScala
    val operators = statements.filter(statement => statement.getStatementname().equals(CompOntClass.Operator.name())).map(statement => statement.getStatementtext())
    val subOperators = statements.filter(statement => statement.getStatementname().equals(CompOntClass.SubOperator.name())).map(statement => statement.getStatementtext())
    subOperators.foreach(subOperators => operators.foreach(operator => handleSubOperatorTriple(util)((title, operator, subOperators))))
  }

  /**
   * suboperatortriples: x._1 competence, x._2 operator,  x._3 suboperator,
   * Hilfsfunktion für handleRcdeoSubOperators
   */
  private def handleSubOperatorTriple(util: CompOntologyAccess)(input: RCDFilter.OperatorTriple) {
    val operatorIndividual: Individual = util.createSingleTonIndividualWithClass2(input._3)
    val competenceIndividual: Individual = util.createSingleTonIndividual(input._1)
    val superOperatorClass: OntClass = util.createSingleTonIndividualWithClass(input._2)
    operatorIndividual.getOntClass().addSuperClass(superOperatorClass)
    util.createObjectPropertyWithIndividual(operatorIndividual, competenceIndividual, CompObjectProperties.OperatorOf)
  }

  /**
   * same procedure as with subOperatorRels
   */
  private def createMetacatchwordRels(util: uzuzjmd.owl.util.CompOntologyAccess, triplesWithObjectProperties: scala.collection.mutable.Buffer[RCDFilter.CompetenceTriple]): Unit = {

  }

  private def createDescriptionElementOfRels(util: uzuzjmd.owl.util.CompOntologyAccess, triplesWithObjectProperties: scala.collection.mutable.Buffer[(String, String, String)]) = {
    triplesWithObjectProperties.filter(RCDFilter.isDescriptionElementOfTriple).
      foreach(x => competenceDescriptionToOnt(x, util))
  }

  /**
   * Die Triple entsprechen mehr oder weniger den RDF-Triplen, die die ObjectProperties ausmachen
   * vgl. RCDFilter.CompetenceTriple
   */
  private def createObjectPropertiesForDefaultCases(util: uzuzjmd.owl.util.CompOntologyAccess, triplesWithObjectProperties: scala.collection.mutable.Buffer[RCDFilter.CompetenceTriple]): Unit = {
    val defaultCasesObjectProperties = triplesWithObjectProperties.
      filterNot(RCDFilter.isDescriptionElementOfTriple).
      filterNot(RCDFilter.isMetaCatchwordOfTriple).
      filterNot(RCDFilter.isSubClassTriple).
      filterNot(RCDFilter.isSubOperatorTriple)
    defaultCasesObjectProperties.foreach(handleDefaultCases(util))
  }

  /**
   * returns the class associated with this property
   */
  private def getPropertyClass(util: CompOntologyAccess, input: String): OntClass = {
    return util.getClass(RCDMaps.objectPropertyToClass(CompObjectProperties.valueOf((input))))
  }

  private def handleDefaultCases(util: CompOntologyAccess)(input: RCDFilter.CompetenceTriple) {
    val genericClass = getPropertyClass(util, input._2)
    val localname = input._3
    val genericClassAsOnt = util.createOntClassForString(localname, localname)
    genericClassAsOnt.addSuperClass(genericClass)
    val genericIndividual = util.createSingleTonIndividual(genericClassAsOnt)
    val competenceIndividual = util.createSingleTonIndividual(util.getOntClassForString(input._1))
    util.createObjectPropertyWithIndividual(genericIndividual, competenceIndividual, CompObjectProperties.valueOf(input._2))
  }

}