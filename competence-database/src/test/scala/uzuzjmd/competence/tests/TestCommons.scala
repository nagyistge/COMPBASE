package uzuzjmd.competence.tests

import uzuzjmd.competence.main.CompetenceImporter
import uzuzjmd.competence.logging.LogConfigurator
import uzuzjmd.competence.main.EposImporter
import uzuzjmd.competence.persistence.owl.{CompFileUtil, CompOntologyManagerJenaImpl}

/**
 * @author dehne
 */
object TestCommons {
  def setup() {
    LogConfigurator.initLogger()

    //      change this, if you want to really reset the database
    CompFileUtil.deleteTDB()
    val compOntManag = new CompOntologyManagerJenaImpl()
    CompetenceImporter.convert();
    EposImporter.convert()
  }
}