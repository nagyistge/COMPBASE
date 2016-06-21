package uzuzjmd.competence.recommender

import java.lang.Double
import java.util

import uzuzjmd.competence.persistence.dao.{CourseContext, Competence, User}

/**
  * Created by dehne on 31.03.2016.
  */
object ActivityRecommenderImpl extends ActivityRecommender {
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
}
