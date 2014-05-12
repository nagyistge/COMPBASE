package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import com.hp.hpl.jena.ontology.Individual
import uzuzjmd.competence.owl.ontology.CompOntClass

class AbstractEvidenceLink(val creator: User,
  val linkedUsers: User,
  val courseContexts: CourseContext,
  val comments: List[Comment],
  val evidenceActivity: EvidenceActivity,
  val dateCreated: Long,
  val isValidated: Boolean,
  val comp: CompOntologyManager,
  val compOntClass: CompOntClass) extends CompetenceOntologyDao(comp, CompOntClass.AbstractEvidenceLink, creator.name + evidenceActivity.printableName) {

  @Override
  protected def deleteMore() {
    //TODO
  }

  @Override
  protected def persistMore() {

  }

}