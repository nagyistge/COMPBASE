package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.persistence.ontology.{CompObjectProperties, CompOntClass}
import uzuzjmd.competence.persistence.owl.CompOntologyAccessScala
import uzuzjmd.competence.shared.StringList

import scala.collection.JavaConverters._

/**
 * This class is the entry point for a template
 *
 * Given the name of a learning template the associated competences and their structure can be retrieved
 */
case class SelectedLearningProjectTemplate(comp: CompOntologyManager, associatedUser: User, associatedCourse: CourseContext, var identifiery: String = null, associatedTemplates: List[LearningProjectTemplate] = null) extends CompetenceOntologyDao(comp, CompOntClass.SelectedLearningProjectTemplate, CompOntologyAccessScala.createIdentifierForSelectedTemplate(associatedUser.identifier, associatedCourse.identifier, identifiery)) {

  @Override
  def getFullDao(): SelectedLearningProjectTemplate = {
    return new SelectedLearningProjectTemplate(comp, getAssociatedUser, getAssociatedCourse, identifier, getAssociatedTemplates)
  }

  @Override
  def persistMore() {
    if (associatedCourse != null) {
      // könnte man jetzt auch die Kompetenzen persistieren
      createEdgeWith(associatedCourse, CompObjectProperties.CourseContextOfSelectedLearningProjectTemplate)
    }

    if (associatedUser != null) {
      createEdgeWith(associatedUser, CompObjectProperties.UserOfSelectedLearningProjectTemplate)
    }

    if (associatedTemplates != null && !associatedTemplates.isEmpty) {
      associatedTemplates.foreach(x => x.persist)
      associatedTemplates.foreach(x => addAssociatedTemplate(x))
    }

  }

  @Override
  def deleteMore() {

  }

  def addCourse(course: CourseContext) {
    createEdgeWith(course, CompObjectProperties.CourseContextOfSelectedLearningProjectTemplate)
  }

  def addAssociatedUser(user: User) {
    createEdgeWith(user, CompObjectProperties.UserOfSelectedLearningProjectTemplate);
  }

  def getAssociatedCourse(): CourseContext = {
    val list = getAssociatedStandardDaosAsDomain(CompObjectProperties.CourseContextOfSelectedLearningProjectTemplate, classOf[CourseContext])
    if (list.isEmpty) {
      return null
    }
    return getAssociatedStandardDaosAsDomain(CompObjectProperties.CourseContextOfSelectedLearningProjectTemplate, classOf[CourseContext]).head
  }

  def getAssociatedUser(): User = {
    val list = getAssociatedStandardDaosAsDomain(CompObjectProperties.UserOfSelectedLearningProjectTemplate, classOf[User])
    if (list.isEmpty) {
      return null
    }
    return getAssociatedStandardDaosAsDomain(CompObjectProperties.UserOfSelectedLearningProjectTemplate, classOf[User]).head
  }

  def getAssociatedTemplates(): List[LearningProjectTemplate] = {
    return getAssociatedStandardDaosAsRange(CompObjectProperties.SelectedTemplateOfLearningTemplate, classOf[LearningProjectTemplate])
  }

  def getAssociatedTemplatesAsStringList(): StringList = {
    val result = new StringList
    if (getAssociatedTemplates().isEmpty) {
      return result
    }
    result.setData(getAssociatedTemplates.map(x => x.getDataField(x.DEFINITION)).asJava)
    return result
  }

  def removeAssociatedTemplate(template: LearningProjectTemplate) {
    deleteEdge(CompObjectProperties.SelectedTemplateOfLearningTemplate, template)
  }

  def addAssociatedTemplate(learningTemplate: LearningProjectTemplate) {
    createEdgeWith(CompObjectProperties.SelectedTemplateOfLearningTemplate, learningTemplate)
  }

}