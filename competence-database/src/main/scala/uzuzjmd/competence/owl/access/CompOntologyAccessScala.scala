package uzuzjmd.competence.owl.access

import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.User
import uzuzjmd.competence.owl.dao.exceptions.NoCompetenceInDBException
import uzuzjmd.competence.owl.dao.exceptions.DataFieldNotInitializedException
import uzuzjmd.competence.owl.dao.EvidenceActivity

object CompOntologyAccessScala {

  def getDefinitionString(subclass: com.hp.hpl.jena.ontology.OntClass, ontologyManager: CompOntologyManager): String = {
    if (getPropertyString(subclass, "definition", ontologyManager) != null) {
      return (getPropertyString(subclass, "definition", ontologyManager).toString().replaceAll("[\n\r]", "")).replaceAll("[\n]", "")
    } else {
      return ""
    }
  }
  @throws[NoCompetenceInDBException]
  @throws[DataFieldNotInitializedException]
  private def getPropertyString(subclass: com.hp.hpl.jena.ontology.OntClass, propertyName: String, ontologyManager: CompOntologyManager): Object = {
    val iProperty = ontologyManager.getM.getOntProperty(MagicStrings.PREFIX + propertyName)
    if (subclass == null) {
      throw new NoCompetenceInDBException
    }
    val value = subclass.getPropertyValue(iProperty)
    if (value == null) {
      //      throw new DataFieldNotInitializedException
      val result = subclass.getLocalName
      return result
    } else if (value.toString().equals("http://www.w3.org/2002/07/owl#Thing")) {
      return null
    } else { return value.asNode().getLiteralValue() }
  }

  def createIdentifierForSelectedTemplate(user: User, course: CourseContext, identifier: String): String = {
    if (identifier != null) {
      return identifier
    }
    if (user.exists && course.exists) {
      return user.getName + course.getId
    } else {
      user.persist
      course.persist
      return user.getName + course.getId
    }
  }

  def computeEncodedStringForLink(identifier2: String, competence: Competence, evidence: EvidenceActivity): String = {
    if (identifier2 != null) {
      return identifier2
    } else {
      return competence.identifier + evidence.identifier
    }
  }

  def createIdentifierForAssessment(user: User, competence: Competence): String = {

    return user.getName + competence.getId
  }
}