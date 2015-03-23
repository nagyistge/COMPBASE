package uzuzjmd.competence.mapper.gui.filter

import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.dao.Competence
import scala.collection.mutable.MutableList
import scala.collection.mutable.LinkedList
import scala.collection.mutable.Buffer

object Ont2SuggestedCompetencyGridMapper {

  type ComPairList = Buffer[(Competence, Competence)]

  def convertListToSuggestedCompetenceTriples(input: Buffer[Competence]): ComPairList = {
    val pairs = input.combinations(2)
    val pairsTMP = pairs.map(x => (x.head, x.last))
    val pairsTMP2 = pairsTMP.map(x => (x._2, x._1))
    val pairsTMP3 = (pairsTMP ++ pairsTMP2).toBuffer
    return pairsTMP3.filter(Ont2SuggestedCompetencyGridFilter.filterisSuggestedCompetency)
  }

  def convertListToSuggestedCompetenceTriplesInverse(input: ComPairList): Buffer[Competence] = {
    return input.map(x => x._1)
  }
}