package arbeitsagentur.generated;

import java.io.InputStream;

public class XMLLoader {
	public InputStream getBerufeXML() {
		return this.getClass().getResourceAsStream("/berufe.xml");
	}
	
	public InputStream getKompHierarchieXML() {
		return this.getClass().getResourceAsStream("/komp_hierarchie.xml");
	}
	
	public InputStream getSuchworteXML() {
		return this.getClass().getResourceAsStream("/komp_suchworte.xml");
	}
	
	public InputStream getKompetenzenXML() {
		return this.getClass().getResourceAsStream("/kompetenzen.xml");
	}
	
}
