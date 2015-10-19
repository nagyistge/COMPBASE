package uzuzjmd.competence.owl.access

import uzuzjmd.competence.main.OntologyWriter

/**
 * @author dehne
 *
 * ensures a write transaction ont
 */
trait TDBWriteTransactional[A] {
  val comp = new CompOntologyManager

  var debugOn = false
  type TRANSACTIONAL = (CompOntologyManager, A) => Unit
  type TRANSACTIONAL2 = (CompOntologyManager) => Unit

  def setDebug() {
    debugOn = MagicStrings.WRITEDEBUGRDF
  }

  def execute(f: TRANSACTIONAL, g: A) {
    comp.begin
    comp.getM.enterCriticalSection(false)
    try {
      f(comp, g)
      comp.commit()
    } catch { case e: Exception => e.printStackTrace() } finally {
      comp.getM.leaveCriticalSection()
      comp.end()
    }
    if (debugOn) {
      OntologyWriter.convert
    }
  }

  def executeWithReasoning(f: TRANSACTIONAL, g: A) {
    comp.begin
    comp.getM.enterCriticalSection(false)
    if (!debugOn) {
      comp.switchOffDebugg();
    }
    comp.startReasoning();
    try {
      f(comp, g)
      comp.commit()
    } catch { case e: Exception => e.printStackTrace() } finally {
      comp.getM.leaveCriticalSection()
      comp.end()
    }
  }

  def executeNoParam(f: TRANSACTIONAL2) {
    comp.begin
    comp.getM.enterCriticalSection(false)
    try {
      f(comp)
      comp.commit()
    } catch { case e: Exception => e.printStackTrace() } finally {
      comp.getM.leaveCriticalSection()
      comp.end()
    }

    if (debugOn) {
      OntologyWriter.convert
    }
  }

  def executeNoParamWithReasoning(f: TRANSACTIONAL2) {
    comp.begin
    comp.getM.enterCriticalSection(false)
    comp.startReasoning();
    if (!debugOn) {
      comp.switchOffDebugg();
    }
    try {
      f(comp)
      comp.getM.validate()
      comp.commit()
    } catch { case e: Exception => e.printStackTrace() } finally {
      comp.getM.leaveCriticalSection()
      comp.end()
    }
    if (debugOn) {
      OntologyWriter.convert
    }
  }

  def execute[T](f: (CompOntologyManager, A) => T, g: A): T = {
    var result: Any = null
    comp.begin
    comp.getM.enterCriticalSection(false)
    try {
      result = f(comp, g)
      comp.commit()
    } catch { case e: Exception => e.printStackTrace() } finally {
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
    comp.begin
    comp.getM.enterCriticalSection(false)
    try {
      f(comp, g)
      comp.commit()
    } catch { case e: Exception => e.printStackTrace() } finally {
      comp.getM.leaveCriticalSection()
      comp.end()
    }
    if (debugOn) {
      OntologyWriter.convert
    }
  }

  def executeAll(l: Array[TRANSACTIONAL], g: A) {
    l.foreach { x => execute(x, g) }
  }

  def executeAllinOneTransaction(l: Array[TRANSACTIONAL], g: A) {
    comp.begin
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