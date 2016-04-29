package uzuzjmd.competence.mapper.rest

import java.util

import uzuzjmd.competence.comparison.simple.mapper.SimpleCompetenceComparatorMapper
import uzuzjmd.competence.persistence.dao.{DaoAbstractImpl, Competence}
import uzuzjmd.competence.persistence.neo4j.CompetenceNeo4jQueryManagerImpl
import uzuzjmd.competence.persistence.ontology.Edge
import uzuzjmd.competence.util.LanguageConverter
import scala.collection.JavaConverters._

/**
  * Created by dehne on 29.04.2016.
  */
object SimilaritiesUpdater extends LanguageConverter {

  def updateSimilarity(tuple: (Competence, Competence)) = {
    val computer = new SimpleCompetenceComparatorMapper
    val result = computer.computeSimilarityScore(tuple._1.getDefinition, tuple._2.getDefinition)
    val queryManagerImpl = new CompetenceNeo4jQueryManagerImpl
    queryManagerImpl.createRelationShipWithWeight(tuple._1.getDefinition, Edge.SimilarTo, tuple._2.getDefinition, result)
  }

  def update: Unit = {
    val competences: util.Set[Competence] = DaoAbstractImpl.getAllInstances(classOf[Competence]);
    val pairs = competences.asScala.toList.combinations(2).map(x=>(x.head,x.tail.head)).foreach(updateSimilarity(_))
  }

  def updateSimilarCompetencies(input: Competence): Unit = {
    val competences = DaoAbstractImpl.getAllInstances(classOf[Competence]);
    competences.asScala.foreach(updateSimilarity(_, input))
  }
}