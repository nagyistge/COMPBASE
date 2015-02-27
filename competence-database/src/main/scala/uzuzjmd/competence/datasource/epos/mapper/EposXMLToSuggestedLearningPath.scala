package uzuzjmd.competence.datasource.epos.mapper

import uzuzjmd.competence.datasource.epos.DESCRIPTORSETType
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.datasource.epos.DESCRIPTORType
import scala.collection.JavaConverters._
import uzuzjmd.competence.datasource.epos.filter.LevelFilter
import uzuzjmd.competence.owl.dao.Competence

object EposXMLToSuggestedLearningPath {
  def convertLevelsToOWLRelations(comp: CompOntologyManager, descriptorSetType: java.util.List[DESCRIPTORSETType]) {
    comp.begin()
    descriptorSetType.asScala.foreach(x => convertLevelsToOWLRelations2(comp, x.getDESCRIPTOR().asScala.toList))
    comp.close()
  }

  def convertLevelsToOWLRelations2(comp: CompOntologyManager, descriptorSetType: List[DESCRIPTORType]) {
    val result = descriptorSetType.map(x => (x.getCOMPETENCE(), x.getLEVEL())).distinct.combinations(2)
    result.foreach(x => convertLevelToOWLRelations3(comp, (x.head._1, x.head._2), (x.tail.head._1, x.tail.head._2)))
  }

  def convertLevelToOWLRelations3(comp: CompOntologyManager, domain: (String, String), range: (String, String)) {

    if (LevelFilter.filterSuggestedLevels(domain._2, range._2) && domain._1.equals(range._1)) {
      //println("create link " + domain + " " + range);
      val domainId = EposXML2FilteredCSVCompetence.descriptorSetType2Id(domain);
      val rangeID = EposXML2FilteredCSVCompetence.descriptorSetType2Id(range);
      val domainCompetence = new Competence(comp, domainId, domainId, false)
      val rangeCompetence = new Competence(comp, rangeID, rangeID, false)
      rangeCompetence.addSuggestedCompetenceRequirement(domainCompetence)
    }
  }
}