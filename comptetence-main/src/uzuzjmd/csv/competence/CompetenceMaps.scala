package uzuzjmd.csv.competence

import scala.collection.mutable.Buffer
import net.htmlparser.jericho;
import net.htmlparser.jericho.Segment
import net.htmlparser.jericho.Source
import net.htmlparser.jericho.Renderer


object CompetenceMaps {
  def comptenceBeansToFilteredCSVCompetences(competenceBeans: CompetenceBean): FilteredCSVCompetence = {
    return new FilteredCSVCompetence(
        competenceBeans.getCompetence(),
        competenceBeans.getCatchword().split(",").toList,
        competenceBeans.getOperator(),
        competenceBeans.getMetaoperator(),
        competenceBeans.getEvidenzen(),
        competenceBeans.getCompetenceArea().split("##").toList)
  }

  def cleanCatchwords(catchword: String): String = {
    return catchword.replace("", " ");
  }
  
   def cleanOperator(catchword: String): String = {
    return catchword.split(",").head.replaceAll(" ", "");
  }
   
   def cleanHTML(htmlText:String) : String = {
     val htmlSource = new Source(htmlText);
     val htmlSeg = new Segment(htmlSource, 0, htmlText.length());
     val htmlRend = new Renderer(htmlSeg);
     return htmlRend.toString();
   } 

}	

//map(comptenzgefiltered => comptenzgefiltered.catchwordsFiltered.filter(catchword => !catchword.trim().equals("")).