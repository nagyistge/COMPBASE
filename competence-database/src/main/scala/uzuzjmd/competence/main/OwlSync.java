package uzuzjmd.competence.main;

import uzuzjmd.competence.owl.access.CompFileUtil;
import uzuzjmd.competence.owl.access.CompOntologyManager;

public class OwlSync {

	public static void main(String[] args) {		
		CompOntologyManager owlManager = new CompOntologyManager();					
		owlManager.close();
		
//		  CompFileUtil.deleteTDB();
	}



}
