package uzuzjmd.competence.evidence.service.rest.mapper

import uzuzjmd.competence.evidence.service.moodle.MoodleContentResponse
import uzuzjmd.competence.evidence.service.moodle.MoodleContentResponseList
import uzuzjmd.competence.evidence.model.MoodleEvidence
import uzuzjmd.competence.evidence.service.rest.dto.UserTree
import uzuzjmd.competence.evidence.service.rest.dto.ActivityEntry
import scala.collection.mutable.Buffer
import scala.collection.JavaConverters._
import uzuzjmd.competence.evidence.service.moodle.Module
import uzuzjmd.competence.evidence.service.rest.dto.ActivityTyp
import uzuzjmd.competence.owl.access.MagicStrings

case class Evidence2Tree(moodleResponses: MoodleContentResponseList, moodleEvidences: Array[MoodleEvidence]) {
  def getUserTrees(): java.util.List[UserTree] = {
    moodleResponses.asScala.map(x => x.getModules().asScala).flatten.
      filter(x => filterNoMachingEvidenceSets(moodleEvidences.toBuffer)).
      map(moodleResponseToUserTree(moodleEvidences.toBuffer)).toList.asJava
  }

  def moodleResponseToUserTree(moodleEvidences: Seq[MoodleEvidence])(moodleResponse: Module): UserTree = {
    val moodleEvidencesView = moodleEvidences.view;
    val matchingEvidence = moodleEvidencesView.find(x => x.getUrl().equals(moodleResponse.getUrl()))

    // TODO
    val activities = moodleEvidencesView.map(x => new ActivityEntry(moodleResponse.getName(), "Activität", MagicStrings.ICONPATHGWT + "/appbar.monitor.to.svg"))
    val activityTypes = moodleEvidencesView.map(x => new ActivityTyp(moodleResponse.getModname(), "Aktivitätstyp", moodleResponse.getModicon(), activities.asJava))
    new UserTree(matchingEvidence.head.getUsername(), "Benutzer", "http://icons.iconarchive.com/icons/artua/dragon-soft/16/User-icon.png", activityTypes.asJava)
  }

  def filterNoMachingEvidenceSets(moodleResponse: Module)(x: Buffer[MoodleEvidence]): Boolean = {
    return x.map(z =>  z.getUrl()).contains(y => y.getUrl().equals(moodleResponse.getUrl()))
  }
}