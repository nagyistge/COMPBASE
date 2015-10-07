package uzuzjmd.competence.mapper.rest.write

import scala.collection.JavaConverters.asScalaBufferConverter

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.owl.dao.Competence

object DeleteCompetenceTreeInOnt extends TDBWriteTransactional[java.util.List[String]] {
  def convert(changes: java.util.List[String]) {
    execute(convertDelteCompetenceTreeInOnt _, changes)
  }

  def convertDelteCompetenceTreeInOnt(comp: CompOntologyManager, changes: java.util.List[String]) {
    System.out.println("deleting competences" + changes);

    changes.asScala.foreach { x => new Competence(comp, x, x, null).deleteTree() }
  }

}