package uzuzjmd.competence.service.rest;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.ws.rs.WebApplicationException;

import uzuzjmd.competence.mapper.gui.Ont2CompetenceTree;
import uzuzjmd.competence.owl.access.CompOntologyAccess;
import uzuzjmd.competence.owl.access.CompOntologyAccessScala;
import uzuzjmd.competence.owl.access.CompOntologyManager;
import uzuzjmd.competence.owl.access.CompOntologyManagerFactory;
import uzuzjmd.competence.owl.access.OntResult;
import uzuzjmd.competence.owl.dao.CourseContext;
import uzuzjmd.competence.owl.ontology.CompObjectProperties;
import uzuzjmd.competence.owl.queries.CompetenceQueries;
import uzuzjmd.competence.service.rest.database.dto.CatchwordXMLTree;
import uzuzjmd.competence.service.rest.database.dto.CompetenceXMLTree;
import uzuzjmd.competence.service.rest.database.dto.OperatorXMLTree;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Statement;

public class CompetenceServiceWrapper {

	private static Ont2CompetenceTree initOnt2Mapper(List<String> selectedCatchwordArray, List<String> selectedOperatorsArray, String course, Boolean compulsoryBoolean, String textFilter) {
		CompOntologyManager compOntologyManager = CompOntologyManagerFactory.startManager();
		if (selectedCatchwordArray == null) {
			selectedCatchwordArray = new LinkedList<String>();
		}
		if (selectedOperatorsArray == null) {
			selectedOperatorsArray = new LinkedList<String>();
		}
		Ont2CompetenceTree ont2CompetenceTree = new Ont2CompetenceTree(compOntologyManager, selectedCatchwordArray, selectedOperatorsArray, course, compulsoryBoolean, textFilter);
		compOntologyManager.close();
		return ont2CompetenceTree;
	}

	public static synchronized OperatorXMLTree[] getOperatorTree(List<String> selectedCatchwordArray, List<String> selectedOperatorsArray, String course) {
		Ont2CompetenceTree ont2CompetenceTree = initOnt2Mapper(selectedCatchwordArray, selectedOperatorsArray, course, false, "");
		OperatorXMLTree[] tmpResult = ont2CompetenceTree.getOperatorXMLTree().toArray(new OperatorXMLTree[0]);
		return tmpResult;
	}

	public static synchronized CatchwordXMLTree[] getCatchwordTree(List<String> selectedCatchwordArray, List<String> selectedOperatorsArray, String course) {
		Ont2CompetenceTree ont2CompetenceTree = initOnt2Mapper(selectedCatchwordArray, selectedOperatorsArray, course, false, "");
		CatchwordXMLTree[] tmpResult = ont2CompetenceTree.getCatchwordXMLTree().toArray(new CatchwordXMLTree[0]);
		return tmpResult;
	}

	public static CompetenceXMLTree[] getCompetenceTree(List<String> selectedCatchwordArray, List<String> selectedOperatorsArray, String course, Boolean compulsoryBoolean, String textFilter) {
		Ont2CompetenceTree ont2CompetenceTree = initOnt2Mapper(selectedCatchwordArray, selectedOperatorsArray, course, compulsoryBoolean, textFilter);
		List<CompetenceXMLTree> tmpResult = ont2CompetenceTree.getComptenceTree();
		assert (!tmpResult.isEmpty());
		return tmpResult.toArray(new CompetenceXMLTree[0]);
	}

	public static void linkCompetencesToCourse(String course, List<String> competences, Boolean compulsoryBoolean, String requirements) {

		CompOntologyManager compOntologyManager = CompOntologyManagerFactory.startManager();
		compOntologyManager.startReasoning();
		CompOntologyAccess util = compOntologyManager.getUtil();

		Individual courseContextIndividual = new CourseContext(compOntologyManager, course).createIndividual();
		addRequirementLiteral(requirements, compOntologyManager, courseContextIndividual);
		linkSingleCompetences(competences, compulsoryBoolean, requirements, compOntologyManager, util, courseContextIndividual);

		compOntologyManager.getM().leaveCriticalSection();
		compOntologyManager.getM().validate();

		compOntologyManager.close();
		// testResult(compOntologyManager);
	}

	/**
	 * create course context (if not exists)
	 * 
	 * @param course
	 * @param util
	 * @return
	 */
	// public static Individual createCourseContext(String course,
	// CompOntologyManager compOntologyManager) {
	// // CompOntologyAccess util = compOntologyManager.getUtil();
	// // OntClass courseContextClass =
	// // util.createOntClass(CompOntClass.CourseContext);
	// // Individual courseContextIndividual =
	// // util.createIndividualForString(courseContextClass, course);
	// CourseContext courseContext = new CourseContext(compOntologyManager,
	// course);
	// return courseContext.createIndividual();
	// }

	private static void linkSingleCompetences(List<String> competences, Boolean compulsoryBoolean, String requirements, CompOntologyManager compOntologyManager, CompOntologyAccess util,
			Individual courseContextIndividual) {
		if (competences == null || competences.isEmpty()) {
			throw new WebApplicationException(new Exception("Es wurden keine Kompetenzen übergeben"));
		}

		for (String competence : competences) {
			OntResult result = util.accessSingletonResource(competence);
			Individual competenceIndividual = result.getIndividual();
			util.createObjectPropertyWithIndividual(courseContextIndividual, competenceIndividual, CompObjectProperties.CourseContextOf);
			handleCompulsoryLink(compulsoryBoolean, competenceIndividual, courseContextIndividual, compOntologyManager);
		}
	}

	private static void addRequirementLiteral(String requirements, CompOntologyManager compOntologyManager, Individual courseContextIndividual) {
		if (requirements != null) {
			Property requirementsLiteral = extractRequirementsLiteral(compOntologyManager);
			Statement prop = courseContextIndividual.getProperty(requirementsLiteral);
			if (prop != null) {
				compOntologyManager.getM().remove(prop);
			}
			courseContextIndividual.addLiteral(requirementsLiteral, requirements);
		}
	}

	private static Property extractRequirementsLiteral(CompOntologyManager compOntologyManager) {
		Property requirementsLiteral = compOntologyManager.getM().createProperty(CompOntologyAccess.encode("requirements"));
		return requirementsLiteral;
	}

	private static void handleCompulsoryLink(Boolean compulsory, Individual competenceIndividual, Individual courseContextIndividual, CompOntologyManager compOntologyManager) {
		if (compulsory) {
			compOntologyManager.getUtil().createObjectPropertyWithIndividual(courseContextIndividual, competenceIndividual, CompObjectProperties.CompulsoryOf);
		} else {
			compOntologyManager.getUtil().deleteObjectPropertyWithIndividual(courseContextIndividual, competenceIndividual, CompObjectProperties.CompulsoryOf);
		}
	}

	public static void delete(String course) {
		CompOntologyManager compOntologyManager = CompOntologyManagerFactory.startManager();
		CourseContext courseContext = new CourseContext(compOntologyManager, course);
		compOntologyManager.close();
	}

	public static String getRequirements(String course) {
		CompOntologyManager compOntologyManager = new CompOntologyManager();
		compOntologyManager.begin();
		// Individual courseContextIndividual = createCourseContext(course,
		// compOntologyManager);
		Individual courseContextIndividual = new CourseContext(compOntologyManager, course).createIndividual();
		Property requirementsLiteral = extractRequirementsLiteral(compOntologyManager);
		Statement statement = courseContextIndividual.getProperty(requirementsLiteral);
		String result = "";
		if (statement != null) {
			result = statement.asTriple().getObject().getLiteralLexicalForm();
		}
		compOntologyManager.close();
		return result;
	}

	public static String[] getSelected(String course) {

		CompOntologyManager compOntologyManager = CompOntologyManagerFactory.startManager();
		CompetenceQueries queries = new CompetenceQueries(compOntologyManager.getM());
		List<String> result = new LinkedList<String>();
		ConcurrentLinkedQueue<Individual> competenceIndividuals = queries.getRelatedIndividualsDomainGiven(course, CompObjectProperties.CourseContextOf);
		Iterator<Individual> it = competenceIndividuals.iterator();
		while (it.hasNext()) {
			Individual competenceIndividual = it.next();
			OntClass competenceClass = competenceIndividual.getOntClass(true);
			result.add(CompOntologyAccessScala.getDefinitionString(competenceClass, compOntologyManager));
		}
		compOntologyManager.close();
		return result.toArray(new String[0]);
	}

	public static CompetenceXMLTree[] getCompetenceTreeForCourse(List<String> selectedCatchwords, List<String> selectedOperators, String course, Boolean compulsoryBoolean, String textFilter) {
		Ont2CompetenceTree ont2CompetenceTree = initOnt2Mapper(selectedCatchwords, selectedOperators, course, compulsoryBoolean, textFilter);
		// List<CompetenceXMLTree> tmpResult =
		// ont2CompetenceTree.getComptenceTreeForCourse();
		List<CompetenceXMLTree> tmpResult = ont2CompetenceTree.getComptenceTreeForCourse();
		assert (!tmpResult.isEmpty());
		return tmpResult.toArray(new CompetenceXMLTree[0]);
	}

	// public static CompetenceXMLTree[]
	// getCompetenceTreeForCourseNoFilter(List<String> selectedCatchwords,
	// List<String> selectedOperators, String course, Boolean compulsoryBoolean,
	// String textFilter) {
	// Ont2CompetenceTree ont2CompetenceTree =
	// initOnt2Mapper(selectedCatchwords, selectedOperators, course,
	// compulsoryBoolean, textFilter);
	// List<CompetenceXMLTree> tmpResult =
	// ont2CompetenceTree.getCompetenceTreeForCourseNoFilter();
	// assert (!tmpResult.isEmpty());
	// return tmpResult.toArray(new CompetenceXMLTree[0]);
	// }
}
