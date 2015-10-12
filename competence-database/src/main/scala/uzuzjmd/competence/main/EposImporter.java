package uzuzjmd.competence.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import uzuzjmd.competence.datasource.epos.mapper.EposXML2Ont;
import uzuzjmd.competence.owl.access.MagicStrings;
import uzuzjmd.competence.shared.DESCRIPTORSETType;

public class EposImporter {

	public static void main(String[] args) throws JAXBException, IOException {

		importEpos();
	}

	public static void importEpos() throws JAXBException, IOException {
		List<DESCRIPTORSETType> eposList = parseEPOSXML();
		importEposCompetences(eposList);
	}

	public static List<DESCRIPTORSETType> parseEPOSXML() throws JAXBException {
		// convert xml to java data
		JAXBContext jaxbContext = JAXBContext.newInstance(DESCRIPTORSETType.class);
		Unmarshaller eposUnMarshallUnmarshaller = jaxbContext.createUnmarshaller();
		DESCRIPTORSETType descriptorsetType = (DESCRIPTORSETType) eposUnMarshallUnmarshaller.unmarshal(new File(MagicStrings.EPOSLocation));
		// we assume that there will be more than just one descriptorset (but
		// don't know ey)
		List<DESCRIPTORSETType> eposList = new ArrayList<DESCRIPTORSETType>();
		eposList.add(descriptorsetType);
		return eposList;
	}

	public static void importEposCompetences(List<DESCRIPTORSETType> eposList) {
		EposXML2Ont.convert(eposList);
	}
}
