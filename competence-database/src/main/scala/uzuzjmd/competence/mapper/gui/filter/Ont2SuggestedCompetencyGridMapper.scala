package uzuzjmd.competence.mapper.gui.filter

import scala.collection.mutable.Buffer
import org.apache.log4j.Level
import org.apache.log4j.LogManager
import uzuzjmd.competence.console.util.LogStream
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.mapper.gui.read.Ont2SuggestedCompetenceGrid
import uzuzjmd.competence.owl.ontology.CompObjectProperties

object Ont2SuggestedCompetencyGridMapper {



  type ComPairList = Buffer[(Competence, Competence)]

  val log = LogManager.getLogger(Ont2SuggestedCompetencyGridMapper.getClass().getName());
  log.setLevel(Level.WARN)
  val logStream = new LogStream(log, Level.DEBUG);

  def convertListToSuggestedCompetenceTriples(input: Buffer[Competence]): ComPairList = {

    val pairs = input.combinations(2)
    val pairsTMP = pairs.map(x => (x.head, x.last))
    val filteredResult = pairsTMP.map(reorderPairs).filter(Ont2SuggestedCompetencyGridFilter.filterisSuggestedCompetency).toBuffer

    log.debug("combinations after filtering:")
    log.debug(Ont2SuggestedCompetenceGrid.compairListToString(filteredResult))

    return filteredResult
  }

  def reorderPairs(pair: (Competence, Competence)): (Competence, Competence) = {
    if (pair._2.hasEdge(CompObjectProperties.SuggestedCompetencePrerequisiteOf, pair._1)) {
      return (pair._2, pair._1)
    } else {
      return pair
    }
  }

  def convertListToSuggestedCompetenceTriplesInverse(input: ComPairList): Buffer[Competence] = {
    val result = input.map(x => x._1)
    result.append(input.last._2)
    return result
  }
}