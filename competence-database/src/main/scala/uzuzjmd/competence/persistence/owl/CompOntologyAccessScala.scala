package uzuzjmd.competence.persistence.owl

import com.hp.hpl.jena.ontology.{Individual, OntClass}
import com.hp.hpl.jena.rdf.model.{Property, Statement}
import uzuzjmd.competence.config.MagicStrings
import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.exceptions._
import uzuzjmd.competence.persistence.dao.{Competence, EvidenceActivity, User}

object CompOntologyAccessScala {
  def encode( string: String): String = {
    var strungX = string

    if (strungX.startsWith(MagicStrings.PREFIX) || (strungX == "")) {
      throw new Error("OntClass should not start with Prefix or empty string")
    }
    if (strungX.matches("^[0-9].*")) {
      strungX = "n" + strungX
    }
    strungX = strungX.trim.replaceAll("[^a-zA-ZäöüÄÖÜß1-9]", "_").replaceAll("[\u0000-\u001f]", "").replaceAll("\\.", "__").replaceAll("[\n\r]", "").replaceAll("[\n]", "")
    return (MagicStrings.PREFIX + strungX).replaceAll("_", "")
  }

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

  def createIdentifierForSelectedTemplate(userId: String, course: String, identifier: String): String = {
    return userId + course
  }

  def computeEncodedStringForLink(identifier2: String, competence: Competence, evidence: EvidenceActivity): String = {
    if (identifier2 != null) {
      return identifier2
    } else {
      return competence.identifier + evidence.identifier
    }
  }

  def createIdentifierForAssessment(user: User, competence: Competence): String = {
    return user.name + competence.identifier
  }

  @throws[OntClassForDaoNotInitializedException]
  @throws[DataFieldNotInitializedException]
  @throws[IndividualNotFoundException]
  def getPropertyPair(key: String, comp: CompOntologyManager, individual: Individual): (Property, Statement) = {
    val keyEncoded = CompOntologyAccessScala.encode(key)
    val literal = comp.getM().createProperty(keyEncoded);

    if (individual == null) {
      throw new IndividualNotFoundException
    }
    val prop: Statement = individual.getProperty(literal);
    if (prop == null) {
      throw new DataFieldNotInitializedException
    }
    return (literal, prop)
  }

  def getDataFieldBoolean(key: String, comp: CompOntologyManager, individual: Individual): java.lang.Boolean = {
    var tmpResult: (Property, Statement) = null
    try {
      tmpResult = getPropertyPair(key, comp, individual)
      return tmpResult._2.getBoolean();
    } catch {
      case e: DataFieldNotInitializedException => return false
    }

  }

  def getDataFieldLong(key: String, comp: CompOntologyManager, individual: Individual): java.lang.Long = {
    val tmpResult = getPropertyPair(key, comp, individual)
    if (tmpResult._2 == null) {
      return null;
    }
    return tmpResult._2.getLong();
  }

  def getDataFieldInt(key: String, comp: CompOntologyManager, individual: Individual): java.lang.Integer = {
    val tmpResult = getPropertyPair(key, comp, individual)
    if (tmpResult._2 == null) {
      return null;
    }
    return tmpResult._2.getInt();
  }

  def deleteDataField(key: String, comp: CompOntologyManager, individual: Individual) = {
      val tmpResult = getPropertyPair(key, comp, individual)
      if (tmpResult._2 != null) {
        comp.getM().remove(tmpResult._2);
      }
  }

  @throws[OntClassForDaoNotInitializedException]
  def getDataField( key: String, comp: CompOntologyManager, individual: Individual): String = {
    val tmpResult = getPropertyPair(key, comp, individual)
    val triple = tmpResult._2.asTriple()
    return triple.getObject().getLiteralLexicalForm();
  }

  @throws[OntClassForDaoNotInitializedException]
  def getDataFieldForClass(key: String, comp: CompOntologyManager, ontClass: OntClass): String = {
    val tmpResult = getPropertyPairForClass(key, comp, ontClass)
    val triple = tmpResult._2.asTriple()
    return triple.getObject().getLiteralLexicalForm();
  }

  @throws[OntClassForDaoNotInitializedException]
  @throws[DataFieldNotInitializedException]
  @throws[DefinitionNotInitalizedException]
  def getPropertyPairForClass(key: String, comp:CompOntologyManager, ontClass: OntClass): (Property, Statement) = {
    val literal = comp.getM().createProperty(CompOntologyAccessScala.encode(key));
    if (ontClass == null) {
      throw new OntClassForDaoNotInitializedException
    }
    val prop: Statement = ontClass.getProperty(literal);
    if (key.equals("definition") && prop == null) {
      throw new DefinitionNotInitalizedException
    }
    if (prop == null) {
      throw new DataFieldNotInitializedException
    }
    return (literal, prop)
  }




}