package uzuzjmd.competence.recommender

import java.lang.Double
import java.util

import uzuzjmd.competence.persistence.dao.{Competence, User}

/**
  * Created by dehne on 31.03.2016.
  */
object CompetenceRecommenderImpl extends CompetenceRecommender{
  override def recommendCompetences(email: String, competenceToReach: String, courseId: String): util.HashMap[String, Double] = ???

  def getResultPathToCompetence(user: User, competenceToReach: Competence) : List[Competence] = ???

  def getNextCompetences(user: User): Unit = {
      val competences = user.getCompetencesLearned;
  }

  def filterCompetences(input: List[Competence]): List[Competence] = ???
}
