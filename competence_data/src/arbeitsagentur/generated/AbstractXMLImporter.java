package arbeitsagentur.generated;

import java.io.InputStream;
import java.lang.reflect.Method;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class AbstractXMLImporter {	

	protected XMLLoader xmlLoader;

	public AbstractXMLImporter() {
		this.xmlLoader = new XMLLoader();
	}
	
	protected <T> T unMarshallXML(Class<T> className, InputStream input) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(className);		
		return (T) context.createUnmarshaller().unmarshal(input);
	}
		
}
