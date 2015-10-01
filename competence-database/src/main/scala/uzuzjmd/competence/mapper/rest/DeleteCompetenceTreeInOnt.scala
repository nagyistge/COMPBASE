package uzuzjmd.competence.mapper.rest

import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.Competence

object DeleteCompetenceTreeInOnt extends TDBWriteTransactional[List[String]] {
  def convert(changes: List[String]) {
    execute(convertDelteCompetenceTreeInOnt _, changes)
  }
  
  def convertDelteCompetenceTreeInOnt(comp: CompOntologyManager, changes: List[String]) {
    System.out.println("deleting competences" + changes);

    changes.foreach { x => new Competence(comp, x, x, null).deleteTree() }
  }

}