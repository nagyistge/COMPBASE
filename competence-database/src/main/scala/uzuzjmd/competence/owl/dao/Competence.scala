package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.ontology.CompOntClass
import com.hp.hpl.jena.rdf.model.Property
import uzuzjmd.competence.owl.access.CompOntologyAccess
import com.hp.hpl.jena.rdf.model.Statement

class Competence(compManager: CompOntologyManager, identifier: String, val definition: String = null, val compulsory: java.lang.Boolean = null) extends CompetenceOntologySingletonDao(compManager, CompOntClass.Competence, identifier) {

  def DEFINITION = "definition"
  def COMPULSORY = "compulsory"

  @Override
  protected def persistMore() {
    val competenceRoot = new CompetenceInstance(comp)
    persist(false).getOntclass().addSuperClass(competenceRoot.persist(false).getOntclass())
  }

  @Override
  def getFullDao(): Competence = {
    return new Competence(compManager, identifier, getDataField(DEFINITION), getDataFieldBoolean(COMPULSORY))
  }

  def addRequiredCompetence(competence: Competence) {
    deleteEdgeWith(competence, CompObjectProperties.NotPrerequisiteOf)
    createEdgeWith(competence, CompObjectProperties.PrerequisiteOf)
  }

  def addNotRequiredCompetence(competence: Competence) {
    deleteEdgeWith(competence, CompObjectProperties.PrerequisiteOf)
    createEdgeWith(competence, CompObjectProperties.NotPrerequisiteOf)
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

}