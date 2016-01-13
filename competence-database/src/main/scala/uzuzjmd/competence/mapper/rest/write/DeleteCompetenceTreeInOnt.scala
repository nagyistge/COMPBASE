package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.monopersistence.daos.Competence
import uzuzjmd.competence.persistence.abstractlayer.WriteTransactional
import scala.collection.JavaConverters.asScalaBufferConverter

object DeleteCompetenceTreeInOnt extends WriteTransactional[java.util.List[String]] {
  def convert(changes: java.util.List[String]) {
    execute(convertDelteCompetenceTreeInOnt _, changes)
  }

  def convertDelteCompetenceTreeInOnt( changes: java.util.List[String]) {
    System.out.println("deleting competences" + changes);
    changes.asScala.foreach { x => new Competence(x).deleteTree() }
  }

}