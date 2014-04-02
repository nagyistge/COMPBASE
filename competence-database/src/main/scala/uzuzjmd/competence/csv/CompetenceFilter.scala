package uzuzjmd.competence.csv

object CompetenceFilter {
	def catchwordString (catchwordString : String) : Boolean = {
	  return  !catchwordString.trim().equals("") && !catchwordString.equals("Schlagwort")
	}	
}