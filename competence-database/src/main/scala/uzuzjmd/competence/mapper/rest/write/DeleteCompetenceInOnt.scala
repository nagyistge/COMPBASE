package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.monopersistence.daos.Competence
import uzuzjmd.competence.persistence.abstractlayer.WriteTransactional

import scala.collection.JavaConverters.asScalaBufferConverter

object DeleteCompetenceInOnt extends WriteTransactional[java.util.List[String]] {

  def convert(change: java.util.List[String]) {
    execute(convertDeleteCompetenceInOnt _, change);
  }

  def convertDeleteCompetenceInOnt(changes: java.util.List[String]) {
    System.out.println("deleting competences" + changes);
    changes.asScala.foreach { x => new Competence(x).delete() }
  }
}