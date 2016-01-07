package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.persistence.ontology.{CompObjectProperties, CompOntClass}
import uzuzjmd.competence.persistence.owl.CompetenceQueriesJenaImpl

case class User(comp: CompOntologyManager, val name: String, val role: Role = null, val courseContext: CourseContext = null, val readableName: String = null) extends CompetenceOntologyDao(comp, CompOntClass.User, name) {

  def NAME = "name"

  @Override
  protected def deleteMore() {
    //    if (role != null) {
    //      role.deleteEdge(CompObjectProperties.RoleOf, this)
    //    }
  }

  @Override
  protected def persistMore() {
    createIndividual
    if (courseContext != null) {
      createEdgeWith(CompObjectProperties.belongsToCourseContext, courseContext)
    }
    if (role != null) {
      role.createEdgeWith(CompObjectProperties.RoleOf, this)
    }
    if (readableName != null) {
      addDataField(NAME, readableName)
    } else {
      addDataField(NAME, identifier)
    }
  }

  def getName(): String = {
    return getDataField(NAME)
  }

  def hasCourseContext(courseContext: CourseContext): Boolean = {
    hasEdge(CompObjectProperties.belongsToCourseContext, courseContext)
  }

  @Override
  def getFullDao(): User = {
    val teacherRole = new TeacherRole(comp)
    val queries = new CompetenceQueriesJenaImpl(comp.getM())
    val courseContext2 = getAssociatedStandardDaosAsRange(CompObjectProperties.belongsToCourseContext, classOf[CourseContext]).map(x => x.getFullDao.asInstanceOf[CourseContext]).head;
    val readableName = getDataField(NAME)
    if (hasEdge(teacherRole, CompObjectProperties.RoleOf)) {
      return new User(comp, name, teacherRole, courseContext2, readableName)
    } else {
      return new User(comp, name, new StudentRole(comp), courseContext2, readableName)
    }
  }

  def getAssociatedLinks(): List[AbstractEvidenceLink] = {
    return getAssociatedStandardDaosAsRange(CompObjectProperties.UserOfLink, classOf[AbstractEvidenceLink])
  }
}