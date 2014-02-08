package arbeitsagentur.generated;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBException;

import sun.launcher.resources.launcher;
import arbeitsagentur.FullCompetence;
import arbeitsagentur.generated.berufe.Beruf;
import arbeitsagentur.generated.berufe.Berufe;
import arbeitsagentur.generated.berufe.Kompetenz;
import arbeitsagentur.generated.kompetenzen.Kompetenzen;
import arbeitsagentur.generated.kompetenzen.Kompmerkmal;
import arbeitsagentur.generated.komphierarchie.Ebene1;
import arbeitsagentur.generated.komphierarchie.Ebene2;
import arbeitsagentur.generated.komphierarchie.Ebene3;
import arbeitsagentur.generated.komphierarchie.Hierarchie;
import arbeitsagentur.generated.kompsuchworte.Suchwort;
import arbeitsagentur.generated.kompsuchworte.Suchworte;

public class XMLImporter extends AbstractXMLImporter {

	public Berufe importBerufe() throws JAXBException {
		return unMarshallXML(Berufe.class, xmlLoader.getBerufeXML());
	}

	public Kompetenzen importKompetenzen() throws JAXBException {
		return unMarshallXML(Kompetenzen.class, xmlLoader.getKompetenzenXML());
	}

	public Hierarchie importHierarchie() throws JAXBException {
		return unMarshallXML(Hierarchie.class, xmlLoader.getKompHierarchieXML());
	}

	public Suchworte importSuchworte() throws JAXBException {
		return unMarshallXML(Suchworte.class, xmlLoader.getSuchworteXML());
	}

	public synchronized  List<FullCompetence> importfullKomptenzdefinitions()
			throws JAXBException, InterruptedException {
		List<FullCompetence> result = new LinkedList<>();
		Berufe berufe = importBerufe();
		Suchworte suchworte = importSuchworte();
		Kompetenzen toFill = importKompetenzen();
		Hierarchie hierarchie = importHierarchie();		
		for (Kompmerkmal kompmerkmal : toFill.getKompmerkmal()) {			
			FullCompetence fullCompetence = new FullCompetence(kompmerkmal);
			// Berufe hinzufügen
			BerufeThread berufeThread = new BerufeThread(berufe, fullCompetence);
			berufeThread.start();
			// Suchworte hinzufügen
			SuchworteThread sucheSuchworteThread = new SuchworteThread(suchworte, kompmerkmal, fullCompetence);
			sucheSuchworteThread.start();
			// Kontexte hinzufügen und Hierarchie konstruieren

			// Kontexte hinzufügen			
			KontexteThread kontexteThread = new KontexteThread(hierarchie, fullCompetence);
			kontexteThread.start();
			
			berufeThread.join();
			sucheSuchworteThread.join();
			kontexteThread.join();

			// Zwischenstand ausgeben		    
			result.add(fullCompetence);			
		}
		
		System.out.println(result.size());	
		System.out.println(result.get(5));
		
//		for (FullCompetence fullCompetence : result) {
//			for (FullCompetence fullCompetence2 : result) {
//				for (HierachieTriple hierachieTriple : triples) {
//					if (fullCompetence.getId().equals(hierachieTriple.over)
//							&& fullCompetence2.getId().equals(
//									hierachieTriple.under)) {
//						fullCompetence.unterKompetenzen.add(fullCompetence2);
//					}
//				}
//			}
//			System.out.println(fullCompetence);
//		}

		return result;
	}

	

	

	
}
