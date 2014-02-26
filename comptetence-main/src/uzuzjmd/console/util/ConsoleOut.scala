package uzuzjmd.console.util

import uzuzjmd.csv.competence.FilteredCSVCompetence
import uzuzjmd.rcd.generated.Rdceo
import uzuzjmd.rcd.generated.Definition
import uzuzjmd.rcd.generated.Statement
import scala.collection.JavaConverters._
import scala.collection.mutable.Buffer
import java.util.ArrayList

object ConsoleOut {
  def printFilteredComptences(filteredList: Buffer[FilteredCSVCompetence]) {
    filteredList.foreach(a => println("\n\n" + a + "\ncatchwords: \n" + ("" :: a.catchwordsFiltered).reduce((b, c) => b + "," + c).replaceFirst(",", "")))
  }
  
  def printRcdeoCompetences(rcdeoCompetences: ArrayList[Rdceo]) {
	  printRcdeoCompetences(rcdeoCompetences.asScala)
  }

  def printRcdeoCompetences(rcdeoCompetences: Buffer[Rdceo]) {
    rcdeoCompetences.foreach(
      x => println("\n\n\n" + "Kompetenz " +
        x.getTitle().getLangstring().asScala.head.getValue() +
        " \nKompetenzBody: \n" +
        x.getDefinition().asScala.map(a => definitionToString(a)).+=("").reduce((a, b) => a + b)))
  }

  def definitionToString(definition: Definition): String = {
    return definition.getStatement().asScala.map(a => statementToString(a)).reduce((a, b) => a + b)
  }

  def statementToString(statement: Statement): String = {
    return "Name: " + statement.getStatementname() + ", " +
      "Text: " + statement.getStatementtext().getLangstring().asScala.head.getValue() + "\n";
  }
}