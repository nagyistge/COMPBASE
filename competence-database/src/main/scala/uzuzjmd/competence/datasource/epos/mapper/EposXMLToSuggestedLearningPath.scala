package uzuzjmd.competence.datasource.epos.mapper

import uzuzjmd.competence.owl.access.CompOntologyManager
import scala.collection.JavaConverters._
import uzuzjmd.competence.datasource.epos.filter.LevelFilter
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.owl.dao.Catchword
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.shared.DESCRIPTORType
import uzuzjmd.competence.shared.DESCRIPTORSETType

object EposXMLToSuggestedLearningPath {
  def convertLevelsToOWLRelations(comp: CompOntologyManager, descriptorSetType: java.util.List[DESCRIPTORSETType]) {
    descriptorSetType.asScala.foreach(x => convertLevelsToOWLRelations2(comp, x.getDESCRIPTOR().asScala.toList))
    descriptorSetType.asScala.foreach(x => createTemplateAssociation(comp, x))
  }

  def convertLevelsAndLearningGoalToTemplate(comp: CompOntologyManager, descriptorSetType: java.util.List[DESCRIPTORSETType]) {
    descriptorSetType.asScala.foreach(x => createTemplateAssociation(comp, x))
  }

  def createTemplateAssociation(comp: CompOntologyManager, x: DESCRIPTORSETType) {
    //TODO
    val templateName = x.getNAME()
    val competences = x.getDESCRIPTOR().asScala.map(EposXML2FilteredCSVCompetence.descriptorSetType2Id).map(x => new Competence(comp, x, x, false))
    competences.foreach(competence => competence.createEdgeWith(new Catchword(comp, EposXML2FilteredCSVCompetence.identifier2Definition(competence.getDataField(competence.DEFINITION))), CompObjectProperties.CatchwordOf))
    val learningProjectTemplate = new LearningProjectTemplate(comp, templateName, competences, templateName)
    learningProjectTemplate.persist
  }

  def convertLevelsToOWLRelations2(comp: CompOntologyManager, descriptorSetType: List[DESCRIPTORType]) {
    val result = descriptorSetType.map(x => (x.getCOMPETENCE(), x.getLEVEL())).distinct.combinations(2)
    result.foreach(x => convertLevelToOWLRelations3(comp, (x.head._1, x.head._2), (x.tail.head._1, x.tail.head._2)))
  }

  def convertLevelToOWLRelations3(comp: CompOntologyManager, domain: (String, String), range: (String, String)) {

    if (LevelFilter.filterSuggestedLevels(domain._2, range._2) && domain._1.equals(range._1)) {
      //println("create link " + domain + " " + range);
      val domainId = EposXML2FilteredCSVCompetence.descriptorSetPair2Id(domain);
      val rangeID = EposXML2FilteredCSVCompetence.descriptorSetPair2Id(range);
      val domainCompetence = new Competence(comp, domainId, domainId, false)
      domainCompetence.persist(true)
      val rangeCompetence = new Competence(comp, rangeID, rangeID, false)
      rangeCompetence.persist(true)
      rangeCompetence.addSuggestedCompetenceRequirement(domainCompetence)
    }
  }
}