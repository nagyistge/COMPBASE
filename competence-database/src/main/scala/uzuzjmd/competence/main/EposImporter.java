package uzuzjmd.competence.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import uzuzjmd.competence.csv.FilteredCSVCompetence;
import uzuzjmd.competence.datasource.epos.DESCRIPTORSETType;
import uzuzjmd.competence.datasource.epos.mapper.EposXML2FilteredCSVCompetence;
import uzuzjmd.competence.datasource.epos.mapper.EposXMLToSuggestedLearningPath;
import uzuzjmd.competence.mapper.rcd.RCD2OWL;
import uzuzjmd.competence.owl.access.CompOntologyManager;
import uzuzjmd.competence.owl.access.MagicStrings;

public class EposImporter {

	public static void main(String[] args) throws JAXBException {
		if (!(args.length < 1)) {
			MagicStrings.EPOSLocation = args[0];
		}

		// convert xml to java data
		JAXBContext jaxbContext = JAXBContext.newInstance(DESCRIPTORSETType.class);
		Unmarshaller eposUnMarshallUnmarshaller = jaxbContext.createUnmarshaller();
		DESCRIPTORSETType descriptorsetType = (DESCRIPTORSETType) eposUnMarshallUnmarshaller.unmarshal(new File(MagicStrings.EPOSLocation));
		// we assume that there will be more than just one descriptorset (but
		// don't know ey)
		List<DESCRIPTORSETType> eposList = new ArrayList<DESCRIPTORSETType>();
		eposList.add(descriptorsetType);

		// write competences in database as usual
		List<FilteredCSVCompetence> result = EposXML2FilteredCSVCompetence.mapEposXML(eposList);
		CompOntologyManager manager = new CompOntologyManager();
		RCD2OWL.convert(EposXML2FilteredCSVCompetence.EPOSXML2RCD(result), manager);

		EposXMLToSuggestedLearningPath.convertLevelsToOWLRelations(manager, eposList);

	}
}
