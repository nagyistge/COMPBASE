package uzuzjmd.competence.tests

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

@RunWith(classOf[JUnitRunner])
class MapperTest extends FunSuite with ShouldMatchers {

  test("The CSV import should run without errors") {

    // change this, if you want to really reset the database
    CompFileUtil.deleteTDB()

    val compOntManag = new CompOntologyManager()

    compOntManag.begin()
    compOntManag.getM().validate()
    compOntManag.close()

    CompetenceImporter.convertCSV(MagicStrings.CSVLOCATION);
    compOntManag.begin()
    compOntManag.getM().validate()
    compOntManag.close()

    compOntManag.begin()
    val fileUtil = new CompFileUtil(compOntManag.getM())
    fileUtil.writeOntologyout()
    compOntManag.close()
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

