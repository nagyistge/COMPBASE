package uzuzjmd.competence.mapper.rest

import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.service.rest.model.dto.CompetenceData
import uzuzjmd.competence.owl.access.CompOntologyManager
import scala.collection.JavaConverters._
import uzuzjmd.competence.owl.validation.CompetenceGraphValidator
import uzuzjmd.competence.owl.dao.Competence
import java.util.LinkedList
import uzuzjmd.competence.owl.validation.CompetenceGraphValidator
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.owl.dao.Catchword
import uzuzjmd.competence.owl.dao.Operator
import uzuzjmd.competence.owl.ontology.CompObjectProperties

/**
 * @author dehne
 */
object Competence2Ont extends TDBWriteTransactional[CompetenceData] {

  def convert(data: CompetenceData): String = {
    execute[String](addCompetence _, data)
  }

  def addCompetence(comp: CompOntologyManager, data: CompetenceData): String = {

    val addedCompetence = new Competence(comp, data.getForCompetence, data.getForCompetence, null);
    val superCompetencesTyped = new LinkedList[Competence]();
    for (competence <- data.getSuperCompetences.asScala) {
      val superCompetence = new Competence(comp, competence, competence, null);
      superCompetencesTyped.add(superCompetence);
    }
    val subCompetencesTyped = new LinkedList[Competence]();
    for (competence <- data.getSubCompetences.asScala) {
      val subCompetence = new Competence(comp, competence, competence, null);
      subCompetencesTyped.add(subCompetence);
    }

    val competenceGraphValidator = new CompetenceGraphValidator(comp, addedCompetence, superCompetencesTyped, subCompetencesTyped);

    if (competenceGraphValidator.isValid()) {
      addedCompetence.persist(true);
      for (catchwordItem <- data.getCatchwords.asScala) {
        val catchword = new Catchword(comp, catchwordItem, catchwordItem);
        catchword.persist(true);
        catchword.createEdgeWith(CompObjectProperties.CatchwordOf, addedCompetence);

      }
      if (data.getLearningProjectName != null) {
        val learningProjectTemplate = new LearningProjectTemplate(comp, data.getLearningProjectName, null, null);
        addedCompetence.addLearningTemplate(learningProjectTemplate);
      }

      val operatorDAO = new Operator(comp, data.getOperator, data.getOperator);
      operatorDAO.persist(true);
      operatorDAO.createEdgeWith(CompObjectProperties.OperatorOf, addedCompetence);
      for (subCompetence <- subCompetencesTyped.asScala) {
        subCompetence.addSuperCompetence(addedCompetence);
      }

      for (superCompetence <- superCompetencesTyped.asScala) {
        addedCompetence.addSuperCompetence(superCompetence);
      }

    }
    val resultMessage = competenceGraphValidator.getExplanationPath();

    return resultMessage;
  }

}