package uzuzjmd.competence.datasource.epos.mapper

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, WriteTransactional}
import uzuzjmd.competence.mapper.rcd.RCD2OWL
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl
import uzuzjmd.competence.shared.DESCRIPTORSETType

/**
 * @author dehne
  *         utility class to bundles different steps of persisting competences given in the epos format
 */
object EposXML2Ont extends WriteTransactional[java.util.List[DESCRIPTORSETType]] {

  def convert(changes: java.util.List[DESCRIPTORSETType]) {
    execute(convertHelper _, changes)
  }

  def convertHelper(comp: CompOntologyManager, changes: java.util.List[DESCRIPTORSETType]) {
    val result = EposXML2FilteredCSVCompetence.mapEposXML(changes)
    RCD2OWL.convert(comp,EposXML2FilteredCSVCompetence.EPOSXML2RCD(result))
    EposXMLToSuggestedLearningPath.convertLevelsToOWLRelations(comp, changes)
    EposXMLToSuggestedLearningPath.convertLevelsAndLearningGoalToTemplate(comp, changes)
  }
}