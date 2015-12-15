package uzuzjmd.competence.mapper.rest.read

import java.util.LinkedList

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, ReadTransactional}
import uzuzjmd.competence.persistence.ontology.CompObjectProperties
import uzuzjmd.competence.persistence.owl.{CompOntologyAccessScala, CompOntologyManagerJenaImpl, CompetenceQueriesJenaImpl}

import scala.collection.JavaConverters.asScalaBufferConverter

/**
 * @author dehne
 */
object Ont2SelectedCompetencesForCourse extends ReadTransactional[String, Array[String]] {
  def convert(changes: String): Array[String] = {
    execute(convertHelper _, changes)
  }

  def convertHelper(comp: CompOntologyManager, changes: String): Array[String] = {
    val queries = new CompetenceQueriesJenaImpl(comp.getM());
    val result = new LinkedList[String]();
    val competenceIndividuals = queries.getRelatedIndividualsDomainGiven(changes, CompObjectProperties.CourseContextOf);
    val it = competenceIndividuals.iterator()
    while (it.hasNext) {
      val competenceIndividual = it.next();
      val competenceClass = competenceIndividual.getOntClass(true);
      result.add(CompOntologyAccessScala.getDefinitionString(competenceClass, comp));
    }
    return result.asScala.toArray
  }

}