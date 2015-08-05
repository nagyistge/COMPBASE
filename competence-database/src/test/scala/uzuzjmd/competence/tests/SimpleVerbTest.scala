package uzuzjmd.competence.tests

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.junit.JUnitRunner
import scala.collection.JavaConverters._
import uzuzjmd.competence.comparison.analysis.SentenceToVerb
import org.tartarus.snowball.SnowballStemmer
import uzuzjmd.competence.comparison.analysis.SentenceToNoun
import uzuzjmd.competence.comparison.synonyms.OpenThesaurusSynonymCreator

@RunWith(classOf[JUnitRunner])
class SimpleVerbTest extends FunSuite with ShouldMatchers {

  test("given a sentence, the verb should be returned") {
    val input = "Die Lehramtsstudenten der alten Schule sind in der Lage komplexe Texte zu verstehen und gehen gerne baden ."
    SentenceToVerb.convertSentenceToFilteredElement(input) should not be ('empty)
    //print(SentenceToVerb.convertSentenceToVerbs(input))
  }
  
   test("given a sentence, the subject should be returned") {
    val input = "Die Lehramtsstudenten der alten Schule sind in der Lage komplexe Texte zu verstehen und gehen gerne baden ."
    SentenceToNoun.convertSentenceToFilteredElement(input) should not be ('empty)
    print(SentenceToNoun.convertSentenceToFilteredElement(input))
  }


  test("given a word, the stemmed version should be returned") {
    
    val result = stemWord("verstehen")
    val result2 = stemWord("verstehst")    
          
    (result == result2) should not be false
  }

  def stemWord(input: String): String = {

    val stemClass = Class.forName("org.tartarus.snowball.ext." + "german" + "Stemmer");
    val stemmer = stemClass.newInstance().asInstanceOf[SnowballStemmer];
    stemmer.setCurrent(input);
    stemmer.stem();
    val result = stemmer.getCurrent();
    return result
  }
  
  test ("given a word, all synonyms should be returned") {
    val result = OpenThesaurusSynonymCreator.getSynonyms("verstehen");
    result.contains("begreifen") should not be false
  }
  
  test ("given a word, similar words should be returned") {
    val result = OpenThesaurusSynonymCreator.getSimilarWords("Atom");
    result.contains("Atomsorte") should not be false
  }
  

}