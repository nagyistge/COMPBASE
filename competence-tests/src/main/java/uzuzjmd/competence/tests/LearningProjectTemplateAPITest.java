package uzuzjmd.competence.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import datastructures.graph.Graph;
import datastructures.graph.GraphTriple;
import uzuzjmd.competence.shared.learningtemplate.LearningTemplateResultSet;
import uzuzjmd.competence.shared.learningtemplate.LearningTemplateResultSetWrapper;

public class LearningProjectTemplateAPITest {

	public static final String LABELNAME = "SuggestedCompetencePrerequisit";
	private static Graph graph;

	private static String learningTemplateName;
	private static LearningTemplateResultSet learningTemplateResultSet;
	private static LearningTemplateResultSetWrapper wrapper;
	private static LoggingFilter logginFilter;
	private static String serveradress = "http://localhost:8084";

	private static String templateName = "TheTemplateName"; // the name of the
															// tested for
															// single-case

	// learningTemplate

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

		// add single learningTemplate
		addSingleRootLearningProjectTemplate();
		createLearningProjectTemplate();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		graph = null;
		learningTemplateName = null;

		// TODO: Delete Templates
	}

	/**
	 * creates a learningprojectTemplate
	 */
	public static void createLearningProjectTemplate() {

		// make client call

		Client client = ClientBuilder.newBuilder()
				.register(JacksonFeature.class).build();
		try {
			WebTarget webResource = client
					.target(serveradress
							+ "/competences/xml/learningtemplate/add/"
							+ learningTemplateName)
					.register(logginFilter)
					.queryParam("learningTemplateResultSet",
							learningTemplateResultSet);

			// .queryParam("graph", graph)
			Response result = webResource.request(MediaType.APPLICATION_XML)
					.post(Entity.entity(learningTemplateResultSet,
							MediaType.APPLICATION_XML));

			System.out.println(result.getStatus());

			client.close();

		} finally {
			client.close();
		}
	}

	/**
	 * test if learning project template can be used
	 */
	@Test
	public void testGetLearningProjectTemplate() {
		Client client = ClientBuilder.newClient();
		try {

			WebTarget webResource = client.target(
					serveradress + "/competences/xml/learningtemplate/get/"
							+ learningTemplateName).register(logginFilter);
			LearningTemplateResultSet result = webResource.request(
					MediaType.APPLICATION_XML).get(
					LearningTemplateResultSet.class);
			assertNotNull(result);
			client.close();
		} finally {
			client.close();
		}
	}

	/**
	 * creates a learning project template with one element
	 */
	public static void addSingleRootLearningProjectTemplate() {

		// make client call

		Client client = ClientBuilder.newBuilder()
				.register(JacksonFeature.class).build();

		try {

			// client.setConnectTimeout(300);
			// client.setReadTimeout(300);
			WebTarget webResource = client
					.target(serveradress + "/competences/json/addOne/")
					.register(logginFilter)
					.queryParam("competence",
							"The ability to jump high distances")
					.queryParam("operator", "jump")
					.queryParam("catchword", "ability")
					.queryParam("learningTemplateName", templateName);

			// .queryParam("graph", graph)
			Response result = webResource.request(MediaType.APPLICATION_JSON)
					.post(null);

			System.out.println(result.getStatus());

			client.close();

		} finally {
			client.close();
		}

	}

	/**
	 * check if template with one element can be returned
	 */
	@Test
	public void checkSingletonLearningTemplate() {
		// make client call

		Client client2 = ClientBuilder.newClient();
		// client.setConnectTimeout(300);
		// client.setReadTimeout(300);
		LearningTemplateResultSet result2 = null;
		try {

			WebTarget webResource = client2.target(
					serveradress + "/competences/xml/learningtemplate/get/"
							+ templateName).register(logginFilter);

			// .queryParam("graph", graph)
			result2 = webResource.request(MediaType.APPLICATION_XML).get(
					LearningTemplateResultSet.class);
			assertNotNull(result2);
			assertEquals(
					result2.getRoot().getLabel()
							.equals("The ability to jump high distances"), true);

			client2.close();
		} finally {
			client2.close();

		}
	}
}
