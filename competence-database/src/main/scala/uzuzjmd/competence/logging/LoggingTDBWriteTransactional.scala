package uzuzjmd.competence.logging

import org.apache.log4j.LogManager
import uzuzjmd.competence.config.Logging
import uzuzjmd.competence.persistence.abstractlayer.TDBWriteTransactional

trait LoggingTDBWriteTransactional[A] extends TDBWriteTransactional[A] with Logging {

  override def execute(f: TRANSACTIONAL, g: A) {
    logger.debug("Entering TDBWriteTransactional with function:" + "dummy" + " and Parameter:" + g.toString())
    super.execute(f, g)
    logger.debug("Leaving TDBWriteTransactional function:" + "dummy")
  }
}