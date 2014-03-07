package uzuzjmd.owl.reasoning.jena;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import uzuzjmd.console.util.LogStream;
import uzuzjmd.owl.util.CompOntologyManager;
import uzuzjmd.owl.util.MagicStrings;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.reasoner.rulesys.Rule.ParserException;

public class SimpleRulesReasoner {

	/*
	 * Logging
	 */
	static final Logger logger = LogManager.getLogger(SimpleRulesReasoner.class.getName());
	static LogStream logStream = new LogStream(logger, Level.DEBUG);
	/**
	 * dependencies
	 */
	private CompOntologyManager manager;
	/**
	 * properties
	 */
	private List<Rule> rules;
	private GenericRuleReasoner reasoner;


	public SimpleRulesReasoner(CompOntologyManager manager) throws IOException {
		this.manager = manager;
		rules = createStaticRules();		
		logger.setLevel(Level.DEBUG);
		setupRulesReasoner(manager, rules);		
	}

	public Model reason() {		
		InfModel inf = ModelFactory.createInfModel(reasoner, manager.getM());		
		logger.debug("RulesReasoner * * =>");		
		manager.getM().add(inf.getDeductionsModel());
		inf.getDeductionsModel().write(logStream,"N-TRIPLE","comp:");
		logger.debug("RulesReasoner close");
		return inf.getDeductionsModel();
	}

	private void setupRulesReasoner(CompOntologyManager manager,
			List<Rule> rules) {
		  reasoner = new GenericRuleReasoner(rules);
		  reasoner.setDerivationLogging(true);
		  reasoner.setOWLTranslation(true);               // not needed in RDFS case
		  reasoner.setTransitiveClosureCaching(true);
		  
		  //reasoner.setParameter(ReasonerVocabulary.PROPtraceOn, Boolean.TRUE);		  	 		
	}

	private List<Rule> createStaticRules() {
		List<Rule> rules = null;
		try {		
			rules = Rule.parseRules( Rule.rulesParserFromReader(new BufferedReader(new FileReader(MagicStrings.RULESFILE))));
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return rules;
	}
	
	public void addRuleAsString(String rule) {
		rule = rule.replaceAll("comp:", MagicStrings.PREFIX);
		List<Rule> rules = Rule.parseRules(rule);		
		this.rules.addAll(rules);	
	} 
}
