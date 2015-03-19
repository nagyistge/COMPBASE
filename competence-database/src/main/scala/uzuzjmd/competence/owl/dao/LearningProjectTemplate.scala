package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompObjectProperties

class LearningProjectTemplate(comp: CompOntologyManager, val name: String, associatedComptences: Seq[Competence] = null, readableName: String = null) extends CompetenceOntologyDao(comp, CompOntClass.LearningProjectTemplate, name) {

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