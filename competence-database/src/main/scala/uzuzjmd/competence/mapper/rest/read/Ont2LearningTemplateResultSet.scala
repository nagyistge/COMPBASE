package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.mapper.gui.Ont2SuggestedCompetenceGraph
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.TDBREADTransactional
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.shared.dto.GraphNode
import uzuzjmd.competence.shared.dto.LearningTemplateResultSet

/**
 * @author dehne
 */
object Ont2LearningTemplateResultSet extends TDBREADTransactional[String, LearningTemplateResultSet] {
  def convert(changes: String): LearningTemplateResultSet = {
    return execute(convertHelper _, changes)
  }

  private def convertHelper(comp: CompOntologyManager, changes: String): LearningTemplateResultSet = {
    val learningProjectTemplate = new LearningProjectTemplate(comp, changes, null, null);
    val associatedCompetences = learningProjectTemplate.getAssociatedCompetencesAsJava();

    if (associatedCompetences.isEmpty()) {
      return null
    } else if (associatedCompetences.size() == 1) {
      return new LearningTemplateResultSet(new GraphNode(associatedCompetences.get(0).getDefinition()));
    } else {
      return Ont2SuggestedCompetenceGraph.getLearningTemplateResultSet(comp, learningProjectTemplate);
    }
  }
}