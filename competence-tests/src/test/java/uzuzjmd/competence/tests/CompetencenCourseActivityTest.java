package uzuzjmd.competence.tests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import uzuzjmd.competence.main.RestServer;
import uzuzjmd.competence.shared.dto.UserCourseListItem;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import de.unipotsdam.anh.dao.ActivityDao;
import de.unipotsdam.anh.dao.CourseDao;

public class CompetencenCourseActivityTest {

	private static final String course = "15";
	private static final String activityURL = "activityURL";
	
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
	
	@AfterClass
	public static void tearDown() {
		t.stop();
	}
	
	@Test
	public void testCreateSuggestedCourseForCompetence() {
		final int status1 = CourseDao.addSuggestedCourseForCompetence("r1", course);
		final int status2 = CourseDao.addSuggestedCourseForCompetence("r2", course);
		final int status3 = CourseDao.addSuggestedCourseForCompetence("r3", course);
		
		Assert.assertEquals(200, status1);
		Assert.assertEquals(200, status2);
		Assert.assertEquals(200, status3);

		final List<String> competencen = getCompetenceFormSuggestedCourse();
		final List<String> tmp = Arrays.asList("r1","r2","r3");
		
		for(String c : competencen) {
			Assert.assertTrue(tmp.contains(c));
		}
		
		final List<UserCourseListItem> courses = getSuggestedCourseForCompetence();
		Assert.assertEquals("15", String.valueOf(courses.get(0).getCourseid()));
	}
	
	@Test
	public void testDeleteSuggestedCourseForCompetence() {
		final int status1 = CourseDao.deleteSuggestedCourseForCompetence("r1", course);
		final int status2 = CourseDao.deleteSuggestedCourseForCompetence("r2", course);
		final int status3 = CourseDao.deleteSuggestedCourseForCompetence("r3", course);
		
		Assert.assertEquals(200, status1);
		Assert.assertEquals(200, status2);
		Assert.assertEquals(200, status3);
		
		List<String> competencen = getCompetenceFormSuggestedCourse();
		
		Assert.assertEquals(0, competencen.size());
	}
	
	@Test
	public void testCreateSuggestedActivityForCompetence() {
		final int status1 = ActivityDao.addSuggestedActivityForCompetence("t1", activityURL);
		final int status2 = ActivityDao.addSuggestedActivityForCompetence("t2", activityURL);
		final int status3 = ActivityDao.addSuggestedActivityForCompetence("t3", activityURL);
		
		Assert.assertEquals(200, status1);
		Assert.assertEquals(200, status2);
		Assert.assertEquals(200, status3);

		final List<String> competencen = getCompetenceFormSuggestedActivity();
		final List<String> tmp = Arrays.asList("t1","t2","t3");
		
		for(String c : competencen) {
			Assert.assertTrue(tmp.contains(c));
		}
	}
	
	@Test
	public void testDeleteSuggestedActivityForCompetence() {
		final int status1 = ActivityDao.deleteSuggestedActivityForCompetence("t1", activityURL);
		final int status2 = ActivityDao.deleteSuggestedActivityForCompetence("t2", activityURL);
		final int status3 = ActivityDao.deleteSuggestedActivityForCompetence("t3", activityURL);
		
		Assert.assertEquals(200, status1);
		Assert.assertEquals(200, status2);
		Assert.assertEquals(200, status3);
	}
	
	
	private List<String> getCompetenceFormSuggestedCourse() {
		// TODO need URL for get SuggestedCourseForCompetence
		Client client1 = Client.create();
		WebResource webResource2 = client1.resource("http://localhost:8084/competences/SuggestedCourseForCompetence/get/"
										+ course);
		String[] result = null;
		try {
			result = webResource2
					.accept(MediaType.APPLICATION_JSON)
					.get(String[].class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client1.destroy();
		}
		
		return Arrays.asList(result);
	}
	
	private List<UserCourseListItem> getSuggestedCourseForCompetence() {
		// TODO need URL for get CompetenceFromSuggestedCourse
		final String selectedCompetence = "r1";
		Client client1 = Client.create();
		WebResource webResource2 = client1.resource("http://localhost:8084/competences/SuggestedCourseForCompetence/"
										+ selectedCompetence);
		UserCourseListItem[] result = null;
		try {
			result = webResource2
					.accept(MediaType.APPLICATION_JSON)
					.get(UserCourseListItem[].class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client1.destroy();
		}
		
		return Arrays.asList(result);
	}
	
	
	private List<String> getCompetenceFormSuggestedActivity() {
		// TODO need URL for get SuggestedCourseForCompetence
		Client client1 = Client.create();
		WebResource webResource2 = client1.resource("http://localhost:8084/competences/SuggestedActivityForCompetence/get/"
										+ course);
		String[] result = null;
		try {
			result = webResource2
					.accept(MediaType.APPLICATION_JSON)
					.get(String[].class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client1.destroy();
		}
		
		return Arrays.asList(result);
	}
	
	private List<String> getSuggestedActivityForCompetence() {
		// TODO need URL for get CompetenceFromSuggestedCourse
		final String selectedCompetence = "r1";
		Client client1 = Client.create();
		WebResource webResource2 = client1.resource("http://localhost:8084/competences/SuggestedActivityForCompetence/"
										+ selectedCompetence);
		String[] result = null;
		try {
			result = webResource2
					.accept(MediaType.APPLICATION_JSON)
					.get(String[].class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client1.destroy();
		}
		
		return Arrays.asList(result);
	}
}
