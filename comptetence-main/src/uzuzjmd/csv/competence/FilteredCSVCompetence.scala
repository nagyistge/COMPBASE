package uzuzjmd.csv.competence

import scala.collection.immutable.List

case class FilteredCSVCompetence (val competence : String, val catchwordsFiltered : List[String], val operator : String, val metaoperator : String, val evidencen : String, val metacatchwords : List[String]) {
  
}