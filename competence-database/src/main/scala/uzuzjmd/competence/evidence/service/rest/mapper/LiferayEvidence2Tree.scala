package uzuzjmd.competence.evidence.service.rest.mapper

import de.unipotsdam.elis.model.EvidenceSoap
import uzuzjmd.competence.evidence.service.rest.dto.UserTree
import uzuzjmd.competence.owl.access.MagicStrings
import uzuzjmd.competence.evidence.service.rest.dto.ActivityTyp
import scala.collection.JavaConverters._
import uzuzjmd.competence.evidence.service.rest.dto.ActivityEntry

case class LiferayEvidence2Tree(activities: Array[EvidenceSoap]) {
  def convert(): Array[UserTree] = {
    val result1 = activities.groupBy(x => x.getUserName())
    return result1.map(x => new UserTree(x._1, "User", MagicStrings.USERICONPATH, convertToActivityTyp(x._2).asJava)).toArray
  }

  def convertToActivityTyp(input: Array[EvidenceSoap]): List[ActivityTyp] = {
    val result2 = input.groupBy(x => x.getActivityTyp())
    return result2.map(x => new ActivityTyp(x._1, "ActivityTyp", MagicStrings.ICONPATHMOODLE + "/appbar.monitor.to.svg", convertToActivityEntry(x._2).asJava)).toList
  }

  def convertToActivityEntry(input: Array[EvidenceSoap]): List[ActivityEntry] = {
    val result3 = input.map(x => new ActivityEntry(x.getTitle(), "Aktivity", MagicStrings.ICONPATHMOODLE + "/appbar.monitor.to.svg", x.getLink())).toList
    return result3;
  }

}