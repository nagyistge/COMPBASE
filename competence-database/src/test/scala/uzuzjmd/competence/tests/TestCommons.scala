package uzuzjmd.competence.tests

import uzuzjmd.competence.owl.access.CompFileUtil
import uzuzjmd.competence.main.CompetenceImporter
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.logging.LogConfigurator
import uzuzjmd.competence.main.EposImporter

/**
 * @author dehne
 */
object TestCommons {
  def setup() {
    LogConfigurator.initLogger()

    //      change this, if you want to really reset the database
    CompFileUtil.deleteTDB()
    val compOntManag = new CompOntologyManager()
    CompetenceImporter.convert();
    EposImporter.convert()
  }
}