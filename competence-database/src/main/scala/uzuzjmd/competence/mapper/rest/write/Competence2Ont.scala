package uzuzjmd.competence.mapper.rest.write

import java.util.LinkedList

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, WriteTransactional}
import uzuzjmd.competence.persistence.dao.{Catchword, Competence, LearningProjectTemplate, Operator}
import uzuzjmd.competence.persistence.ontology.CompObjectProperties
import uzuzjmd.competence.persistence.validation.CompetenceGraphValidator
import uzuzjmd.competence.service.rest.dto.CompetenceData

import scala.collection.JavaConverters.asScalaBufferConverter

/**
 * @author dehne
 */
object Competence2Ont extends WriteTransactional[CompetenceData] {

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

      //      val operatorDAO = new Operator(comp, data.getOperator, data.getOperator);
      //      operatorDAO.persist(true);
      //      operatorDAO.createEdgeWith(CompObjectProperties.OperatorOf, addedCompetence);

      if (data.getOperator != null) {
        val operatorDAO = new Operator(comp, data.getOperator, data.getOperator)
        operatorDAO.persist(true)
        addedCompetence.createEdgeWith(operatorDAO, CompObjectProperties.OperatorOf)
      }

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