package uzuzjmd.competence.mapper.gui

import scala.collection.JavaConverters._
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.MagicStrings
import com.hp.hpl.jena.ontology.OntProperty
import scala.collection.mutable.Buffer
import uzuzjmd.competence.service.rest.dto.CompetenceXMLTree
import uzuzjmd.competence.service.rest.dto.OperatorXMLTree
import uzuzjmd.competence.service.rest.dto.CatchwordXMLTree
import com.hp.hpl.jena.ontology.OntClass
import uzuzjmd.competence.view.xml.AbstractXMLTree
import scala.reflect._
import java.util.LinkedList
import scala.reflect.runtime.universe._
import uzuzjmd.scalahacks.ScalaHacks
import uzuzjmd.competence.owl.ontology.CompObjectProperties

/**
 * Diese Klasse mappt die Kompetenzen auf einen Baum, der in GWT-anzeigbar ist
 */
class Ont2CompetenceTree(ontologyManager: CompOntologyManager, selectedCatchwordArray: java.util.List[String], selectedOperatorsArray: java.util.List[String], course: String) {

  val selectedCatchwordIndividuals = selectedCatchwordArray.asScala.filterNot(_.trim().equals("")).map(ontologyManager.getUtil().createSingleTonIndividual(_)).filterNot(_ == null)
  val selectedOperatorIndividuals = selectedOperatorsArray.asScala.filterNot(_.trim().equals("")).map(ontologyManager.getUtil().createSingleTonIndividual(_)).filterNot(_ == null)
  val util = ontologyManager.getUtil()

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
  private def convertClassToAbstractXMLEntries[A <: AbstractXMLTree[A]](subclass: OntClass, label: String, iconPath: String, clazz: java.lang.Class[A], allow: (OntClass => Boolean)): A = {
    val definitionString = getDefinitionString(subclass)
    var result: A = instantiate[A](clazz)(definitionString, label, iconPath, new LinkedList).asInstanceOf[A]
    if (subclass.hasSubClass() && !subclass.listSubClasses().asScala.toList.isEmpty && !subclass.listSubClasses().asScala.toList.head.getLocalName().equals("Nothing")) {
      val subberclasses = subclass.listSubClasses().toList().asScala.filter(allow).map(x => convertClassToAbstractXMLEntries[A](x, label, iconPath, clazz, allow)).toList
      result = instantiate[A](clazz)(definitionString, label, iconPath, subberclasses.asJava).asInstanceOf[A]
    }
    if (clazz.equals(classOf[CompetenceXMLTree])) {
      result.asInstanceOf[CompetenceXMLTree].setIsCompulsory(getCompulsory(subclass))
    }
    return result

  }

  def hasLinks(ontClass: OntClass): Boolean = {
    val util = ontologyManager.getUtil()
    return (selectedCatchwordIndividuals.forall(util.existsObjectPropertyWithIndividual(_, util.createSingleTonIndividual(ontClass), CompObjectProperties.CatchwordOf))
      && selectedOperatorIndividuals.forall(util.existsObjectPropertyWithIndividual(_, util.createSingleTonIndividual(ontClass), CompObjectProperties.OperatorOf)))
  }

  def containsCatchword(ontClass: OntClass): Boolean = {
    if (selectedCatchwordArray.isEmpty()) {
      return true;
    }
    return selectedCatchwordArray.contains(ontClass.getLocalName())
  }

  def containsOperator(ontClass: OntClass): Boolean = {
    if (selectedOperatorsArray.isEmpty()) {
      return true;
    }
    return selectedOperatorsArray.contains(ontClass.getLocalName())
  }

  /**
   * returns the operatortree
   */
  def getOperatorXMLTree(): java.util.List[OperatorXMLTree] = {
    ontologyManager.begin()
    // Klasse, in die rekursiv abgestiegen werden soll
    val operatorClass = ontologyManager.getUtil().getClass(CompOntClass.Operator);
    val result = convertClassToAbstractXMLEntries[OperatorXMLTree](operatorClass, "Operator", "nopathspecified", classOf[OperatorXMLTree], containsCatchword)
    ontologyManager.close()
    val filteredResult = filterResults(result)
    return filteredResult.asJava
  }

  /**
   * returns the catchwordtree
   */
  def getCatchwordXMLTree(): java.util.List[CatchwordXMLTree] = {
    ontologyManager.begin()
    // Klasse, in die rekursiv abgestiegen werden soll
    val catchwordClass = ontologyManager.getUtil().getClass(CompOntClass.Catchword);
    val result = convertClassToAbstractXMLEntries[CatchwordXMLTree](catchwordClass, "Catchwords", "nopathspecified", classOf[CatchwordXMLTree], containsOperator)
    ontologyManager.close()
    val filteredResult = filterResults(result)
    return filteredResult.asJava
  }

  /**
   * returns the competencetree
   */
  def getComptenceTree(): java.util.List[CompetenceXMLTree] = {
    ontologyManager.begin()
    // Klasse, in die rekursiv abgestiegen werden soll
    val catchwordClass = ontologyManager.getUtil().getClass(CompOntClass.Competence);
    val result = convertClassToAbstractXMLEntries[CompetenceXMLTree](catchwordClass, "Kompetenz", "nopathspecified", classOf[CompetenceXMLTree], hasLinks)
    ontologyManager.close()
    val filteredResult = filterResults(result)
    return filteredResult.asJava
  }

  /**
   * Hilfsfunktion um Ergebnis zu säubern
   */
  private def filterResults[A <: AbstractXMLTree[A]](result: A): List[A] = {
    val filteredResult = (result :: List.empty).filterNot(_ == null)
    filteredResult
  }

  private def getDefinitionString(subclass: com.hp.hpl.jena.ontology.OntClass): String = {
    if (getPropertyString(subclass, "definition") != null) {
      return (getPropertyString(subclass, "definition").toString().replaceAll("[\n\r]", "")).replaceAll("[\n]", "")
    } else {
      return ""
    }

  }

  private def getCompulsory(subclass: com.hp.hpl.jena.ontology.OntClass): Boolean = {

    //    if (getPropertyString(subclass, "compulsory") != null) {
    //      val result = getPropertyString(subclass, "compulsory").asInstanceOf[Boolean]
    //      return result
    //    } else {
    //      return false;
    //    }

    return util.existsObjectPropertyWithIndividual(util.getIndividualForString(course), util.createSingleTonIndividual(subclass), CompObjectProperties.CompulsoryOf);

  }

  private def getPropertyString(subclass: com.hp.hpl.jena.ontology.OntClass, propertyName: String): Object = {
    val iProperty = ontologyManager.getM.getOntProperty(MagicStrings.PREFIX + propertyName)
    val value = subclass.getPropertyValue(iProperty)
    if (value == null) { return null }
    else { return value.asNode().getLiteralValue() }
  }

}