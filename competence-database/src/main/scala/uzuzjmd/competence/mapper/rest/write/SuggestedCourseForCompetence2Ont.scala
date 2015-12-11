package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.persistence.abstractlayer.ObjectPropertyCED
import uzuzjmd.competence.persistence.dao.{EvidenceActivity, Competence, CourseContext}
import uzuzjmd.competence.persistence.ontology.CompObjectProperties

/**
  * Created by dehne on 03.12.2015.
  */
object SuggestedCourseForCompetence2Ont extends ObjectPropertyCED[CourseContext,Competence] {

  override def setEdge : CompObjectProperties = {
    return CompObjectProperties.SuggestedCourseForCompetence
  }

  override def setDomain(): java.lang.Class[CourseContext] = {
    return classOf[CourseContext]
  }

  override def setRange(): java.lang.Class[Competence] = {
    return classOf[Competence]
  }

}
