package uzuzjmd.owl.reasoning.jena;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
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
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

public class SimpleRulesReasoner {

	/*
	 * Logging
	 */
	public static final Logger logger = LogManager
			.getLogger(SimpleRulesReasoner.class.getName());
	static LogStream logStream = new LogStream(logger, Level.DEBUG);

	/**
	 * properties
	 */
	public GenericRuleReasoner reasoner;
	private CompOntologyManager manager;

	public SimpleRulesReasoner(CompOntologyManager manager, Boolean ruleLogging)
			throws IOException {
		this.manager = manager;
		setupRulesReasoner(ruleLogging);
	}

	public synchronized Model reason() {
		InfModel inf = ModelFactory.createInfModel(reasoner, manager.getM());
		manager.getM().validate();
		manager.getM().add(inf.getDeductionsModel());
		if (!inf.getDeductionsModel().isEmpty()) {
			logger.debug("RulesReasoner * * =>");
			inf.getDeductionsModel().write(logStream, "N-TRIPLE", "comp:");
			logger.debug("RulesReasoner close");
		}
		return inf.getDeductionsModel();
	}

	private void setupRulesReasoner(Boolean ruleLogging) {
		List<Rule> rules = new LinkedList<Rule>();
		reasoner = new GenericRuleReasoner(rules);
		reasoner.setDerivationLogging(true);
		reasoner.setOWLTranslation(true); // not needed in RDFS case
		reasoner.setTransitiveClosureCaching(true);
		reasoner.setParameter(ReasonerVocabulary.PROPtraceOn, ruleLogging);
		reason();
	}

	/**
	 * somehow not working use addRule Method
	 * 
	 * @deprecated
	 * @return
	 */
	private List<Rule> createStaticRules() {
		List<Rule> rules = null;
		try {
			rules = Rule.parseRules(Rule
					.rulesParserFromReader(new BufferedReader(new FileReader(
							MagicStrings.RULESFILE))));
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rules;
	}

	public synchronized void addRuleAsString(String rule) {
		rule = rule.replaceAll("comp:", MagicStrings.PREFIX);
		List<Rule> rules = Rule.parseRules(rule);
		reasoner.addRules(rules);
	}

	/**
	 * Rule hat hier nur noch das Format (a b c) (b c e) -> (c d e)
	 * 
	 * @param rule
	 * @param rulename
	 */
	synchronized public void addRuleAsString(String rule, String rulename) {
		rule = rule.replaceAll("comp:", MagicStrings.PREFIX);
		String resultRule = "[" + rulename + ":" + rule + "]";
		List<Rule> rules = Rule.parseRules(resultRule);
		reasoner.addRules(rules);
	}

	public GenericRuleReasoner getReasoner() {
		return reasoner;
	}

}
