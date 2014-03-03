package uzuzjmd.rcd.mapper

import uzuzjmd.csv.competence.FilteredCSVCompetence
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

object RCD2OWL {

  
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

  implicit def toMyString5(x: (Title, String, Statementtext)): (String, String, String) = {
    return (toMyString3(x._1), x._2, toMyString4(x._3))
  }

  implicit def toMyString6(x: Buffer[(Title, String, Statementtext)]): Buffer[(String, String, String)] = {
    return x.map(x => toMyString5(x))
  }

  /**
   * TODO: Needs implementation
   */
  def convert(rcdeos: Buffer[Rdceo]): OntModel = {
    val manager = new CompOntologyManager
    val ontmodel = manager.getM()
    val objectProperties = convertObjectProperties(rcdeos, ontmodel)

    return null;
  }

  def convertObjectProperties(rcdeos: Buffer[Rdceo], ontModel: OntModel): Buffer[ObjectProperty] = {
    val util = new CompOntologyUtil(ontModel)

    // add all operators
    val result: Buffer[ObjectProperty] = Buffer.empty

    // transform to pairs of titel and filters statement that are not classes (defined by CompOntClass)
    val filteredInputSortedByStatementText = rcdeos.map(x => (x.getTitle(), x.getDefinition().asScala.head.getStatement().asScala))
    val flattendStatements: Buffer[(Title, Statement)] = flatStatements(filteredInputSortedByStatementText)
    // triples: KompetenzIndividual (Title), ObjectProperty, Individual related to that ObjectProperty i.e. OperatorIndividual to OperatorOf     
    val triples: Buffer[(String, String, String)] = flattendStatements.map(x => (x._1, x._2.getStatementname(), x._2.getStatementtext()))

    triples.map(x => println("Triple" + x._1 + " " + x._2 + " " + x._3))

    return null;
  }

  /**
   * Utility Function
   * links the statements to the specified title
   */
  def flatStatements(titleStatements: Buffer[(Title, Buffer[Statement])]): Buffer[(Title, Statement)] = {
    titleStatements.map(x => x._2.map(y => (x._1, y))).flatten
  }

  def getPairs[A, B](a: A, b: List[B]): List[(A, B)] = {
    if (b.isEmpty) List.empty
    else (a, b.head) :: getPairs(a, b.tail)
  }

}