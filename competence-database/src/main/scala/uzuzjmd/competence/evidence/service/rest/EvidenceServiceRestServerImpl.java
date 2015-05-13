package uzuzjmd.competence.evidence.service.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uzuzjmd.competence.evidence.service.EvidenceService;
import uzuzjmd.competence.evidence.service.moodle.MoodleCourseListResponse;
import uzuzjmd.competence.evidence.service.rest.dto.UserTree;

/**
 * Implementiert den Moodle-RestService mit Grizzly. Dies ist die Hauptklasse
 * für die Rest-Services, die auf Moodle oder Liferay zugreifen
 * 
 * @author Julian Dehne
 * 
 */
@Path("/lms")
public class EvidenceServiceRestServerImpl implements EvidenceService {

	public static EvidenceService evidenceService;

	public EvidenceServiceRestServerImpl() {
	}

	@Override
	@Path("/activities/usertree/xml/{course}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public UserTree[] getUserTree(@PathParam("course") String course) {
		return evidenceService.getUserTree(course);
	}

	@Path("/activities/usertree/xml/crossdomain/{course}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getUserTreeCrossDomain(@PathParam("course") String course) {
		Response response = Response.status(200).entity(evidenceService.getUserTree(course)).build();
		// Response response =
		// Response.status(200).entity(moodleServiceImpl.getCachedUserTree(course)).build();
		return response;
	}

	@Override
	public String[] getOrganizations() {
		return evidenceService.getOrganizations();
	}

	@Override
	public String[] getLMSSystems() {
		return evidenceService.getLMSSystems();
	}

	@Path("/courses/{lmsSystem}/{userEmail}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Override
	public MoodleCourseListResponse getCourses(@PathParam("userEmail") String useremail, @PathParam("lmsSystem") String lmsSystem, @QueryParam("organization") String organization) {
		return evidenceService.getCourses(useremail, lmsSystem, organization);
	}

	@Path("/user/exists")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Boolean exists(@QueryParam(value = "user") String user, @QueryParam(value = "password") String password) {
		return evidenceService.exists(user, password);
	}

}
