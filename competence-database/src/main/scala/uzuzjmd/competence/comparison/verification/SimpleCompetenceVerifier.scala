package comparison.verification

import edu.stanford.nlp.trees.GrammaticalRelation
import edu.stanford.nlp.trees.GrammaticalRelation.Language
import uzuzjmd.competence.comparison.analysis.{SentenceToNoun, SentenceToOperator}
import uzuzjmd.competence.comparison.verification.{ValidSubjects, ValidOperators}

/**
  * Created by dehne on 14.03.2016.
  */
class SimpleCompetenceVerifier {
  private var language: Language = null
  private var sentence: String = null

  def this(sentence: String, language: Language) {
    this()
    this.sentence = sentence
    this.language = language
  }

  def isCompetence(sentence: String, language: GrammaticalRelation.Language): Boolean = {
    this.sentence = sentence
    this.language = language
    return isCompetence2
  }

  def isCompetence2: Boolean = {
    return satisfiesCorrectSubjectRestriction && satisfiesCorrectVerbRestriction
  }

  /**
    * checks for valid operators
    */
  def satisfiesCorrectVerbRestriction: Boolean = {
    val g = SentenceToOperator.convertSentenceToFilteredElement _
    val set: Array[String] = ValidOperators.values().map(x => x.toString);
    return checkIfMatchesSet(g, set)
  }

  /**
    * checks if the subject is correct
    *
    * @return
    */
  def satisfiesCorrectSubjectRestriction: Boolean = {
    val f = SentenceToNoun.convertSentenceToFilteredElement _
    val set = ValidSubjects.values().map(x => x.toString)
    return checkIfMatchesSet(f, set)
  }

  def checkIfMatchesSet(f: (String) => List[String], set: Array[String]): Boolean = {
    val noun: List[String] = f(sentence);
    if (noun.isEmpty) return false;
    return set.contains(noun.head)
  }
}