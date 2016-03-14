package comparison.verification

import edu.stanford.nlp.trees.GrammaticalRelation
import uzuzjmd.competence.comparison.analysis.{SentenceToNoun, SentenceToOperator}

/**
  * Created by dehne on 14.03.2016.
  */
class SimpleCompetenceVerifier extends CompetenceVerifier {
  private var sentence: String = null
  private var language: GrammaticalRelation.Language = null

  def this(sentence: String, language: GrammaticalRelation.Language) {
    this()
    this.sentence = sentence
    this.language = language
  }

  def isCompetence(sentence: String, language: GrammaticalRelation.Language): Boolean = {
    this.sentence = sentence
    this.language = language
    return isCompetence
  }

  def isCompetence: Boolean = {
    return satisfiesCorrectSubjectResctriction && satisfiesCorrectVerbRestrction
  }



  /**
    * checks for valid operators
    */
  def satisfiesCorrectVerbRestrction: Boolean = {
    val g = SentenceToOperator.convertSentenceToFilteredElement _
    val set: Array[String] = ValidOperators.values().map(x=>x.toString);
    return checkIfMatchesSet(g, set)
  }

  /**
    * checks if the subject is correct
    *
    * @return
    */
  def satisfiesCorrectSubjectResctriction: Boolean = {
    val f = SentenceToNoun.convertSentenceToFilteredElement _
    val set = ValidSubjects.values().map(x=>x.toString)
    return checkIfMatchesSet(f, set)
  }

  def checkIfMatchesSet(f: (String) => List[String], set: Array[String]): Boolean = {
    val noun: List[String] = f(sentence);
    if (noun.size > 1) return false;
    return set.contains(noun.head)
  }
}