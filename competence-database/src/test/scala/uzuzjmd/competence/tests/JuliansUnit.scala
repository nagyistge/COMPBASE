package uzuzjmd.competence.tests

import org.apache.log4j.Logger
import org.scalatest.BeforeAndAfterEachTestData
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfterEach
import uzuzjmd.competence.owl.access.Logging

/**
 * @author dehne
 */
trait JuliansUnit extends FunSuite with BeforeAndAfterEach with Logging {

  override def test(testName: String, testTags: org.scalatest.Tag*)(testFun: ⇒ Unit): Unit = {

    logger.trace("starting test with name: " + testName + " and testTagsIsEmpy:" + testTags.isEmpty.toString())
    if (testTags.isEmpty) {
      super.test(testName)(testFun)
    } else {
      super.test(testName, testTags.head)(testFun)
    }
    //    logger.debug("finishing test with name: " + testName)
    //    Thread.sleep(100)
  }
}