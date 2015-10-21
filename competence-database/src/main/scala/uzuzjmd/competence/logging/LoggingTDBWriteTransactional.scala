package uzuzjmd.competence.logging

import uzuzjmd.competence.owl.access.TDBWriteTransactional
import org.apache.log4j.LogManager
import uzuzjmd.competence.owl.access.Logging

trait LoggingTDBWriteTransactional[A] extends TDBWriteTransactional[A] with Logging {

  override def execute(f: TRANSACTIONAL, g: A) {
    logger.debug("Entering TDBWriteTransactional with function:" + "dummy" + " and Parameter:" + g.toString())
    super.execute(f, g)
    logger.debug("Leaving TDBWriteTransactional function:" + "dummy")
  }
}