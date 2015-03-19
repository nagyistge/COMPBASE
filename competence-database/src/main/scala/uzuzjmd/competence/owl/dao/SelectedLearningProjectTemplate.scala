package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.access.CompOntologyAccessScala
import uzuzjmd.competence.liferay.reflexion.StringList
import scala.collection.JavaConverters._

case class SelectedLearningProjectTemplate(comp: CompOntologyManager, associatedUser: User, associatedCourse: CourseContext, var identifiery: String = null, associatedTemplates: List[LearningProjectTemplate] = null) extends CompetenceOntologyDao(comp, CompOntClass.SelectedLearningProjectTemplate, CompOntologyAccessScala.createIdentifierForSelectedTemplate(associatedUser, associatedCourse, identifiery)) {

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
    return getAssociatedStandardDaosAsDomain(CompObjectProperties.CourseContextOfSelectedLearningProjectTemplate, classOf[CourseContext]).head
  }

  def getAssociatedUser(): User = {
    return getAssociatedStandardDaosAsDomain(CompObjectProperties.UserOfSelectedLearningProjectTemplate, classOf[User]).head
  }

  def getAssociatedTemplates(): List[LearningProjectTemplate] = {
    return getAssociatedStandardDaosAsRange(CompObjectProperties.SelectedTemplateOfLearningTemplate, classOf[LearningProjectTemplate])
  }

  def getAssociatedTemplatesAsStringList(): StringList = {
    val result = new StringList
    result.setData(getAssociatedTemplates.map(x => x.getDataField(x.DEFINITION)).asJava)
    return result
  }

  def addAssociatedTemplate(learningTemplate: LearningProjectTemplate) {
    createEdgeWith(CompObjectProperties.SelectedTemplateOfLearningTemplate, learningTemplate)
  }

}