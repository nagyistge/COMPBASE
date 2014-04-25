import uzuzjmd.competence.service.rest.CompetenceServiceRest
import scala.collection.JavaConverters._
import uzuzjmd.competence.service.rest.dto.CompetenceTree
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.MagicStrings
import com.hp.hpl.jena.ontology.OntProperty
//import uzuzjmd.competence.owl.access.Singletons
import uzuzjmd.competence.owl.access.CompFileUtil
import uzuzjmd.competence.owl.ontology.Helper


object Ont2CompetenceTree {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(584); 
  println("Welcome to the Scala worksheet");$skip(83); 
  //val ontologyManager = Singletons.ontologyManager
 
  val helper = new Helper();System.out.println("""helper  : uzuzjmd.competence.owl.ontology.Helper = """ + $show(helper ));$skip(56); 
  val ontologyManager = helper.getOntologyManagerInit();System.out.println("""ontologyManager  : uzuzjmd.competence.owl.access.CompOntologyManager = """ + $show(ontologyManager ));$skip(66); 
  
  val compFileUtil = new CompFileUtil(ontologyManager.getM());System.out.println("""compFileUtil  : uzuzjmd.competence.owl.access.CompFileUtil = """ + $show(compFileUtil ));$skip(34); 
  compFileUtil.writeOntologyout();$skip(94); 
  
  
   val competenceClass = ontologyManager.getUtil().getClass(CompOntClass.Competence);System.out.println("""competenceClass  : com.hp.hpl.jena.ontology.OntClass = """ + $show(competenceClass ));$skip(72); ;
    val competences = competenceClass.listSubClasses().toList().asScala;System.out.println("""competences  : scala.collection.mutable.Buffer[com.hp.hpl.jena.ontology.OntClass] = """ + $show(competences ));$skip(19); val res$0 = 
		competences.head;System.out.println("""res0: com.hp.hpl.jena.ontology.OntClass = """ + $show(res$0));$skip(21); val res$1 = 
    competences.size;System.out.println("""res1: Int = """ + $show(res$1));$skip(98); 
    val singleTons = competences.map(x => ontologyManager.getUtil().createSingleTonIndividual(x));System.out.println("""singleTons  : scala.collection.mutable.Buffer[com.hp.hpl.jena.ontology.Individual] = """ + $show(singleTons ));$skip(240); 

    //val iProperty = ontologyManager.getM.getOntProperty(MagicStrings.PREFIX + "definition")
                                                   
    val iProperty = ontologyManager.getM().getProperty(MagicStrings.PREFIX + "definition");System.out.println("""iProperty  : com.hp.hpl.jena.rdf.model.Property = """ + $show(iProperty ));$skip(125); 
                                                      
    val definitions = competences.map(x => x.getProperty(iProperty));System.out.println("""definitions  : scala.collection.mutable.Buffer[com.hp.hpl.jena.rdf.model.Statement] = """ + $show(definitions ));$skip(117); 
    val result = definitions.filterNot(_ == null).map(x => new CompetenceTree(x.toString(), "Kompetenz", "", null));System.out.println("""result  : scala.collection.mutable.Buffer[uzuzjmd.competence.service.rest.dto.CompetenceTree] = """ + $show(result ));$skip(50); ;
    // todo implement
    ontologyManager.close()}
  
}
