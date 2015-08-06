package uzuzjmd.competence.comparison.simple.mapper



import uzuzjmd.competence.comparison.analysis.SentenceToOperator
import uzuzjmd.competence.comparison.synonyms.OpenThesaurusSynonymCreator
import uzuzjmd.competence.comparison.simple.SimilarExplanations
import uzuzjmd.competence.comparison.simple.SimpleCompetenceComparator
import uzuzjmd.competence.comparison.analysis.WordToStem

/**
 * @author dehne
 */
class SimpleCompetenceComparatorMapper()  {
  
  var strat1Explanation = SimilarExplanations.NONE
  var strat2Explanation = SimilarExplanations.NONE    
  
  def isSimilarStrings(input1: String, input2: String) : Boolean = {
    return isSimilarVerbsStrat1(input1, input2)
  }
  
   
  
  def isSimilarVerbsStrat2(input1: String, input2: String): Boolean = {
    val result = SentenceToOperator.convertSentenceToFilteredElement(input1);
    val result2 = SentenceToOperator.convertSentenceToFilteredElement(input2);
    
    val stemmedResult = result.mapConserve(WordToStem.stemWord)
    val stemmedResult2 = result2.mapConserve(WordToStem.stemWord)
    
    //check if same operator
    if (!stemmedResult.intersect(stemmedResult2).isEmpty) {
        strat2Explanation = SimilarExplanations.VERBSEQUAL
        return true
    }
    
    // check similarWords
    val similarStemmedResult = stemmedResult.map(OpenThesaurusSynonymCreator.getSimilarWords).flatten
    val similarStemmedResult2 = stemmedResult2.map(OpenThesaurusSynonymCreator.getSimilarWords).flatten
    
    if (!similarStemmedResult.intersect(similarStemmedResult2).isEmpty) {
       strat2Explanation = SimilarExplanations.VERBSSQLLIKE
       return true
    }
    
    // check similarWordsInCase of stemming and similars
    val similarStemmedResultSyonomys = similarStemmedResult.map(OpenThesaurusSynonymCreator.getSynonyms).flatten
    val similarStemmedResultSyonomys2 = similarStemmedResult2.map(OpenThesaurusSynonymCreator.getSynonyms).flatten
    if (!similarStemmedResultSyonomys.intersect(similarStemmedResultSyonomys2).isEmpty) {
      strat2Explanation = SimilarExplanations.VERBSSYNONYMS
      return true
    }
    
    return false
  }
  
//  def isSimilarVerbsStrat2_Words(input1: String, input2: String): Boolean = {
//    
//  }
  
  /**
   * Simple Comparison without stemming 
   */
  def isSimilarVerbsStrat1(input1: String, input2: String): Boolean = {
    val result = SentenceToOperator.convertSentenceToFilteredElement(input1);
    val result2 = SentenceToOperator.convertSentenceToFilteredElement(input2);
    
    // check if same operator
    if (!result.intersect(result2).isEmpty) {
      strat1Explanation = SimilarExplanations.VERBSEQUAL
      return true
    }
        
    // check if sql like between operators or similar words
    val similarWords = result.map(OpenThesaurusSynonymCreator.getSimilarWords).flatten
    val similarWords2 = result2.map(OpenThesaurusSynonymCreator.getSimilarWords).flatten
    
    if (!similarWords.intersect(similarWords2).isEmpty) {
       strat1Explanation = SimilarExplanations.VERBSSQLLIKE
       return true
    }
    
    // check if synonyms
    val synonyms = result.map(OpenThesaurusSynonymCreator.getSynonyms).flatten
    val synonyms2 = result2.map(OpenThesaurusSynonymCreator.getSynonyms).flatten
    
    if (!synonyms.intersect(synonyms2).isEmpty) {
      strat1Explanation = SimilarExplanations.VERBSSYNONYMS
      return true
    }
    
    return false
  }
}