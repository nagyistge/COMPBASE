package uzuzjmd.competence.persistence.owl.reasoning;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import uzuzjmd.competence.console.util.LogStream;
import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager;
import uzuzjmd.competence.config.MagicStrings;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Derivation;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

public class SimpleRulesReasoner {

	/*
	 * Logging
	 */
	public static final Logger logger = LogManager
			.getLogger(SimpleRulesReasoner.class.getName());
	public static LogStream logStream = new LogStream(
			logger, Level.DEBUG);

	/**
	 * properties
	 */
	public GenericRuleReasoner reasoner;
	private CompOntologyManager manager;

	public SimpleRulesReasoner(CompOntologyManager manager,
			Boolean ruleLogging) throws IOException {
		this.manager = manager;
		setupRulesReasoner(ruleLogging);
	}

	public synchronized void reason() {
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		model.add(manager.getM());
		InfModel inf = ModelFactory.createInfModel(
				reasoner, model);
		model.validate();
		manager.getM().add(inf.getDeductionsModel());
		if (!inf.getDeductionsModel().isEmpty()) {
			logger.debug("RulesReasoner * * =>");
			inf.getDeductionsModel().write(logStream,
					"N-TRIPLE", "comp:");
			printTrace(inf);
			logger.debug("RulesReasoner close");
		}
	}

	private void setupRulesReasoner(Boolean ruleLogging) {
		List<Rule> rules = new LinkedList<Rule>();
		reasoner = new GenericRuleReasoner(rules);
		reasoner.setDerivationLogging(true);
		reasoner.setOWLTranslation(true); // not needed in RDFS case
		reasoner.setTransitiveClosureCaching(true);
		reasoner.setParameter(
				ReasonerVocabulary.PROPtraceOn, ruleLogging);
		reasoner.setTraceOn(ruleLogging);
		reason();
	}

	public void printTrace(InfModel inf) {
		PrintWriter out = new PrintWriter(System.out);
		for (StmtIterator i = inf.listStatements(); i
				.hasNext();) {
			Statement s = i.nextStatement();
			// System.out.println("Statement is " + s);
			for (Iterator id = inf.getDerivation(s); id
					.hasNext();) {
				Derivation deriv = (Derivation) id.next();
				deriv.printTrace(out, true);
			}
		}
		out.flush();
	}

	// /**
	// * somehow not working use addRule Method
	// *
	// * @deprecated
	// * @return
	// */
	// private List<Rule> createStaticRules() {
	// List<Rule> rules = null;
	// try {
	// rules = Rule.parseRules(Rule
	// .rulesParserFromReader(new BufferedReader(new FileReader(
	// MagicStrings.RULESFILE))));
	// } catch (ParserException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return rules;
	// }

	public synchronized void addRuleAsString(String rule) {
		rule = rule
				.replaceAll("comp:", MagicStrings.PREFIX);
		List<Rule> rules = Rule.parseRules(rule);
		reasoner.addRules(rules);
	}

	/**
	 * Rule hat hier nur noch das Format (a b c) (b c e) -> (c d e)
	 * 
	 * @param rule
	 * @param rulename
	 */
	synchronized public void addRuleAsString(String rule,
			String rulename) {
		rule = rule
				.replaceAll("comp:", MagicStrings.PREFIX);
		String resultRule = "[" + rulename + ":" + rule
				+ "]";
		List<Rule> rules = Rule.parseRules(resultRule);
		reasoner.addRules(rules);
	}

	public GenericRuleReasoner getReasoner() {
		return reasoner;
	}

	public void switchOnDebug() {
		getReasoner().setParameter(
				ReasonerVocabulary.PROPtraceOn, true);
	}

	public void switchOffDebug() {
		getReasoner().setParameter(
				ReasonerVocabulary.PROPtraceOn, false);
	}

}
