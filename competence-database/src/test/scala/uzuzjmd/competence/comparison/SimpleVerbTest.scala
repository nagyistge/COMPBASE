package uzuzjmd.competence.comparison

import scala.collection.JavaConverters.seqAsJavaListConverter
import org.junit.AfterClass
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.mapper.gui.Ont2CompetenceTree
import uzuzjmd.competence.owl.access.CompFileUtil
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.StudentRole
import uzuzjmd.competence.owl.dao.TeacherRole
import org.scalatest.junit.JUnitRunner
import org.specs2.specification.After
import org.specs2.mutable.After
import uzuzjmd.competence.owl.dao.User
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.dao.Role
import uzuzjmd.competence.owl.dao.Comment
import uzuzjmd.competence.owl.dao.EvidenceActivity
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import uzuzjmd.competence.owl.dao.StudentRole
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.TeacherRole
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.mapper.gui.Ont2CompetenceLinkMap
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import uzuzjmd.competence.mapper.gui.Ont2ProgressMap
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.SelectedLearningProjectTemplate
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.owl.dao.CompetenceInstance
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.access.CompOntologyAccess
import org.apache.log4j.Level
import com.hp.hpl.jena.rdf.model.InfModel
import com.hp.hpl.jena.rdf.model.ModelFactory
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.main.CompetenceImporter
import uzuzjmd.competence.main.EposImporter
import uzuzjmd.competence.owl.access.MagicStrings
import edu.stanford.nlp.trees.GrammaticalStructureFactory
import edu.stanford.nlp.parser.lexparser.LexicalizedParser
import edu.stanford.nlp.trees.PennTreebankLanguagePack
import edu.stanford.nlp.ling.Sentence
import scala.collection.JavaConverters._
import uzuzjmd.competence.comparison.analysis.SentenceToVerb
import org.tartarus.snowball.SnowballProgram;
import org.tartarus.snowball.SnowballStemmer;

@RunWith(classOf[JUnitRunner])
class CoreTests extends FunSuite with ShouldMatchers {

  test("given a sentence, the verb should be returned") {
    val input = "Die Lehramtsstudenten der alten Schule sind in der Lage komplexe Texte zu verstehen und gehen gerne baden ."
    SentenceToVerb.convertSentenceToVerbs(input) should not be ('empty)
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

}