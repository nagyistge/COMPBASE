package uzuzjmd.competence.owl.access

import org.apache.log4j.LogManager

/**
 * @author dehne
 */
trait Logging {
  implicit protected def anyref2classnameadder(ref: AnyRef) = new ClassNameAdder(ref: AnyRef)
  protected val logger = LogManager.getLogger(this.className)
}