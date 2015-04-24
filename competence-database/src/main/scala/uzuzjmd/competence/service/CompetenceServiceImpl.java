package uzuzjmd.competence.service;

import java.util.ArrayList;

import javax.jws.WebService;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import uzuzjmd.competence.console.util.LogStream;
import uzuzjmd.competence.main.CompetenceImporter;
import uzuzjmd.competence.mapper.rcd.RCD2OWL;
import uzuzjmd.competence.owl.access.CompOntologyManager;
import uzuzjmd.competence.rcd.generated.Rdceo;

@WebService(endpointInterface = "uzuzjmd.competence.service.CompetenceService")
public class CompetenceServiceImpl implements CompetenceService {

	static final Logger logger = LogManager.getLogger(CompetenceServiceImpl.class.getName());
	static LogStream logStream = new LogStream(logger, Level.TRACE);

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
		CompOntologyManager manager = new CompOntologyManager();
		RCD2OWL.convertList(list, manager);
		// uzuzjmd.console.util.ConsoleOut.printRcdeoCompetences(list);
	}

}
