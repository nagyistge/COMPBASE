package uzuzjmd.csv.competence

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy
import au.com.bytecode.opencsv.bean.CsvToBean
import java.io.FileReader
import java.io.File
import scala.collection.JavaConverters._

object CompetenceImporter {

  def main(args: Array[String]) {
    val strat = new ColumnPositionMappingStrategy[CompetenceBean];
    strat.setType(classOf[CompetenceBean])
    val columns = "operator" :: "catchword" :: "competence" :: "similarCompetence" :: "superCompetence" :: "reference" :: "evidenzen" :: "competenceArea" :: "metaoperator" :: Nil;
    strat.setColumnMapping(columns.toArray);

    val csv = new CsvToBean[CompetenceBean];

    val list = csv.parse(strat, new FileReader(new File(args.head)));
    val filteredlist = list.asScala.map(a => new FilteredCSVCompetence(a.getCompetence(), a.getCatchword().split(",").toList, a.getOperator()));
    filteredlist.foreach(comptenzgefiltered => comptenzgefiltered.catchwordsFiltered.filter(catchword => !catchword.trim().equals("")).map(catchword=>catchword.replace(" ", "")).foreach(catchword => println(catchword)));

  }

}