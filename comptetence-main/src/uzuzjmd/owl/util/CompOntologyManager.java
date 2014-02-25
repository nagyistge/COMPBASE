package uzuzjmd.owl.util;

import java.io.IOException;

import uzuzjmd.owl.competence.ontology.CompOntClass;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;

public class CompOntologyManager {
	
	private CompOntologyUtil util;
	private OntModel m;

	public CompOntologyManager() {
		initializeOntologyModelInMemory();
		this.util = new CompOntologyUtil(getM());
	}
	
	public void loadOntology() throws IOException {
		// create the base model
		
	}
	
	
	public OntModel createBaseOntology() {
		//m = this.util.initializeOntologyModel();		
		initClasses();									
		initObjectProperties();
						
		//TODO create Restrictions			
				
		return getM();
	}

	private void initObjectProperties() {
		getUtil().createObjectProperty(CompOntClass.Learner, CompOntClass.Competence, "isLearnerOf");
		getUtil().createObjectProperty(CompOntClass.Catchword, CompOntClass.Competence, "isCatchwordFor");
		getUtil().createObjectProperty(CompOntClass.Evidence, CompOntClass.Competence, "isEvidenceFor");
		getUtil().createObjectProperty(CompOntClass.Operator, CompOntClass.Competence, "isOperatorFor");
		getUtil().createObjectProperty(CompOntClass.DescriptionElement, CompOntClass.CompetenceDescription, "isElementOf");
		getUtil().createObjectProperty(CompOntClass.CompetenceDescription, CompOntClass.Competence, "isDescriptionFor");
		getUtil().createObjectProperty(CompOntClass.Competence, CompOntClass.CompetenceSpec, "isPartOf");
	}

	private void initClasses() {
		for (CompOntClass compOntClass : CompOntClass.values()) {
			getUtil().createOntClass(getM(), compOntClass);	
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
		setM(ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF));
		
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
