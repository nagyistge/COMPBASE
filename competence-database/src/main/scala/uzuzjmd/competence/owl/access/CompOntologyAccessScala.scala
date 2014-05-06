package uzuzjmd.competence.owl.access

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
}