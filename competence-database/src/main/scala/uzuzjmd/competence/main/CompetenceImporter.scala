package uzuzjmd.competence.main

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy
import au.com.bytecode.opencsv.bean.CsvToBean
import java.io.FileReader
import java.io.File
import scala.collection.JavaConverters._
import uzuzjmd.competence.mapper.rcd.CSV2RCD
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.mapper.rcd.RCD2OWL
import uzuzjmd.competence.csv.CompetenceBean
import scala.collection.mutable.Buffer
import uzuzjmd.competence.csv.CompetenceMaps
import uzuzjmd.competence.rcd.generated.Rdceo
import uzuzjmd.competence.csv.CompetenceFilter

object CompetenceImporter {

  def main(args: Array[String]) {
    convertCSVArray(args)
  }

  def convertCSV(pathToCSV: String) {
    convertCSVArray((pathToCSV :: Nil).toArray)
  }

  private def convertCSVArray(args: Array[String]) {
    val rcdeoCompetences = getCompetencesFromCSV(args)
    val compOntManager = new CompOntologyManager
    compOntManager.createBaseOntology()
    val result = RCD2OWL.convert(rcdeoCompetences, compOntManager)
  }

  def getCompetencesFromCSV(args: Array[String]): Seq[Rdceo] = {
    return getCompetencesFromCSV(args.head);
  }

  def getCompetencesFromCSVJava(csvLocation: String): Array[Rdceo] = {
    return getCompetencesFromCSV(csvLocation).toArray;
  }

  def getCompetencesFromCSV(csvLocation: String): Seq[Rdceo] = {
    // using csv library to map csv to java
    val strat = new ColumnPositionMappingStrategy[CompetenceBean];
    strat.setType(classOf[CompetenceBean])
    val columns = "operator" :: "catchword" :: "competence" :: "similarCompetence" :: "superCompetence" :: "reference" :: "evidenzen" :: "competenceArea" :: "metaoperator" :: Nil;
    strat.setColumnMapping(columns.toArray);
    val csv = new CsvToBean[CompetenceBean];
    val list = csv.parse(strat, new FileReader(new File(csvLocation)));

    //using scala maps to clean the entries
    val filteredList = list.asScala. //java list nach scala list
      map(CompetenceMaps.comptenceBeansToFilteredCSVCompetences).view. // erstellt neue Klasse    
      map(a => a.copy(catchwordsFiltered = a.catchwordsFiltered.filter(CompetenceFilter.catchwordString).map(CompetenceMaps.cleanCatchwords))). //filtered leere Catchwords und überschrift
      map(a => a.copy(operator = CompetenceMaps.cleanOperator(a.operator))). //bereinigt den Operator (es darf nur einen geben)
      map(a => a.copy(competence = CompetenceMaps.cleanHTML(a.competence))). //bereinigt HTML-Content aus der Kompetenzbeschreibung;
      map(a => a.copy(evidencen = CompetenceMaps.cleanHTML(a.evidencen)))

    //mapping CSVObjects to RCD
    val rcdeoCompetences = CSV2RCD.mapCompetence(filteredList)
    rcdeoCompetences
  }

}