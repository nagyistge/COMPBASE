package uzuzjmd.competence.mapper.rcd

import org.apache.log4j.Level
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import scala.collection.mutable.Buffer
import scala.collection.JavaConverters._
import com.hp.hpl.jena.ontology.OntModel
import com.hp.hpl.jena.ontology.ObjectProperty
import com.hp.hpl.jena.ontology.OntClass
import com.hp.hpl.jena.ontology.Individual
import com.hp.hpl.jena.util.iterator.Filter
import uzuzjmd.competence.rcd.RCDMaps
import uzuzjmd.competence.owl.access.CompOntologyAccess
import uzuzjmd.competence.rcd.RCDFilter
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.rcd.generated.Rdceo
import uzuzjmd.competence.rcd.generated.Title
import uzuzjmd.competence.console.util.LogStream
import uzuzjmd.competence.rcd.generated.Statement
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.MagicStrings
import java.util.ArrayList
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.access.TDBWriteTransactional

/**
 *
 * Diese Klasse mappt Daten im RDCEO-Format auf die Ontologie
 * TODO change name
 */

object RCD2OWL extends RCDImplicits with TDBWriteTransactional[Seq[Rdceo]] {

  val logger = LogManager.getLogger(RCD2OWL.getClass().getName());
  logger.setLevel(Level.TRACE)
  val logStream = new LogStream(logger, Level.DEBUG);

  def convertList(rcdeos: ArrayList[Rdceo], manager: CompOntologyManager) {
    val rdceos = rcdeos.asScala
    execute(convert _, rdceos)
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
  def convert(manager: CompOntologyManager, rcdeos: Seq[Rdceo]) {

    val logger = RCD2OWL.logger
    val logStream = RCD2OWL.logStream

    val util = manager.getUtil()
    val ontModel = manager.getM()

    val triples = getStatementTriples(rcdeos).view.map(RCDMaps.convertTriplesToOperators).filterNot(RCDFilter.isTripleWithBlanc)
    // debugging output
    triples.map(x => logger.trace("Triple: " + x._1 + " " + x._2 + " " + x._3))

    // die mit object property & classes
    val triplesWithObjectProperties = triples.filter(RCDFilter.isObjectPropertyTriple)
    createOntClassesForTitle(util, triplesWithObjectProperties)
    logger.debug("Classes for Titles created")
    createObjectPropertiesForDefaultCases(util, triplesWithObjectProperties)
    logger.debug("ObjectProps for default cases created")
    //RCD2Operators.createSubOperatorRels(util, rcdeos)
    RCD2Catchword.createMetaCatchwordRels(util, rcdeos)
    logger.debug("SubOperator rels created")
    logger.debug("metaoperator rels created")
    // data properties
    rcdeos.foreach(x => util.createOntClassForString(x.getTitle(), false).addLiteral(ontModel.createProperty(MagicStrings.PREFIX, "definition"), x.getDescription().getLangstring().asScala.head.getValue()))
    logger.debug("definition properties created")

    //    util.getIndividualForString("INothing").remove()
    //    util.getOntClassForString("Nothing").remove()

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
  private def getStatementTriples(rcdeos: Seq[Rdceo]): Buffer[RCDFilter.CompetenceTriple] = {
    // transform to pairs of titel and statement
    val inputStatements = rcdeos.map(x => (x.getTitle(), x.getDefinition().asScala.head.getStatement().asScala)).map(x => flatStatements(x)).flatten
    // triples: _1 KompetenzIndividual _2 (Title), ObjectProperty, _3 Individual related to that ObjectProperty i.e. OperatorIndividual to OperatorOf
    val triples = inputStatements.map(x => (x._1, x._2.getStatementname(), x._2.getStatementtext()))
    val result = triples.toBuffer
    return result
  }

  /**
   *
   *  classes erstellen
   */
  private def createOntClassesForTitle(util: CompOntologyAccess, triples: Seq[(String, String, String)]): Unit = {

    // die mit subcompetence
    val triplesGroupedByTitles2a = triples.filter(RCDFilter.isSubClassTriple).groupBy(x => x._1)
    // we assume, that the triple text contains the super-class
    //triplesGroupedByTitles2a.foreach(titleMap => titleMap._2.foreach(triple => util.createOntClassForString(titleMap._1).addSuperClass(util.getOntClassForString(triple._3))))
    triplesGroupedByTitles2a.foreach(titleMap => titleMap._2.foreach(triple => new Competence(util.getManager(), titleMap._1, titleMap._1, false).addSuperClass(new Competence(util.getManager(), triple._3, triple._3, false))))

    // die ohne subcompetence
    val triplesGroupedByTitles2b = triples.filterNot(RCDFilter.isSubClassTriple).groupBy(x => x._1)
    triplesGroupedByTitles2b.foreach(x => util.createOntClassForString(x._1, false).addSuperClass(util.getClass(CompOntClass.Competence, false)))

    // finally noch individuals  jede Kompetenz erstellen
    triples.foreach(x => util.createIndividualForString(util.getOntClassForString(x._1), "I" + x._1, false))
  }

  private def createDescriptionElementOfRels(util: CompOntologyAccess, triplesWithObjectProperties: Seq[(String, String, String)]) = {
    triplesWithObjectProperties.filter(RCDFilter.isDescriptionElementOfTriple).
      foreach(x => competenceDescriptionToOnt(x, util))
  }

  /**
   * Die Triple entsprechen mehr oder weniger den RDF-Triplen, die die ObjectProperties ausmachen
   * vgl. RCDFilter.CompetenceTriple
   */
  private def createObjectPropertiesForDefaultCases(util: CompOntologyAccess, triplesWithObjectProperties: Seq[RCDFilter.CompetenceTriple]): Unit = {
    val defaultCasesObjectProperties = triplesWithObjectProperties.
      filterNot(RCDFilter.isDescriptionElementOfTriple).
      filterNot(RCDFilter.isMetaCatchwordOfTriple).
      filterNot(RCDFilter.isSubClassTriple).
      filterNot(RCDFilter.isSubOperatorTriple)
    defaultCasesObjectProperties.foreach(handleDefaultCases(util))
  }

  /**
   * returns the class associated with this property
   * HelferClass  handledefaultcases
   */
  private def getPropertyClass(util: CompOntologyAccess, input: String): OntClass = {
    return util.getClass(RCDMaps.objectPropertyToClass(CompObjectProperties.valueOf((input))), false)
  }

  private def handleDefaultCases(util: CompOntologyAccess)(input: RCDFilter.CompetenceTriple) {
    val genericClass = getPropertyClass(util, input._2)
    val localname = input._3
    val genericClassAsOnt = util.createOntClassForString(localname, false, localname)
    genericClassAsOnt.addSuperClass(genericClass)
    val genericIndividual = util.createSingleTonIndividual(genericClassAsOnt, false)
    val competenceIndividual = util.createSingleTonIndividual(util.getOntClassForString(input._1), false)
    util.createObjectPropertyWithIndividual(genericIndividual, competenceIndividual, CompObjectProperties.valueOf(input._2))
  }

}