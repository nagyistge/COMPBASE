package uzuzjmd.csv.competence

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy
import au.com.bytecode.opencsv.bean.CsvToBean
import java.io.FileReader
import java.io.File
import scala.collection.JavaConverters._
import uzuzjmd.rcd.mapper.CSV2RCD
import uzuzjmd.rcd.generated.Statement
import uzuzjmd.rcd.generated.Definition
import uzuzjmd.rcd.generated.Rdceo
import uzuzjmd.console.util.ConsoleOut
import uzuzjmd.rcd.mapper.RCD2OWL
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy
import uzuzjmd.owl.util.CompOntologyManager
import uzuzjmd.owl.util.CompFileUtil

object CompetenceImporter {

  def main(args: Array[String]) {
    // using csv library to map csv to java
    val strat = new ColumnPositionMappingStrategy[CompetenceBean];
    strat.setType(classOf[CompetenceBean])
    val columns = "operator" :: "catchword" :: "competence" :: "similarCompetence" :: "superCompetence" :: "reference" :: "evidenzen" :: "competenceArea" :: "metaoperator" :: Nil;
    strat.setColumnMapping(columns.toArray);
    val csv = new CsvToBean[CompetenceBean];
    val list = csv.parse(strat, new FileReader(new File(args.head)));

    //using scala maps to clean the entries
    val filteredList = list.asScala. //java list nach scala list
      map(CompetenceMaps.comptenceBeansToFilteredCSVCompetences). // erstellt neue Klasse    
      map(a => a.copy(catchwordsFiltered = a.catchwordsFiltered.filter(CompetenceFilter.catchwordString).map(CompetenceMaps.cleanCatchwords))). //filtered leere Catchwords und Überschrift
      map(a => a.copy(operator = CompetenceMaps.cleanOperator(a.operator))). //bereinigt den Operator (es darf nur einen geben)
      map(a => a.copy(competence = CompetenceMaps.cleanHTML(a.competence))). //bereinigt HTML-Content aus der Kompetenzbeschreibung;
      map(a => a.copy(evidencen = CompetenceMaps.cleanHTML(a.evidencen)))

    //mapping CSVObjects to RCD
    val rcdeoCompetences = CSV2RCD.mapCompetence(filteredList)

    //debuggingOutput
    //ConsoleOut.printFilteredComptences(filteredList)
    //ConsoleOut.printRcdeoCompetences(rcdeoCompetences)
    val compOntManager = new CompOntologyManager
    compOntManager.createBaseOntology()
    val result = RCD2OWL.convert(rcdeoCompetences, compOntManager)

    // for debugging
    val compFileUtil = new CompFileUtil(compOntManager.getM());
    compFileUtil.writeOntologyout(compOntManager.getM());

  }

}