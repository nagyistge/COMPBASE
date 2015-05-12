package uzuzjmd.competence.evidence.service.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uzuzjmd.competence.evidence.service.EvidenceService;
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

}
