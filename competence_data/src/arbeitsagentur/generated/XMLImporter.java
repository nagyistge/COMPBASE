package arbeitsagentur.generated;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBException;

import arbeitsagentur.FullCompetence;
import arbeitsagentur.generated.berufe.Berufe;
import arbeitsagentur.generated.kompetenzen.Kompetenzen;
import arbeitsagentur.generated.kompetenzen.Kompmerkmal;
import arbeitsagentur.generated.komphierarchie.Hierarchie;
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

	public synchronized List<FullCompetence> importfullKomptenzdefinitions()
			throws JAXBException, InterruptedException {
		List<FullCompetence> result = new LinkedList<>(); // Ergebnisliste
		List<HierachieTriple> triples = new LinkedList<HierachieTriple>(); // Ergebnisliste für Kompetenzrelationen
		
		// xml mit jaxb einlesen
		Berufe berufe = importBerufe();
		Suchworte suchworte = importSuchworte();
		Kompetenzen toFill = importKompetenzen();
		Hierarchie hierarchie = importHierarchie();	
		Hierarchie hierarchie2 = importHierarchie();
								
		fillCompetencesAndCalculateTriples(result, triples, berufe, suchworte,
				toFill, hierarchie, hierarchie2);

		testOutputAusgeben(result, triples);
		competenzenVerbinden(result, triples);

		return result;
	}

	private void fillCompetencesAndCalculateTriples(
			List<FullCompetence> result, List<HierachieTriple> triples,
			Berufe berufe, Suchworte suchworte, Kompetenzen toFill,
			Hierarchie hierarchie, Hierarchie hierarchie2)
			throws InterruptedException {
		HierarchieThread hierarchieThread = new HierarchieThread(hierarchie2,
				triples);
		hierarchieThread.start();
		fillCompetencies(result, berufe, suchworte, toFill, hierarchie);		
		hierarchieThread.join();
	}

	private void testOutputAusgeben(List<FullCompetence> result,
			List<HierachieTriple> triples) {
		System.out.println(("Number of Competences: " + result.size()));
		System.out.println(result.get(5));
		System.out.println("Number of Triples: " + triples.size());
	}

	/**
	 * erstellt die Beziehungen zwischen den Kompetenzen (untergeordnet/übergeordnet)
	 * @param result
	 * @param triples
	 */
	private void competenzenVerbinden(List<FullCompetence> result,
			List<HierachieTriple> triples) {
		for (FullCompetence fullCompetence : result) {
			for (FullCompetence fullCompetence2 : result) {
				for (HierachieTriple hierachieTriple : triples) {
					if (fullCompetence.getId().equals(hierachieTriple.over)
							&& fullCompetence2.getId().equals(
									hierachieTriple.under)) {
						fullCompetence.unterKompetenzen.add(fullCompetence2);
					}
				}
			}			
		}
	}

	/**
	 * füllt die Kompetenzen mit Metadaten
	 * wie Berufe, Kontexte und Suchworte
	 * @param result
	 * @param berufe
	 * @param suchworte
	 * @param toFill
	 * @param hierarchie
	 * @throws InterruptedException
	 */
	private void fillCompetencies(List<FullCompetence> result, Berufe berufe,
			Suchworte suchworte, Kompetenzen toFill, Hierarchie hierarchie)
			throws InterruptedException {
		for (Kompmerkmal kompmerkmal : toFill.getKompmerkmal()) {
			FullCompetence fullCompetence = new FullCompetence(kompmerkmal);
			// Berufe hinzufügen
			BerufeThread berufeThread = new BerufeThread(berufe, fullCompetence);
			berufeThread.start();
			// Suchworte hinzufügen
			SuchworteThread sucheSuchworteThread = new SuchworteThread(
					suchworte, kompmerkmal, fullCompetence);
			sucheSuchworteThread.start();		

			// Kontexte hinzufügen
			KontexteThread kontexteThread = new KontexteThread(hierarchie,
					fullCompetence);
			kontexteThread.start();

			// Threads wieder zusammenführen
			berufeThread.join();
			sucheSuchworteThread.join();
			kontexteThread.join();

			// Zwischenstand ausgeben
			result.add(fullCompetence);
		}
	}

}
