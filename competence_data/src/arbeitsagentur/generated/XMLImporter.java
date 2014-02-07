package arbeitsagentur.generated;

import javax.xml.bind.JAXBException;

import arbeitsagentur.generated.berufe.Berufe;
import arbeitsagentur.generated.kompetenzen.Kompetenzen;
import arbeitsagentur.generated.komphierarchie.Hierarchie;
import arbeitsagentur.generated.kompsuchworte.Suchworte;

public class XMLImporter extends AbstractXMLImporter {
		
	public Berufe importBerufe() throws JAXBException {
		return unMarshallXML(Berufe.class,xmlLoader.getBerufeXML());
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
}
