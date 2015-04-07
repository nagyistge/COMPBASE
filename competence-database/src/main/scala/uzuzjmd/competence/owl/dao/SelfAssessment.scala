package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyAccessScala
import uzuzjmd.competence.owl.ontology.CompObjectProperties

case class SelfAssessment(comp: CompOntologyManager, competence: Competence, user: User, val assmentIndex: java.lang.Integer = null) extends CompetenceOntologyDao(comp, CompOntClass.SelfAssessment, CompOntologyAccessScala.createIdentifierForAssessment(user, competence)) {
  def ASSESSMENTINDEX = "assessmentIndex" // 1-4 in liferay implementation
  def LEARNINGGOAL = "learningGoal" // boolean

  @Override
  def getFullDao(): SelfAssessment = {
    return new SelfAssessment(comp, competence, user, getDataFieldInt(ASSESSMENTINDEX))
  }

  @Override
  def persistMore() {
    if (assmentIndex != null) {
      addDataField(ASSESSMENTINDEX, assmentIndex)
    }
  }

  @Override
  def deleteMore() {

  }

  def updateIndex(assmentIndex: Integer) {
    addDataField(ASSESSMENTINDEX, assmentIndex)
  }

  def addCompetenceToAssessment(competence: Competence) {
    createEdgeWith(CompObjectProperties.AssessmentOfCompetence, competence)
  }

  def addUserToAssessment(user: User) {
    createEdgeWith(CompObjectProperties.AssessmentOfUser, user)
  }

  def getAssmentIndexInProgress(): Integer = {
    if (getDataField(ASSESSMENTINDEX) == null) {
      return 0
    }
    return new Integer(getDataFieldInt(ASSESSMENTINDEX) * 25)
  }

  def getAssmentIndex(): Integer = {
    if (getDataField(ASSESSMENTINDEX) == null) {
      return 0
    }
    return new Integer(getDataFieldInt(ASSESSMENTINDEX))
  }

  def getLearningGoal(): Boolean = {
    if (getDataField(LEARNINGGOAL) == null) {
      return false;
    }
    return getDataFieldBoolean(LEARNINGGOAL)
  }

}