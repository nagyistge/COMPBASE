package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.CompOntologyAccess

case class EvidenceActivity(comp: CompOntologyManager, val url: String, val printableName: String = null) extends CompetenceOntologyDao(comp, CompOntClass.EvidenceActivity, url) {

  def URL = "url"
  def PRINTABLENAME = "printableName"

  @Override
  protected def deleteMore() {
    //TODO
  }

  @Override
  protected def persistMore() {
    addDataField(URL, url)
    addDataField(PRINTABLENAME, printableName)
  }

  def getFullDao(): EvidenceActivity = {

    val hasPrintableName = getDataField(PRINTABLENAME) != null;
    val hasURL = getDataField(URL) != null;
    if (hasPrintableName && hasURL) {
      return new EvidenceActivity(comp, getDataField(PRINTABLENAME), getDataField(URL))
    } else {
      return this;
    }
  }
}