package uzuzjmd.competence.recommender

import java.lang.Double
import java.util
import uzuzjmd.competence.persistence.ontology.Edge;
import scala.collection.JavaConverters._

import uzuzjmd.competence.persistence.dao.{EvidenceActivity, CourseContext, Competence, User}
import uzuzjmd.competence.util.LanguageConverter

/**
  * Created by dehne on 31.03.2016.
  */
object ActivityRecommenderImpl extends ActivityRecommender with LanguageConverter{
  override def recommendActivities(email: String, competenceToReach: String, courseId: String): util.HashMap[Evidence, Double] = {
     val competence = new Competence(competenceToReach);
     val user = new User(userEmail);
     val course = new CourseContext(courseId)
     if (email != null && competenceToReach != null && user.exists() && competenceToReach.exists()) {
        // recommend activities that are suggested for the competence
        // recommend activities that are linked to a course which in turn is linked to the competence


     }
     // find out intermediary competences
     // recommend activities that are suggested for intermediary competences directly
     // recommend activities that are linked to courses that are linked to intermediary competences


  }

   def findSuggestedActivities(competence: Competence): List[EvidenceActivity] = {
      val evidences = competence.getAssociatedDaosAsDomain(Edge.SuggestedActivityForCompetence, classOf[EvidenceActivity])
      return evidences;
   }

  def findSuggestedActivitiesBasedOnCourse(competence: Competence) : List[EvidenceActivity] = {
      val courses: util.List[CourseContext] = competence.getAssociatedDaosAsDomain(Edge.CourseContextOfCompetence, classOf[CourseContext])
      courses.asScala.map(x=>x.getAssociatedDaoAsRange(Edge))
  }
}
