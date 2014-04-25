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


object Ont2CompetenceTree {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  //val ontologyManager = Singletons.ontologyManager
 
  val helper = new Helper()                       //> helper  : uzuzjmd.competence.owl.ontology.Helper = uzuzjmd.competence.owl.on
                                                  //| tology.Helper@26d0fffc
  val ontologyManager = helper.getOntologyManagerInit()
                                                  //> org.apache.jena.atlas.lib.InternalErrorException: Invalid id node for subjec
                                                  //| t (null node): ([0000000000000082], [0000000000000024], [00000000000000A4])
                                                  //| 
                                                  //| 	at com.hp.hpl.jena.tdb.lib.TupleLib.triple(TupleLib.java:128)
                                                  //| 	at com.hp.hpl.jena.tdb.lib.TupleLib.triple(TupleLib.java:114)
                                                  //| 	at com.hp.hpl.jena.tdb.lib.TupleLib.access$000(TupleLib.java:45)
                                                  //| 	at com.hp.hpl.jena.tdb.lib.TupleLib$3.convert(TupleLib.java:76)
                                                  //| 	at com.hp.hpl.jena.tdb.lib.TupleLib$3.convert(TupleLib.java:72)
                                                  //| 	at org.apache.jena.atlas.iterator.Iter$4.next(Iter.java:322)
                                                  //| 	at org.apache.jena.atlas.iterator.Iter$4.next(Iter.java:322)
                                                  //| 	at org.apache.jena.atlas.iterator.Iter$4.next(Iter.java:322)
                                                  //| 	at org.apache.jena.atlas.iterator.Iter.next(Iter.java:920)
                                                  //| 	at com.hp.hpl.jena.util.iterator.WrappedIterator.next(WrappedIterator.ja
                                                  //| va:94)
                                                  //| 	at com.hp.hpl.jena.util.iterator.WrappedIterator.next(WrappedIterator.ja
                                                  //| va:94)
                                                  //| 	at com.hp.hpl.jena.mem.TrackingTripleIterator.next(TrackingTripleIterato
                                                  //| r.java:47)
                                                  //| 	at com.hp.hpl.jena.mem.TrackingTripleIterator.next(TrackingTripleIterato
                                                  //| r.java:31)
                                                  //| 	at com.hp.hpl.jena.util.iterator.NiceIterator$1.next(NiceIterator.java:1
                                                  //| 19)
                                                  //| 	at com.hp.hpl.jena.reasoner.rulesys.impl.RETEEngine.fastInit(RETEEngine.
                                                  //| java:151)
                                                  //| 	at com.hp.hpl.jena.reasoner.rulesys.FBRuleInfGraph.prepare(FBRuleInfGrap
                                                  //| h.java:471)
                                                  //| 	at com.hp.hpl.jena.reasoner.BaseInfGraph.requirePrepared(BaseInfGraph.ja
                                                  //| va:542)
                                                  //| 	at com.hp.hpl.jena.reasoner.rulesys.FBRuleInfGraph.validate(FBRuleInfGra
                                                  //| ph.java:734)
                                                  //| 	at com.hp.hpl.jena.ontology.impl.OntModelImpl.validate(OntModelImpl.java
                                                  //| :2763)
                                                  //| 	at uzuzjmd.competence.owl.reasoning.SimpleRulesReasoner.reason(SimpleRul
                                                  //| esReasoner.java:45)
                                                  //| 	at uzuzjmd.competence.owl.reasoning.SimpleRulesReasoner.setupRulesReason
                                                  //| er(SimpleRulesReasoner.java:62)
                                                  //| 	at uzuzjmd.competence.owl.reasoning.SimpleRulesReasoner.<init>(SimpleRul
                                                  //| esReasoner.java:40)
                                                  //| 	at uzuzjmd.competence.owl.access.CompOntologyManager.initReasoner(CompOn
                                                  //| tologyManager.java:92)
                                                  //| 	at uzuzjmd.competence.owl.access.CompOntologyManager.<init>(CompOntology
                                                  //| Manager.java:52)
                                                  //| 	at uzuzjmd.competence.owl.ontology.Helper.getOntologyManagerInit(Helper.
                                                  //| java:7)
                                                  //| 	at Ont2CompetenceTree$$anonfun$main$1.apply$mcV$sp(Ont2CompetenceTree.sc
                                                  //| ala:18)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at Ont2CompetenceTree$.main(Ont2CompetenceTree.scala:13)
                                                  //| 	at Ont2CompetenceTree.main(Ont2CompetenceTree.scala)
  
  val compFileUtil = new CompFileUtil(ontologyManager.getM())
  compFileUtil.writeOntologyout()
  
  
   val competenceClass = ontologyManager.getUtil().getClass(CompOntClass.Competence);
    val competences = competenceClass.listSubClasses().toList().asScala
		competences.head
    competences.size
    val singleTons = competences.map(x => ontologyManager.getUtil().createSingleTonIndividual(x))

    //val iProperty = ontologyManager.getM.getOntProperty(MagicStrings.PREFIX + "definition")
                                                   
    val iProperty = ontologyManager.getM().getProperty(MagicStrings.PREFIX + "definition")
                                                      
    val definitions = competences.map(x => x.getProperty(iProperty))
    val result = definitions.filterNot(_ == null).map(x => new CompetenceTree(x.toString(), "Kompetenz", "", null));
    // todo implement
    ontologyManager.close()
  
}