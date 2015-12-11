package uzuzjmd.competence.comparison.analysis

import edu.stanford.nlp.parser.lexparser.LexicalizedParser
import edu.stanford.nlp.trees.PennTreebankLanguagePack
import uzuzjmd.competence.config.MagicStrings
import scala.collection.JavaConverters._
import edu.stanford.nlp.ling.Sentence
import edu.stanford.nlp.trees.Tree

/**
 * @author dehne
 */
object SentenceToOperator extends SentenceAnalyser {



  override def hitList = List("VVIZU", "VVINF", "VVFIN", "VVPP", "ADJD")



}