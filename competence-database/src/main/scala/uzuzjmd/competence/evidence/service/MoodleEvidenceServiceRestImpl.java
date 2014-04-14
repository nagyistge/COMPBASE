package uzuzjmd.competence.evidence.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uzuzjmd.competence.evidence.model.Evidence;
import uzuzjmd.competence.evidence.model.MoodleEvidence;
import uzuzjmd.competence.evidence.service.moodle.MoodleContentResponseList;

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

	@Path("/contents/json/{course}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MoodleContentResponseList getCourseContent(
			@PathParam("course") String course) {
		return moodleServiceImpl.getCourseContents(course);
	}

	@Path("/activities/xml/{course}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public MoodleEvidence[] getUserEvidencesforMoodleCourseAsTree(
			@PathParam("course") String course) {
		return moodleServiceImpl.getUserEvidencesforMoodleCourse(course);
	}

}
