package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.mapper.gui.Ont2SuggestedCompetenceGraph
import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, TDBReadTransactional}
import uzuzjmd.competence.persistence.dao.LearningProjectTemplate
import uzuzjmd.competence.shared.dto.{GraphNode, LearningTemplateResultSet}

/**
 * @author dehne
 */
object Ont2LearningTemplateResultSet extends TDBReadTransactional[String, LearningTemplateResultSet] {
  def convert(changes: String): LearningTemplateResultSet = {
    return execute(convertHelper _, changes)
  }

  private def convertHelper(comp: CompOntologyManager, changes: String): LearningTemplateResultSet = {
    val learningProjectTemplate = new LearningProjectTemplate(comp, changes, null, null);
    val associatedCompetences = learningProjectTemplate.getAssociatedCompetencesAsJava();

    if (associatedCompetences.isEmpty()) {
      val result = new LearningTemplateResultSet
      result.setNameOfTheLearningTemplate(changes);
      return result
    } else if (associatedCompetences.size() == 1) {
      val result = new LearningTemplateResultSet(new GraphNode(associatedCompetences.get(0).getDefinition()));
      result.setNameOfTheLearningTemplate(changes);
      return result
    } else {
      return Ont2SuggestedCompetenceGraph.getLearningTemplateResultSet(comp, learningProjectTemplate);
    }
  }
}