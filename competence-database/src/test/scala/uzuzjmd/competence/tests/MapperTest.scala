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
import uzuzjmd.competence.main.EposImporter

@RunWith(classOf[JUnitRunner])
class MapperTest extends FunSuite with ShouldMatchers {

  test("The CSV import should run without errors") {

    // change this, if you want to really reset the database
    CompFileUtil.deleteTDB()

    val compOntManag = new CompOntologyManager()

    compOntManag.begin()
    compOntManag.getM().validate()
    compOntManag.close()

    CompetenceImporter.convertCSVArray();
    compOntManag.begin()
    compOntManag.getM().validate()
    compOntManag.close()

    EposImporter.importEpos();
    compOntManag.begin()
    compOntManag.getM().validate()
    compOntManag.close()

    compOntManag.begin()
    val fileUtil = new CompFileUtil(compOntManag.getM())
    fileUtil.writeOntologyout()
    compOntManag.close()
  }


}

