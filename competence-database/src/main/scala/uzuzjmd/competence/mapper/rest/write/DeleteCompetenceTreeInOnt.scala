package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, WriteTransactional}
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl

import scala.collection.JavaConverters.asScalaBufferConverter
import uzuzjmd.competence.persistence.dao.Competence

object DeleteCompetenceTreeInOnt extends WriteTransactional[java.util.List[String]] {
  def convert(changes: java.util.List[String]) {
    execute(convertDelteCompetenceTreeInOnt _, changes)
  }

  def convertDelteCompetenceTreeInOnt(comp: CompOntologyManager, changes: java.util.List[String]) {
    System.out.println("deleting competences" + changes);

    changes.asScala.foreach { x => new Competence(comp, x, x, null).deleteTree() }
  }

}