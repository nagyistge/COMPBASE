package uzuzjmd.competence.config

import org.apache.log4j.LogManager
import uzuzjmd.competence.persistence.abstractlayer.ClassNameAdder

/**
 * @author dehne
 */
trait Logging {
  implicit protected def anyref2classnameadder(ref: AnyRef) = new ClassNameAdder(ref: AnyRef)
  protected val logger = LogManager.getLogger(this.className)
}