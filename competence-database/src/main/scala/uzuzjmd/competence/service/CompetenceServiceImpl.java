package uzuzjmd.competence.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.main.CompetenceImporter;
import uzuzjmd.competence.mapper.rcd.RCD2OWL;
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl;
import uzuzjmd.competence.rcd.generated.Rdceo;

import javax.jws.WebService;
import java.util.ArrayList;

@WebService(endpointInterface = "uzuzjmd.competence.service.CompetenceService")
public class CompetenceServiceImpl implements CompetenceService {

	static final Logger logger = LogManager.getLogger(CompetenceServiceImpl.class.getName());

	@Override
	public Rdceo[] getCompetences() {
		logger.info("Competences queried");
		return CompetenceImporter.getCompetencesFromCSVasJava().toArray(new Rdceo[0]);

	}

	@Override
	public void insertCompetence(Rdceo rcdeo) {
		logger.info("Competences inserted");
		ArrayList<Rdceo> list = new ArrayList<Rdceo>();
		list.add(rcdeo);
		CompOntologyManagerJenaImpl manager = new CompOntologyManagerJenaImpl();
		RCD2OWL.convertList(list, manager);

	}

}
