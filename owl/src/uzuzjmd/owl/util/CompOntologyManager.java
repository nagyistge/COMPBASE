package uzuzjmd.owl.util;

import java.io.IOException;
import uzuzjmd.owl.competence.ontology.CompOntClass;
import com.hp.hpl.jena.ontology.OntModel;

public class CompOntologyManager {
	
	private CompOntologyUtil util;
	private OntModel m;

	public CompOntologyManager() {
		this.util = new CompOntologyUtil();
	}
	
	public void loadOntology() throws IOException {
		// create the base model
		
	}
	
	/**
	 * wichtigste Methode
	 */
	public OntModel createBaseOntology() {
		//m = this.util.initializeOntologyModel();
		m = this.util.initializeOntologyModelInMemory();
		initClasses();		
		util.createOntClass(m, CompOntClass.Catchword);				
		
				
		//TODO create object properties
//		CompetenceOntClassFactory ontClassFactory = new CompetenceOntClassFactory(m, util);
//		OntClass catchword = ontClassFactory.getCatchwordClass();
//		OntClass competence = ontClassFactory.getCompetenceClass();
//		util.createObjectProperty(m, catchword, competence, "isCompetenceFor");
		
		//TODO create Restrictions			
				
		return m;
	}

	private void initClasses() {
		// TODO Auto-generated method stub
		
	}
	
}
