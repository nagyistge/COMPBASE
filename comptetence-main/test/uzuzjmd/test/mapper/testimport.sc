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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



object testimport {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val rcdeo = new Rdceo                           //> rcdeo  : uzuzjmd.rcd.generated.Rdceo = uzuzjmd.rcd.generated.Rdceo@1fe48af2
                                                  //| 
  val competence : String = "Lehramtstudent programmiert bemerkenswerte Programme"
                                                  //> competence  : String = Lehramtstudent programmiert bemerkenswerte Programme
                                                  //| 
  val catchwordsFiltered : List[String] = "Programmierung" ::Nil
                                                  //> catchwordsFiltered  : List[String] = List(Programmierung)
  val operator : String = "programmieren"         //> operator  : String = programmieren
  val metaoperator : String = "herstellen"        //> metaoperator  : String = herstellen
  val evidencen : String = "testevidenz"          //> evidencen  : String = testevidenz
  val metacatchwords : List[String] = "Programmierung" :: "Java" :: "Scala" :: Nil
                                                  //> metacatchwords  : List[String] = List(Programmierung, Java, Scala)
  val learner : String = "Lehramtsstudent"        //> learner  : String = Lehramtsstudent
  val filteredCSVcomptence = new FilteredCSVCompetence(competence,catchwordsFiltered,operator,metaoperator,evidencen,metacatchwords,learner)
                                                  //> filteredCSVcomptence  : uzuzjmd.csv.competence.FilteredCSVCompetence = Filt
                                                  //| eredCSVCompetence(Lehramtstudent programmiert bemerkenswerte Programme,List
                                                  //| (Programmierung),programmieren,herstellen,testevidenz,List(Programmierung, 
                                                  //| Java, Scala),Lehramtsstudent)
  val csvList = filteredCSVcomptence :: Nil       //> csvList  : List[uzuzjmd.csv.competence.FilteredCSVCompetence] = List(Filter
                                                  //| edCSVCompetence(Lehramtstudent programmiert bemerkenswerte Programme,List(P
                                                  //| rogrammierung),programmieren,herstellen,testevidenz,List(Programmierung, Ja
                                                  //| va, Scala),Lehramtsstudent))
  val rcdeos = CSV2RCD.mapCompetence(csvList.toBuffer)
                                                  //> rcdeos  : scala.collection.mutable.Buffer[uzuzjmd.rcd.generated.Rdceo] = Ar
                                                  //| rayBuffer(uzuzjmd.rcd.generated.Rdceo@58d7330d)
  
  val testrcdeo =  rcdeos.head                    //> testrcdeo  : uzuzjmd.rcd.generated.Rdceo = uzuzjmd.rcd.generated.Rdceo@58d7
                                                  //| 330d
  val statement = CSV2RCD.createStatement("tippen", CompOntClass.SubOperator.name())
                                                  //> statement  : uzuzjmd.rcd.generated.Statement = uzuzjmd.rcd.generated.Statem
                                                  //| ent@1858c80c
  testrcdeo.getDefinition().asScala.head.getStatement().add(statement)
                                                  //> res0: Boolean = true
  val manager = new CompOntologyManager           //> SLF4J: Class path contains multiple SLF4J bindings.
                                                  //| SLF4J: Found binding in [jar:file:/I:/workspace/Wissensmodellierung/compete
                                                  //| nce-dependencies/dependencies_lib/slf4j-log4j12-1.5.8.jar!/org/slf4j/impl/S
                                                  //| taticLoggerBinder.class]
                                                  //| SLF4J: Found binding in [jar:file:/I:/workspace/Wissensmodellierung/compete
                                                  //| nce-dependencies/dependencies_lib/slf4j-log4j12-1.6.4.jar!/org/slf4j/impl/S
                                                  //| taticLoggerBinder.class]
                                                  //| SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explana
                                                  //| tion.
                                                  //| manager  : uzuzjmd.owl.util.CompOntologyManager = uzuzjmd.owl.util.CompOnto
                                                  //| logyManager@22d4a91e
  val ontModel = RCD2OWL.convert(rcdeos, manager) //> DEBUG [main] (RCD2OWL.scala:68) - Classes for Titles created
                                                  //| DEBUG [main] (RCD2OWL.scala:70) - ObjectProps for default cases created
                                                  //| DEBUG [main] (RCD2OWL.scala:72) - SubOperator rels created
                                                  //| DEBUG [main] (RCD2OWL.scala:74) - metaoperator rels created
                                                  //| ontModel  : com.hp.hpl.jena.ontology.OntModel = <ModelCom   {http://comp#Co
                                                  //| mpetence @rdfs:subClassOf owl:Thing; rdfs:domain @rdfs:domain rdf:Property;
                                                  //|  rdfs:domain @rdfs:range rdfs:Class; rdfs:domain @rdf:type rdf:Property; rd
                                                  //| fs:domain @rdfs:subPropertyOf rdfs:domain; rdfs:domain @owl:equivalentPrope
                                                  //| rty rdfs:domain; http://comp#OperatorOf @owl:equivalentProperty http://comp
                                                  //| #OperatorOf; http://comp#OperatorOf @rdfs:subPropertyOf http://comp#Operato
                                                  //| rOf; http://comp#OperatorOf @rdf:type rdf:Property; http://comp#OperatorOf 
                                                  //| @rdfs:range owl:Thing; http://comp#OperatorOf @rdfs:domain owl:Thing; owl:P
                                                  //| roperty @rdf:type rdfs:Class; owl:Property @rdfs:subClassOf rdfs:Resour
                                                  //| Output exceeds cutoff limit.
 ontModel.listClasses().toSet().asScala.map(x=>x.getLocalName())
                                                  //> res1: scala.collection.mutable.Set[String] = Set(DatatypeProperty, Thing, R
                                                  //| estriction, SymmetricProperty, Datatype, Seq, FunctionalProperty, programmi
                                                  //| eren, ObjectProperty, tippen, Container, testevidenz, InverseFunctionalProp
                                                  //| erty, ContainerMembershipProperty, Kompetenz-1017147894, Competence, Eviden
                                                  //| ce, Operator, Resource, TransitiveProperty, Learner, Java, Scala, Bag, Noth
                                                  //| ing, Programmierung, Lehramtsstudent, Catchword, Alt, Class, OntologyProper
                                                  //| ty, Property)
 ontModel.listClasses().toSet().asScala.filter(x => x.getLocalName().equals("Thing") || x.getLocalName().equals("Resource")  ).map(x=>(x.getLocalName(), "super:"+ x.getSuperClass().getLocalName(), "sub:" + x.getSubClass().getLocalName) )
                                                  //> res2: scala.collection.mutable.Set[(String, String, String)] = Set((Thing,s
                                                  //| uper:Resource,sub:Scala), (Resource,super:Resource,sub:Thing))

 
                                                    
 
  
}