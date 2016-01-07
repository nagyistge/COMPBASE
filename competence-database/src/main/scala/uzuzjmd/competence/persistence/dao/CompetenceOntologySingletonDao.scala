package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.config.MagicStrings
import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, OntResult, CompOntologyAccess}
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl
import uzuzjmd.scala.reflection.DAOFactory

import scala.collection.JavaConverters._

import com.hp.hpl.jena.ontology.Individual
import com.hp.hpl.jena.ontology.OntClass
import com.hp.hpl.jena.rdf.model.Property
import com.hp.hpl.jena.rdf.model.Statement
import uzuzjmd.competence.persistence.performance._
import uzuzjmd.competence.exceptions.DataFieldNotInitializedException
import uzuzjmd.competence.exceptions.DefinitionNotInitalizedException
import uzuzjmd.competence.exceptions.NoRecursiveSubClassException
import uzuzjmd.competence.exceptions.OntClassForDaoNotInitializedException
import uzuzjmd.competence.persistence.ontology.CompObjectProperties
import uzuzjmd.competence.persistence.ontology.CompOntClass

abstract case class CompetenceOntologySingletonDao(comp: CompOntologyManager, val compOntClass: CompOntClass, val identifier: String = null) extends Dao(comp, compOntClass, identifier) {
  val util = comp.getUtil()

  def DEFINITION = "definition"

  @throws[NoRecursiveSubClassException]
  def persistManualCascades(isCascade: Boolean): OntResult = {
    var result: OntResult = null
    if (identifier == null) {
      result = util.accessSingletonResourceWithClass(compOntClass, false)
    } else {
      result = util.accessSingletonResource(identifier, false, identifier)
    }
    if (isCascade) {
      val uniContext = new UniversityContext(comp)
      uniContext.createEdgeWith(CompObjectProperties.CourseContextOf, this)
      persistMore
    }
    return result
  }


  def persist: OntResult = {
    return persistManualCascades(true) //enforce complete persist
  }

  def persistFluent(more: Boolean): CompetenceOntologySingletonDao = {
    persistManualCascades(more)
    return this
  }

  def createIndividual: Individual = {
    return persistManualCascades(false).getIndividual()
  }

  def exists(): Boolean = {
    val result = util.getClass(compOntClass, true)
    if (result != null) {
      if (identifier != null) {
        val result = comp.getUtil().getIndividualForString(MagicStrings.SINGLETONPREFIX + identifier)
        return result != null
      } else {
        return true
      }
    }
    return false

  }

  override def getId: String = {
    //    return createIndividual.getLocalName()
    val individual = getIndividual
    if (individual == null) {
      return null
    } else {
      return individual.getLocalName
    }
  }

  //  override def getId: String = {
  //    return CompOntologyAccess.encode(identifier)
  //  }

  override def getIndividual: Individual = {
    //    return createIndividual.getLocalName()
    if (identifier != null) {
      val result = comp.getUtil().getIndividualForString(MagicStrings.SINGLETONPREFIX + identifier)
      return result
    } else {
      val result = util.getIndividualForString(MagicStrings.SINGLETONPREFIX + compOntClass.name())
      return result
    }
  }

  @Override
  def getOntClass: OntClass = {
    if (identifier != null) {
      val result = comp.getUtil().getOntClassForString(identifier)
      return result
    } else {
      val result = util.getOntClassForString(MagicStrings.SINGLETONPREFIX + compOntClass.name())
      return result
    }
  }

  @Override
  def equals(toCompare: Competence): Boolean = {
    return getDefinition().equals(toCompare.getDefinition())
  }

  def listSubClasses[T <: CompetenceOntologySingletonDao](clazz: java.lang.Class[T]): List[T] = {
    var definition = getDefinition
    if (definition == null) {
      definition = compOntClass.toString()
    }
    val ontClass = comp.getUtil().accessSingletonResource(definition, true).getOntclass()
    val classList = ontClass.listSubClasses(false).toList().asScala.filter(!_.toString().equals("http://www.w3.org/2002/07/owl#Nothing"))
    val identifierList = classList.map(x => x.getLocalName()).toList
    return identifierList.map(x => DAOFactory.instantiateDao(clazz)(comp, x).asInstanceOf[T]).toList
  }

  def getDefinition(): String = {
    try {
      return getDataField(DEFINITION)
    } catch {
      case e: DefinitionNotInitalizedException => return compOntClass.toString()
      case e: DataFieldNotInitializedException => return compOntClass.toString()
    }
    return null;
  }

  def isSubClass(parent: Competence): Boolean = {
    val ontClass = toOntClass;
    if (ontClass == null) {
      return false
    }
    return toOntClass.hasSuperClass(parent.toOntClass, false)
  }

  def toOntClass(): OntClass = {
    if (identifier == null) {
      val result = comp.getUtil().getOntClassForString(compOntClass.toString())
      return result
    }
    val result = comp.getUtil().getOntClassForString(identifier)
    return result
  }

  def delete() {
    val ontClass = toOntClass
    createIndividual.remove()
    ontClass.remove()
  }

  def deleteTree() {
    val ontClass = toOntClass
    createIndividual.remove()
    ontClass.listInstances().toList().asScala.foreach(x => x.remove())
    ontClass.remove()
  }

  def isSuperClass(child: CompetenceOntologySingletonDao): Boolean = {
    if (toOntClass == null) {
      return false
    }
    return toOntClass.hasSubClass(child.toOntClass, false)
  }

  def listSuperClasses[T <: CompetenceOntologySingletonDao](clazz: java.lang.Class[T]): List[T] = {
    val definition = getDefinition()
    val ontClass = comp.getUtil().accessSingletonResource(definition, true).getOntclass()
    val classList = ontClass.listSuperClasses(false).toList().asScala.filter(!_.toString().equals("http://www.w3.org/2002/07/owl#Nothing")).filterNot { x => x.getLocalName.equals("IThing") || x.getLocalName.equals("Thing") }
    val identifierList = classList.map(x => x.getLocalName()).toList
    return identifierList.map(x => DAOFactory.instantiateDao(clazz)(comp, x).asInstanceOf[T]).toList
  }

  def hasSuperClass: Boolean = {
    return !listSuperClasses(classOf[Competence]).isEmpty
  }



}