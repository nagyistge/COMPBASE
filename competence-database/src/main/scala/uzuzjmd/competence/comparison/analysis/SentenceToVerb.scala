package uzuzjmd.competence.comparison.analysis

import edu.stanford.nlp.parser.lexparser.LexicalizedParser
import edu.stanford.nlp.trees.PennTreebankLanguagePack
import uzuzjmd.competence.owl.access.MagicStrings
import scala.collection.JavaConverters._
import edu.stanford.nlp.ling.Sentence
import edu.stanford.nlp.trees.Tree

/**
 * @author dehne
 */
object SentenceToVerb {
  def convertSentenceToVerbs(input: String): List[String] = {

    val lp = LexicalizedParser.loadModel(MagicStrings.GERMANMODELLOCATION, "-maxLength", "80");
    val tlp = new PennTreebankLanguagePack();
    // Uncomment the following line to obtain original Stanford Dependencies
    // tlp.setGenerateOriginalDependencies(true);
//    val gsf = tlp.grammaticalStructureFactory();
    val sent = input.split(" ").toList
    val parse = lp.apply(Sentence.toWordList(sent.asJava));
    
//    val gs = gsf.newGrammaticalStructure(parse);
//    val tdl = gs.typedDependenciesCCprocessed();
//    println(tdl.toString())
    
    val result = treeWalker(parse)
    
    return result
  }
  
  
  
  def treeWalker (input: Tree) : List[String] = {
    val startList = List() : List[String]  
    return treeWalker_Helper(input, startList)
  }
  
   
  def treeWalker_Helper (input: Tree, startList: List[String]) : List[String] = {
     val label = input.nodeString() 
     if (input.nodeString().contains("VVIZU") || input.nodeString().contains("VVINF") || input.nodeString().contains("VVFIN") || input.nodeString().contains("VVPP") || input.nodeString.contains("ADJD")) {
       val newList = input.getChild(0).toString() :: startList
       return startList ::: input.children().map (x => treeWalker_Helper(x,newList)).flatten.toList 
     } else {
       return startList ::: input.children().map (x => treeWalker_Helper(x,startList)).flatten.toList  
     }
     
  }
  

  
  
  
  
}