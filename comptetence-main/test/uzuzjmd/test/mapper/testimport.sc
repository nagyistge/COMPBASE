package uzuzjmd.test.mapper

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
import uzuzjmd.csv.competence.FilteredCSVCompetence
import uzuzjmd.csv.competence.FilteredCSVCompetence
import uzuzjmd.rcd.mapper.RCD2OWL
import uzuzjmd.rcd.mapper.CSV2RCD


object testimport {
  println("Welcome to the Scala worksheet")
  
  val rcdeo = new Rdceo
  val competence : String = "Lehramtstudent programmiert bemerkenswerte Programme"
  val catchwordsFiltered : List[String] = "Programmierung" ::Nil
  val operator : String = "programmieren"
  val metaoperator : String = "herstellen"
  val evidencen : String = "testevidenz"
  val metacatchwords : List[String] = "Programmierung" :: "Java" :: "Scala" :: Nil
  val learner : String = "Lehramtsstudent"
  val filteredCSVcomptence = new FilteredCSVCompetence(competence,catchwordsFiltered,operator,metaoperator,evidencen,metacatchwords,learner)
  val csvList = filteredCSVcomptence :: Nil
  val rcdeos = CSV2RCD.mapCompetence(csvList.toBuffer)
  
  val testrcdeo =  rcdeos.head
  val statement = CSV2RCD.createStatement("tippen", CompOntClass.SubOperator.name())
  testrcdeo.getDefinition().asScala.head.getStatement().add(statement)
  val manager = new CompOntologyManager
  val ontModel = RCD2OWL.convert(rcdeos, manager)
 ontModel.listClasses().toSet().asScala.map(x=>x.getLocalName())
 ontModel.listClasses().toSet().asScala.filter(x => x.getLocalName().equals("Thing") || x.getLocalName().equals("Resource")  ).map(x=>(x.getLocalName(), "super:"+ x.getSuperClass().getLocalName(), "sub:" + x.getSubClass().getLocalName) )

 
                                                    
 
  
}