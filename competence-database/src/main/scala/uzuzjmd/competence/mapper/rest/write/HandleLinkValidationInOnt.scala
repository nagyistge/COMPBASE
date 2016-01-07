package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, WriteTransactional}
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl
import uzuzjmd.competence.persistence.dao.DaoFactory
import uzuzjmd.competence.service.rest.dto.LinkValidationData

/**
* @author jbe
**/

object HandleLinkValidationInOnt extends WriteTransactional[LinkValidationData]{
  def convert(change:LinkValidationData) {
    execute(convertHandleLinkValidationInOnt _, change)
  }
  
  def convertHandleLinkValidationInOnt(comp: CompOntologyManager, changes: LinkValidationData) {
    val abstractEvidenceLink = DaoFactory.getAbstractEvidenceDao(comp, changes.getLinkId);
		abstractEvidenceLink.addDataField(abstractEvidenceLink.ISVALIDATED, changes.getIsValid);

  }
}