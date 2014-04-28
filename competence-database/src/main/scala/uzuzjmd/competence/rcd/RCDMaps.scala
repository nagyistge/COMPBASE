package uzuzjmd.competence.rcd

import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.ontology.CompOntClass

object RCDMaps {
  type TripleToString = RCDFilter.CompetenceTriple => String
  type classToObjectProperty = CompOntClass => CompObjectProperties

  def convertTriplesToOperators(input: RCDFilter.CompetenceTriple): RCDFilter.CompetenceTriple = {
    val values = CompOntClass.values.map(x => x.name())
    val value = input._2
    val contains = values.contains(value)
    if (contains) {
      return (input._1, classToObjectProperty(CompOntClass.valueOf(input._2)).name(), input._3)
    } else {
      return input
    }
  }

  def classToObjectProperty(compOntClass: CompOntClass): CompObjectProperties = {
    compOntClass match {
      case CompOntClass.Catchword => return CompObjectProperties.CatchwordOf
      case CompOntClass.Competence => return CompObjectProperties.SubCompetenceOf
      case CompOntClass.CompetenceArea => return CompObjectProperties.BelongsToArea
      case CompOntClass.CompetenceSpec => return CompObjectProperties.SpecifiedBy
      case CompOntClass.CompetenceDescription => return CompObjectProperties.CompetenceDescriptionOf
      case CompOntClass.DescriptionElement => return CompObjectProperties.DescriptionElementOf
      case CompOntClass.Evidence => return CompObjectProperties.EvidencOf
      case CompOntClass.Learner => return CompObjectProperties.LearnerOf
      case CompOntClass.MetaCatchword => return CompObjectProperties.MetaCatchwordOf
      case CompOntClass.Operator => return CompObjectProperties.OperatorOf
      case CompOntClass.SubOperator => return CompObjectProperties.SubOperatorOf
      case CompOntClass.MetaOperator => return CompObjectProperties.MetaOperatorOf
      case CompOntClass.CourseContext => return CompObjectProperties.CourseContextOf
      case default => return null
    }
  }

  def objectPropertyToClass(objectProperty: CompObjectProperties): CompOntClass = {
    val classToObjectPropertyMap = CompOntClass.values.map(x => x -> classToObjectProperty(x))
    val objectToPropertyPairs = classToObjectPropertyMap.map(_.swap)
    val objectToPropertyMap = objectToPropertyPairs.toMap
    return objectToPropertyMap.get(objectProperty).head
  }

}