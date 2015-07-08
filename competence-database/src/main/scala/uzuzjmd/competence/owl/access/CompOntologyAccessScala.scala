package uzuzjmd.competence.owl.access

import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.User
import uzuzjmd.competence.owl.dao.Competence

object CompOntologyAccessScala {

  def getDefinitionString(subclass: com.hp.hpl.jena.ontology.OntClass, ontologyManager: CompOntologyManager): String = {
    if (getPropertyString(subclass, "definition", ontologyManager) != null) {
      return (getPropertyString(subclass, "definition", ontologyManager).toString().replaceAll("[\n\r]", "")).replaceAll("[\n]", "")
    } else {
      return ""
    }
  }
  private def getPropertyString(subclass: com.hp.hpl.jena.ontology.OntClass, propertyName: String, ontologyManager: CompOntologyManager): Object = {
    val iProperty = ontologyManager.getM.getOntProperty(MagicStrings.PREFIX + propertyName)
    val value = subclass.getPropertyValue(iProperty)
    if (value == null) { return null }
    else { return value.asNode().getLiteralValue() }
  }

  def convertMoodleIdToName(name: String): String = {
    if (name.startsWith("0") || name.startsWith("1") || name.startsWith("2") ||
      name.startsWith("3") || name.startsWith("4") ||
      name.startsWith("5") || name.startsWith("6") ||
      name.startsWith("7")
      || name.startsWith("8") || name.startsWith("9")) {
      return "n" + name
    } else {
      return name;
    }
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

  def createIdentifierForAssessment(user: User, competence: Competence): String = {

    return user.getName + competence.getId
  }
}