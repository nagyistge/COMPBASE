package uzuzjmd.competence.main

import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.dao.EvidenceActivity
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.TeacherRole
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.User
import uzuzjmd.competence.owl.dao.Comment
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.access.CompFileUtil
import uzuzjmd.competence.owl.dao.StudentRole
import uzuzjmd.competence.mapper.gui.Ont2CompetenceLinkMap
import uzuzjmd.competence.owl.access.MagicStrings

object CreateLink {

  def main(args: Array[String]): Unit = {
    val compOntManag = new CompOntologyManager
//   compOntManag.begin()
//    compOntManag.getM().validate()
//    compOntManag.close()
//
    CompFileUtil.deleteTDB()
    
    CompetenceImporter.convertCSV(MagicStrings.CSVLOCATION);
    compOntManag.begin()
    compOntManag.getM().validate()
    compOntManag.close()
//
//    compOntManag.begin()
//    val fileUtil = new CompFileUtil(compOntManag.getM())
//    fileUtil.writeOntologyout()
//    compOntManag.close()

    compOntManag.begin()
    val linkId = "hellolinkId"
    val userId = "Hendrik Geßner"
    val studentRole = new StudentRole(compOntManag)
    val coursecontext = new CourseContext(compOntManag, "n2")
    val userstudent = new User(compOntManag, userId, studentRole, coursecontext)
    userstudent.persist
    val link = createAbstract(compOntManag, linkId, userstudent)
    val mapper = new Ont2CompetenceLinkMap(compOntManag, userId)
    compOntManag.close()
    //    link.delete
//    showResult
  }

  private def showResult() {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()
    val fileUtil = new CompFileUtil(compOntManag.getM())
    fileUtil.writeOntologyout()
    compOntManag.close()
  }

  private def createAbstract(compOntManag: CompOntologyManager, linkId: String, userstudent: User): AbstractEvidenceLink = {
    val coursecontext = new CourseContext(compOntManag, "2")
    val teacherRole = new TeacherRole(compOntManag)
    val user = new User(compOntManag, "me", teacherRole, coursecontext)
    user.persist
    val user2 = new User(compOntManag, "me")
    val fullUser = user2.getFullDao
    // and now a more complicated example    
    val testkommentar = "mein testkommentar"
    val comment = new Comment(compOntManag, testkommentar, userstudent, System.currentTimeMillis())
    comment.persist
    val testkommentar2 = "mein testkommentar2"
    val comment2 = new Comment(compOntManag, testkommentar2, userstudent, System.currentTimeMillis())
    comment2.persist
    val evidenceActivity = new EvidenceActivity(compOntManag, "http://testest", "meine testaktivitat")
    val competence = new Competence(compOntManag, "Die Lehramtsanwärter kooperieren mit Kolleginnen und Kollegen bei der  Erarbeitung von Beratung/Empfehlung")
    competence.createEdgeWith(coursecontext, CompObjectProperties.CourseContextOf)
    val link = new AbstractEvidenceLink(compOntManag, linkId, user, userstudent, coursecontext, (comment :: comment2 :: Nil), evidenceActivity, System.currentTimeMillis(), false, competence)
    link.persist
    return link
  }

}