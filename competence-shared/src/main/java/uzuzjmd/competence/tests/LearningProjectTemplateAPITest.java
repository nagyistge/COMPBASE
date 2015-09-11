package uzuzjmd.competence.tests;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import uzuzjmd.competence.shared.dto.Graph;
import uzuzjmd.competence.shared.dto.GraphTriple;
import uzuzjmd.competence.shared.dto.LearningTemplateResultSet;
import uzuzjmd.competence.shared.dto.LearningTemplateResultSetWrapper;

public class LearningProjectTemplateAPITest {

	public static final String LABELNAME = "SuggestedCompetencePrerequisit";
	private static Graph graph;

	private static String learningTemplateName;
	private static LearningTemplateResultSet learningTemplateResultSet;
	private static LearningTemplateResultSetWrapper wrapper;
	private static LoggingFilter logginFilter;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		// TESTDATA

		graph = new Graph();
		graph.addTriple("using tags", "using JSP tags", LABELNAME, true);
		graph.addTriple("using JSP tags", "using primfaces tags", LABELNAME,
				true);
		graph.addTriple("using JSP tags", "using faces tags", LABELNAME, true);
		graph.addTriple("programming", "creating api", LABELNAME, true);
		graph.addTriple("creating api",
				"being the first person to generate a universal api",
				LABELNAME, true);

		GraphTriple first = new GraphTriple("using tags", "using JSP tags",
				LABELNAME, true);
		GraphTriple second = new GraphTriple("using JSP tags",
				"using primfaces tags", LABELNAME, true);
		GraphTriple third = new GraphTriple("programming", "creating api",
				LABELNAME, true);
		GraphTriple fourth = new GraphTriple("using JSP tags",
				"using faces tags", LABELNAME, true);
		GraphTriple fifth = new GraphTriple("creating api",
				"being the first person to generate a universal api",
				LABELNAME, true);

		HashMap<GraphTriple, String[]> map = new HashMap<GraphTriple, String[]>();
		map.put(first, new String[] { "programming", "jsp" });
		map.put(second, new String[] { "programming", "jsp" });
		map.put(fourth, new String[] { "programming", "jsp" });
		map.put(third, new String[] { "programming", "api" });
		map.put(fifth, new String[] { "programming", "api", "universality" });

		learningTemplateName = "TestLernprojekt";

		learningTemplateResultSet = new LearningTemplateResultSet(graph, map,
				learningTemplateName);
		wrapper = new LearningTemplateResultSetWrapper();
		wrapper.setLearningTemplateResultSet(learningTemplateResultSet);

		// set up logger
		Logger logger = Logger.getLogger(LearningProjectTemplateAPITest.class
				.getName());
		logger.setLevel(Level.ALL);

		logginFilter = new LoggingFilter(logger, true);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		graph = null;
		learningTemplateName = null;
	}

	@Test
	public void testCreateLearningProjectTemplate() {

		// make client call

		Client client = ClientBuilder.newBuilder()
				.register(JacksonFeature.class).build();

		try {

			// client.setConnectTimeout(300);
			// client.setReadTimeout(300);
			WebTarget webResource = client
					.target("http://localhost:8084/competences/xml/learningtemplate/add/"
							+ learningTemplateName)
					.register(logginFilter)
					.queryParam("learningTemplateResultSet",
							learningTemplateResultSet);

			// .queryParam("graph", graph)
			Response result = webResource.request(
					javax.ws.rs.core.MediaType.APPLICATION_XML).post(
					Entity.entity(learningTemplateResultSet,
							MediaType.APPLICATION_XML));

			System.out.println(result.getStatus());

			client.close();

		} finally {
			client.close();
		}
	}

	@Test
	public void testGetLearningProjectTemplate() {

		// make client call

		Client client = ClientBuilder.newClient();
		// client.setConnectTimeout(300);
		// client.setReadTimeout(300);
		try {

			WebTarget webResource = client.target(
					"http://localhost:8084/competences/xml/learningtemplate/get/"
							+ learningTemplateName).register(logginFilter);

			// .queryParam("graph", graph)
			LearningTemplateResultSet result = webResource.request(
					MediaType.APPLICATION_XML).get(
					LearningTemplateResultSet.class);

		} finally {
			client.close();
		}
	}
}
