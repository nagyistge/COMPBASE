package uzuzjmd.competence.service.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uzuzjmd.competence.mapper.gui.Ont2CompetenceLinkMap;
import uzuzjmd.competence.owl.access.CompOntologyManager;
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink;
import uzuzjmd.competence.owl.dao.Comment;
import uzuzjmd.competence.owl.dao.Competence;
import uzuzjmd.competence.owl.dao.CourseContext;
import uzuzjmd.competence.owl.dao.DaoFactory;
import uzuzjmd.competence.owl.dao.EvidenceActivity;
import uzuzjmd.competence.owl.dao.User;
import uzuzjmd.competence.rcd.generated.Rdceo;
import uzuzjmd.competence.service.CompetenceServiceImpl;
import uzuzjmd.competence.service.rest.dto.CompetenceLinksMap;

/**
 * Root resource (exposed at "competences" path)
 */
@Path("/competences/json")
public class CompetenceServiceRestJSON {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 * 
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<Rdceo> getRdceo() {
		System.out.println("Competences queried (rest)");
		// return "Got it!";
		CompetenceServiceImpl competenceServiceImpl = new CompetenceServiceImpl();
		return new ArrayList<Rdceo>(Arrays.asList(competenceServiceImpl.getCompetences()));
	}

	/**
	 * Link the competences to a course context
	 * 
	 * @param course
	 * @param compulsory
	 *            (optional)
	 * @param competences
	 * @param requirements
	 * @return
	 */
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/coursecontext/create/{course}/{compulsory}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response linkCompetencesToCourseContextJson(@PathParam("course") String course, @PathParam("compulsory") String compulsory,
			@QueryParam(value = "competences") final List<String> competences, @QueryParam(value = "requirements") String requirements) {

		Boolean compulsoryBoolean = RestUtil.convertCompulsory(compulsory);
		CompetenceServiceWrapper.linkCompetencesToCourse(course, competences, compulsoryBoolean, requirements);
		// todo stuff here
		return Response.ok("competences linked to course").build();
	}

	/**
	 * DELETE the course context
	 * 
	 * @param course
	 * @return
	 */
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/coursecontext/delete/{course}")
	public Response deleteCourseContextJSON(@PathParam("course") String course) {
		CompetenceServiceWrapper.delete(course);
		return Response.ok("competences deleted from course:" + course).build();
	}

	/**
	 * GET the requirements for a course context
	 * 
	 * @param course
	 * @param cache
	 * @return
	 */
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	@Path("/coursecontext/requirements/{course}")
	public String getRequirements(@PathParam("course") String course) {
		String result = CompetenceServiceWrapper.getRequirements(course);
		return result;
	}

	/*
	 * GET the selected competences for the selection
	 * 
	 * @param course
	 * 
	 * @param cache
	 * 
	 * @return
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/selected/{course}")
	public String[] getSelected(@PathParam("course") String course) {

		return CompetenceServiceWrapper.getSelected(course);
	}

	/**
	 * 
	 * @param course
	 * @param creator
	 * @param linkedUser
	 * @param competences
	 * @param evidences
	 * @return
	 */
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/link/create/{course}/{creator}/{linkedUser}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response linkCompetencesToUserJson(@PathParam("course") String course, @PathParam("creator") String creator, @PathParam("linkedUser") String linkedUser,
			@QueryParam(value = "competences") List<String> competences, @QueryParam(value = "evidences") List<String> evidences) {

		CompOntologyManager compOntologyManager = initManagerInCriticalMode();
		for (String evidence : evidences) {
			for (String competence : competences) {
				User creatorUser = new User(compOntologyManager, creator, null);
				User linkedUserUser = new User(compOntologyManager, linkedUser, null);
				CourseContext courseContext = new CourseContext(compOntologyManager, course);
				scala.collection.immutable.List<Comment> comments = null;
				EvidenceActivity evidenceActivity = new EvidenceActivity(compOntologyManager, evidence.split(",")[0], evidence.split(",")[1]);
				Competence competenceDao = new Competence(compOntologyManager, competence, null, null);
				AbstractEvidenceLink abstractEvidenceLink = new AbstractEvidenceLink(compOntologyManager, (evidence + competence), creatorUser, linkedUserUser, courseContext, comments,
						evidenceActivity, Long.valueOf(System.currentTimeMillis()), Boolean.valueOf(false), competenceDao);
				abstractEvidenceLink.persist();
			}
		}
		closeManagerInCriticalMode(compOntologyManager);
		return Response.ok("competences linked to evidences").build();
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/link/comment/{linkId}/{user}")
	public Response commentCompetence(@PathParam("linkId") String linkId, @PathParam("user") String user, @QueryParam("text") String text) {
		CompOntologyManager compOntologyManager = initManagerInCriticalMode();
		User creator = new User(compOntologyManager, user, null).getFullDao();
		Comment comment = new Comment(compOntologyManager, text, creator, System.currentTimeMillis());
		comment.persist();
		AbstractEvidenceLink abstractEvidenceLink = DaoFactory.getAbstractEvidenceDao(compOntologyManager, linkId);
		abstractEvidenceLink.linkComment(comment);
		closeManagerInCriticalMode(compOntologyManager);
		return Response.ok("competences linked to evidences").build();
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/link/validate/{linkId}/{user}")
	public Response validateLink(@PathParam("linkId") String linkId) {
		Boolean isvalid = true;
		return handleLinkValidation(linkId, isvalid);
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/link/invalidate/{linkId}/{user}")
	public Response invalidateLink(@PathParam("linkId") String linkId) {
		Boolean isvalid = false;
		return handleLinkValidation(linkId, isvalid);
	}

	@GET
	@Path("/link/overview/{user}")
	@Produces(MediaType.APPLICATION_JSON)
	public CompetenceLinksMap getCompetenceLinksMap(@PathParam("user") String user) {
		CompOntologyManager comp = new CompOntologyManager();
		Ont2CompetenceLinkMap competenceLinkMap = new Ont2CompetenceLinkMap(comp, user);
		CompetenceLinksMap map = competenceLinkMap.getCompetenceLinkMap();
		return map;

	}

	private Response handleLinkValidation(String linkId, Boolean isvalid) {
		CompOntologyManager compOntologyManager = initManagerInCriticalMode();
		AbstractEvidenceLink abstractEvidenceLink = DaoFactory.getAbstractEvidenceDao(compOntologyManager, linkId);
		abstractEvidenceLink.addDataField(abstractEvidenceLink.ISVALIDATED(), isvalid);
		closeManagerInCriticalMode(compOntologyManager);
		return Response.ok("link updated").build();
	}

	private void closeManagerInCriticalMode(CompOntologyManager compOntologyManager) {
		compOntologyManager.getM().leaveCriticalSection();
		compOntologyManager.close();
	}

	private CompOntologyManager initManagerInCriticalMode() {
		CompOntologyManager compOntologyManager = new CompOntologyManager();
		compOntologyManager.begin();
		compOntologyManager.getM().enterCriticalSection(false);
		return compOntologyManager;
	}

}