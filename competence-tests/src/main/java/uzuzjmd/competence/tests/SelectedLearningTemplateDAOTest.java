package uzuzjmd.competence.tests;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import uzuzjmd.competence.main.RestServer;
import uzuzjmd.competence.shared.*;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URISyntaxException;

public class SelectedLearningTemplateDAOTest {

	private final static String user = "xunguyen";

	public static Thread t = new Thread(new Runnable() {
		public void run() {
			try {
				RestServer.startServer();
			} catch (IOException e) {
			} catch (URISyntaxException e) {
			}
		}
	});

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		t.start();
	}
	private void createTestUser() {
		Client client = Client.create();
		client.addFilter(new LoggingFilter(System.out));
		WebResource webResource = client.resource("http://localhost:8084"
				+ "/competences/user/create/" + user + "/teacher");

		try {
			webResource.queryParam("groupId", "user")
					.accept(MediaType.APPLICATION_JSON).post();
		} finally {
			client.destroy();
		}
	}

	@AfterClass
	public static void tearDown() {
		t.stop();
	}

	@Test
	public void testSelectTemplate() throws InterruptedException {
		Thread.sleep(400l);
		createTestUser();
		Client client = com.sun.jersey.api.client.Client.create();
		client.addFilter(new LoggingFilter(System.out));
		WebResource webResource = client.resource("http://localhost:8084"
				+ "/competences/learningtemplates/selected/add");
		System.out.println("FETCHING FROM_: " + webResource.getURI());
		try {

			webResource.queryParam("userId", user)
					.queryParam("groupId", "user")
					.queryParam("selectedTemplate", LearningTemplateDaoTest.learningTemplateName)
					.type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML)
					.post();
		} finally {
			client.destroy();
		}
	}

	/**
	 * tests that interface does not through exception may not return data if
	 * user has not previously selected learning templates for himself to learn
	 */
	@Test
	public void testSelectedTemplates() throws InterruptedException {
		testSelectTemplate();
		Client client = com.sun.jersey.api.client.Client.create();
		client.addFilter(new LoggingFilter(System.out));
		WebResource webResource = client.resource("http://localhost:8084"
				+ "/competences/learningtemplates/selected");
		System.out.println("FETCHING FROM_: " + webResource.getURI());
		StringList result = null;
		try {

			result = webResource.queryParam("userId", user)
					.queryParam("groupId", "user")
					.accept(MediaType.APPLICATION_XML).get(StringList.class);
		} finally {
			client.destroy();
		}
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testUpdateReflexion() throws InterruptedException {
		// setup data
		testSelectedTemplates();
		LearningTemplateDaoTest learningTemplateDaoTest = new LearningTemplateDaoTest();
		learningTemplateDaoTest.initTestGraph();
		learningTemplateDaoTest.testCreateTemplateWithGraph();
		SuggestedCompetenceGrid result = testFetchingGrid();
		Assert.assertNotNull(result);
		testSendingNewGrid(result);
	}

	private SuggestedCompetenceGrid testFetchingGrid() {
		Client client1 = Client.create();
		WebResource webResource2 = client1.resource("http://localhost:8084/competences/learningtemplates/gridview");
		SuggestedCompetenceGrid result = null;
		try {
			result = webResource2
					.queryParam("userId", user)
					.queryParam("groupId","user")
					.queryParam("selectedTemplate", LearningTemplateDaoTest.learningTemplateName)
					.accept(MediaType.APPLICATION_XML)
					.get(SuggestedCompetenceGrid.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client1.destroy();
		}
		return result;
	}

	private void testSendingNewGrid(SuggestedCompetenceGrid result) {
		System.out.println(result.getSuggestedCompetenceRows().get(0).getSuggestedCompetenceRowHeader());

		ReflectiveAssessmentsListHolder holder = result.getSuggestedCompetenceRows().get(0).getSuggestedCompetenceColumns().get(0).getReflectiveAssessmentListHolder();
		holder.getReflectiveAssessmentList().get(0).setIsLearningGoal(true);
		holder.getReflectiveAssessmentList().get(0).setAssessment(new Assessment().getItems().get(1));
		System.out.println("updating: " + holder.getSuggestedMetaCompetence());

		Client client = Client.create();
		client.addFilter(new LoggingFilter(System.out));
		WebResource webResource = client.resource("http://localhost:8084/competences/learningtemplates/gridview/update");
		try {
			 webResource
					.queryParam("userId", user)
					.queryParam("groupId", "user")
					.post(holder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.destroy();
		}
	}
}
