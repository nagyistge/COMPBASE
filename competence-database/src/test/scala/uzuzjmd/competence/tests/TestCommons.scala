package uzuzjmd.competence.tests

import uzuzjmd.competence.main.CompetenceImporter
import uzuzjmd.competence.main.EposImporter
import uzuzjmd.competence.persistence.owl.{CompFileUtil, CompOntologyManagerJenaImpl}

/**
 * @author dehne
 */
object TestCommons {
  def setup() {
    //      change this, if you want to really reset the database
    CompFileUtil.deleteTDB()
    CompetenceImporter.convert();
    EposImporter.convert()
  }
}