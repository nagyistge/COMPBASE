package uzuzjmd.owl.util;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import uzuzjmd.console.util.LogStream;
import uzuzjmd.owl.competence.ontology.CompObjectProperties;
import uzuzjmd.owl.competence.ontology.CompOntClass;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.vocabulary.OWL2;
import com.hp.hpl.jena.vocabulary.RDF;

public class CompOntologyManager {

	private CompOntologyUtil util;
	private OntModel m;
	static final Logger logger = LogManager.getLogger(CompOntologyManager.class.getName());
	static LogStream logStream = new LogStream(logger, Level.TRACE);
	
	public CompOntologyManager() {		
		// initialize Model
		initializeOntologyModelInMemory();
		
		// initializeOntologyModel();
		this.util = new CompOntologyUtil(getM());
	}

	
	public OntModel createBaseOntology() {
		// m = this.util.initializeOntologyModel();	
		initClasses();
		initObjectProperties();
		
		logger.info("Base Ontology created");
		logger.setLevel(Level.DEBUG);		
		m.write(logStream);

		// TODO create Restrictions

		return getM();
	}

	private void initObjectProperties() {
		getUtil().createObjectProperty(CompOntClass.Learner,
				CompOntClass.Competence, CompObjectProperties.LearnerOf);
		getUtil().createObjectProperty(CompOntClass.Catchword,
				CompOntClass.Competence, CompObjectProperties.CatchwordOf);
		getUtil().createObjectProperty(CompOntClass.Evidence,
				CompOntClass.Competence, CompObjectProperties.EvidencOf);
		getUtil().createObjectProperty(CompOntClass.Operator,
				CompOntClass.Competence, CompObjectProperties.OperatorOf);
		getUtil().createObjectProperty(CompOntClass.DescriptionElement,
				CompOntClass.CompetenceDescription,
				CompObjectProperties.DescriptionElementOf);
		getUtil().createObjectProperty(CompOntClass.CompetenceDescription,
				CompOntClass.Competence,
				CompObjectProperties.CompetenceDescriptionOf);
		getUtil().createObjectProperty(CompOntClass.Competence,
				CompOntClass.CompetenceSpec, CompObjectProperties.SpecifiedBy);
		getUtil().createObjectProperty(CompOntClass.Competence,
				CompOntClass.Competence, CompObjectProperties.SimilarTo);
		getM().getObjectProperty(
				MagicStrings.PREFIX + CompObjectProperties.SimilarTo)
				.addProperty(RDF.type, OWL2.ReflexiveProperty);
	}

	private void initClasses() {
		for (CompOntClass compOntClass : CompOntClass.values()) {
			getUtil().createOntClass(compOntClass);
		}
	}

	/**
	 * Also creates a database, if it does not exist already If there already
	 * exist one, Nullpointer is thrown
	 * 
	 * @return
	 */
	private void initializeOntologyModel() {
		Dataset dataset = TDBFactory.createDataset(MagicStrings.TDBLocation);
		Model tdb = dataset.getDefaultModel();
		setM(ModelFactory.createOntologyModel(
				OntModelSpec.OWL_MEM_MICRO_RULE_INF, tdb));

	}

	private void initializeOntologyModelInMemory() {
		setM(ModelFactory
				.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF));		

	}

	public CompOntologyUtil getUtil() {
		return util;
	}

	public OntModel getM() {
		return m;
	}

	public void setM(OntModel m) {
		this.m = m;
	}

}
