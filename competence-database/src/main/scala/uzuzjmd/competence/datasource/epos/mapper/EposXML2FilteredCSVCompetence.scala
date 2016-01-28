package uzuzjmd.competence.datasource.epos.mapper


import uzuzjmd.competence.datasource.csv.FilteredCSVCompetence
import uzuzjmd.competence.shared.{DESCRIPTORSETType, DESCRIPTORType}
import scala.collection.JavaConverters._
import scala.collection.mutable.Buffer

/**
  * Maps competence taxonomies given in the epos xml form to the persistence scheme
  */
object EposXML2FilteredCSVCompetence {
  def mapEposXML(eposCompetences: java.util.List[DESCRIPTORSETType]): java.util.List[FilteredCSVCompetence] = {
    val eposCompetencesScala = eposCompetences.asScala.toList
    return eposCompetencesScala.map(mapDescriptorSetToFilteredCSVCompetence).flatten.asJava
  }

  def mapDescriptorSetToFilteredCSVCompetence(descriptorSetType: DESCRIPTORSETType): Buffer[FilteredCSVCompetence] = {
    //descriptorSetType.getNAME()
    return descriptorSetType.getDESCRIPTOR().asScala.map(x => new FilteredCSVCompetence(x.getNAME(), List(x.getCOMPETENCE(), x.getLEVEL()), x.getCOMPETENCE(), "", "", List.empty, "Language learner", descriptorSetType2Id(x)))
  }

  def descriptorSetType2Id(des: DESCRIPTORType): String = {
    return (des.getCOMPETENCE() + " " + des.getLEVEL())
  }

  def descriptorSetPair2Id(a: (String, String)): String = {
    return a._1 + a._2;
  }

  def identifier2Definition(input: String): String = {
    if (input == null) {
      return null
    }
    return input.substring(0, input.length() - 2);
  }

}