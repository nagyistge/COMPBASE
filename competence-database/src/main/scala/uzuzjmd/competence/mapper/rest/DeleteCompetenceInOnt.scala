package uzuzjmd.competence.mapper.rest

import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.Competence

object DeleteCompetenceInOnt extends TDBWriteTransactional[List[String]] {

  def convert(change: List[String]) {
    execute(convertDeleteCompetenceInOnt _, change);
  }
  
  def convertDeleteCompetenceInOnt(comp: CompOntologyManager, changes: List[String]) {
    System.out.println("deleting competences" + changes);

    changes.foreach { x => new Competence(comp, x, x, null).delete() }
  }
}