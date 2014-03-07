package uzuzjmd.test.reasoner;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
import org.mindswap.pellet.IndividualIterator;
import org.mindswap.pellet.KnowledgeBase;
import org.mindswap.pellet.jena.PelletInfGraph;
import org.mindswap.pellet.jena.PelletReasoner;

import uzuzjmd.owl.competence.ontology.CompObjectProperties;
import uzuzjmd.owl.competence.ontology.CompOntClass;
import uzuzjmd.owl.reasoning.jena.SimpleRulesReasoner;
import uzuzjmd.owl.util.CompOntologyManager;
import uzuzjmd.owl.util.CompOntologyUtil;
import uzuzjmd.owl.util.MagicStrings;
import aterm.ATermAppl;

import com.clarkparsia.pellet.rules.model.AtomIObject;
import com.clarkparsia.pellet.rules.model.AtomIVariable;
import com.clarkparsia.pellet.rules.model.ClassAtom;
import com.clarkparsia.pellet.rules.model.IndividualPropertyAtom;
import com.clarkparsia.pellet.rules.model.Rule;
import com.clarkparsia.pellet.rules.model.RuleAtom;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerFactory;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;

public class RulesReasonerTest {

	@Test
	public void test() throws IOException {
		System.out.println("testing simple rules");
		CompOntologyManager manager = initOntology();		
		CompOntologyUtil util = manager.getUtil();
		OntClass learnerClass = util.createOntClass(CompOntClass.Learner);
		OntClass competence = util.createOntClassForString("TestCompetence");
		competence.addSuperClass(util.getClass(CompOntClass.Competence));
		Individual individual = util.createIndividualForString(learnerClass, "julian coming late");
		util.createObjectPropertyWithIndividualAndClass(individual, competence, CompObjectProperties.LearnerOf);				
	}
	
	//@Test
	public void testPrintingClassesAndIndividuals() throws IOException {
		CompOntologyManager manager = initOntology();
		OntClass ontclass = addTestIndividualk(manager);
		PelletReasoner reasoner = new PelletReasoner();		
		//com.clarkparsia.pellet.rules.rete.Rule
		InfModel infmodel = initReasoner(manager, reasoner);			
		infmodel.validate();
		//manager.getUtil().writeOntologyout(infmodel);	
		//infmodel.getDeductionsModel();
		//KnowledgeBase kb = createRules(ontclass, infmodel);
		KnowledgeBase kb = ((PelletInfGraph) infmodel.getGraph()).getKB();	
				
		
		assert(!kb.getClasses().isEmpty());
		assert(!kb.getIndividuals().isEmpty());
		
	}
	
	//@Test
	public void testConvertingObjectsAndClasses() throws IOException {
		CompOntologyManager manager = initOntology();
		OntClass ontclass = addTestIndividualk(manager);
		PelletReasoner reasoner = new PelletReasoner();			
		InfModel infmodel = initReasoner(manager, reasoner);			
		infmodel.validate();
		
		ATermAppl strangeFormatClass = convertOntclass(ontclass);
		assert (strangeFormatClass.toString().equals("http://www.uzuzjmd.de/proof-of-concept.owl#helloclass"));
		ATermAppl strangeFormatOP = convertOP(manager.getUtil().getObjectPropertyForString(CompObjectProperties.CatchwordOf.name()));
		assert (strangeFormatOP.toString().equals("http://www.uzuzjmd.de/proof-of-concept.owl#CatchwordOf"));
		ATermAppl strangeFormatOP2 = convertOP(manager.getUtil().getObjectPropertyForString(CompObjectProperties.LearnerOf.name()));
		assert (strangeFormatOP2.toString().equals("http://www.uzuzjmd.de/proof-of-concept.owl#LearnerOf"));
	}
	
	//@Test
	public void testWritingRules() throws IOException {
		CompOntologyManager manager = initOntology();
		CompOntologyUtil util = manager.getUtil();	
		Individual individual = util.createIndividualForString(util.getOntClassForString(CompOntClass.Learner.name()), "julian");
		Individual individual2 = util.createIndividualForString(util.getOntClassForString(CompOntClass.Competence.name()), "competencea");
		util.createObjectPropertyWithIndividual(individual, individual2, CompObjectProperties.LearnerOf);
		PelletReasoner reasoner = new PelletReasoner();			
		InfModel infmodel = initReasoner(manager, reasoner);			
		//infmodel.validate();		
		
		// entspricht SWRL Competence(?competence)			
		RuleAtom isCompetence = swrlClassAtom(util, "?competence", CompOntClass.Competence.name());
		RuleAtom isOperator = swrlClassAtom(util, "?operator", CompOntClass.Operator.name());			
		RuleAtom learnerOf = swrlObjectPropertyAtom(util, CompObjectProperties.LearnerOf.name(), "?operator", "?competence");
		RuleAtom learnerOfInverse = swrlObjectPropertyAtom(util, CompObjectProperties.LearnerOfInverse.name(), "?competence",  "?operator");

		// finally
		Collection<RuleAtom> head = new HashSet<RuleAtom>();		
		Collection<RuleAtom> body = new HashSet<RuleAtom>();
		body.add(isCompetence);
		body.add(isOperator);
		body.add(learnerOf);
		head.add(learnerOfInverse);
		
		Rule rule = new Rule(head,body);
		KnowledgeBase kb = ((PelletInfGraph) infmodel.getGraph()).getKB();
		kb.addRule(rule);
		//kb.realize();
		infmodel.validate();
		manager.getM().add(infmodel);		
		
		assert false;			
	}

	private RuleAtom swrlObjectPropertyAtom(CompOntologyUtil util,
			String objectProperty, String domain, String range) {
		ATermAppl predicate = convertOP(util.getObjectPropertyForString(objectProperty));
		RuleAtom learnerOf = new IndividualPropertyAtom(predicate, new AtomIVariable(domain), new AtomIVariable(range));
		return learnerOf;
	}

	private RuleAtom swrlClassAtom(CompOntologyUtil util, String variableName,
			String className) {
		AtomIObject argument = new AtomIVariable(variableName);
		ATermAppl predicate = convertOntclass(util.createOntClassForString(className));		
		RuleAtom isCompetence = new ClassAtom(predicate, argument);
		return isCompetence;
	}

	private ATermAppl getAtermApplForOP(CompOntologyManager manager,
			CompObjectProperties prop) {
		ATermAppl strangeFormatOP = convertOP(manager.getUtil().getObjectPropertyForString(prop.name()));
		return strangeFormatOP;
	}
	


	private void printIndividuals(KnowledgeBase kb) {
		IndividualIterator it = kb.getABox().getIndIterator();
		while (it.hasNext()) {
			System.out.println(it.next().getNameStr());
		}
	}

	private void printClasses(KnowledgeBase kb) {
		Set<ATermAppl> classes = kb.getAllClasses();
		for (ATermAppl aTermAppl : classes) {
			System.out.println(aTermAppl);
		}
	}

	private InfModel initReasoner(CompOntologyManager manager,
			PelletReasoner reasoner) {
		reasoner.bind(manager.getM().getGraph());		
		InfModel infmodel = ModelFactory.createInfModel(reasoner, manager.getM());
		return infmodel;
	}

	private OntClass addTestIndividualk(CompOntologyManager manager) {
		OntClass ontclass = manager.getM().createClass(MagicStrings.PREFIX+"helloclass");
		manager.getUtil().createIndividualForString(ontclass, "helloindividuaul");
		return ontclass;
	}

	private CompOntologyManager initOntology() {
		CompOntologyManager manager = new CompOntologyManager();
		manager.createBaseOntology();
		return manager;
	}

	/**
	 * TODO find a better way of doing this (WAY TO HACKY)
	 * @param ontclass
	 * @return
	 */
	private ATermAppl convertOntclass(OntClass ontclass) {			
		PelletReasoner reasoner = new PelletReasoner();	
		OntModel model = ModelFactory.createOntologyModel();
		OntClass copy = model.createClass(ontclass.getURI());
		for (Property prop : ontclass.listDeclaredProperties().toList()) {
			copy.addProperty(prop,prop.asResource());
		}
		InfModel infModel = ModelFactory.createInfModel(reasoner,model);
		infModel.validate();		
		ATermAppl predicate = ((PelletInfGraph) infModel.getGraph()).getKB().getClasses().iterator().next();		
		return predicate;
	}
	
	/**
	 * TODO find a better way of doing this (WAY TO HACKY)
	 * @param oProperty
	 * @return
	 */
	private ATermAppl convertOP(ObjectProperty oProperty) {
		PelletReasoner reasoner = new PelletReasoner();	
		OntModel model = ModelFactory.createOntologyModel();
		ObjectProperty copy = model.createObjectProperty(oProperty.getURI());
		copy.setDomain(oProperty.getDomain());
		copy.setRange(oProperty.getRange());
		InfModel infModel = ModelFactory.createInfModel(reasoner,model);
		infModel.validate();	
		Iterator<ATermAppl> it = ((PelletInfGraph) infModel.getGraph()).getKB().getObjectProperties().iterator();
		it.next();
		ATermAppl predicate = it.next();		
		return predicate;
	}
		
		

}
