package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.ontology.CompOntClass
import com.hp.hpl.jena.rdf.model.Property
import uzuzjmd.competence.owl.access.CompOntologyAccess
import com.hp.hpl.jena.rdf.model.Statement
import com.hp.hpl.jena.ontology.OntClass
import scala.collection.JavaConverters._

class Competence(compManager: CompOntologyManager, identifierlocal: String, val definition: String = null, val compulsory: java.lang.Boolean = null) extends CompetenceOntologySingletonDao(compManager, CompOntClass.Competence, identifierlocal) {

  def COMPULSORY = "compulsory"

  @Override
  protected def persistMore() {
    val competenceRoot = new CompetenceInstance(comp)
    val ontClass = persist(false).getOntclass()
    ontClass.addSuperClass(competenceRoot.persist(false).getOntclass())
    if (definition != null) {
      addDataField(DEFINITION, definition)
      //      compManager.getUtil().createOntClassForString(definition, definition)
    } else {
      addDataField(DEFINITION, identifierlocal)
    }
  }

  @Override
  def getFullDao(): Competence = {
    return new Competence(compManager, identifier, getDataField(DEFINITION), getDataFieldBoolean(COMPULSORY))
  }

  //  def getIdentifier(): String = {
  //    return identifier
  //  }

  def addSuggestedCompetenceRequirement(competence: Competence) {
    createEdgeWith(competence, CompObjectProperties.SuggestedCompetencePrerequisiteOf)
  }

  def getSuggestedCompetenceRequirements(): List[Competence] = {
    return getAssociatedSingletonDaosAsDomain(CompObjectProperties.SuggestedCompetencePrerequisiteOf, classOf[Competence])
  }

  def addRequiredCompetence(competence: Competence) {
    deleteEdgeWith(competence, CompObjectProperties.NotPrerequisiteOf)
    createEdgeWith(competence, CompObjectProperties.PrerequisiteOf)
  }

  def addNotRequiredCompetence(competence: Competence) {
    deleteEdgeWith(competence, CompObjectProperties.PrerequisiteOf)
    createEdgeWith(competence, CompObjectProperties.NotPrerequisiteOf)
  }

  def getCatchwords(): List[Catchword] = {
    val result = getAssociatedSingletonDaosAsDomain(CompObjectProperties.CatchwordOf, classOf[Catchword])
    //val result = getAssociatedSingletonDaosAsRange(CompObjectProperties.CatchwordOfInverse, classOf[Catchword])
    return result
  }

  def getRequiredCompetences(): List[Competence] = {
    return getAssociatedSingletonDaosAsDomain(CompObjectProperties.PrerequisiteOf, classOf[Competence])
  }

  def getRequiredCompetencesAsArray(): Array[String] = {
    return getRequiredCompetences().map(x => x.getDataField(x.DEFINITION)).toArray;
  }

  def isLinkedAsRequired(): Boolean = {
    return !(getRequiredCompetences.isEmpty && getAssociatedSingletonDaosAsRange(CompObjectProperties.PrerequisiteOf, classOf[Competence]).isEmpty);
  }

  def isAllowed(): Boolean = {
    return getAssociatedSingletonDaosAsDomain(CompObjectProperties.PrerequisiteOf, classOf[Competence]).isEmpty
  }

  def addSuperCompetence(superCompetence: Competence): Competence = {
    persist(false).getOntclass().addSuperClass(superCompetence.persist(true).getOntclass())
    persist(false)
    return this
  }

  def removeSuperCompetence(superCompetence: Competence): Competence = {
    persist(false).getOntclass().removeSuperClass(superCompetence.persist(true).getOntclass())
    persist(false)
    return this
  }

  def toStrinz(): String = {
    return getDataField(DEFINITION)
  }

  def getAssessment(user: User): SelfAssessment = {
    val result = getAssociatedStandardDaosAsDomain(CompObjectProperties.AssessmentOfCompetence, classOf[SelfAssessment]).filter(x => x.hasEdge(CompObjectProperties.AssessmentOfUser, user))
    if (result.isEmpty) {
      return new SelfAssessment(compManager, this, user, 0)
    }
    return result.head
  }

  def getShortestPathToSubCompetence(subCompetence: Competence): java.util.List[Competence] = {
    return compManager.getUtil().getShortestSubClassPath(subCompetence.toOntClass, toOntClass).asScala.map(x => new Competence(compManager, x, null, null)).asJava
  }

  def getCatchwordsAsJava(): java.util.List[Catchword] = {
    return getCatchwords.asJava
  }

  def addCatchword(dao: Catchword) {
    dao.persist(true)
    dao.createEdgeWith(CompObjectProperties.CatchwordOf, this)
  }

  def addLearningTemplate(learningTemplate: LearningProjectTemplate) {
    createEdgeWith(learningTemplate, CompObjectProperties.LearningProjectTemplateOf)
  }

  def getOperators(): List[Operator] = {
    return getAssociatedSingletonDaosAsDomain(CompObjectProperties.OperatorOf, classOf[Operator]);
  }

}