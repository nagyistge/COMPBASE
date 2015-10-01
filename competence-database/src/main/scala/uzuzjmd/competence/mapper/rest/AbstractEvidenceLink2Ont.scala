package uzuzjmd.competence.mapper.rest

import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import uzuzjmd.competence.shared.dto.HierarchieChangeSet
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink

/**
 * @author dehne
 */
object AbstractEvidenceLink2Ont extends TDBWriteTransactional[String] {

  def convert(changes: String) {
    execute(convertAbstractEvidenceLink _, changes)
  }

  def convertAbstractEvidenceLink(comp: CompOntologyManager, changes: String) {
    val abstractEvidenceLink = new AbstractEvidenceLink(comp, changes);
    abstractEvidenceLink.delete;
  }
}