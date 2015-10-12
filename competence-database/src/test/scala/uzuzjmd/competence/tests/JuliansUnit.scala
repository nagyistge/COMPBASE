package uzuzjmd.competence.tests

import org.scalatest.FunSuite
import uzuzjmd.competence.owl.access.PropUtil
import uzuzjmd.competence.owl.access.MagicStrings

/**
 * @author dehne
 */
trait JuliansUnit extends FunSuite {
  override def test(testName: String, testTags: org.scalatest.Tag*)(testFun: ⇒ Unit): Unit = {
    println("starting test with name: " + testName)
    if (testTags.isEmpty) {
      super.test(testName)(testFun)
    } else {
      super.test(testName, testTags.head)(testFun)
    }
    println("finishing test with name: " + testName)
    Thread.sleep(100)
  }
}