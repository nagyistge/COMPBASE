package uzuzjmd.owl.util;

import java.io.InputStream;

public class XMLLoader {
	public InputStream getOntology() {
		return this.getClass().getResourceAsStream("/proof-of-conceptrdf.owl");
	}
}
