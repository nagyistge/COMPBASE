package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager

class EvidenceActivity(comp: CompOntologyManager, val printableName: String, val url: String = null) extends CompetenceOntologyDao(comp, CompOntClass.EvidenceActivity, printableName) {

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

  def getFullDao(): CompetenceOntologyDao = {
    if (getDataField(PRINTABLENAME) != null && getDataField(url) != null) {
      return new EvidenceActivity(comp, getDataField(PRINTABLENAME).toString(), getDataField(url).toString())
    } else {
      return this;
    }
  }
}