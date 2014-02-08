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

	public List<FullCompetence> importfullKomptenzdefinitions()
			throws JAXBException {
		List<FullCompetence> result = new LinkedList<>();
		Berufe berufe = importBerufe();
		Suchworte suchworte = importSuchworte();
		Kompetenzen toFill = importKompetenzen();
		Hierarchie hierarchie = importHierarchie();		
		for (Kompmerkmal kompmerkmal : toFill.getKompmerkmal()) {
			FullCompetence fullCompetence = new FullCompetence(kompmerkmal);
			// Berufe hinzufügen
			fillBerufe(berufe, fullCompetence);
			// Suchworte hinzufügen
			fillSuchwort(suchworte, kompmerkmal, fullCompetence);
			// Kontexte hinzufügen und Hierarchie konstruieren

			// Kontexte hinzufügen
			fillKontexte(hierarchie, fullCompetence);

			// Zwischenstand ausgeben
		    System.out.println(fullCompetence);
			result.add(fullCompetence);
		}
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

	private void fillKontexte(Hierarchie hierarchie,
			FullCompetence fullCompetence) {
		for (Ebene1 ebene1 : hierarchie.getEbene1()) {
			extractKontextLevel1(fullCompetence, ebene1);
			for (Ebene2 ebene2 : ebene1.getEbene2()) {
				extractKontextLevel2(fullCompetence, ebene2);
				for (Ebene3 ebene3 : ebene2.getEbene3()) {
					extractKontextLevel3(fullCompetence, ebene3);
				}
			}
		}
	}

	private void extractKontextLevel3(FullCompetence fullCompetence,
			Ebene3 ebene3) {		
		for (arbeitsagentur.generated.komphierarchie.Kompetenz kompetenz : ebene3
				.getKompetenz()) {
			if (kompetenz.getIdref().equals(fullCompetence.getId())) {
				fullCompetence.kontexte.add(ebene3.getBezeichnung());					
			} 			
		}		
	}

	private void extractKontextLevel2(FullCompetence fullCompetence,
			Ebene2 ebene2) {		
		for (arbeitsagentur.generated.komphierarchie.Kompetenz kompetenz : ebene2
				.getKompetenz()) {
			if (kompetenz.getIdref().equals(fullCompetence.getId())) {
				fullCompetence.kontexte.add(ebene2.getBezeichnung());				
			} 			
		}		
	}

	private void extractKontextLevel1(FullCompetence fullCompetence,
			Ebene1 ebene1) {		
		for (arbeitsagentur.generated.komphierarchie.Kompetenz kompetenz : ebene1
				.getKompetenz()) {
			if (kompetenz.getIdref().equals(fullCompetence.getId())) {
				fullCompetence.kontexte.add(ebene1.getBezeichnung());				
			} 			
		}		
	}

	private void fillSuchwort(Suchworte suchworte, Kompmerkmal kompmerkmal,
			FullCompetence fullCompetence) {
		for (Suchwort suchwort : suchworte.getSuchwort()) {
			for (BigInteger integer : kompmerkmal.getSuchw()) {
				if (suchwort.getId().equals(integer)) {
					fullCompetence.suchworte.getSuchwort().add(suchwort);
				}
			}
		}
	}

	private void fillBerufe(Berufe berufe, FullCompetence fullCompetence) {
		for (Beruf beruf : berufe.getBeruf()) {
			for (Kompetenz kompetenz : beruf.getKompetenz()) {
				if (kompetenz.getIdref().equals(fullCompetence.getId())) {
					fullCompetence.berufe.getBeruf().add(beruf);
				}
			}
		}
	}
}
