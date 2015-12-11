package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, CompOntologyAccess}
import uzuzjmd.competence.persistence.ontology.CompOntClass
import uzuzjmd.competence.persistence.dao.exceptions.IndividualNotFoundException
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl

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

    try {
      val hasPrintableName: Boolean = getDataField(PRINTABLENAME) != null;
      val hasURL = getDataField(URL) != null;
      return new EvidenceActivity(comp, getDataField(URL), getDataField(PRINTABLENAME))
    } catch {
      case e: IndividualNotFoundException =>
        return this
    }

  }
}