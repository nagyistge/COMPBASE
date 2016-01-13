package uzuzjmd.competence.datasource.epos.mapper

import uzuzjmd.competence.persistence.abstractlayer.WriteTransactional
import uzuzjmd.competence.shared.DESCRIPTORSETType

/**
 * @author dehne
  *         utility class to bundles different steps of persisting competences given in the epos format
 */
object EposXML2Ont extends WriteTransactional[java.util.List[DESCRIPTORSETType]] {

  def convert(changes: java.util.List[DESCRIPTORSETType]) {
    execute(convertHelper _, changes)
  }

  def convertHelper(changes: java.util.List[DESCRIPTORSETType]) {
    EposXML2FilteredCSVCompetence.mapEposXML(changes)
    EposXMLToSuggestedLearningPath.convertLevelsToOWLRelations(changes)
    EposXMLToSuggestedLearningPath.convertLevelsAndLearningGoalToTemplate(changes)
  }
}