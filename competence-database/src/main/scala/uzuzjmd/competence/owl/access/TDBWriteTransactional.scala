package uzuzjmd.competence.owl.access

import uzuzjmd.competence.owl.access.CompOntologyManager

/**
 * @author dehne
 *
 * ensures a write transaction ont
 */
trait TDBWriteTransactional[A] {
  val comp = new CompOntologyManager
  type TRANSACTIONAL = (CompOntologyManager, A) => Unit

  def execute(f: TRANSACTIONAL, g: A) {
    comp.begin
    comp.getM.enterCriticalSection(false)
    try {
      f(comp, g)
      comp.commit()
    } finally {
      comp.getM.leaveCriticalSection()
      comp.end()
    }
  }

  def execute[T](f: (CompOntologyManager, A) => T, g: A): T = {
    comp.begin
    comp.getM.enterCriticalSection(false)
    try {
      val result = f(comp, g)
      comp.commit()
      return result
    } finally {
      comp.getM.leaveCriticalSection()
      comp.end()
    }
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
    } finally {
      comp.getM.leaveCriticalSection()
      comp.end()
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
    }
  }

}