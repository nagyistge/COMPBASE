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


object testimport {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(1618); 
  println("Welcome to the Scala worksheet");$skip(27); 
  
  val rcdeo = new Rdceo;System.out.println("""rcdeo  : uzuzjmd.rcd.generated.Rdceo = """ + $show(rcdeo ));$skip(83); 
  val competence : String = "Lehramtstudent programmiert bemerkenswerte Programme";System.out.println("""competence  : String = """ + $show(competence ));$skip(65); 
  val catchwordsFiltered : List[String] = "Programmierung" ::Nil;System.out.println("""catchwordsFiltered  : List[String] = """ + $show(catchwordsFiltered ));$skip(42); 
  val operator : String = "programmieren";System.out.println("""operator  : String = """ + $show(operator ));$skip(43); 
  val metaoperator : String = "herstellen";System.out.println("""metaoperator  : String = """ + $show(metaoperator ));$skip(41); 
  val evidencen : String = "testevidenz";System.out.println("""evidencen  : String = """ + $show(evidencen ));$skip(83); 
  val metacatchwords : List[String] = "Programmierung" :: "Java" :: "Scala" :: Nil;System.out.println("""metacatchwords  : List[String] = """ + $show(metacatchwords ));$skip(43); 
  val learner : String = "Lehramtsstudent";System.out.println("""learner  : String = """ + $show(learner ));$skip(141); 
  val filteredCSVcomptence = new FilteredCSVCompetence(competence,catchwordsFiltered,operator,metaoperator,evidencen,metacatchwords,learner);System.out.println("""filteredCSVcomptence  : uzuzjmd.csv.competence.FilteredCSVCompetence = """ + $show(filteredCSVcomptence ));$skip(44); 
  val csvList = filteredCSVcomptence :: Nil;System.out.println("""csvList  : List[uzuzjmd.csv.competence.FilteredCSVCompetence] = """ + $show(csvList ));$skip(55); 
  val rcdeos = CSV2RCD.mapCompetence(csvList.toBuffer);System.out.println("""rcdeos  : scala.collection.mutable.Buffer[uzuzjmd.rcd.generated.Rdceo] = """ + $show(rcdeos ));$skip(34); 
  
  val testrcdeo =  rcdeos.head;System.out.println("""testrcdeo  : uzuzjmd.rcd.generated.Rdceo = """ + $show(testrcdeo ));$skip(85); 
  val statement = CSV2RCD.createStatement("tippen", CompOntClass.SubOperator.name());System.out.println("""statement  : uzuzjmd.rcd.generated.Statement = """ + $show(statement ));$skip(71); val res$0 = 
  testrcdeo.getDefinition().asScala.head.getStatement().add(statement);System.out.println("""res0: Boolean = """ + $show(res$0));$skip(40); 
  val manager = new CompOntologyManager;System.out.println("""manager  : uzuzjmd.owl.util.CompOntologyManager = """ + $show(manager ));$skip(50); 
  val ontModel = RCD2OWL.convert(rcdeos, manager);System.out.println("""ontModel  : com.hp.hpl.jena.ontology.OntModel = """ + $show(ontModel ));$skip(65); val res$1 = 
 ontModel.listClasses().toSet().asScala.map(x=>x.getLocalName());System.out.println("""res1: scala.collection.mutable.Set[String] = """ + $show(res$1));$skip(238); val res$2 = 
 ontModel.listClasses().toSet().asScala.filter(x => x.getLocalName().equals("Thing") || x.getLocalName().equals("Resource")  ).map(x=>(x.getLocalName(), "super:"+ x.getSuperClass().getLocalName(), "sub:" + x.getSubClass().getLocalName) );System.out.println("""res2: scala.collection.mutable.Set[(String, String, String)] = """ + $show(res$2))}

 
                                                    
 
  
}
