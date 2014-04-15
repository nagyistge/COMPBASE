package uzuzjmd.competence.mapper.rcd

import org.apache.log4j.Level
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import scala.collection.mutable.Buffer
import scala.collection.JavaConverters._
import edu.uci.ics.jung.io.graphml.Metadata
import com.hp.hpl.jena.ontology.OntModel
import com.hp.hpl.jena.ontology.ObjectProperty
import com.hp.hpl.jena.ontology.OntClass
import com.hp.hpl.jena.ontology.Individual
import com.hp.hpl.jena.util.iterator.Filter
import uzuzjmd.competence.rcd.RCDFilter
import uzuzjmd.competence.rcd.generated.Langstring
import uzuzjmd.competence.rcd.generated.Title
import uzuzjmd.competence.rcd.generated.Statementtext
import uzuzjmd.competence.rcd.generated.Rdceo
import java.util.ArrayList

trait RCDImplicits {
  /**
   * A bunch of useful toString - Methoden, um die Arbeit mit RCDEOs zu vereinfachen
   * Wichtige Annahme: Die formell als Liste definierte Langstring-Liste besteht immer nur aus einem Element
   * Wenn dies anders verwendet werden soll, gelten diese implicits nicht mehr
   *
   */

  type rcdeoView = scala.collection.SeqView[uzuzjmd.competence.rcd.generated.Rdceo, scala.collection.mutable.Buffer[uzuzjmd.competence.rcd.generated.Rdceo]]

  implicit def arrayToBuffer(buffer: Array[Rdceo]): rcdeoView = {
    return buffer.toBuffer.view;
  }

  //  protected implicit def arrayToBuffer(buffer: ArrayList[Rdceo]): Buffer[Rdceo] = {
  //    return buffer.toBuffer;
  //  }

  implicit def amyString1(x: Langstring): String = {
    return x.getValue()
  }
  implicit def amyString2(x: Buffer[Langstring]): String = {
    return amyString1(x.head)
  }

  implicit def toMyString3(x: Title): String = {
    return x.getLangstring().asScala.head.getValue()
  }

  implicit def toMyString4(x: Statementtext): String = {
    return x.getLangstring().asScala.head.getValue()
  }

  implicit def toMyString5(x: (Title, String, Statementtext)): (RCDFilter.CompetenceTriple) = {
    return (toMyString3(x._1), x._2, toMyString4(x._3))
  }

  implicit def toMyString6(x: Buffer[(Title, String, Statementtext)]): Buffer[RCDFilter.CompetenceTriple] = {
    return x.map(x => toMyString5(x))
  }
}