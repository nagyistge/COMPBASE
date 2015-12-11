package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl

object DaoFactory {
  def getAbstractEvidenceDao(comp: CompOntologyManager, identifier: String): AbstractEvidenceLink = {
    return new AbstractEvidenceLink(comp, identifier).getFullDao
  }

}