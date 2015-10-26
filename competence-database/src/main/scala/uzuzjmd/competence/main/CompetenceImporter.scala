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
import uzuzjmd.competence.owl.access.CompFileUtil
import uzuzjmd.competence.owl.access.MagicStrings
import uzuzjmd.competence.owl.access.TDBWriteTransactional

object CompetenceImporter extends TDBWriteTransactional[Seq[uzuzjmd.competence.rcd.generated.Rdceo]] {

  def main(args: Array[String]) {
    convert()
    EposImporter.convert()
  }

  def convert() {
    val rcdeoCompetences = getCompetencesFromCSV()
    val compOntManager = new CompOntologyManager
    compOntManager.createBaseOntology()
    execute(RCD2OWL.convert _, rcdeoCompetences) // ensures transaction context
  }

  def getCompetencesFromCSVasJava(): java.util.List[Rdceo] = {
    return getCompetencesFromCSV.asJava
  }

  def getCompetencesFromCSV(): Seq[Rdceo] = {
    val list = parseCompetenceBeans()
    return competenceBeansToRDCEO(list)
  }

  def parseCompetenceBeans(): java.util.List[CompetenceBean] = {
    // using csv library to map csv to java
    val strat = new ColumnPositionMappingStrategy[CompetenceBean];
    strat.setType(classOf[CompetenceBean])
    val columns = "operator" :: "catchword" :: "competence" :: "similarCompetence" :: "superCompetence" :: "reference" :: "evidenzen" :: "competenceArea" :: "metaoperator" :: Nil;
    strat.setColumnMapping(columns.toArray);
    val csv = new CsvToBean[CompetenceBean];
    val list = csv.parse(strat, new FileReader(new File(MagicStrings.CSVLOCATION)));
    return list
  }

  def competenceBeansToRDCEO(list: java.util.List[CompetenceBean]): Seq[Rdceo] = {

    //using scala maps to clean the entries
    val filteredList = list.asScala. //java list nach scala list
      map(CompetenceMaps.comptenceBeansToFilteredCSVCompetences).view. // erstellt neue Klasse
      map(a => a.copy(catchwordsFiltered = a.catchwordsFiltered.filter(CompetenceFilter.catchwordString).map(CompetenceMaps.cleanCatchwords))). //filtered leere Catchwords und überschrift
      map(a => a.copy(operator = CompetenceMaps.cleanOperator(a.operator))). //bereinigt den Operator (es darf nur einen geben)
      map(a => a.copy(competence = CompetenceMaps.cleanHTML(a.competence))). //bereinigt HTML-Content aus der Kompetenzbeschreibung;
      map(a => a.copy(evidencen = CompetenceMaps.cleanHTML(a.evidencen))).filter(x => CompetenceFilter.operatorString(x.operator)).filter(x => !x.metaoperator.equals("Metaoperator")).filterNot(x => x.operator.trim().equals(""))

    //mapping CSVObjects to RCD
    val rcdeoCompetences = CSV2RCD.mapCompetence(filteredList)
    rcdeoCompetences
  }

  def competenceBeanToDatabase(list: Array[CompetenceBean]) {
    executeX[Array[CompetenceBean]](competenceBeanToDatabase_helper, list)
  }

  def competenceBeanToDatabase_helper(comp: CompOntologyManager, list: Array[CompetenceBean]) {
    val rdceos = competenceBeansToRDCEO(list.toList.asJava)
    val result = RCD2OWL.convert(comp, rdceos)
  }

}