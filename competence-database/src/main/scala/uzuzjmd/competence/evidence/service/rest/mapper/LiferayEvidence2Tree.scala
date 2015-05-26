package uzuzjmd.competence.evidence.service.rest.mapper

import de.unipotsdam.elis.model.EvidenceSoap
import uzuzjmd.competence.owl.access.MagicStrings
import scala.collection.JavaConverters._
import uzuzjmd.competence.service.rest.client.dto.UserTree
import uzuzjmd.competence.service.rest.client.dto.ActivityEntry
import uzuzjmd.competence.service.rest.client.dto.ActivityTyp

case class LiferayEvidence2Tree(activities: Array[EvidenceSoap]) {
  def convert(): Array[UserTree] = {
    val result1 = activities.groupBy(x => x.getUserName())
    return result1.map(x => new UserTree(x._1, "User", MagicStrings.USERICONPATH, convertToActivityTyp(x._2).asJava)).toArray
  }

  def convertToActivityTyp(input: Array[EvidenceSoap]): List[ActivityTyp] = {
    val result2 = input.groupBy(x => x.getActivityTyp())
    return result2.map(x => new ActivityTyp(x._1, "ActivityTyp", MagicStrings.webapplicationPath + "/icons/WindowsIcons-master/WindowsPhone/svg/appbar.monitor.to.svg", convertToActivityEntry(x._2).asJava)).toList
  }

  def convertToActivityEntry(input: Array[EvidenceSoap]): List[ActivityEntry] = {
    val result3 = input.map(x => new ActivityEntry(x.getTitle(), "Aktivity", MagicStrings.webapplicationPath + "/icons/WindowsIcons-master/WindowsPhone/svg/appbar.monitor.to.svg", x.getLink())).toList
    return result3;
  }

}