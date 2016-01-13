package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.config.Logging
import uzuzjmd.competence.persistence.abstractlayer.ReadTransactional
import uzuzjmd.competence.service.rest.dto.{CatchwordXMLTree, CompetenceXMLTree, OperatorXMLTree}

/**
  * Diese Klasse mappt die Kompetenzen auf einen Baum, der in GWT-anzeigbar ist
  */
class Ont2CompetenceTree(selectedCatchwordArray: java.util.List[String], selectedOperatorsArray: java.util.List[String], course: String, compulsory: java.lang.Boolean, textFilter: String) extends ReadTransactional[Any, Any] with Logging {

  def getOperatorXMLTree(): java.util.List[OperatorXMLTree] = {
    throw new NotImplementedError()
  }


  def getCatchwordXMLTree(): java.util.List[CatchwordXMLTree] = {
    throw new NotImplementedError()
  }

  def getCompetenceTree(): java.util.List[CompetenceXMLTree] = {
    throw new NotImplementedError()
  }

}

