package uzuzjmd.competence.mapper.rest.read

import java.util

import uzuzjmd.competence.persistence.abstractlayer.ReadTransactional
import uzuzjmd.competence.persistence.neo4j.{Neo4JQueryManagerImpl}
import uzuzjmd.competence.persistence.ontology.Label
import uzuzjmd.competence.shared.StringList

/**
 * @author dehne
 */
object Ont2LearningTemplates extends ReadTransactional[Any, StringList] {
  def convert(): StringList = {
    return getLearningTemplates()
  }

  private def getLearningTemplates(): StringList = {
    val manager = new Neo4JQueryManagerImpl()
    val tmp: util.List[String] = manager.getAllInstanceDefinitions(Label.LearningProjectTemplate)
    val learningTemplates = new StringList(tmp);
    return learningTemplates
  }
}
