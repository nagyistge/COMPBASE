package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.access.CompOntologyAccessScala
import scala.collection.JavaConverters._

case class LearningProjectTemplate(comp: CompOntologyManager, var name: String, associatedComptences: Seq[Competence] = null, readableName: String = null) extends CompetenceOntologyDao(comp, CompOntClass.LearningProjectTemplate, name) {

  def DEFINITION = "definition"

  @Override
  def getFullDao(): LearningProjectTemplate = {
    return new LearningProjectTemplate(comp, name, getAssociatedCompetences, getDataField(DEFINITION))
  }

  @Override
  def persistMore() {
    if (associatedComptences != null && !associatedComptences.isEmpty) {
      // könnte man jetzt auch die Kompetenzen persistieren
      associatedComptences.foreach(x => addCompetenceToProject(x))
    }

    if (readableName != null) {
      addDataField(DEFINITION, readableName);
    } else {
      addDataField(DEFINITION, name);
    }
  }

  @Override
  def deleteMore() {

  }

  def getAssociatedCompetences(): Seq[Competence] = {
    return getAssociatedSingletonDaosAsRange(CompObjectProperties.LearningProjectTemplateOf, classOf[Competence])
  }

  def getAssociatedCompetencesAsJava(): java.util.List[Competence] = {
    return getAssociatedCompetences.asJava
  }

  def addCompetenceToProject(competence: Competence) {
    createEdgeWith(CompObjectProperties.LearningProjectTemplateOf, competence)
  }

}