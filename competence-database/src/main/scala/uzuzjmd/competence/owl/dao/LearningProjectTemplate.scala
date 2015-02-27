package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompObjectProperties

class LearningProjectTemplate(comp: CompOntologyManager, val name: String, associatedComptences: Seq[Competence] = null) extends CompetenceOntologyDao(comp, CompOntClass.LearningProjectTemplate, name) {

  @Override
  def getFullDao(): LearningProjectTemplate = {
    return new LearningProjectTemplate(comp, name, getAssociatedCompetences)
  }

  @Override
  def persistMore() {
    if (associatedComptences != null && !associatedComptences.isEmpty) {
      // könnte man jetzt auch die Kompetenzen persistieren
    }
  }

  @Override
  def deleteMore() {

  }

  def getAssociatedCompetences(): Seq[Competence] = {
    return getAssociatedSingletonDaosAsRange(CompObjectProperties.LearningProjectTemplateOf, classOf[Competence])
  }

  def addCompetenceToProject(competence: Competence) {
    createEdgeWith(CompObjectProperties.LearningProjectTemplateOf, competence)
  }

}