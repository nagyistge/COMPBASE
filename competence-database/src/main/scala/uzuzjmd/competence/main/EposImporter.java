package uzuzjmd.competence.main;

import java.io.File;
import java.io.IOException;
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
import uzuzjmd.competence.owl.access.CompFileUtil;
import uzuzjmd.competence.owl.access.CompOntologyManager;
import uzuzjmd.competence.owl.access.MagicStrings;

public class EposImporter {

	public static void main(String[] args) throws JAXBException, IOException {
		if (!(args.length < 1)) {
			MagicStrings.EPOSLocation = args[0];
		}
		importEpos();
	}

	public static void importEpos() throws JAXBException, IOException {
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

		manager.begin();
		EposXMLToSuggestedLearningPath.convertLevelsToOWLRelations(manager, eposList);
		EposXMLToSuggestedLearningPath.convertLevelsAndLearningGoalToTemplate(manager, eposList);
		manager.close();

		// write result out
		manager.begin();
		CompFileUtil fileUtil = new CompFileUtil(manager.getM());
		fileUtil.writeOntologyout();
		manager.close();

	}
}
