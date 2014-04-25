package uzuzjmd.competence.evidence.service.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uzuzjmd.competence.evidence.model.Evidence;
import uzuzjmd.competence.evidence.model.MoodleEvidence;
import uzuzjmd.competence.evidence.service.EvidenceService;
import uzuzjmd.competence.evidence.service.MoodleEvidenceServiceImpl;
import uzuzjmd.competence.evidence.service.moodle.MoodleContentResponse;
import uzuzjmd.competence.evidence.service.moodle.MoodleContentResponseList;
import uzuzjmd.competence.evidence.service.rest.dto.UserTree;

/**
 * Implementiert den Moodle-RestService mit Grizzly Dies ist die Hauptklasse für
 * die Rest-Services, die auf Moodle zugreifen
 * 
 * @author julian
 * 
 */
@Path("/moodle")
public class MoodleEvidenceServiceRestImpl implements EvidenceService {

	public static MoodleEvidenceServiceImpl moodleServiceImpl;

	public MoodleEvidenceServiceRestImpl() {
	}

	@Path("/activities/json/{course}/{user}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public MoodleEvidence[] getMoodleEvidences(
			@PathParam("course") String course, @PathParam("user") String user) {
		return moodleServiceImpl.getMoodleEvidences(course, user);
	}

	@Path("/activities/xml/{course}/{user}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public MoodleEvidence[] getMoodleEvidencesasXML(
			@PathParam("course") String course, @PathParam("user") String user) {
		return moodleServiceImpl.getMoodleEvidences(course, user);
	}

	@Path("/other/{user}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Evidence[] getEvidences(@PathParam("user") String user) {
		return moodleServiceImpl.getEvidences(user);
	}

	@Path("/activities/json/{course}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public MoodleEvidence[] getUserEvidencesforMoodleCourse(
			@PathParam("course") String course) {
		return moodleServiceImpl.getUserEvidencesforMoodleCourse(course);
	}

	@Override
	@Path("/contents/json/{course}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MoodleContentResponseList getCourseContent(
			@PathParam("course") String course) {
		return moodleServiceImpl.getCourseContents(course);
	}

	@Override
	@Path("/contents/xml/{course}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public MoodleContentResponse[] getCourseContentXML(
			@PathParam("course") String course) {
		return moodleServiceImpl.getCourseContentXML(course);
	}

	@Path("/activities/xml/{course}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public MoodleEvidence[] getUserEvidencesforMoodleCourseAsXML(
			@PathParam("course") String course) {
		return moodleServiceImpl.getUserEvidencesforMoodleCourse(course);
	}

	@Override
	@Path("/activities/usertree/xml/{course}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public UserTree[] getUserTree(@PathParam("course") String course) {
		return moodleServiceImpl.getUserTree(course);
	}

	@Path("/activities/usertree/xml/crossdomain/{course}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getUserTreeCrossDomain(@PathParam("course") String course) {
		Response response = Response.status(200)
				.entity(moodleServiceImpl.getUserTree(course))
				.header("Access-Control-Allow-Origin", "*").build();
		return response;
	}

}
