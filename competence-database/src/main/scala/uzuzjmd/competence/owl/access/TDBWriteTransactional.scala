package uzuzjmd.competence.owl.access

/**
 * @author dehne
 *
 * ensures a write transaction ont
 */
trait TDBWriteTransactional[A] {
  val comp = new CompOntologyManager
  val debugOn = true
  type TRANSACTIONAL = (CompOntologyManager, A) => Unit
  type TRANSACTIONAL2 = (CompOntologyManager) => Unit

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
    } finally {
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
    } finally {
      comp.getM.leaveCriticalSection()
      comp.end()
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