package uzuzjmd.csv.competence

import scala.collection.mutable.Buffer

object CompetenceMaps {
  def comptenceBeansToFilteredCSVCompetences(competenceBeans: CompetenceBean): FilteredCSVCompetence = {
    return new FilteredCSVCompetence(competenceBeans.getCompetence(), competenceBeans.getCatchword().split(",").toList, competenceBeans.getOperator())
  }

  def cleanCatchwords(catchword: String): String = {
    return catchword.replace("", " ");
  }

}	

//map(comptenzgefiltered => comptenzgefiltered.catchwordsFiltered.filter(catchword => !catchword.trim().equals("")).