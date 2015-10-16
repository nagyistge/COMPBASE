package uzuzjmd.competence.owl.access

/**
 * @author dehne
 */
trait TDBREADTransactional[A, T] {

  type TRANSACTIONAL = (CompOntologyManager, A) => T
  type TRANSACTIONAL2 = (CompOntologyManager) => Unit
  type TRANSACTIONAL3 = A => T
  type TRANSACTIONAL4 = CompOntologyManager => T

  val comp = new CompOntologyManager

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
    var result: Any = null
    comp.beginRead()
    try {
      result = f(comp)
    } catch { case e: Exception => e.printStackTrace() } finally {
      comp.end()
      return result.asInstanceOf[X]
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
    result
  }

}