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
import uzuzjmd.owl.util.CompOntologyUtil
import uzuzjmd.owl.competence.ontology.CompObjectProperties
import uzuzjmd.owl.util.CompOntologyManager
import com.hp.hpl.jena.ontology.OntClass
import com.hp.hpl.jena.ontology.Individual
import com.hp.hpl.jena.util.iterator.Filter
import uzuzjmd.console.util.LogStream
import uzuzjmd.rcd.competence.RCDMaps


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

  implicit def toMyString1(x: Langstring): String = {
    return x.getValue()
  }
  implicit def toMyString2(x: Buffer[Langstring]): String = {
    return toMyString1(x.head)
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
   * TODO: Needs implementation
   * ist nicht Zustandsfrei, da das StandardModel über den
   * Manager geladen wird
   */
  def convert(rcdeos: Buffer[Rdceo], manager : CompOntologyManager): OntModel = {      
    val ontmodel = manager.getM()
    val objectProperties = convertObjectProperties(rcdeos, ontmodel)
    return ontmodel;
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
  private def convertObjectProperties(rcdeos: Buffer[Rdceo], ontModel: OntModel) {    
    val logger = RCD2OWL.logger
    val logStream = RCD2OWL.logStream
    
    val util = new CompOntologyUtil(ontModel)
        
    
    val triples = getStatementTriples(rcdeos).map(RCDMaps.convertTriplesToOperators).filterNot(RCDFilter.isTripleWithBlanc)
    // debugging output
    triples.map(x => logger.trace("Triple: " + x._1 + " " + x._2 + " " + x._3))
    

    // die mit object property & classes
    val triplesWithObjectProperties = triples.filter(RCDFilter.isObjectPropertyTriple)    
    createOntClassesForTitle(util, triplesWithObjectProperties)        
    logger.debug("Classes for Titles created")
    createObjectPropertiesForDefaultCases(util, triplesWithObjectProperties)    
        
    logger.debug("ObjectProps for default cases created")
    createDescriptionElementOfRels(util, triplesWithObjectProperties)
    logger.debug("DescriptionElementRels created ")
    createSubOperatorRels(util, triplesWithObjectProperties)
    //ontModel.write(logStream)
    logger.debug("SubOperator rels created")
    createMetaOperatorRels(util, triplesWithObjectProperties)
    logger.debug("metaoperator rels created")
    
    util.writeOntologyout(ontModel)

    
    
    // data properties
    //TODO

    // debugging output
    //triples.map(x => println("Triple" + x._1 + " " + x._2 + " " + x._3))
    

  }

  /**
   * vgl. RCDFilter.CompetenceTriple
   * jedem CompetenceDescriptionElement wird die ObjectProperty
   * TODO:TestThisMethod
   */
  private def competenceDescriptionToOnt(triple: RCDFilter.CompetenceTriple, util: CompOntologyUtil) {
    val individualDescriptionElement = util.createIndividualForString(util.getClass(CompOntClass.DescriptionElement), triple._3)
    val objectProperty = util.getObjectPropertyForString(triple._2)   
    val competenceClass = util.getClass(CompOntClass.DescriptionElement)
    // TODO 
    val relatedClasses = util.getRelatedClassesForOntClass(triple._1, CompObjectProperties.HasCompetence);
    relatedClasses.asScala.foreach(y=>individualDescriptionElement.addProperty(objectProperty.asObjectProperty(), y))          
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
  private def createOntClassesForTitle(util: uzuzjmd.owl.util.CompOntologyUtil, triples: scala.collection.mutable.Buffer[(String, String, String)]): Unit = {

    // die mit subcompetence
    val triplesGroupedByTitles2a = triples.filter(RCDFilter.isSubClassTriple).groupBy(x => x._1)
    // we assume, that the triple text contains the super-class
    triplesGroupedByTitles2a.foreach(titleMap => titleMap._2.foreach(triple => util.createOntClassForString(titleMap._1).addSuperClass(util.getOntClassForString(triple._3))))

    // die ohne subcompetence
    val triplesGroupedByTitles2b = triples.filterNot(RCDFilter.isSubClassTriple).groupBy(x => x._1)
    triplesGroupedByTitles2b.foreach(x => util.createOntClassForString(x._1).addSuperClass(util.getClass(CompOntClass.Competence)))
  }

  /**
   * Looks up Competence for each Title
   * Then it searches for all Operators of this Competence
   * Then it makes them inherit the superOperator specified
   * TODO
   */
  private def createSubOperatorRels(util: uzuzjmd.owl.util.CompOntologyUtil, triplesWithObjectProperties: scala.collection.mutable.Buffer[RCDFilter.CompetenceTriple]): Unit = {

//    val suboperatorTriples = triplesWithObjectProperties.filter(RCDFilter.isSubOperatorTriple)
//    suboperatorTriples.foreach(
//      x =>
//        util.getOperatorsForOntClass(x._1).asScala.foreach(y => y.addSubClass(util.createOntClassForString(x._3))))
  }

  /**
   * TODO
   */
  private def createMetaOperatorRels(util: uzuzjmd.owl.util.CompOntologyUtil, triplesWithObjectProperties: scala.collection.mutable.Buffer[RCDFilter.CompetenceTriple]): Unit = {
//    val metaCatchwordTriples = triplesWithObjectProperties.filter(RCDFilter.isMetaCatchwordOfTriple)
//    metaCatchwordTriples.foreach(
//      x => util.getRelatedClassesForOntClass(x._1, CompObjectProperties.CatchwordOf).asScala.foreach(y => y.addSuperClass(util.createOntClassForString(x._3))))
  }

  private def createDescriptionElementOfRels(util: uzuzjmd.owl.util.CompOntologyUtil, triplesWithObjectProperties: scala.collection.mutable.Buffer[(String, String, String)]) = {
    triplesWithObjectProperties.filter(RCDFilter.isDescriptionElementOfTriple).foreach(x => competenceDescriptionToOnt(x, util))
  }

  /**
   * Die Triple entsprechen mehr oder weniger den RDF-Triplen, die die ObjectProperties ausmachen
   * vgl. RCDFilter.CompetenceTriple
   */
  private def createObjectPropertiesForDefaultCases(util: uzuzjmd.owl.util.CompOntologyUtil, triplesWithObjectProperties: scala.collection.mutable.Buffer[RCDFilter.CompetenceTriple]): Unit = {
    val defaultCasesObjectProperties = triplesWithObjectProperties.
      filterNot(RCDFilter.isDescriptionElementOfTriple).
      filterNot(RCDFilter.isMetaCatchwordOfTriple).
      filterNot(RCDFilter.isSubClassTriple).
      filterNot(RCDFilter.isSubOperatorTriple)
    defaultCasesObjectProperties.foreach(x => 
      util.createObjectPropertyWithIndividualAndClass(
          util.createIndividualForString(util.getClass(RCDMaps.objectPropertyToClass(CompObjectProperties.valueOf((x._2)))), x._3),
          util.getOntClassForString(x._1),           
          CompObjectProperties.valueOf(x._2)))
  }

}