package uzuzjmd.competence.main

import java.io.IOException
import java.util
import java.util.Arrays
import comparison.verification.{SimpleCompetenceVerifier, ValidOperators}
import org.apache.solr.client.solrj.SolrServerException
import org.apache.solr.client.solrj.response.QueryResponse
import org.apache.solr.common.{SolrDocument, SolrDocumentList}
import uzuzjmd.competence.comparison.analysis.SentenceToOperator
import uzuzjmd.competence.crawler.solr.SolrConnector
import uzuzjmd.competence.logging.Logging
import uzuzjmd.competence.util.LanguageConverter
import scala.collection.JavaConverters._
import scala.collection.mutable


/**
  * Created by dehne on 16.03.2016.
  */
object CompetenceCrawler extends LanguageConverter with Logging {
  private val solrUrl: String = "http://learnlib.soft.cs.uni-potsdam.de:80/solr/test2"
  private val blockingQueue: mutable.Set[String] = mutable.Set.empty[String]
  private var counter: Int = 0

  @throws(classOf[IOException])
  @throws(classOf[SolrServerException])
  def main(args: Array[String]) {
    val simpleCompetenceVerifier: SimpleCompetenceVerifier = new SimpleCompetenceVerifier
    val solrConnector: SolrConnector = new SolrConnector(solrUrl, 20000000)
    // ++ ValidSubjects.values().map(x=>x.toString)
    val inputList: Array[String] = ValidOperators.values().map(x => x.toString)
    val inputListFolded = inputList.reduce((a, b) => a + "\" OR \"" + b)
    val result: QueryResponse = solrConnector.connectToSolr(inputListFolded)
    val documents: SolrDocumentList = result.getResults

    val documentIterator = documents.listIterator();
    while (documentIterator.hasNext) {
      val document = documentIterator.next()
      val content: String = document.getFieldValue("content").asInstanceOf[String]
      val sentences: mutable.Buffer[String] = Arrays.asList(content.split("\\.")).asScala.flatten
      sentences.foreach(x => blockingQueue.add(x))
    }
    blockingQueue.foreach(verifySentence(simpleCompetenceVerifier,_))
    logger.info("found " + counter + " competencies!")
  }

  @throws[IndexOutOfBoundsException]
  def verifySentence(simpleCompetenceVerifier: SimpleCompetenceVerifier, strings: String): Unit = {
    if(strings == null || strings.equals("") || strings.size < 20) {
      return;
    }
    val verbs = SentenceToOperator.convertSentenceToFilteredElement(strings)
    if (!verbs.isEmpty) {
      val operator: String = verbs.head
      logger.info("verb is: " + operator)
    }

    val nouns = SentenceToOperator.convertSentenceToFilteredElement(strings)
    if (!nouns.isEmpty) {
      val noun: String = nouns.head
      logger.info("noun is: " + noun)
    }

    if (!verbs.isEmpty && !nouns.isEmpty) {
      if (simpleCompetenceVerifier.isCompetence(strings, edu.stanford.nlp.trees.GrammaticalRelation.Language.Any)) {
        counter += 1
        logger.info(strings)
      }
    }
  }
}