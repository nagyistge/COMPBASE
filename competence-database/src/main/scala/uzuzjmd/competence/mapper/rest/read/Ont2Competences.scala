package uzuzjmd.competence.mapper.rest.read

import java.util

import uzuzjmd.competence.persistence.dao.Competence
import uzuzjmd.competence.persistence.neo4j.CompetenceNeo4jQueryManagerImpl
import uzuzjmd.competence.service.rest.dto.CompetenceFilterData
import uzuzjmd.competence.util.LanguageConverter
import scala.collection.mutable

/**
  * Created by dehne on 12.04.2016.
  */
object Ont2Competences extends LanguageConverter{
    val qManager = new CompetenceNeo4jQueryManagerImpl

    def convert(filter : CompetenceFilterData) : java.util.List[String] = {
      val nodesArray: util.List[util.ArrayList[String]] = qManager.getSubClassTriples(classOf[Competence].getSimpleName, filter)
      val result: mutable.Buffer[String] = nodesArray.flatten
      return result
      //return qManager.getCompetences(filter)
      // TODO implement more efficient function
    }
}
