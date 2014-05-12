package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.OntResult
import com.hp.hpl.jena.ontology.Individual
import com.hp.hpl.jena.ontology.OntClass

class CompetenceOntologySingletonDao(comp: CompOntologyManager, compOntClass: CompOntClass, superClass: CompetenceOntologySingletonDao) extends Dao(comp) {
  val util = comp.getUtil()

  def persist(): OntResult = {
    val result = util.accessSingletonResourceWithClass(compOntClass)
    if (superClass != null) {
      val ontSuperClass = superClass.persist.getOntclass()
      result.getOntclass().addSuperClass(ontSuperClass)
    }
    return result
  }

  def createIndividual: Individual = {
    return persist.getIndividual()
  }
}