package uzuzjmd.competence.mapper.rest

import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.service.rest.model.dto.CompetenceLinkedToCourseData
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.CourseContext
import com.hp.hpl.jena.ontology.Individual
import uzuzjmd.competence.owl.access.CompOntologyAccess
import com.hp.hpl.jena.rdf.model.Property
import scala.collection.JavaConverters._
import javax.ws.rs.WebApplicationException
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.access.AccessHelper

/**
 * @author dehne
 */
object CompetenceLinkedCourseToOnt extends TDBWriteTransactional[CompetenceLinkedToCourseData] with AccessHelper {
  def convert(changes: CompetenceLinkedToCourseData) {
    executeWithReasoning(convertHelper _, changes)
  }

  private def convertHelper(comp: CompOntologyManager, changes: CompetenceLinkedToCourseData) {
    val courseContextIndividual = new CourseContext(comp, changes.getCourse).createIndividual;
    addRequirementLiteral(changes.getRequirements, comp, courseContextIndividual);
    linkSingleCompetences(changes.getCompetences, changes.getCompulsoryBoolean, changes.getRequirements, comp, comp.getUtil, courseContextIndividual);
  }

  def addRequirementLiteral(requirements: String, compOntologyManager: CompOntologyManager, courseContextIndividual: Individual) {
    if (requirements != null) {
      val requirementsLiteral = extractRequirementsLiteral(compOntologyManager);
      val prop = courseContextIndividual.getProperty(requirementsLiteral);
      if (prop != null) {
        compOntologyManager.getM().remove(prop);
      }
      courseContextIndividual.addLiteral(requirementsLiteral, requirements);
    }
  }

  def linkSingleCompetences(competences: java.util.List[String], compulsoryBoolean: Boolean, requirements: String, compOntologyManager: CompOntologyManager, util: CompOntologyAccess,
                            courseContextIndividual: Individual) {
    if (competences == null || competences.isEmpty()) {
      throw new WebApplicationException(new Exception("Es wurden keine Kompetenzen übergeben"));
    }

    for (competence <- competences.asScala) {
      val result = util.accessSingletonResource(competence, false);
      val competenceIndividual = result.getIndividual();
      util.createObjectPropertyWithIndividual(courseContextIndividual, competenceIndividual, CompObjectProperties.CourseContextOf);
      handleCompulsoryLink(compulsoryBoolean, competenceIndividual, courseContextIndividual, compOntologyManager);
    }
  }

  def handleCompulsoryLink(compulsory: Boolean, competenceIndividual: Individual, courseContextIndividual: Individual, compOntologyManager: CompOntologyManager) {
    if (compulsory) {
      compOntologyManager.getUtil().createObjectPropertyWithIndividual(courseContextIndividual, competenceIndividual, CompObjectProperties.CompulsoryOf);
    } else {
      compOntologyManager.getUtil().deleteObjectPropertyWithIndividual(courseContextIndividual, competenceIndividual, CompObjectProperties.CompulsoryOf);
    }
  }

}