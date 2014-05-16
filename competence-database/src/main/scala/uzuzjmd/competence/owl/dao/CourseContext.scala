package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyAccess
import uzuzjmd.competence.owl.ontology.CompObjectProperties

case class CourseContext(comp: CompOntologyManager, val name: String) extends CompetenceOntologyDao(comp, CompOntClass.CourseContext, name) {
  @Override
  protected def deleteMore() {
    //TODO
  }

  @Override
  protected def persistMore() {
    //TODO  
  }

  def getFullDao(): CompetenceOntologyDao = {
    return this;
  }

  def getLinkedCompetences(): List[Competence] = {
    return getAssociatedStandardDaosAsRange(CompObjectProperties.CourseContextOf, classOf[Competence])
  }
}