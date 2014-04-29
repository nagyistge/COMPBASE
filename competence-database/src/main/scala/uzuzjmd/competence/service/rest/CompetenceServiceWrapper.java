package uzuzjmd.competence.service.rest;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import uzuzjmd.competence.mapper.gui.Ont2CompetenceTree;
import uzuzjmd.competence.owl.access.CompFileUtil;
import uzuzjmd.competence.owl.access.CompOntologyAccess;
import uzuzjmd.competence.owl.access.CompOntologyManager;
import uzuzjmd.competence.owl.access.OntResult;
import uzuzjmd.competence.owl.ontology.CompObjectProperties;
import uzuzjmd.competence.owl.ontology.CompOntClass;
import uzuzjmd.competence.service.rest.dto.CatchwordXMLTree;
import uzuzjmd.competence.service.rest.dto.CompetenceXMLTree;
import uzuzjmd.competence.service.rest.dto.OperatorXMLTree;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Property;

public class CompetenceServiceWrapper {

	private static Ont2CompetenceTree initOnt2Mapper(String[] selectedCatchwordArray, String[] selectedOperatorsArray, String course) {
		CompOntologyManager compOntologyManager = new CompOntologyManager();
		List<String> catchwordarray = new LinkedList<String>();
		if (selectedCatchwordArray != null) {
			catchwordarray = Arrays.asList(selectedCatchwordArray);
		}

		List<String> operatorArray = new LinkedList<String>();
		if (selectedOperatorsArray != null) {
			operatorArray = Arrays.asList(selectedOperatorsArray);
		}
		Ont2CompetenceTree ont2CompetenceTree = new Ont2CompetenceTree(compOntologyManager, catchwordarray, operatorArray, course);
		return ont2CompetenceTree;
	}

	public static synchronized OperatorXMLTree[] getOperatorTree(String[] selectedCatchwordArray, String[] selectedOperatorsArray, String course) {
		Ont2CompetenceTree ont2CompetenceTree = initOnt2Mapper(selectedCatchwordArray, selectedOperatorsArray, course);
		OperatorXMLTree[] tmpResult = ont2CompetenceTree.getOperatorXMLTree().toArray(new OperatorXMLTree[0]);
		return tmpResult;
	}

	public static synchronized CatchwordXMLTree[] getCatchwordTree(String[] selectedCatchwordArray, String[] selectedOperatorsArray, String course) {
		Ont2CompetenceTree ont2CompetenceTree = initOnt2Mapper(selectedCatchwordArray, selectedOperatorsArray, course);
		CatchwordXMLTree[] tmpResult = ont2CompetenceTree.getCatchwordXMLTree().toArray(new CatchwordXMLTree[0]);
		return tmpResult;
	}

	public static CompetenceXMLTree[] getCompetenceTree(String[] selectedCatchwordArray, String[] selectedOperatorsArray, String course) {
		Ont2CompetenceTree ont2CompetenceTree = initOnt2Mapper(selectedCatchwordArray, selectedOperatorsArray, course);
		List<CompetenceXMLTree> tmpResult = ont2CompetenceTree.getComptenceTree();
		assert (!tmpResult.isEmpty());
		return tmpResult.toArray(new CompetenceXMLTree[0]);
	}

	public static void linkCompetencesToCourse(String course, String competences, String compulsory, String requirements) {
		System.out.println("linking competences: " + competences);
		CompOntologyManager compOntologyManager = startManager();
		CompOntologyAccess util = compOntologyManager.getUtil();

		Individual courseContextIndividual = createCourseContext(course, util);
		addRequirementLiteral(requirements, compOntologyManager, courseContextIndividual);
		linkSingleCompetences(competences, compulsory, requirements, compOntologyManager, util, courseContextIndividual);

		compOntologyManager.getM().leaveCriticalSection();
		compOntologyManager.getM().validate();
		compOntologyManager.close();
		testResult(compOntologyManager);
	}

	/**
	 * create course context (if not exists)
	 * 
	 * @param course
	 * @param util
	 * @return
	 */
	private static Individual createCourseContext(String course, CompOntologyAccess util) {

		OntClass courseContextClass = util.createOntClass(CompOntClass.CourseContext);
		Individual courseContextIndividual = util.createIndividualForString(courseContextClass, course);
		return courseContextIndividual;
	}

	private static void linkSingleCompetences(String competences, String compulsory, String requirements, CompOntologyManager compOntologyManager, CompOntologyAccess util,
			Individual courseContextIndividual) {
		if (competences == null) {
			throw new WebApplicationException(new Exception("Es wurden keine Kompetenzen übergeben"));
		}
		for (String competence : competences.split(",")) {
			OntResult result = util.accessSingletonResource(competence);
			Individual competenceIndividual = result.getIndividual();
			util.createObjectPropertyWithIndividual(courseContextIndividual, competenceIndividual, CompObjectProperties.CourseContextOf);
			addCompulsoryLiteral(compulsory, competenceIndividual, courseContextIndividual, compOntologyManager);
		}
	}

	private static void addRequirementLiteral(String requirements, CompOntologyManager compOntologyManager, Individual courseContextIndividual) {
		if (requirements != null) {
			Property requirementsLiteral = extractRequirementsLiteral(compOntologyManager);
			courseContextIndividual.addLiteral(requirementsLiteral, requirements);
		}
	}

	private static Property extractRequirementsLiteral(CompOntologyManager compOntologyManager) {
		Property requirementsLiteral = compOntologyManager.getM().createProperty(CompOntologyAccess.encode("requirements"));
		return requirementsLiteral;
	}

	private static void addCompulsoryLiteral(String compulsory, Individual competenceIndividual, Individual courseContextIndividual, CompOntologyManager compOntologyManager) {
		// Property literal =
		// compOntologyManager.getM().createProperty(CompOntologyAccess.encode("compulsory"));

		if (compulsory != null) {
			compOntologyManager.getUtil().createObjectPropertyWithIndividual(courseContextIndividual, competenceIndividual, CompObjectProperties.CompulsoryOf);
		} else {
			compOntologyManager.getUtil().deleteObjectPropertyWithIndividual(courseContextIndividual, competenceIndividual, CompObjectProperties.CompulsoryOf);
		}
	}

	private static void testResult(CompOntologyManager compOntologyManager) {
		compOntologyManager.begin();

		CompFileUtil compFileUtil = new CompFileUtil(compOntologyManager.getM());
		try {
			compFileUtil.writeOntologyout();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		compOntologyManager.close();
	}

	public static void delete(String course) {
		CompOntologyManager compOntologyManager = startManager();
		CompOntologyAccess util = compOntologyManager.getUtil();
		Individual courseContextIndividual = createCourseContext(course, util);

		// ObjectProperty courseContextOfProperty =
		// compOntologyManager.getM().getObjectProperty(util.encode(CompObjectProperties.CourseContextOf.name()));
		// courseContextIndividual.removeAll(courseContextOfProperty);

		courseContextIndividual.remove();

		compOntologyManager.close();

		testResult(compOntologyManager);

	}

	private static CompOntologyManager startManager() {
		CompOntologyManager compOntologyManager = new CompOntologyManager();
		compOntologyManager.begin();
		compOntologyManager.getM().enterCriticalSection(false);
		return compOntologyManager;
	}

	public static String getRequirements(String course) {
		CompOntologyManager compOntologyManager = new CompOntologyManager();
		compOntologyManager.begin();
		Individual courseContextIndividual = createCourseContext(course, compOntologyManager.getUtil());
		Property requirementsLiteral = extractRequirementsLiteral(compOntologyManager);
		String result = courseContextIndividual.getProperty(requirementsLiteral).asTriple().getObject().getLiteralLexicalForm();
		compOntologyManager.close();
		return result;
	}
}
