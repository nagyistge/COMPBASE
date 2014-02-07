package arbeitsagentur;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import arbeitsagentur.generated.XMLImporter;
import arbeitsagentur.generated.berufe.Beruf;
import arbeitsagentur.generated.berufe.Berufe;
import arbeitsagentur.generated.kompetenzen.Kompetenzen;
import arbeitsagentur.generated.kompetenzen.Kompmerkmal;
import arbeitsagentur.generated.kompsuchworte.Suchwort;
import arbeitsagentur.generated.kompsuchworte.Suchworte;

public class ImportTester {

	// @Test
	public void testBerufe() throws JAXBException {
		XMLImporter importer = new XMLImporter();
		Berufe berufeImported = importer.importBerufe();
		for (Beruf beruf : berufeImported.getBeruf()) {
			System.out.println(beruf.getName());
		}
	}

	// @Test
	public void testKompetenzen() throws JAXBException {
		XMLImporter importer = new XMLImporter();
		Kompetenzen berufeImported = importer.importKompetenzen();
		for (Kompmerkmal beruf : berufeImported.getKompmerkmal()) {
			System.out.println(beruf.getBezeichnung());
		}
	}

	// @Test
	public void testSuchworte() throws JAXBException {
		XMLImporter importer = new XMLImporter();
		Suchworte suchworte = importer.importSuchworte();
		for (Suchwort suchwort : suchworte.getSuchwort()) {
			System.out.println(suchwort.getContent());
		}
	}

	 @Test
	public void testFillKompetenz() throws JAXBException {
		XMLImporter importer = new XMLImporter();
		List<FullCompetence> competencies = importer
				.importfullKomptenzdefinitions();
		// for (FullCompetence fullCompetence : competencies) {
		// System.out.println(fullCompetence.toString());
		// }
	}

}
