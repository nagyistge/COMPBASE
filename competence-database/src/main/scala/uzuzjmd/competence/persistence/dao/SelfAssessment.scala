package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.persistence.ontology.{CompObjectProperties, CompOntClass}

case class SelfAssessment(comp: CompOntologyManager, val identifiery: String, val competence: Competence = null, val user: User = null, val assmentIndex: java.lang.Integer = null, val learningGoal: java.lang.Boolean = null) extends CompetenceOntologyDao(comp, CompOntClass.SelfAssessment, identifiery) {
  def ASSESSMENTINDEX = "assessmentIndex" // 1-4 in liferay implementation
  def LEARNINGGOAL = "learningGoal" // boolean

  //comp: CompOntologyManager, competence: Competence = null, user: User = null, val assmentIndex: java.lang.Integer = null, val learningGoal: java.lang.Boolean = null, val identifiery: String = null

  //  def this(comp: CompOntologyManager, competence: Competence = null, user: User = null, assmentIndex: java.lang.Integer = null, learningGoal: java.lang.Boolean = null) = this(comp, CompOntologyAccessScala.createIdentifierForAssessment(user, competence), competence, user, assmentIndex, learningGoal)

  @Override
  def getFullDao(): SelfAssessment = {
    //    val competenceLocal = getAssociatedSingletonDaosAsRange(CompObjectProperties.AssessmentOfCompetence, classOf[Competence]).head
    //    val userLocal = getAssociatedStandardDaosAsRange(CompObjectProperties.AssessmentOfUser, classOf[User]).head
    //    return new SelfAssessment(comp, identifier, null, null, getDataFieldInt(ASSESSMENTINDEX), getDataFieldBoolean(LEARNINGGOAL))
    return this
  }

  @Override
  def persistMore() {
    if (learningGoal != null) {
      addDataField(LEARNINGGOAL, learningGoal)
    } else {
      addDataField(LEARNINGGOAL, new java.lang.Boolean(false))
    }

    if (assmentIndex != null) {
      addDataField(ASSESSMENTINDEX, assmentIndex)
    } else {
      addDataField(ASSESSMENTINDEX, new java.lang.Integer(0))
    }
    if (competence != null) {
      createEdgeWith(CompObjectProperties.AssessmentOfCompetence, competence)
    } else {
      logger.warn("persisting SelfAssessment without corresponding competence with identifier " + identifierTop)
    }

    if (user != null) {
      createEdgeWith(CompObjectProperties.AssessmentOfUser, user)
    } else {
      logger.warn("persisting SelfAssessment without corresponding user with identifier " + identifierTop)
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

  //  def getAssmentIndexInProgress(): Integer = {
  //    if (getDataField(ASSESSMENTINDEX) == null) {
  //      return 0
  //    }
  //    return new Integer(getDataFieldInt(ASSESSMENTINDEX) * 25)
  //  }

  def getAssmentIndex(): Integer = {
    if (!exists()) {
      return 0
    }
    if (getDataField(ASSESSMENTINDEX) == null) {
      return 0
    }
    return new Integer(getDataFieldInt(ASSESSMENTINDEX))
  }

  def getLearningGoal(): Boolean = {
    if (!exists()) {
      return false
    }
    if (getDataField(LEARNINGGOAL) == null) {
      return false;
    }
    return getDataFieldBoolean(LEARNINGGOAL)
  }

}