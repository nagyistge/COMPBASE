package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager

object DaoFactory {
  def getAbstractEvidenceDao(comp: CompOntologyManager, identifier: String): AbstractEvidenceLink = {
    return new AbstractEvidenceLink(comp, identifier).getFullDao
  }

}