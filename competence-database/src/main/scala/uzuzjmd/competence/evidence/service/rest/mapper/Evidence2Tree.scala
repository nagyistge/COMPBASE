package uzuzjmd.competence.evidence.service.rest.mapper

import uzuzjmd.competence.evidence.service.moodle.MoodleContentResponse
import uzuzjmd.competence.evidence.service.moodle.MoodleContentResponseList
import uzuzjmd.competence.evidence.model.MoodleEvidence
import uzuzjmd.competence.evidence.service.rest.dto.UserTree
import uzuzjmd.competence.evidence.service.rest.dto.ActivityEntry
import scala.collection.mutable.Buffer
import scala.collection.JavaConverters._
import uzuzjmd.competence.evidence.service.moodle.Module

class Evidence2Tree(moodleResponses: MoodleContentResponseList, moodleEvidences: Array[MoodleEvidence]) {
  def getUserTrees(): Buffer[UserTree] = {
    moodleResponses.asScala.map(x => x.getModules().asScala).flatten.map(moodleResponseToUserTree(moodleEvidences.toBuffer));
  }

  def moodleResponseToUserTree(moodleEvidences: Buffer[MoodleEvidence])(moodleResponse: Module): UserTree = {
    val filteredMoodleEvidences = moodleEvidences.find(x => x.getUrl().equals(moodleResponse.getUrl()))
    // TODO
    new UserTree()
  }
}