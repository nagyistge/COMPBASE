package uzuzjmd.competence.mapper.gui

import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.owl.dao.User
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.mapper.gui.filter.Ont2SuggestedCompetencyGridFilter
import uzuzjmd.competence.mapper.gui.filter.Ont2SuggestedCompetencyGridMapper
import uzuzjmd.competence.owl.dao.Catchword
import scala.collection.mutable.MutableList
import scala.collection.mutable.Buffer
import org.apache.log4j.Level
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import scala.collection.mutable.Buffer
import scala.collection.JavaConverters._
import com.hp.hpl.jena.ontology.OntModel
import com.hp.hpl.jena.ontology.ObjectProperty
import com.hp.hpl.jena.ontology.OntClass
import com.hp.hpl.jena.ontology.Individual
import com.hp.hpl.jena.util.iterator.Filter
import uzuzjmd.competence.rcd.RCDMaps
import uzuzjmd.competence.owl.access.CompOntologyAccess
import uzuzjmd.competence.rcd.RCDFilter
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.rcd.generated.Rdceo
import uzuzjmd.competence.rcd.generated.Title
import uzuzjmd.competence.console.util.LogStream
import uzuzjmd.competence.rcd.generated.Statement
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.MagicStrings
import java.util.ArrayList
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.mapper.rcd.RCD2OWL
import uzuzjmd.competence.owl.dao.Catchword
import uzuzjmd.competence.shared.SuggestedCompetenceColumn
import uzuzjmd.competence.shared.SuggestedCompetenceRow
import uzuzjmd.competence.shared.SuggestedCompetenceGrid
import uzuzjmd.competence.shared.ReflectiveAssessmentsListHolder
import uzuzjmd.competence.shared.ReflectiveAssessment
import uzuzjmd.competence.shared.Assessment

object Ont2SuggestedCompetenceGrid {

  protected type ComPairList = Buffer[(Competence, Competence)]

  private val log = LogManager.getLogger(Ont2SuggestedCompetenceGrid.getClass().getName());
  log.setLevel(Level.WARN)
  private val logStream = new LogStream(log, Level.DEBUG);

  def convertToTwoDimensionalGrid(comp: CompOntologyManager, learningProjectTemplate: LearningProjectTemplate, user: User): SuggestedCompetenceGrid = {
    val result = new SuggestedCompetenceGrid
    val scalaGrid = convertToTwoDimensionalGrid1(comp, learningProjectTemplate)
    val scalaGridDeNormalized: Buffer[(uzuzjmd.competence.owl.dao.Catchword, List[uzuzjmd.competence.owl.dao.Competence])] = Buffer.empty
    scalaGrid.foreach(x => x._2.foreach(oneList => scalaGridDeNormalized.append((x._1, oneList))))
    
    val unsortedRows = scalaGridDeNormalized.map(x => mapScalaGridToSuggestedCompetenceRow(x._1, x._2, user))
    val rows = unsortedRows.sortBy(_.getSuggestedCompetenceRowHeader()).asJava
    result.setSuggestedCompetenceRows(rows)
    return result
  }

  private def mapScalaGridToSuggestedCompetenceRow(catchword: Catchword, competences: List[Competence], user: User): SuggestedCompetenceRow = {
    val result = new SuggestedCompetenceRow
    result.setSuggestedCompetenceRowHeader(catchword.getDataField(catchword.DEFINITION))
    result.setSuggestedCompetenceColumns(competences.map(convertCompetenceToColumn(_)(user)).asJava)
    return result
  }

  private def convertCompetenceToColumn(competence: Competence)(user: User): SuggestedCompetenceColumn = {
    val result = new SuggestedCompetenceColumn
    result.setTestOutput(competence.getDataField(competence.DEFINITION))
    result.setProgressInPercent(calculateAssessmentIndex(competence, user))
    result.setReflectiveAssessmentListHolder(competenceToReflextiveAssessmentsListHolder(competence, user))
    return result
  }

  private def competenceToReflextiveAssessmentsListHolder(competence: Competence, user: User): ReflectiveAssessmentsListHolder = {
    val holder = new ReflectiveAssessmentsListHolder
    val assessment = new Assessment
    holder.setAssessment(assessment)
    holder.setSuggestedMetaCompetence(competence.getDefinition)
    holder.setReflectiveAssessmentList(competence.listSubClasses(classOf[Competence]).map(competenceToReflectiveAssessment(_)(user)).filter(_.getCompetenceDescription() != null).asJava)
    return holder
  }

  private def competenceToReflectiveAssessment(competence: Competence)(user: User): ReflectiveAssessment = {
    val result = new ReflectiveAssessment
    val index = competence.getAssessment(user).getAssmentIndex
    val assessment = new Assessment
    result.setAssessment(assessment.getItems().get(index))
    result.setCompetenceDescription(competence.getDefinition)
    result.setIsLearningGoal(competence.getAssessment(user).getLearningGoal)
    return result
  }

  private def calculateAssessmentIndex(competence: Competence, user: User): java.lang.Integer = {
    val listSubclases = competence.listSubClasses(classOf[Competence])
    val size = competence.listSubClasses(classOf[Competence]).size
    val sizeInJava: java.lang.Double = size
    val sum: java.lang.Double = listSubclases.map(x => x.getAssessment(user)).map(x => x.getAssmentIndex).map(x => x.toInt).sum
    val average = (sum) / listSubclases.size
    val result = Math.round(average * 33.33333)
    return Integer.parseInt(result + "")
  }

  def convertToTwoDimensionalGrid1(comp: CompOntologyManager, learningProjectTemplate: LearningProjectTemplate): Map[Catchword, List[List[Competence]]] = {

    val includedCompetences = learningProjectTemplate.getAssociatedCompetences

    // identify most used catchwords
    val allCatchwords = includedCompetences.map(x => x.getCatchwords).flatten.groupBy(identity).mapValues(_.size).toList

    val sortedCatchwords = allCatchwords.sortBy(_._2).toMap.map(x => x._1)
    log.debug("catchwords isolated: " + catchwordsStoString(sortedCatchwords.toBuffer))

    // group by catchwords
    val groupedCompetences = sortedCatchwords.map(catchword => (catchword, includedCompetences)).map(x => (x._1, x._2.filter(_.getCatchwords.contains(x._1)))).toMap

    // convert to datagrid structure for visualization
    val grid = groupedCompetences.mapValues(x => x.toList).mapValues(sortListOfSuggestedCompetences)

    return grid
  }

  /**
   * in case of bad run time performance topological sorting might be a better choice
   */
  private def sortListOfSuggestedCompetences(rawList: List[Competence]): List[List[Competence]] = {

    log.debug("list of competences to be sorted: ")
    log.debug(rawList.map(x => x.toStrinz).reduce((a, b) => a + " , " + b))

    // map to triples and filter the ones that have a suggested prerequisiste relationship
    val hList0TMP = Ont2SuggestedCompetencyGridMapper.convertListToSuggestedCompetenceTriples(rawList.toBuffer)
    val hList0 = hList0TMP.filter(Ont2SuggestedCompetencyGridFilter.filterisSuggestedCompetency)
    // init algorithm 
    val hList1 = Buffer(hList0.head)
    val hList0WithoutPivot = hList0.tail

    log.debug("init algorithm with:")
    log.debug("hList0:" + compairListToString(hList0))
    log.debug("hListWithoutPivot:" + compairListToString(hList0WithoutPivot))
    log.debug("hList1:" + compairListToString(hList1))

    // start recursive algorithm 
    return sortListOfSuggestedCompetences1(hList0WithoutPivot, hList1)
  }

  private def sortListOfSuggestedCompetences1(hList0WithoutPivot: ComPairList, hList1: ComPairList): List[List[Competence]] = {

    // need to copy the result of this run in order to calculate longest path
    val hListThisRun: ComPairList = Buffer.empty
    hListThisRun.appendAll(hList1)

    return sortListOfSuggestedCompetences2(hList0WithoutPivot, hList1, hListThisRun)
  }

  private def sortListOfSuggestedCompetences2(hList0WithoutPivot: ComPairList, hList1: ComPairList, hList1LastRun: ComPairList): List[List[Competence]] = {
    val hList2: ComPairList = Buffer.empty
    val sortedhList0WithoutPivot = hList0WithoutPivot.sortWith(Ont2SuggestedCompetencyGridFilter.sortCompetencePairs)
    sortedhList0WithoutPivot.foreach(addHlist0ElementToCorrectList(_)(hList1, hList2))

    log.debug("after adding elements to correct lists:")
    log.debug("hList0:" + compairListToString(sortedhList0WithoutPivot))
    log.debug("hList1:" + compairListToString(hList1))
    log.debug("hList2:" + compairListToString(hList2))

    val reverseConvertedList = Ont2SuggestedCompetencyGridMapper.convertListToSuggestedCompetenceTriplesInverse(hList1).toList
    // stop if all elements have been added to a path
    if (hList2.isEmpty) {
      return List(reverseConvertedList)
    }
    // restarting if path length has increased
    val hListThisRun: ComPairList = Buffer.empty
    hListThisRun.appendAll(hList1)
    if (hList1.size > hList1LastRun.size) {
      return sortListOfSuggestedCompetences2(hList2, hList1, hListThisRun)
    }
    // start new list if a fork has been detected
    return reverseConvertedList :: sortListOfSuggestedCompetences2(hList2.tail, hList2.take(1), hList2.take(1))
  }

  /**
   * returns Pair Of (Hlist1, and HList2)
   */
  private def addHlist0ElementToCorrectList(hList0Element: (Competence, Competence))(hList1: ComPairList, hList2: ComPairList) = {

    if (hList0Element._2.equals(hList1.head._1)) {
      hList1.prepend(hList0Element)
    } else if (hList0Element._1.equals(hList1.last._2)) {
      hList1.append(hList0Element)
    } else {
      hList2.append(hList0Element)
    }
  }

  def compairListToString(input: ComPairList): String = {
    if (input.isEmpty) {
      return "[]";
    }
    return input.map(x => (x._1.getDataField(x._1.DEFINITION), x._2.getDataField(x._2.DEFINITION))).map(x => "(" + x._1 + "," + x._2 + ")").reduce((a, b) => a + " , " + b)
  }

  private def catchwordsStoString(input: Buffer[Catchword]): String = {
    return input.map(x => x.getDataField(x.DEFINITION)).reduce((a, b) => a + ", " + b)
  }

}