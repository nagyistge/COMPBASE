package uzuzjmd.competence.mapper.importer

import uzuzjmd.competence.datasource.epos.mapper.EposXML2FilteredCSVCompetence
import uzuzjmd.competence.datasource.epos.mapper.EposXMLToSuggestedLearningPath
import uzuzjmd.competence.mapper.rcd.RCD2OWL
import uzuzjmd.competence.persistence.abstractlayer.TDBWriteTransactional
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl
import uzuzjmd.competence.shared.DESCRIPTORSETType
import uzuzjmd.competence.rcd.generated.Rdceo

/**
 * @author dehne
 */

class EposImporter extends TDBWriteTransactional[java.util.List[DESCRIPTORSETType]] {
  def importEposCompetences(eposList: java.util.List[DESCRIPTORSETType]) {
    // write competences in database as usual
    val result = EposXML2FilteredCSVCompetence.mapEposXML(eposList);
    val manager = new CompOntologyManagerJenaImpl();

    executeX[Seq[Rdceo]](RCD2OWL.convert _, EposXML2FilteredCSVCompetence.EPOSXML2RCD(result))
    val listOfExecuteAbles = Array(EposXMLToSuggestedLearningPath.convertLevelsToOWLRelations _, EposXMLToSuggestedLearningPath.convertLevelsAndLearningGoalToTemplate _)
    executeAllinOneTransaction(listOfExecuteAbles, eposList)
  }
}