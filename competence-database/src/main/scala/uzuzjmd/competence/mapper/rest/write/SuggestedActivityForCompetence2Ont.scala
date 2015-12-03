package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.owl.access.ObjectPropertyCED
import uzuzjmd.competence.owl.dao.{Competence, EvidenceActivity}
import uzuzjmd.competence.owl.ontology.CompObjectProperties

/**
  * Created by dehne on 03.12.2015.
  */
object SuggestedActivityForCompetence2Ont extends ObjectPropertyCED[EvidenceActivity, Competence]{
    override def setEdge() : CompObjectProperties = {
      return CompObjectProperties.SuggestedActivityForCompetence
    }

    override def setDomain(): java.lang.Class[EvidenceActivity] = {
        return classOf[EvidenceActivity]
    }

    override def setRange(): java.lang.Class[Competence] = {
        return classOf[Competence]
    }

    def convertWrite(activity : String, course : String, competence: String): Unit = {
        write(activity,competence)
        SuggestedCourseForCompetence2Ont.write(course, competence)
    }

    def convertDelete(activity : String, course : String, competence: String): Unit = {
        delete(activity,competence)
    }
}
