package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, TDBWriteTransactional}
import uzuzjmd.competence.persistence.dao.AbstractEvidenceLink
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl

/**
 * @author dehne
 */
object AbstractEvidenceLink2Ont extends TDBWriteTransactional[String] {

  def convert(changes: String) {
    execute(convertAbstractEvidenceLink _, changes)
  }

  def convertAbstractEvidenceLink(comp: CompOntologyManager, changes: String) {
    val abstractEvidenceLink = new AbstractEvidenceLink(comp, changes, null, null);
    abstractEvidenceLink.delete;
  }
}