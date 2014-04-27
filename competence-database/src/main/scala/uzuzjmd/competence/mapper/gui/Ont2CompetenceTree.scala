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

/**
 * Diese Klasse mappt die Kompetenzen auf einen Baum, der in GWT-anzeigbar ist
 */
class Ont2CompetenceTree(ontologyManager: CompOntologyManager) {

  /**
   * this implementation works, but should not be used in the end, because it only shows the top
   * level of competences
   */
  def getComptenceTree(): java.util.List[CompetenceXMLTree] = {
    ontologyManager.begin()

    val subCompetences = getSubClasses(CompOntClass.Competence)
    val iProperty = ontologyManager.getM.getOntProperty(MagicStrings.PREFIX + "definition")
    val definitions = subCompetences.map(x => x.getPropertyValue(iProperty))
    val result = definitions.filterNot(_ == null).map(x => new CompetenceXMLTree(x.asNode().getLiteralValue().toString(), "Kompetenz", "", null));
    ontologyManager.close()

    // todo implement
    return result.toList.asJava
  }

  /**
   * simply gets all the subclasses for a specified root class type
   */
  def getSubClasses(classType: CompOntClass): Buffer[com.hp.hpl.jena.ontology.OntClass] = {
    val competenceClass = ontologyManager.getUtil().getClass(classType);
    val competences = competenceClass.listSubClasses().toList().asScala
    return competences
  }

  def instantiate[A](clazz: java.lang.Class[A])(args: AnyRef*): Any = {

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
      if (!instance.isInstanceOf[OperatorXMLTree]) {
        throw new Error("falscher Rückgabewert")
      }
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
  def convertClassToAbstractXMLEntries[A <: AbstractXMLTree[A]](subclass: OntClass, label: String, iconPath: String, clazz: java.lang.Class[A]): A = {
    if (subclass.getLocalName().equals("Nothing")) {
      println("wait for it");
    }

    val iProperty = ontologyManager.getM.getOntProperty(MagicStrings.PREFIX + "definition")
    val definition = subclass.getPropertyValue(iProperty)
    if (subclass.hasSubClass() && !subclass.listSubClasses(true).asScala.toList.head.getLocalName().equals("Nothing")) {
      val subberclasses = subclass.listSubClasses(true).toList().asScala.map(x => convertClassToAbstractXMLEntries[A](x, label, iconPath, clazz)).toList
      return instantiate[A](clazz)(definition.asNode().getLiteralValue().toString(), label, iconPath, subberclasses.asJava).asInstanceOf[A]
    }
    return instantiate[A](clazz)(definition.asNode().getLiteralValue().toString(), label, iconPath, new LinkedList).asInstanceOf[A]
  }

  /**
   * returns the operatortree
   */
  def getOperatorXMLTree(): java.util.List[OperatorXMLTree] = {
    ontologyManager.begin()
    // zu füllendes root element für die Rekursion
    val operatorTree = (new OperatorXMLTree("Operatoren", "Operatoren", "iconpath", new LinkedList) :: List.empty)
    // Klasse, in die rekursiv abgestiegen werden soll
    val operatorClass = ontologyManager.getUtil().getClass(CompOntClass.Operator);
    val result = convertClassToAbstractXMLEntries[OperatorXMLTree](operatorClass, "Operator", "nopathspecified", classOf[OperatorXMLTree])
    ontologyManager.close()
    val filteredResult = (result :: List.empty).filterNot(_ == null)
    return filteredResult.asJava
  }

  def getCatchwordXMLTree(): java.util.List[CatchwordXMLTree] = {
    return null
  }

}