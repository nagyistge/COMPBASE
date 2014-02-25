package uzuzjmd.csv.competence

object CompetenceFilter {
	def catchwordString (catchwordString : String) : Boolean = {
	  return  !catchwordString.trim().equals("") && !catchwordString.equals("Schlagwort")
	}	
}