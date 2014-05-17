package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyAccess
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.access.CompOntologyAccessScala

case class CourseContext(comp: CompOntologyManager, var name: String) extends CompetenceOntologyDao(comp, CompOntClass.CourseContext, CompOntologyAccessScala.convertMoodleIdToName(name)) {
  name = CompOntologyAccessScala.convertMoodleIdToName(name)

  @Override
  protected def deleteMore() {
    //TODO
  }

  @Override
  protected def persistMore() {

  }

  def getFullDao(): CompetenceOntologyDao = {
    return this
  }

  def getLinkedCompetences(): List[Competence] = {
    return getAssociatedSingletonDaosAsRange(CompObjectProperties.CourseContextOf, classOf[Competence])
  }

  def getLinkedUser(): List[User] = {
    return getAssociatedStandardDaosAsDomain(CompObjectProperties.belongsToCourseContext, classOf[User])
  }

  def getMoodleId(): java.lang.Integer = {
    return Integer.parseInt(name.replace("n", ""))
  }

}