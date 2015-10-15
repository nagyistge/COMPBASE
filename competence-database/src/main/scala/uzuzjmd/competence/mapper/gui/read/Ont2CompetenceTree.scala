package uzuzjmd.competence.mapper.gui.read

import java.util.LinkedList

import scala.collection.JavaConverters.asScalaBufferConverter
import scala.collection.JavaConverters.asScalaIteratorConverter
import scala.collection.JavaConverters.seqAsJavaListConverter

import com.hp.hpl.jena.ontology.OntClass

import uzuzjmd.competence.mapper.gui.mapper.TextValidator
import uzuzjmd.competence.owl.access.CompOntologyAccessScala
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.MagicStrings
import uzuzjmd.competence.owl.access.TDBREADTransactional
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.service.rest.database.dto.AbstractXMLTree
import uzuzjmd.competence.service.rest.database.dto.CatchwordXMLTree
import uzuzjmd.competence.service.rest.database.dto.CompetenceXMLTree
import uzuzjmd.competence.service.rest.database.dto.OperatorXMLTree

/**
 * Diese Klasse mappt die Kompetenzen auf einen Baum, der in GWT-anzeigbar ist
 */
class Ont2CompetenceTree(selectedCatchwordArray: java.util.List[String], selectedOperatorsArray: java.util.List[String], course: String, compulsory: java.lang.Boolean, textFilter: String) extends TDBREADTransactional[Any, Any] {

  val selectedOperatorIndividualstmp = selectedOperatorsArray.asScala.filterNot(_ == null).filterNot(_.trim().equals(""))

  /**
   * Hilfsfunktion, um eine generisch spezifizierte Klasse zu instantiieren
   */
  private def instantiate[A](clazz: java.lang.Class[A])(args: AnyRef*): Any = {

    val constructor = clazz.getDeclaredConstructors().toList.filter(x => x.getGenericParameterTypes().length == args.length).head

    if (constructor.getGenericParameterTypes().length != 4) {
      throw new Error("falschen Konstruktor rausgesucht")
    }

    if (args.length != 4) {
      throw new Error("falsche Anzahl an Parametern übergeben")
    }
    constructor.setAccessible(true);

    //    val constructor = clazz.getConstructors()(1)
    //    val objects = ScalaHacks.getObjectArray(args)
    //    val arg0 = objects(0);

    try {
      val instance = constructor.newInstance(args: _*)
      return instance
    } catch {
      case e: Exception =>
        println("hello my friend")
    }

    throw new Error("immer in hello fried-exception gelaufen")
  }

  /**
   * [A] the SubClass of AbstractXMLTree to be returned; can be CatchwordXMLTree, CompetenceXMLTree or OperatorXMLTree
   * needs a Ontclass to start recursively collection subclasses
   * needs label and iconpath in order to create the view
   */
  private def convertClassToAbstractXMLEntries[A <: AbstractXMLTree[A]](comp: CompOntologyManager, subclass: OntClass, label: String, iconPath: String, clazz: java.lang.Class[A], allow: (OntClass => Boolean), realTree: Boolean = true): A = {

    val definitionString = CompOntologyAccessScala.getDefinitionString(subclass, comp) match {
      case "" => label
      case x  => x
    }

    val adaptedIconPath = MagicStrings.webapplicationPath + "/" + iconPath;

    var result: A = instantiate[A](clazz)(definitionString, label, adaptedIconPath, new LinkedList).asInstanceOf[A]
    if (subclass.hasSubClass() && !subclass.listSubClasses().asScala.toList.isEmpty) {
      val subberclasses = subclass.listSubClasses(realTree).toList().asScala.filter(allow).filterNot(x => x.getURI().contains("Nothing")).map(x => convertClassToAbstractXMLEntries[A](comp, x, label, iconPath, clazz, allow)).toList
      result = instantiate[A](clazz)(definitionString, label, adaptedIconPath, subberclasses.asJava).asInstanceOf[A]
    }
    if (clazz.equals(classOf[CompetenceXMLTree])) {
      result.asInstanceOf[CompetenceXMLTree].setIsCompulsory(getCompulsory(comp, subclass))
    }
    return result

  }

  def hasLinks(comp: CompOntologyManager, ontClass: OntClass): Boolean = {
    val util = comp.getUtil()

    val selectedOperatorIndividuals = selectedOperatorIndividualstmp.map(comp.getUtil().createSingleTonIndividualWithClass2(_, true))
    val selectedCatchwordIndividuals = selectedCatchwordArray.asScala.filterNot(_ == null).filterNot(_.trim().equals("")).map(comp.getUtil().createSingleTonIndividualWithClass2(_, true))

    return (selectedCatchwordIndividuals.forall(util.existsObjectPropertyWithIndividual(_, util.createSingleTonIndividual(ontClass, true), CompObjectProperties.CatchwordOf))
      && selectedOperatorIndividuals.forall(util.existsObjectPropertyWithIndividual(_, util.createSingleTonIndividual(ontClass, true), CompObjectProperties.OperatorOf)))
  }

  def allowedAndCourse(comp: CompOntologyManager, ontClass: OntClass): Boolean = {
    val util = comp.getUtil()
    val competence = new Competence(comp, ontClass.getLocalName())
    val courseIndividual = new CourseContext(comp, course).getIndividual
    return hasLinks(comp, ontClass) && util.existsObjectPropertyWithIndividual(courseIndividual, util.createSingleTonIndividual(ontClass, true), CompObjectProperties.CourseContextOf) && competence.isAllowed()
  }

  def hasLinksAndCourse(comp: CompOntologyManager, ontClass: OntClass): Boolean = {
    val util = comp.getUtil()
    val courseIndividual = new CourseContext(comp, course).getIndividual
    return hasLinks(comp, ontClass) && util.existsObjectPropertyWithIndividual(courseIndividual, util.createSingleTonIndividual(ontClass, true), CompObjectProperties.CourseContextOf)
  }

  def containsCatchword(ontClass: OntClass): Boolean = {
    return true;
  }

  def containsOperator(ontClass: OntClass): Boolean = {
    return true;
  }

  /**
   * returns the operatortree
   */
  def getOperatorXMLTree(): java.util.List[OperatorXMLTree] = {
    return executeNoParamX[java.util.List[OperatorXMLTree]](getOperatorXMLTree)
  }

  /**
   * returns the operatortree
   */
  def getOperatorXMLTree(comp: CompOntologyManager): java.util.List[OperatorXMLTree] = {
    val operatorClass = comp.getUtil().getClass(CompOntClass.Operator, true);
    val result = convertClassToAbstractXMLEntries[OperatorXMLTree](comp: CompOntologyManager, operatorClass, "Operator", "icons/filter.png", classOf[OperatorXMLTree], containsOperator)
    val filteredResult = filterResults(result)
    return filteredResult.asJava
  }

  /**
   * returns the catchwordtree
   */
  def getCatchwordXMLTree(): java.util.List[CatchwordXMLTree] = {
    return executeNoParamX[java.util.List[CatchwordXMLTree]](getCatchwordXMLTree)
  }

  def getCatchwordXMLTree(comp: CompOntologyManager): java.util.List[CatchwordXMLTree] = {
    val catchwordClass = comp.getUtil().getClass(CompOntClass.Catchword, true);
    val result = convertClassToAbstractXMLEntries[CatchwordXMLTree](comp, catchwordClass, "Catchwords", "icons/filter.png", classOf[CatchwordXMLTree], containsCatchword)
    val filteredResult = filterResults(result)
    return filteredResult.asJava
  }

  //  /**
  //   * returns the competencetree
  //   */
  //  def getComptenceTree(): java.util.List[CompetenceXMLTree] = {
  //    if (selectedCatchwordArray.isEmpty() && selectedOperatorsArray.isEmpty()) {
  //      getCompetenceTreeHelper(hasLinks)
  //    } else {
  //      getCompetenceTreeHelperNoTree(hasLinks)
  //    }
  //  }

  def filterCompetenceTree(input: List[CompetenceXMLTree]): List[CompetenceXMLTree] = {

    if (input.isEmpty || compulsory == null) {
      return input;
    } else {
      val result = input.filter(x => x.getIsCompulsory().toString().equals(compulsory.toString))
      result.foreach(x => x.setChildren(filterCompetenceTree(x.getChildren().asScala.toList).asJava))
      return result
    }

  }

  /**
   * Hilfsfunktion um Ergebnis zu säubern
   */
  private def filterResults[A <: AbstractXMLTree[A]](result: A): List[A] = {
    val filteredResult = (result :: List.empty).filterNot(_ == null).map(TextValidator.purifyText(_, textFilter)).flatten
    //.map(x => filterText(x))
    filteredResult
  }

  //  private filterCourseContext[A <: AbstractXMLTree[A]](result: A) : Boolean = {
  //    result.getComptenceTree.
  //    return false
  //  }

  private def getCompulsory(comp: CompOntologyManager, subclass: com.hp.hpl.jena.ontology.OntClass): Boolean = {
    val util = comp.getUtil
    return util.existsObjectPropertyWithIndividual(util.getIndividualForString(course), util.createSingleTonIndividual(subclass, true), CompObjectProperties.CompulsoryOf);

  }

  def getComptenceTreeForCourse(): java.util.List[CompetenceXMLTree] = {
    executeNoParamX[java.util.List[CompetenceXMLTree]](getCompetenceTree)
  }

  def getCompetenceTree(comp: CompOntologyManager): java.util.List[CompetenceXMLTree] = {
    //    val noTree = getCompetenceTreeHelperNoTree(comp, allowedAndCourse(comp, _))
    val tree = getCompetenceTreeHelper(comp, allowedAndCourse(comp, _))
    //    if (noTree.isEmpty() && tree.isEmpty()) {
    //      return tree
    //    } else {
    //      tree.get(0).getChildren.addAll(noTree.get(0).getChildren)
    //      return tree
    //    }
    return tree
  }

  private def getCompetenceTreeHelper(comp: CompOntologyManager, allow: (OntClass => Boolean)): java.util.List[CompetenceXMLTree] = {
    // Klasse, in die rekursiv abgestiegen werden soll
    val catchwordClass = comp.getUtil().getClass(CompOntClass.Competence, true);
    val result = convertClassToAbstractXMLEntries[CompetenceXMLTree](comp, catchwordClass, "Kompetenz", "icons/competence.png", classOf[CompetenceXMLTree], allow)
    result.setIsCompulsory(compulsory);
    val filteredResult = filterCompetenceTree(filterResults(result))
    return filteredResult.asJava
  }

  private def getCompetenceTreeHelperNoTree(comp: CompOntologyManager, allow: (OntClass => Boolean)): java.util.List[CompetenceXMLTree] = {
    // Klasse, in die rekursiv abgestiegen werden soll
    val catchwordClass = comp.getUtil().getClass(CompOntClass.Competence, true);
    val result = convertClassToAbstractXMLEntries[CompetenceXMLTree](comp, catchwordClass, "Kompetenz", "icons/competence.png", classOf[CompetenceXMLTree], allow, false)
    result.setIsCompulsory(compulsory);
    val filteredResult = filterCompetenceTree(filterResults(result))
    return filteredResult.asJava
  }

}

