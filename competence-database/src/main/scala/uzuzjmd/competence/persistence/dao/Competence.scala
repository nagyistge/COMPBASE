package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.config.Logging
import uzuzjmd.competence.persistence.abstractlayer.{OntResult, CompOntologyManager, CompOntologyAccess}
import uzuzjmd.competence.persistence.ontology.CompObjectProperties
import uzuzjmd.competence.persistence.ontology.CompOntClass
import com.hp.hpl.jena.rdf.model.Property
import com.hp.hpl.jena.rdf.model.Statement
import com.hp.hpl.jena.ontology.OntClass
import uzuzjmd.competence.persistence.owl.{CompOntologyAccessScala, CompOntologyManagerJenaImpl}
import scala.collection.JavaConverters._
import uzuzjmd.competence.exceptions.{IndividualNotFoundException, NoRecursiveSubClassException, DataFieldNotInitializedException}

class Competence(compManager: CompOntologyManager,
                 identifierLocal: String,
                 val definition: String = null,
                 val compulsory: java.lang.Boolean = null) extends CompetenceOntologySingletonDao(compManager, CompOntClass.Competence, identifierLocal) with Logging {


  def COMPULSORY = "compulsory"

  def getCompulsory() = getDataField(COMPULSORY)

  @Override
  protected def persistMore() {
    val competenceRoot = new CompetenceInstance(comp)
    val ontClass = getOntClass;
    var rootOntClass = competenceRoot.getOntClass;
    if (rootOntClass == null) {
      rootOntClass = competenceRoot.persist.getOntclass
    }
    ontClass.addSuperClass(rootOntClass)
    if (definition != null) {
      addDataField(DEFINITION, definition)
    } else {
      addDataField(DEFINITION, identifierLocal)
    }
    if (compulsory == null) {
      addDataField(COMPULSORY, new java.lang.Boolean(false))
    } else {
      addDataField(COMPULSORY, compulsory)
    }

    addCourseContext(new CourseContext(compManager, "university"))
  }




  @Override
  def getFullDao(): Competence = {
   /* if (this.identifierTop.equals("ComptenceIndividual")) {
      return this;
    }*/

    val individual = getIndividual
    if (individual == null) {
      logger.error("Individual not found for id: "+ identifierLocal)
      throw new IndividualNotFoundException();
    }
    val definition2 = getDefinition
    try {
      getDataFieldBoolean(COMPULSORY)
    } catch {
      case e:DataFieldNotInitializedException => new Competence(compManager, identifier, definition2, false);
    }
    return new Competence(compManager, identifier, definition2, getDataFieldBoolean(COMPULSORY))
  }

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

  def isAllowed(user: User): Boolean = {
    val prerequisites = getAssociatedSingletonDaosAsDomain(CompObjectProperties.PrerequisiteOf, classOf[Competence])
    if (prerequisites.isEmpty) {
      return true
    } else {
      return prerequisites.forall(x => x.hasEdge(user, CompObjectProperties.UserHasPerformed) && x.isAllowed(user))
    }
  }

  def addSuperCompetence(superCompetence: Competence): Competence = {
    persistManualCascades(false).getOntclass().addSuperClass(superCompetence.persistManualCascades(true).getOntclass())
    persistManualCascades(false)
    return this
  }

  def removeSuperCompetence(superCompetence: Competence): Competence = {
    persistManualCascades(false).getOntclass().removeSuperClass(superCompetence.persistManualCascades(true).getOntclass())
    persistManualCascades(false)
    return this
  }

  def toStrinz(): String = {
    return getDefinition()
  }

  def getAssessment(user: User): SelfAssessment = {
    val result1 = getAssociatedStandardDaosAsDomain(CompObjectProperties.AssessmentOfCompetence, classOf[SelfAssessment])
    val result = result1.filter(x => x.hasEdge(CompObjectProperties.AssessmentOfUser, user))
    if (result.isEmpty) {
      return new SelfAssessment(compManager, CompOntologyAccessScala.createIdentifierForAssessment(user, this), this, user, 0, false)
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
    dao.persistManualCascades(true)
    dao.createEdgeWith(CompObjectProperties.CatchwordOf, this)
  }

  def addLearningTemplate(learningTemplate: LearningProjectTemplate) {
    createEdgeWith(learningTemplate, CompObjectProperties.LearningProjectTemplateOf)
  }

  def getOperators(): List[Operator] = {
    return getAssociatedSingletonDaosAsDomain(CompObjectProperties.OperatorOf, classOf[Operator]);
  }

  def addCourseContext(course: CourseContext) {
    createEdgeWith(course, CompObjectProperties.CourseContextOf)
    addSuperCompetencesToCourse(this, course)
  }

  private def addSuperCompetencesToCourse(competence: Competence, course: CourseContext) {
    if (competence.hasSuperClass) {
      val superCompetences = competence.listSuperClasses(classOf[Competence]).filterNot { x => x.identifier.equals("Competence") }.filterNot { x => x.identifier.equals("Resource") }
      superCompetences.foreach { x => x.createEdgeWith(course, CompObjectProperties.CourseContextOf) }
      superCompetences.foreach { x => addSuperCompetencesToCourse(x, course) }
    }
  }

  override def canEqual(other: Any): Boolean = other.isInstanceOf[Competence]

  override def hashCode(): Int = {
    val state = Seq(getDefinition(), getCompulsory())
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}