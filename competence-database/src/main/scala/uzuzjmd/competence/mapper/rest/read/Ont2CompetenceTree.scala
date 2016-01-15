package uzuzjmd.competence.mapper.rest.read

import java.util

import uzuzjmd.competence.config.MagicStrings
import uzuzjmd.competence.monopersistence.daos._
import uzuzjmd.competence.persistence.neo4j.Neo4JQueryManagerImpl
import uzuzjmd.competence.persistence.validation.TextValidator
import uzuzjmd.competence.service.rest.dto._
import uzuzjmd.java.collections.{TreePair, _}
import scala.collection.JavaConverters._

object Ont2CompetenceTree {

  val neo4jqueryManager = new Neo4JQueryManagerImpl
  val iconRootPath = MagicStrings.webapplicationPath
  val iconPathCompetence = iconRootPath + "/icons/competence.png"
  val iconPathOperator = iconRootPath + "/icons/filter.png"
  val iconPathCatchword = iconRootPath + "/icons/filter.png"


  /**
    * returns all the operators in the database as a tree
    * @param filterData
    * @return
    */
  def getOperatorXMLTree(filterData: CompetenceTreeFilterData): java.util.List[OperatorXMLTree] = {
    val competenceLabel = classOf[Operator].getSimpleName;
    val f = convertNodeXMLTree (classOf[OperatorXMLTree]) _
    // TODO implement filter
    return convertTree (competenceLabel, f(x=>true)(iconPathOperator))
  }

  /**
    * returns all the catchwords in the database as a tree
    * @param filterData
    * @return
    */
  def getCatchwordXMLTree(filterData: CompetenceTreeFilterData): java.util.List[CatchwordXMLTree] = {
    val competenceLabel = classOf[Catchword].getSimpleName;
    val f = convertNodeXMLTree (classOf[CatchwordXMLTree]) _
    // TODO implement filter
    return convertTree(competenceLabel, f(x=>true)(iconPathCatchword))
  }

  def getCompetenceTree(filterData: CompetenceTreeFilterData): java.util.List[CompetenceXMLTree] = {
    val competenceLabel = classOf[Competence].getSimpleName;
    val f = convertNodeXMLTree (classOf[CompetenceXMLTree]) _
    return convertTree(competenceLabel, f(competenceNodeFilter (filterData)(_))(iconPathCompetence))
  }

  /**
    * filters the result from the database and converts it into pairs of the subclass triples
    * @param competenceLabel
    * @param f
    * @tparam T
    * @return
    */
  def convertTree[T <: AbstractXMLTree[T]](competenceLabel: String, f: (Node) => T) : java.util.List[T] = {
    val nodesArray = neo4jqueryManager.getSubClassTriples(competenceLabel)
    val nodesArray2 = nodesArray.asScala
      .filterNot(x => x.size() > 2)
      .filterNot(x => x.iterator().next() == null)
      .map(x => new TreePair(x.get(1), x.get(0)))
      .asJava
    val rootNode = TreeGenerator.getTree(nodesArray2);
    return (f(rootNode) :: Nil).asJava
  }

  /**
    * converts a Node to a xml tree
    * @param iconPath
    * @param node
    * @tparam T
    * @return
    */
  def convertNodeXMLTree[T <: AbstractXMLTree[T]] (clazz: Class[T])(nodeFilter : (String) => Boolean )(iconPath: String)(node : Node)  : T = {
    val result =  clazz.getConstructor(classOf[String], classOf[String], classOf[String], classOf[java.util.List[T]]).newInstance(node.id, "", iconPath, new util.LinkedList[T]());
    if (node.children != null && !node.children.isEmpty && nodeFilter(node.id)) {
      val children: java.util.List[T] = node.children.asScala.map(x => convertNodeXMLTree (clazz)(nodeFilter)(iconPath)(x)).map(x=>x.asInstanceOf[T]).asJava
      result.asInstanceOf[T].setChildren(children)
    } else {
      result.asInstanceOf[T].setChildren(new util.ArrayList[T]());
    }
    return result
  }

  def competenceNodeFilter (competenceFilterData : CompetenceTreeFilterData) (input : String) :  java.lang.Boolean = {
     val competence = new Competence(input).getFullDao.asInstanceOf[Competence]
     val catchwords = competenceFilterData.getSelectedCatchwordArray.asScala.map(x=> new Catchword(x));
     val operators = competenceFilterData.getSelectedOperatorsArray.asScala.map(x=> new Operator(x))
     val course = new CourseContext(competenceFilterData.getCourse)

     val textCorrect = TextValidator.isValidText(input,competenceFilterData.getTextFilter)
     val catchwordsCorrect = competence.getCatchwords().containsAll(catchwords.asJava)
     val operatorsCorrect = competence.getOperators.containsAll(operators.asJava)
     val courseCorrect = course.getLinkedCompetences.contains(course)

     return textCorrect && catchwordsCorrect && operatorsCorrect && courseCorrect
  }




}

