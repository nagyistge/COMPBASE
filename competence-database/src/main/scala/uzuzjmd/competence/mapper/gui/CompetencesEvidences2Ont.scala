package uzuzjmd.competence.mapper.gui

import scala.collection.JavaConverters._
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import uzuzjmd.competence.owl.access.CompOntologyManager
import scala.collection.mutable.MutableList

class CompetencesEvidences2Ont {
  def writeLink(course: String, creator: String, linkedUser: String, courseContext: String, competences: java.util.List[String], evidences: java.util.List[String]) {
    val comp = new CompOntologyManager
    comp.begin()
    //    User creator = new User
    //    val evidenceLinks = MutableList.empty[AbstractEvidenceLink]
    //    competences.asScala.foreach(competence => evidences.asScala.foreach(evidence => evidenceLinks += new AbstractEvidenceLink(creator, linkedUser, courseContext, null, evidence, System.currentTimeMillis(), false, comp)))
    comp.close()
  }
}