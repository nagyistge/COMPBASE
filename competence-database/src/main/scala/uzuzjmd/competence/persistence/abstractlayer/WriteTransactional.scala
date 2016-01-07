package uzuzjmd.competence.persistence.abstractlayer

import uzuzjmd.competence.config.MagicStrings
import uzuzjmd.competence.main.OntologyWriter
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl

/**
  * @author dehne
  *
  *         ensures a write transaction
  */
trait WriteTransactional[A] {
  type TRANSACTIONAL = (CompOntologyManager, A) => Unit
  type TRANSACTIONAL2 = (CompOntologyManager) => Unit
  type TRANSACTIONAL3 = (CompOntologyManager) => Any

  val comp = CompOntologyManagerFactory.createManager();
  var debugOn = MagicStrings.WRITEDEBUGRDF


  def execute(f: TRANSACTIONAL, g: A) {
    comp.beginWrite
    comp.getM.enterCriticalSection(false)
    try {
      f(comp, g)
      comp.commit()
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      comp.getM.leaveCriticalSection()
      comp.end()
    }
    if (debugOn) {
      OntologyWriter.convert
    }
  }

  def executeWithReasoning(f: TRANSACTIONAL, g: A) {
    comp.beginWrite
    comp.getM.enterCriticalSection(false)
    comp.startReasoning(debugOn);
    try {
      f(comp, g)
      comp.commit()
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      comp.getM.leaveCriticalSection()
      comp.end()
    }
  }

  def executeNoParam(f: TRANSACTIONAL2) {
    ()
    comp.beginWrite
    comp.getM.enterCriticalSection(false)
    try {
      f(comp)
      comp.commit()
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      comp.getM.leaveCriticalSection()
      comp.end()
    }

    if (debugOn) {
      OntologyWriter.convert
    }
  }


  def executeNoParamWithReturn(f: TRANSACTIONAL3) : Any ={
    comp.beginWrite
    comp.getM.enterCriticalSection(false)
    try {
      val result = f(comp)
      comp.commit()
      return result
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      comp.getM.leaveCriticalSection()
      comp.end()
    }
    if (debugOn) {
      OntologyWriter.convert
    }
  }

  def executeNoParamWithReasoning(f: TRANSACTIONAL2) {
    ()
    comp.beginWrite
    comp.getM.enterCriticalSection(false)
    comp.startReasoning(debugOn);

    try {
      f(comp)
      comp.getM.validate()
      comp.commit()
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      comp.getM.leaveCriticalSection()
      comp.end()
    }
    if (debugOn) {
      OntologyWriter.convert
    }
  }

  def execute[T](f: (CompOntologyManager, A) => T, g: A): T = {
    ()
    var result: Any = null
    comp.beginWrite
    comp.getM.enterCriticalSection(false)
    try {
      result = f(comp, g)
      comp.commit()
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      comp.getM.leaveCriticalSection()
      comp.end()
      if (debugOn) {
        OntologyWriter.convert
      }
    }
    return result.asInstanceOf[T]
  }

  /**
    * in case multiple purposes are followed in one class
    */
  def executeX[X](f: (CompOntologyManager, X) => Unit, g: X) {
    ()
    comp.beginWrite
    comp.getM.enterCriticalSection(false)
    try {
      f(comp, g)
      comp.commit()
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      comp.getM.leaveCriticalSection()
      comp.end()
    }
    if (debugOn) {
      OntologyWriter.convert
    }
  }

  def executeAll(l: Array[TRANSACTIONAL], g: A) {
    ()
    l.foreach { x => execute(x, g) }
  }

  def executeAllinOneTransaction(l: Array[TRANSACTIONAL], g: A) {
    comp.beginWrite
    comp.getM.enterCriticalSection(false)
    try {
      l.foreach { x => x(comp, g) }
      comp.commit()
    } finally {
      comp.getM.leaveCriticalSection()
      comp.end()
      if (debugOn) {
        OntologyWriter.convert
      }
    }
  }

}
