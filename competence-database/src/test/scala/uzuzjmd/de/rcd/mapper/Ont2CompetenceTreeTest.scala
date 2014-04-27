package uzuzjmd.de.rcd.mapper

/*
Testing the mapping
*/
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import uzuzjmd.competence.owl.access.CompFileUtil
import uzuzjmd.competence.main.CompetenceImporter
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.MagicStrings
import uzuzjmd.competence.mapper.gui.Ont2CompetenceTree

@RunWith(classOf[JUnitRunner])
class Ont2CompetenceTreeTest extends FunSuite with ShouldMatchers {

  test("The CompetenceTree should not be empty") {
    val compOntManag = new CompOntologyManager()
    compOntManag.switchOnDebug()

    compOntManag.begin()
    compOntManag.getM().validate()
    compOntManag.close()

    val mapper = new Ont2CompetenceTree(compOntManag)

    val subClasses = mapper.getSubClassesOfCompetence()
    subClasses should not be ('empty)

    val competenceTree = mapper.getComptenceTree
    competenceTree should not be ('empty)

  }

  test("get subClasses should not be empty") {
    val compOntManag = new CompOntologyManager()
    val mapper = new Ont2CompetenceTree(compOntManag)
    compOntManag.begin()
    val subClasses = mapper.getSubClassesOfCompetence()
    compOntManag.close()
    subClasses should not be ('empty)
  }

  test("the operator tree should not be empty") {

  }

  test("the catchword tree should not be empty") {

  }

  //  test("A non-empty list should not be empty") {
  //    List(1, 2, 3) should not be ('empty)
  //    List("fee", "fie", "foe", "fum") should not be ('empty)
  //  }
  //
  //  test("A list's length should equal the number of elements it contains") {
  //    List() should have length (0)
  //    List(1, 2) should have length (2)
  //    List("fee", "fie", "foe", "fum") should have length (4)
  //  }
}

