package uzuzjmd.competence.mapper.rest

import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.service.rest.model.dto.LinkValidationData
import uzuzjmd.competence.owl.dao.DaoFactory
import uzuzjmd.competence.owl.access.CompOntologyManager

/**
* @author jbe
**/

object HandleLinkValidationInOnt extends TDBWriteTransactional[LinkValidationData]{
  def convert(change:LinkValidationData) {
    execute(convertHandleLinkValidationInOnt _, change)
  }
  
  def convertHandleLinkValidationInOnt(comp: CompOntologyManager, changes: LinkValidationData) {
    val abstractEvidenceLink = DaoFactory.getAbstractEvidenceDao(comp, changes.getLinkId);
		abstractEvidenceLink.addDataField(abstractEvidenceLink.ISVALIDATED, changes.getIsvalid);

  }
}