import uzuzjmd.competence.service.rest.CompetenceServiceRest
import scala.collection.JavaConverters._
import scala.collection.JavaConverters._
import uzuzjmd.competence.service.rest.dto.CompetenceTree
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.MagicStrings
import com.hp.hpl.jena.ontology.OntProperty
import uzuzjmd.competence.owl.ontology.Singletons
import uzuzjmd.competence.owl.access.CompFileUtil;


object Ont2CompetenceTree {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val ontologyManager = Singletons.ontologyManager//> ontologyManager  : uzuzjmd.competence.owl.access.CompOntologyManager = uzuzj
                                                  //| md.competence.owl.access.CompOntologyManager@bf26484
  
  ontologyManager.begin()
  
  val compFileUtil = new CompFileUtil(ontologyManager.getM())
                                                  //> compFileUtil  : uzuzjmd.competence.owl.access.CompFileUtil = uzuzjmd.compete
                                                  //| nce.owl.access.CompFileUtil@ba38280
  compFileUtil.writeOntologyout()                 //> Written Ontology to mymodelrdf.owl
  
  
   val competenceClass = ontologyManager.getUtil().getClass(CompOntClass.Competence);
                                                  //> competenceClass  : com.hp.hpl.jena.ontology.OntClass = http%3A%2F%2Fcomp%23C
                                                  //| ompetence
    val competences = competenceClass.listSubClasses().toList().asScala
                                                  //> competences  : scala.collection.mutable.Buffer[com.hp.hpl.jena.ontology.OntC
                                                  //| lass] = Buffer(http://www.w3.org/2002/07/owl#Nothing)
		competences.head                  //> res0: com.hp.hpl.jena.ontology.OntClass = http://www.w3.org/2002/07/owl#Noth
                                                  //| ing
    competences.size                              //> res1: Int = 1
    val singleTons = competences.map(x => ontologyManager.getUtil().createSingleTonIndividual(x))
                                                  //> singleTons  : scala.collection.mutable.Buffer[com.hp.hpl.jena.ontology.Indi
                                                  //| vidual] = ArrayBuffer(http%3A%2F%2Fcomp%23INothing)

    //val iProperty = ontologyManager.getM.getOntProperty(MagicStrings.PREFIX + "definition")
                                                   
    val iProperty = ontologyManager.getM().getProperty(MagicStrings.PREFIX + "definition")
                                                  //> iProperty  : com.hp.hpl.jena.rdf.model.Property = http://comp#definition
                                                      
    val definitions = competences.map(x => x.getProperty(iProperty))
                                                  //> definitions  : scala.collection.mutable.Buffer[com.hp.hpl.jena.rdf.model.St
                                                  //| atement] = ArrayBuffer(null)
    val result = definitions.filterNot(_ == null).map(x => new CompetenceTree(x.toString(), "Kompetenz", "", null));
                                                  //> result  : scala.collection.mutable.Buffer[uzuzjmd.competence.service.rest.d
                                                  //| to.CompetenceTree] = ArrayBuffer()
    // todo implement
    ontologyManager.close()
     
  
}