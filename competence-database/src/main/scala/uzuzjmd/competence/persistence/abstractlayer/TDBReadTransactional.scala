package uzuzjmd.competence.persistence.abstractlayer

import uzuzjmd.competence.main.{CompetenceImporter, EposImporter}
import uzuzjmd.competence.persistence.dao.exceptions.NoCompetenceInDBException
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl

/**
 * @author dehne
 */
trait TDBReadTransactional[A, T] {

  type TRANSACTIONAL = (CompOntologyManager, A) => T
  type TRANSACTIONAL2 = (CompOntologyManager) => Unit
  type TRANSACTIONAL3 = A => T
  type TRANSACTIONAL4 = CompOntologyManager => T

  val comp = CompOntologyManagarFactory.createManager();

  def execute(f: TRANSACTIONAL, g: A): T = {
    var result: Any = null
    comp.beginRead()
    try {
      result = f(comp, g)
    } catch { case e: Exception => e.printStackTrace() } finally {
      comp.end()
      return result.asInstanceOf[T]
    }
    return result.asInstanceOf[T]
  }

  def executeOnlyParam(f: TRANSACTIONAL3, g: A): T = {
    var result: Any = null
    comp.beginRead()
    try {
      result = f(g)
    } catch { case e: Exception => e.printStackTrace() } finally {
      comp.end()
      return result.asInstanceOf[T]
    }
    return result.asInstanceOf[T]
  }

  def executeNoParam(f: TRANSACTIONAL4): T = {
    var result: Any = null
    comp.beginRead()
    try {
      result = f(comp)
    } catch { case e: Exception => e.printStackTrace() }
    finally {
      comp.end()
      return result.asInstanceOf[T]
    }
    return result.asInstanceOf[T]

  }

  def executeNoParamX[X](f: CompOntologyManager => X): X = {
    var dbIsEmpty = false
    var result: Any = null
    comp.beginRead()
    try {
      result = f(comp)
    } catch {
      case db: NoCompetenceInDBException => dbIsEmpty = true
      case e: Exception =>
        e.printStackTrace()
        System.err.println(e.getMessage)

    } finally {
      comp.end()
      if (!dbIsEmpty) {
        return result.asInstanceOf[X]
      } else {
        CompetenceImporter.convert()
        EposImporter.convert()
        return executeNoParamX(f)
      }
    }
    return result.asInstanceOf[X]
  }

  /**
   * no return
   */
  def execute2(f: TRANSACTIONAL2) {
    var result: Any = null
    comp.beginRead()
    try {
      result = f(comp)
    } catch { case e: Exception => e.printStackTrace() } finally {
      comp.end()
      result
    }

  }

}