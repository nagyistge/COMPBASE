package uzuzjmd.competence.service.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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

import uzuzjmd.competence.mapper.gui.HierarchieChangesToOnt;
import uzuzjmd.competence.mapper.gui.Ont2CompetenceGraph;
import uzuzjmd.competence.mapper.gui.Ont2CompetenceLinkMap;
import uzuzjmd.competence.mapper.gui.Ont2ProgressMap;
import uzuzjmd.competence.owl.access.CompOntologyManager;
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink;
import uzuzjmd.competence.owl.dao.Catchword;
import uzuzjmd.competence.owl.dao.Comment;
import uzuzjmd.competence.owl.dao.Competence;
import uzuzjmd.competence.owl.dao.CourseContext;
import uzuzjmd.competence.owl.dao.DaoFactory;
import uzuzjmd.competence.owl.dao.EvidenceActivity;
import uzuzjmd.competence.owl.dao.Operator;
import uzuzjmd.competence.owl.dao.Role;
import uzuzjmd.competence.owl.dao.StudentRole;
import uzuzjmd.competence.owl.dao.TeacherRole;
import uzuzjmd.competence.owl.dao.User;
import uzuzjmd.competence.owl.ontology.CompObjectProperties;
import uzuzjmd.competence.owl.validation.CompetenceGraphValidator;
import uzuzjmd.competence.rcd.generated.Rdceo;
import uzuzjmd.competence.service.CompetenceServiceImpl;
import uzuzjmd.competence.service.rest.client.dto.CompetenceLinksMap;
import uzuzjmd.competence.service.rest.client.dto.Graph;
import uzuzjmd.competence.service.rest.client.dto.HierarchieChangeSet;
import uzuzjmd.competence.service.rest.client.dto.ProgressMap;

/**
 * Root resource (exposed at "competences" path)
 */
@Path("/competences/json")
public class CompetenceServiceRestJSON extends CompetenceOntologyInterface {

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

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/updateHierarchie")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateHierarchie(@QueryParam("changes") List<String> changes) {

		HierarchieChangeSet changeSet = new HierarchieChangeSet().convertListToModel(changes);

		CompOntologyManager comp = initManagerInCriticalMode();
		HierarchieChangesToOnt.convertChanges(comp, changeSet);
		comp.close();

		return Response.ok("updated taxonomy").build();
	}

	/**
	 * Link the competences to a course context
	 * 
	 * @param course
	 *            (the id of the course)
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
	 * link a competence and a Competence-Evidence-Activity
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
	@Path("/link/create/{course}/{creator}/{role}/{linkedUser}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response linkCompetencesToUserJson(@PathParam("course") String course, @PathParam("creator") String creator, @PathParam("role") String role, @PathParam("linkedUser") String linkedUser,
			@QueryParam(value = "competences") List<String> competences, @QueryParam(value = "evidences") List<String> evidences) {

		CompOntologyManager compOntologyManager = initManagerInCriticalMode();
		Role creatorRole = convertRole(role, compOntologyManager);
		for (String evidence : evidences) {
			for (String competence : competences) {
				CourseContext courseContext = new CourseContext(compOntologyManager, course);
				User creatorUser = new User(compOntologyManager, creator, creatorRole, courseContext, creator);
				User linkedUserUser = new User(compOntologyManager, linkedUser, new StudentRole(compOntologyManager), courseContext, linkedUser);
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

	/**
	 * add a comment to a link
	 * 
	 * @param linkId
	 * @param user
	 * @param text
	 * @return
	 */
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Path("/link/comment/{linkId}/{user}/{courseContext}/{role}")
	public Response commentCompetence(@PathParam("linkId") String linkId, @PathParam("user") String user, @QueryParam("text") String text, @PathParam("courseContext") String courseContext,
			@PathParam("role") String role) {

		createUserIfNotExists(user, courseContext, role);

		createComment(linkId, user, text, courseContext, role);
		return Response.ok("link commented").build();
	}

	private void createComment(String linkId, String user, String text, String courseContext, String role) {
		CompOntologyManager compOntologyManager = initManagerInCriticalMode();

		// debug
		// CompFileUtil fileUtil = new CompFileUtil(compOntologyManager.getM());
		// try {
		// fileUtil.writeOntologyout();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// end debug

		Role creatorRole2 = convertRole(role, compOntologyManager);
		CourseContext coursecontext2 = new CourseContext(compOntologyManager, courseContext);
		User creator2 = new User(compOntologyManager, user, creatorRole2, coursecontext2, user);
		AbstractEvidenceLink abstractEvidenceLink = DaoFactory.getAbstractEvidenceDao(compOntologyManager, linkId);
		Comment comment = new Comment(compOntologyManager, text, creator2, System.currentTimeMillis(), text);
		comment.persist();
		abstractEvidenceLink.linkComment(comment);

		// // debug
		// CompFileUtil fileUtil2 = new
		// CompFileUtil(compOntologyManager.getM());
		// try {
		// fileUtil2.writeOntologyout();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// end debug
		closeManagerInCriticalMode(compOntologyManager);
	}

	private void createUserIfNotExists(String user, String courseContext, String role) {
		CompOntologyManager comp = initManagerInCriticalMode();
		Role creatorRole = convertRole(role, comp);
		creatorRole.persist(false);
		CourseContext coursecontext = new CourseContext(comp, courseContext);
		coursecontext.persist();
		User creator = new User(comp, user, creatorRole, coursecontext, user);
		creator.persist();
		comp.close();
	}

	private Role convertRole(String role, CompOntologyManager comp) {
		Role creatorRole = null;
		if (role.equals("student")) {
			creatorRole = new StudentRole(comp);
		} else {
			creatorRole = new TeacherRole(comp);
		}
		return creatorRole;
	}

	/*
	 * validate a link (this should only be done by teacher role (which should
	 * be checked in the frontend)
	 */
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/link/validate/{linkId}")
	public Response validateLink(@PathParam("linkId") String linkId) {
		Boolean isvalid = true;
		return handleLinkValidation(linkId, isvalid);
	}

	/**
	 * 
	 * invalidate a link (this should only be done by teacher role (which should
	 * be checked in the frontend)
	 * 
	 * @param linkId
	 * @return
	 */
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/link/invalidate/{linkId}")
	public Response invalidateLink(@PathParam("linkId") String linkId) {
		Boolean isvalid = false;
		return handleLinkValidation(linkId, isvalid);
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/link/delete/{linkId}")
	public Response deleteLink(@PathParam("linkId") String linkId) {
		CompOntologyManager manager = initManagerInCriticalMode();
		AbstractEvidenceLink abstractEvidenceLink = new AbstractEvidenceLink(manager, linkId, null, null, null, null, null, null, null, null);
		abstractEvidenceLink.delete();
		manager.close();
		return Response.ok("link deleted").build();
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/competence/delete")
	public Response deleteCompetence(@QueryParam("competences") List<String> competences) {
		CompOntologyManager manager = initManagerInCriticalMode();

		System.out.println("deleting competences" + competences);

		for (String string : competences) {
			Competence toDelete = new Competence(manager, string, string, null);
			toDelete.delete();
		}

		manager.close();
		return Response.ok("competences deleted").build();
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/competence/deleteTree")
	public Response deleteCompetenceTree(@QueryParam("competences") List<String> competences) {
		CompOntologyManager manager = initManagerInCriticalMode();

		System.out.println("deleting competences" + competences);

		for (String string : competences) {
			Competence toDelete = new Competence(manager, string, string, null);
			toDelete.deleteTree();
		}

		manager.close();
		return Response.ok("competences deleted").build();
	}

	/**
	 * gets the competencelinks map in order to show the overview for a
	 * specified user
	 * 
	 * @param user
	 * @return
	 */
	@GET
	@Path("/link/overview/{user}")
	@Produces(MediaType.APPLICATION_JSON)
	public CompetenceLinksMap getCompetenceLinksMap(@PathParam("user") String user) {
		CompOntologyManager comp = new CompOntologyManager();
		Ont2CompetenceLinkMap competenceLinkMap = new Ont2CompetenceLinkMap(comp, user);
		CompetenceLinksMap map = competenceLinkMap.getCompetenceLinkMap();
		// for (List<CompetenceLinksView> linkCollections :
		// map.getMapUserCompetenceLinks().values()) {
		// Collections.sort(linkCollections);
		// }
		return map;
	}

	@GET
	@Path("/link/progress/{course}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProgressMap getProgressM(@PathParam("course") String course, @QueryParam("competences") List<String> selectedCompetences) {

		ProgressMap result = null;
		CompOntologyManager comp = initManagerInCriticalMode();
		Ont2ProgressMap map = new Ont2ProgressMap(comp, course, selectedCompetences);
		result = map.getProgressMap();
		closeManagerInCriticalMode(comp);
		return result;
	}

	/**
	 * creates the given relationship
	 * 
	 * @param course
	 * @param linkedCompetence
	 * @param selectedCompetences
	 */
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/prerequisite/create/{course}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPrerequisite(@PathParam("course") String course, @QueryParam("linkedCompetence") String linkedCompetence, @QueryParam("selectedCompetences") List<String> selectedCompetences) {
		CompOntologyManager compOntologyManager = initManagerInCriticalMode();
		compOntologyManager.startReasoning();
		compOntologyManager.switchOffDebugg();
		Competence competence = new Competence(compOntologyManager, linkedCompetence, null, null);
		for (String string : selectedCompetences) {
			Competence preCompetence = new Competence(compOntologyManager, string, null, null);
			competence.addRequiredCompetence(preCompetence);
		}
		compOntologyManager.close();
		return Response.ok("prerequisite created").build();
	}

	/**
	 * deletes the given relationship
	 * 
	 * @param course
	 * @param linkedCompetence
	 * @param selectedCompetences
	 * 
	 * @param course
	 * @param linkedCompetence
	 * @param selectedCompetences
	 */
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/prerequisite/delete/{course}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePrerequisite(@PathParam("course") String course, @QueryParam("linkedCompetence") String linkedCompetence, @QueryParam("competences") List<String> selectedCompetences) {
		CompOntologyManager compOntologyManager = initManagerInCriticalMode();
		compOntologyManager.startReasoning();
		compOntologyManager.switchOffDebugg();
		Competence competence = new Competence(compOntologyManager, linkedCompetence, null, null);
		if (selectedCompetences == null || selectedCompetences.isEmpty()) {
			selectedCompetences = new LinkedList<String>();
			for (String string : competence.getRequiredCompetencesAsArray()) {
				selectedCompetences.add(string);
			}
		}
		for (String string : selectedCompetences) {
			Competence preCompetence = new Competence(compOntologyManager, string, null, null);
			competence.addNotRequiredCompetence(preCompetence);
		}
		compOntologyManager.close();
		return Response.ok("prerequisite deleted").build();
	}

	/**
	 * returns the whole graph (given filter)
	 * 
	 * @param selectedCompetences
	 * @return
	 */
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Path("/prerequisite/graph/{course}")
	@Produces(MediaType.APPLICATION_JSON)
	public Graph getPrerequisiteGraph(@QueryParam("selectedCompetences") List<String> selectedCompetences, @PathParam("course") String course) {
		CompOntologyManager comp = new CompOntologyManager();
		Ont2CompetenceGraph mapper = new Ont2CompetenceGraph(comp, selectedCompetences, course);
		return mapper.getCompetenceGraph();
	}

	/**
	 * gets the prerequisites for the given competence
	 * 
	 * @param forCompetence
	 * @return
	 */
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Path("/prerequisite/required/{course}")
	@Produces(MediaType.APPLICATION_JSON)
	public String[] getRequiredCompetences(@QueryParam("competence") String forCompetence) {
		CompOntologyManager compOntologyManager = initManagerInCriticalMode();
		compOntologyManager.startReasoning();
		compOntologyManager.switchOffDebugg();
		Competence competence = new Competence(compOntologyManager, forCompetence, null, null);
		String[] requiredCompetences = competence.getRequiredCompetencesAsArray();
		compOntologyManager.close();
		return requiredCompetences;
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Path("/operator")
	public String getOperatorForCompetence(@QueryParam("competence") String forCompetence) {
		CompOntologyManager comp = new CompOntologyManager();
		comp.begin();

		Competence competence = new Competence(comp, forCompetence, forCompetence, null);
		scala.collection.immutable.List<Operator> operators = competence.getAssociatedSingletonDaosAsDomain(CompObjectProperties.OperatorOf, Operator.class);

		String result = "";
		if (!operators.isEmpty()) {
			result = operators.head().getDefinition();
		}

		comp.close();
		return result;
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Path("/catchwords")
	public String getCatchwordsForCompetence(@QueryParam("competence") String forCompetence) {
		CompOntologyManager comp = new CompOntologyManager();
		comp.begin();

		Competence competence = new Competence(comp, forCompetence, forCompetence, null);
		List<Catchword> catchwords = competence.getCatchwordsAsJava();

		String result = "";
		if (!catchwords.isEmpty()) {
			for (Catchword catchword : catchwords) {
				result += catchword.getDefinition() + ",";
			}
			result = result.substring(0, result.length() - 1);
		}

		comp.close();
		return result;
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/addOne")
	public Response addCompetenceToModel(@QueryParam("competence") String forCompetence, @QueryParam("operator") String operator, @QueryParam("catchwords") List<String> catchwords,
			@QueryParam("superCompetences") List<String> superCompetences, @QueryParam("subCompetences") List<String> subCompetences) {
		CompOntologyManager compOntologyManager = initManagerInCriticalMode();
		String resultMessage = addCompetence(forCompetence, operator, catchwords, superCompetences, subCompetences, compOntologyManager);
		closeManagerInCriticalMode(compOntologyManager);

		return Response.ok(resultMessage).build();
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/editOne")
	public Response editCompetenceToModel(@QueryParam("competence") String forCompetence, @QueryParam("operator") String operator, @QueryParam("catchwords") List<String> catchwords,
			@QueryParam("superCompetences") List<String> superCompetences, @QueryParam("subCompetences") List<String> subCompetences, @QueryParam("originalCompetence") String orgininalCompetence) {

		CompOntologyManager compOntologyManager = initManagerInCriticalMode();
		Competence original = new Competence(compOntologyManager, orgininalCompetence, orgininalCompetence, null);
		original.delete();
		String resultMessage = addCompetence(forCompetence, operator, catchwords, superCompetences, subCompetences, compOntologyManager);
		closeManagerInCriticalMode(compOntologyManager);

		return Response.ok(resultMessage).build();
	}

	private String addCompetence(String forCompetence, String operator, List<String> catchwords, List<String> superCompetences, List<String> subCompetences, CompOntologyManager compOntologyManager) {

		Competence addedCompetence = new Competence(compOntologyManager, forCompetence, forCompetence, null);
		List<Competence> superCompetencesTyped = new LinkedList<Competence>();
		for (String competence : superCompetences) {
			Competence superCompetence = new Competence(compOntologyManager, competence, competence, null);
			superCompetencesTyped.add(superCompetence);
		}
		List<Competence> subCompetencesTyped = new LinkedList<Competence>();
		for (String competence : subCompetences) {
			Competence subCompetence = new Competence(compOntologyManager, competence, competence, null);
			subCompetencesTyped.add(subCompetence);
		}

		CompetenceGraphValidator competenceGraphValidator = new CompetenceGraphValidator(compOntologyManager, addedCompetence, superCompetencesTyped, subCompetencesTyped);

		if (competenceGraphValidator.isValid()) {
			addedCompetence.persist(true);
			for (String catchwordItem : catchwords) {
				Catchword catchword = new Catchword(compOntologyManager, catchwordItem, catchwordItem);
				catchword.persist(true);
				catchword.createEdgeWith(CompObjectProperties.CatchwordOf, addedCompetence);

			}
			Operator operatorDAO = new Operator(compOntologyManager, operator, operator);
			operatorDAO.persist(true);
			operatorDAO.createEdgeWith(CompObjectProperties.OperatorOf, addedCompetence);
			// new Catchword(compOntologyManager, )

			// addedCompetence.createEdgeWith(, edgeType);
			// CompetenceInstance competenceRoot = new
			// CompetenceInstance(compOntologyManager);
			// addedCompetence.addSuperClass(competenceRoot);
			for (Competence subCompetence : subCompetencesTyped) {
				subCompetence.addSuperCompetence(addedCompetence);
			}

			for (Competence superCompetence : superCompetencesTyped) {
				addedCompetence.addSuperCompetence(superCompetence);
			}

		}
		String resultMessage = competenceGraphValidator.getExplanationPath();

		return resultMessage;
	}

	private Response handleLinkValidation(String linkId, Boolean isvalid) {
		CompOntologyManager compOntologyManager = initManagerInCriticalMode();

		AbstractEvidenceLink abstractEvidenceLink = DaoFactory.getAbstractEvidenceDao(compOntologyManager, linkId);
		abstractEvidenceLink.addDataField(abstractEvidenceLink.ISVALIDATED(), isvalid);

		compOntologyManager.close();

		return Response.ok("link updated").build();
	}

}