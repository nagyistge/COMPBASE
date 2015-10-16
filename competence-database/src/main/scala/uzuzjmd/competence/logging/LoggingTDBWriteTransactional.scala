package uzuzjmd.competence.logging

import uzuzjmd.competence.owl.access.TDBWriteTransactional
import org.apache.log4j.LogManager

trait LoggingTDBWriteTransactional[A] extends TDBWriteTransactional[A] {
  
  private val logger = LogManager.getLogger(getClass.getName)
  override def execute(f: TRANSACTIONAL, g: A) {
    logger.debug("Entering TDBWriteTransactional with function:" + "dummy" + " and Parameter:" + g.toString())
    super.execute(f, g)
    logger.debug("Leaving TDBWriteTransactional function:" + "dummy")
  }
}