package uzuzjmd.competence.datasource.epos.mapper

import uzuzjmd.competence.datasource.epos.DESCRIPTORSETType
import uzuzjmd.competence.csv.FilteredCSVCompetence
import uzuzjmd.competence.csv.FilteredCSVCompetence
import scala.collection.JavaConverters._
import uzuzjmd.competence.csv.FilteredCSVCompetence
import scala.collection.mutable.Buffer
import uzuzjmd.competence.mapper.rcd.CSV2RCD
import uzuzjmd.competence.rcd.generated.Rdceo

object EposXML2FilteredCSVCompetence {
  def mapEposXML(eposCompetences: java.util.List[DESCRIPTORSETType]): java.util.List[FilteredCSVCompetence] = {
    val eposCompetencesScala = eposCompetences.asScala.toList
    return eposCompetencesScala.map(mapDescriptorSetToFilteredCSVCompetence).flatten.asJava
  }

  def mapDescriptorSetToFilteredCSVCompetence(descriptorSetType: DESCRIPTORSETType): Buffer[FilteredCSVCompetence] = {
    return descriptorSetType.getDESCRIPTOR().asScala.map(x => new FilteredCSVCompetence(x.getNAME(), List((x.getGOAL() + ""), x.getLEVEL()), x.getCOMPETENCE(), "", "", List.empty, "Language learner", descriptorSetType.getNAME()))
  }

  def EPOSXML2RCD(csvCompetences: java.util.List[FilteredCSVCompetence]): Seq[Rdceo] = {
    return CSV2RCD.mapCompetence(csvCompetences.asScala)
  }

}