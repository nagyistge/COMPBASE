package uzuzjmd.competence.mapper.gui

import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.owl.dao.User
import uzuzjmd.competence.liferay.reflexion.SuggestedCompetenceGrid
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.mapper.gui.filter.Ont2SuggestedCompetencyGridFilter
import uzuzjmd.competence.mapper.gui.filter.Ont2SuggestedCompetencyGridMapper
import uzuzjmd.competence.owl.dao.Catchword
import scala.collection.mutable.MutableList
import scala.collection.mutable.Buffer

object Ont2SuggestedCompetenceGrid {
  type ComPairList = Buffer[(Competence, Competence)]

  def convertToTwoDimensionalGrid(comp: CompOntologyManager, learningProjectTemplate: LearningProjectTemplate): SuggestedCompetenceGrid = {
    // TODO convert to Java Object
    return null
  }

  def convertToTwoDimensionalGrid1(comp: CompOntologyManager, learningProjectTemplate: LearningProjectTemplate): Map[Catchword, List[List[Competence]]] = {

    val includedCompetences = learningProjectTemplate.getAssociatedCompetences

    // identify most used catchwords
    val allCatchwords = includedCompetences.map(x => x.getCatchwords).flatten.groupBy(identity).mapValues(_.size).toList.sortBy(_._2).toMap.map(x => x._1)

    // group by catchwords
    val groupedCompetences = allCatchwords.map(catchword => (catchword, includedCompetences)).map(x => (x._1, x._2.filter(_.getCatchwords.contains(x._1)))).toMap

    // convert to datagrid structure for visualization
    val grid = groupedCompetences.mapValues(x => x.toList).mapValues(sortListOfSuggestedCompetences)

    return grid
  }

  def sortListOfSuggestedCompetences(rawList: List[Competence]): List[List[Competence]] = {
    // map to triples and filter the ones that have a suggested prerequisiste relationship
    val hList0TMP = Ont2SuggestedCompetencyGridMapper.convertListToSuggestedCompetenceTriples(rawList.toBuffer)
    val hList0 = hList0TMP.filter(Ont2SuggestedCompetencyGridFilter.filterisSuggestedCompetency)
    // init algorithm 
    val hList1 = hList0.take(1)
    val hList0WithoutPivot = hList0.tail
    // start recursive algorithm 
    return sortListOfSuggestedCompetences1(hList0WithoutPivot, hList1)
  }

  def sortListOfSuggestedCompetences1(hList0WithoutPivot: ComPairList, hList1: ComPairList): List[List[Competence]] = {
    return sortListOfSuggestedCompetences2(hList0WithoutPivot, hList1)
  }

  def sortListOfSuggestedCompetences2(hList0WithoutPivot: ComPairList, hList1: ComPairList): List[List[Competence]] = {
    val hList2: ComPairList = Buffer.empty

    hList0WithoutPivot.foreach(addHlist0ElementToCorrectList(_)(hList1, hList2))
    val reverseConvertedList = Ont2SuggestedCompetencyGridMapper.convertListToSuggestedCompetenceTriplesInverse(hList1).toList
    if (hList2.isEmpty) {
      return List(reverseConvertedList)
    }
    return reverseConvertedList :: sortListOfSuggestedCompetences2(hList2.tail, hList2.take(1))
  }

  /**
   * returns Pair Of (Hlist1, and HList2)
   */
  def addHlist0ElementToCorrectList(hList0Element: (Competence, Competence))(hList1: ComPairList, hList2: ComPairList) = {

    if (hList0Element._2.equals(hList1.head._1)) {
      hList1.prepend(hList0Element)

    }

    if (hList0Element._1.equals(hList1.last._2)) {
      hList2.append(hList0Element)
    }

    hList2.prepend(hList0Element)
  }

}