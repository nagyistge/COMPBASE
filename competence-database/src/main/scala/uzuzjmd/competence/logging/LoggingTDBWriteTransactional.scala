package uzuzjmd.competence.logging

import uzuzjmd.competence.config.Logging
import uzuzjmd.competence.persistence.abstractlayer.TDBWriteTransactional

/**
  * A shortcut for a write transactional that provides logging
  * @tparam A
  */
trait LoggingTDBWriteTransactional[A] extends TDBWriteTransactional[A] with Logging {

  override def execute(f: TRANSACTIONAL, g: A) {
    logger.debug("Entering TDBWriteTransactional with function:" + "dummy" + " and Parameter:" + g.toString())
    super.execute(f, g)
    logger.debug("Leaving TDBWriteTransactional function:" + "dummy")
  }
}