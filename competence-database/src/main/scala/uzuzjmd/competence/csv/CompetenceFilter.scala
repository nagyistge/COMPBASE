package uzuzjmd.competence.csv

object CompetenceFilter {
  /**
   * Mit dieser Methode kann man mit Scala leere Schlagworte filtern.
   * Außerdem wird die erste Zeile der Exceltabelle ignoriert
   */
  def catchwordString(catchwordString: String): Boolean = {
    return !catchwordString.equals("") && !catchwordString.trim().equals("") && !catchwordString.equals("Schlagwort")
  }
}