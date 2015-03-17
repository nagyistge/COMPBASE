package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.ontology.CompObjectProperties

case class SelectedLearningProjectTemplate(comp: CompOntologyManager, val name: String, associatedCourses: List[CourseContext], associatedUser: User) extends CompetenceOntologyDao(comp, CompOntClass.SelectedLearningProjectTemplate, name) {
  @Override
  def getFullDao(): SelectedLearningProjectTemplate = {
    return new SelectedLearningProjectTemplate(comp, name, getAssociatedCourses, getAssociatedUser)
  }

  @Override
  def persistMore() {
    if (associatedCourses != null && !associatedCourses.isEmpty) {
      // könnte man jetzt auch die Kompetenzen persistieren
      associatedCourses.foreach(x => createEdgeWith(x, CompObjectProperties.CourseContextOfSelectedLearningProjectTemplate))
    }

    if (associatedUser != null) {
      createEdgeWith(associatedUser, CompObjectProperties.UserOfSelectedLearningProjectTemplate)
    }
  }

  @Override
  def deleteMore() {

  }

  def getAssociatedCourses(): List[CourseContext] = {
    return getAssociatedStandardDaosAsDomain(CompObjectProperties.CourseContextOfSelectedLearningProjectTemplate, classOf[CourseContext])
  }

  def getAssociatedUser(): User = {
    return getAssociatedStandardDaosAsDomain(CompObjectProperties.UserOfSelectedLearningProjectTemplate, classOf[User]).head
  }

}