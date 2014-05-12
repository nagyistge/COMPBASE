package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager

class EvidenceActivity(val url: String, val printableName: String, comp: CompOntologyManager) extends CompetenceOntologyDao(comp, CompOntClass.EvidenceActivity, printableName) {

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
}