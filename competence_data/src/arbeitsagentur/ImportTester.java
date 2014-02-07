package arbeitsagentur;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import arbeitsagentur.generated.XMLImporter;
import arbeitsagentur.generated.berufe.Beruf;
import arbeitsagentur.generated.berufe.Berufe;
import arbeitsagentur.generated.kompetenzen.Kompetenzen;
import arbeitsagentur.generated.kompetenzen.Kompmerkmal;

public class ImportTester {

	//@Test
	public void testBerufe() throws JAXBException {
		XMLImporter importer = new XMLImporter();
		Berufe berufeImported = importer.importBerufe();
		for (Beruf beruf: berufeImported.getBeruf()) {
			System.out.println(beruf.getName());
		}
	}
	
	@Test
	public void testKompetenzen() throws JAXBException {
		XMLImporter importer = new XMLImporter();
		Kompetenzen berufeImported = importer.importKompetenzen();
		for (Kompmerkmal beruf: berufeImported.getKompmerkmal()) {
			System.out.println(beruf.getBezeichnung());
		}
	}
	
	
	

}
