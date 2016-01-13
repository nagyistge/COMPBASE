package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.monopersistence.daos.Competence
import uzuzjmd.competence.persistence.abstractlayer.ReadTransactional

import scala.collection.JavaConverters._

/**
 * @author dehne
 */
object Ont2Catchwords extends ReadTransactional[String, String] {

  def convert(forCompetence: String): String = {
    return execute(getCatchwords, forCompetence)
  }


  def getCatchwords(forCompetence: String): String = {
    val competence = new Competence(forCompetence);
    val catchwords = competence.getCatchwords().asScala;

    var result: String = "";
    if (!catchwords.isEmpty) {
      for (catchword <- catchwords) {
        result += catchword.getDefinition() + ",";
      }
      result = result.substring(0, result.length() - 1);
    }
    return result;
  }
}
