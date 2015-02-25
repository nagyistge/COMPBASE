package uzuzjmd.competence.main;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import uzuzjmd.competence.datasource.epos.DESCRIPTORSETType;
import uzuzjmd.competence.owl.access.MagicStrings;

public class EposImporter {

	public static void main(String[] args) throws JAXBException {
		if (!(args.length < 1)) {
			MagicStrings.EPOSLocation = args[0];
		}

		// todo import epos-competences to ontology
		JAXBContext jaxbContext = JAXBContext.newInstance(DESCRIPTORSETType.class);
		Unmarshaller eposUnMarshallUnmarshaller = jaxbContext.createUnmarshaller();
		DESCRIPTORSETType descriptorsetType = (DESCRIPTORSETType) eposUnMarshallUnmarshaller.unmarshal(new File(MagicStrings.EPOSLocation));
		System.out.println(descriptorsetType.getDESCRIPTOR().get(0).getCOMPETENCE());

	}

}
