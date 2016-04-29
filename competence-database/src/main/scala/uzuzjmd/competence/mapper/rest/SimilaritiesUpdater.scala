package uzuzjmd.competence.mapper.rest

import uzuzjmd.competence.comparison.simple.mapper.SimpleCompetenceComparatorMapper
import uzuzjmd.competence.persistence.dao.{DaoAbstractImpl, Competence}
import uzuzjmd.competence.util.LanguageConverter
import scala.collection.JavaConverters._

/**
  * Created by dehne on 29.04.2016.
  */
class SimilaritiesUpdater extends  LanguageConverter{

  def updateSimilarity(tuple: (Competence, Competence)) = {
    // TODO
    val computer = new SimpleCompetenceComparatorMapper
    val result = computer.computeSimilarityScore(tuple._1.getDefinition, tuple._2.getDefinition)
    // TODO finish
  }

  def update: Unit = {
    val competences = DaoAbstractImpl.getAllInstances(classOf[Competence]);
    val pairs = competences.asScala.toList.combinations(2).map(x=>(x.head,x.tail.head)).foreach(updateSimilarity(_))
  }
}