package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.persistence.ontology.{CompObjectProperties, CompOntClass}

case class CourseContext(comp: CompOntologyManager, val name: String) extends CompetenceOntologyDao(comp, CompOntClass.CourseContext, name) {

  @Override
  protected def deleteMore() {
    //TODO
  }

  @Override
  protected def persistMore() {

  }

  def getFullDao(): CompetenceOntologyDao = {
    this
  }

  def getLinkedCompetences: List[Competence] = {
    if (name.equals("0") || name.equals("n0")) return List.empty
    getAssociatedSingletonDaosAsRange(CompObjectProperties.CourseContextOf, classOf[Competence])
  }

  def getLinkedUser(): List[User] = {
    if (name.equals("0") || name.equals("n0")) {
      return List.empty
    }
    return getAssociatedStandardDaosAsDomain(CompObjectProperties.belongsToCourseContext, classOf[User])
  }

  def getMoodleId(): java.lang.Integer = {
    return Integer.parseInt(name.replace("n", ""))
  }

  override def equals(courseContext: Any): Boolean = {
    return courseContext.asInstanceOf[CourseContext].getId.equals(getId)
  }

}