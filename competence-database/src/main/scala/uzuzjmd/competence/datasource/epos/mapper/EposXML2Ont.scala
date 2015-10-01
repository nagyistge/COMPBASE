package uzuzjmd.competence.datasource.epos.mapper

import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.owl.access.CompOntologyManager

/**
 * @author dehne
 */
object EposXML2Ont extends TDBWriteTransactional[java.util.List[DESCRIPTORSETType]{

  def convert(changes : java.util.List[DESCRIPTORSETType])  {
    execute(convertHelper _, changes)
  }

   def convertHelper(comp : CompOntologyManager,changes : java.util.List[DESCRIPTORSETType]) {
    val result = EposXML2FilteredCSVCompetence.mapEposXML(changes);
    RCD2OWL.convert(EposXML2FilteredCSVCompetence.EPOSXML2RCD(result), comp);
    EposXMLToSuggestedLearningPath.convertLevelsToOWLRelations(comp, changes);
    EposXMLToSuggestedLearningPath.convertLevelsAndLearningGoalToTemplate(comp, changes);
   }
}