package uzuzjmd.competence.tests

import org.apache.log4j.Logger
import org.scalatest.BeforeAndAfterEachTestData
import org.scalatest.FunSuite
import uzuzjmd.competence.logging.LogConfigurator
import org.scalatest.BeforeAndAfterEach

/**
 * @author dehne
 */
trait JuliansUnit extends FunSuite with BeforeAndAfterEach{
  
  
  
  private val logger = Logger.getLogger(getClass.getName);
  
  override def beforeEach() {
    LogConfigurator.initLogger()
    logger.debug("starting test ");
  }
  
  override def afterEach() {
    logger.debug("finished test");
  }
  override def test(testName: String, testTags: org.scalatest.Tag*)(testFun: ⇒ Unit): Unit = {
    
    logger.debug("starting test with name: " + testName + " and testTagsIsEmpy:" + testTags.isEmpty.toString())
    if (testTags.isEmpty) {
      super.test(testName)(testFun)
    } else {
      super.test(testName, testTags.head)(testFun)
    }
    logger.debug("finishing test with name: " + testName)
    //    Thread.sleep(100)
  }
}