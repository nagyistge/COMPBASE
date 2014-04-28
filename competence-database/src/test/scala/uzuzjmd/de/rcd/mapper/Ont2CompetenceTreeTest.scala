package uzuzjmd.de.rcd.mapper

/*
Testing the mapping

*/
import org.scalatest.FunSuite
import scala.collection.JavaConverters._
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import uzuzjmd.competence.owl.access.CompFileUtil
import uzuzjmd.competence.main.CompetenceImporter
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.MagicStrings
import uzuzjmd.competence.mapper.gui.Ont2CompetenceTree
import uzuzjmd.competence.owl.ontology.CompOntClass

@RunWith(classOf[JUnitRunner])
class Ont2CompetenceTreeTest extends FunSuite with ShouldMatchers {

  test("The CompetenceTree should not be empty") {
    val compOntManag = new CompOntologyManager()
    compOntManag.switchOnDebug()

    compOntManag.begin()
    compOntManag.getM().validate()
    compOntManag.close()

    val mapper = new Ont2CompetenceTree(compOntManag, List.empty.asJava, List.empty.asJava)
    val competenceTree = mapper.getComptenceTree
    competenceTree should not be ('empty)

  }

  test("The filtered CompetenceTree should not be empty") {
    val compOntManag = new CompOntologyManager()
    compOntManag.switchOnDebug()

    compOntManag.begin()
    compOntManag.getM().validate()
    compOntManag.close()

    val catchwords = "Kooperation" :: "Diagnostik" :: List.empty
    val operators = "bewerten" :: "kooperieren" :: List.empty
    val mapper = new Ont2CompetenceTree(compOntManag, catchwords.asJava, operators.asJava)
    val competenceTree = mapper.getComptenceTree
    competenceTree should not be ('empty)

  }

  test("the operator tree should not be empty") {
    val compOntManag = new CompOntologyManager()
    val mapper = new Ont2CompetenceTree(compOntManag, List.empty.asJava, List.empty.asJava)
    val result = mapper.getOperatorXMLTree
    result should not be ('empty)
  }

  test("the catchword tree should not be empty") {
    val compOntManag = new CompOntologyManager()
    val mapper = new Ont2CompetenceTree(compOntManag, List.empty.asJava, List.empty.asJava)
    val result = mapper.getCatchwordXMLTree
    result should not be ('empty)
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

