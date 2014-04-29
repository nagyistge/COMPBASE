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
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uzuzjmd.competence.rcd.generated.Rdceo;
import uzuzjmd.competence.service.CompetenceServiceImpl;
import uzuzjmd.competence.service.rest.dto.CatchwordXMLTree;
import uzuzjmd.competence.service.rest.dto.CompetenceXMLTree;
import uzuzjmd.competence.service.rest.dto.OperatorXMLTree;

/**
 * Root resource (exposed at "competences" path)
 */
@Path("/competences")
public class CompetenceServiceRest {

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
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/add")
	public void addRcdeo(Rdceo input) {
		System.out.println("Competences inserted");
		CompetenceServiceImpl competenceServiceImpl = new CompetenceServiceImpl();
		competenceServiceImpl.insertCompetence(input);
	}

	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/tree/xml/crossdomain/{course}/{cache}")
	public Response getCompetenceTree(@PathParam("course") String course, @PathParam("cache") String cache, @QueryParam(value = "selectedCatchwords") String selectedCatchwords,
			@QueryParam(value = "selectedOperators") String selectedOperators) {

		CompetenceXMLTree[] result = null;
		String[] selectedCatchwordArray = null;
		String[] selectedOperatorsArray = null;
		if (selectedCatchwords == null || selectedOperators == null) {
			result = CompetenceServiceWrapper.getCompetenceTree(null, null, course);
		} else {
			selectedCatchwordArray = selectedCatchwords.split(",");
			selectedOperatorsArray = selectedOperators.split(",");
			result = CompetenceServiceWrapper.getCompetenceTree(selectedCatchwordArray, selectedOperatorsArray, course);
		}

		Response response = buildCrossDomainResponse(result, cache.equals("cached"));
		return response;
	}

	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/operatortree/xml/crossdomain/{course}/{cache}")
	public Response getOperatorTree(@PathParam("course") String course, @PathParam("cache") String cache, @QueryParam(value = "selectedCatchwords") String selectedCatchwords,
			@QueryParam(value = "selectedOperators") String selectedOperators) {

		OperatorXMLTree[] result = null;
		String[] selectedCatchwordArray = null;
		String[] selectedOperatorsArray = null;
		if (selectedCatchwords == null || selectedOperators == null) {
			result = CompetenceServiceWrapper.getOperatorTree(null, null, course);
		} else {
			selectedCatchwordArray = selectedCatchwords.split(",");
			selectedOperatorsArray = selectedOperators.split(",");
			result = CompetenceServiceWrapper.getOperatorTree(selectedCatchwordArray, selectedOperatorsArray, course);
		}

		Response response = buildCrossDomainResponse(result, cache.equals("cached"));
		return response;
	}

	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/catchwordtree/xml/crossdomain/{course}/{cache}")
	public Response getCatchwordTree(@PathParam("course") String course, @PathParam("cache") String cache, @QueryParam(value = "selectedCatchwords") String selectedCatchwords,
			@QueryParam(value = "selectedOperators") String selectedOperators) {
		CatchwordXMLTree[] result = null;
		String[] selectedCatchwordArray = null;
		String[] selectedOperatorsArray = null;
		if (selectedCatchwords == null || selectedOperators == null) {
			result = CompetenceServiceWrapper.getCatchwordTree(null, null, course);
		} else {
			selectedCatchwordArray = selectedCatchwords.split(",");
			selectedOperatorsArray = selectedOperators.split(",");
			result = CompetenceServiceWrapper.getCatchwordTree(selectedCatchwordArray, selectedOperatorsArray, course);
		}

		Response response = buildCrossDomainResponse(result, cache.equals("cached"));
		return response;
	}

	@Consumes(MediaType.APPLICATION_XML)
	@POST
	@Path("/coursecontext/create/xml/crossdomain/{course}/{compulsory}")
	public Response linkCompetencesToCourseContext(@PathParam("course") String course, @PathParam("compulsory") String compulsory, @QueryParam(value = "competences") String competences,
			@QueryParam(value = "requirements") String requirements) {
		CompetenceServiceWrapper.linkCompetencesToCourse(course, competences, compulsory, requirements);
		// todo stuff here
		return Response.ok("competences linked").header("Access-Control-Allow-Origin", "*").build();
	}

	@Consumes(MediaType.APPLICATION_XML)
	@POST
	@Path("/coursecontext/delete/xml/crossdomain/{course}")
	public Response deleteCourseContext(@PathParam("course") String course) {
		CompetenceServiceWrapper.delete(course);
		// todo stuff here
		return Response.ok("competences deleted from course:" + course).header("Access-Control-Allow-Origin", "*").build();
	}

	// @Consumes(MediaType.APPLICATION_XML)
	// @POST
	// @Path("/coursecontext/xml/crossdomain/{course}")
	// public Response linkCompetencesToCourseContext(@PathParam("course")
	// String course, @QueryParam(value = "competences") String competences) {
	// CompetenceServiceWrapper.linkCompetencesToCourse(course, competences);
	// // todo stuff here
	// return Response.ok("it has worked").header("Access-Control-Allow-Origin",
	// "*").build();
	// }

	@Produces(MediaType.TEXT_HTML)
	@GET
	@Path("/coursecontext/requirements/xml/crossdomain/{course}/{cache}")
	public Response getRequirements(@PathParam("course") String course, @PathParam("cache") String cache) {
		String result = CompetenceServiceWrapper.getRequirements(course);
		return buildCrossDomainResponse(result, cache.equals("cached"));
	}

	private Response buildCrossDomainResponse(Object result, Boolean cache) {
		if (cache) {
			CacheControl control = new CacheControl();
			control.setMaxAge(10000);
			Response response = Response.status(200).entity(result).cacheControl(control).header("Access-Control-Allow-Origin", "*").build();
			return response;
		} else {
			Response response = Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
			return response;
		}

	}
}