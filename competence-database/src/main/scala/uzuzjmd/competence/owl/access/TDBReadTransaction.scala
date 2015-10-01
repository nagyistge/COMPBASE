package uzuzjmd.competence.owl.access

import uzuzjmd.competence.owl.access.CompOntologyManager

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
    comp.beginRead()
    try {
      f(comp, g)
    } finally {
      comp.end()
    }
  }

  def executeOnlyParam(f: TRANSACTIONAL3, g: A): T = {
    comp.beginRead()
    try {
      f(g)
    } finally {
      comp.end()
    }
  }

  def executeNoParam(f: TRANSACTIONAL4): T = {
    comp.beginRead()
    try {
      return f(comp)
    } finally {
      comp.end()
    }
  }

  def executeNoParamX[X](f: CompOntologyManager => X): X = {
    comp.beginRead()
    try {
      return f(comp)
    } finally {
      comp.end()
    }
  }

  /**
   * no return
   */
  def execute2(f: TRANSACTIONAL2) {
    comp.beginRead()
    try {
      f(comp)
    } finally {
      comp.end()
    }
  }

}