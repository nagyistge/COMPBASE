package uzuzjmd.competence.service.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
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
		return new ArrayList<Rdceo>(Arrays.asList(competenceServiceImpl
				.getCompetences()));
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
	@Path("/tree/xml/crossdomain/{course}/{compulsory}/{cache}")
	public Response getCompetenceTree(
			@PathParam("course") String course,
			@PathParam("compulsory") String compulsory,
			@PathParam("cache") String cache,
			@QueryParam(value = "selectedCatchwords") String selectedCatchwords,
			@QueryParam(value = "selectedOperators") String selectedOperators) {

		Boolean compulsoryBoolean = false;
		if (compulsory.equals("true")) {
			compulsoryBoolean = true;
		}

		CompetenceXMLTree[] result = null;
		String[] selectedCatchwordArray = null;
		String[] selectedOperatorsArray = null;
		if (selectedCatchwords == null || selectedOperators == null) {
			result = CompetenceServiceWrapper.getCompetenceTree(null, null,
					course, compulsoryBoolean);
		} else {
			selectedCatchwordArray = selectedCatchwords.split(",");
			selectedOperatorsArray = selectedOperators.split(",");
			result = CompetenceServiceWrapper.getCompetenceTree(
					selectedCatchwordArray, selectedOperatorsArray, course,
					compulsoryBoolean);
		}

		Response response = buildCachedResponse(result,
				cache.equals("cached"));
		return response;
	}

	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/operatortree/xml/crossdomain/{course}/{cache}")
	public Response getOperatorTree(
			@PathParam("course") String course,
			@PathParam("cache") String cache,
			@QueryParam(value = "selectedCatchwords") String selectedCatchwords,
			@QueryParam(value = "selectedOperators") String selectedOperators) {

		OperatorXMLTree[] result = null;
		String[] selectedCatchwordArray = null;
		String[] selectedOperatorsArray = null;
		if (selectedCatchwords == null || selectedOperators == null) {
			result = CompetenceServiceWrapper.getOperatorTree(null, null,
					course);
		} else {
			selectedCatchwordArray = selectedCatchwords.split(",");
			selectedOperatorsArray = selectedOperators.split(",");
			result = CompetenceServiceWrapper.getOperatorTree(
					selectedCatchwordArray, selectedOperatorsArray, course);
		}

		Response response = buildCachedResponse(result,
				cache.equals("cached"));
		return response;
	}

	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/catchwordtree/xml/crossdomain/{course}/{cache}")
	public Response getCatchwordTree(
			@PathParam("course") String course,
			@PathParam("cache") String cache,
			@QueryParam(value = "selectedCatchwords") String selectedCatchwords,
			@QueryParam(value = "selectedOperators") String selectedOperators) {
		CatchwordXMLTree[] result = null;
		String[] selectedCatchwordArray = null;
		String[] selectedOperatorsArray = null;
		if (selectedCatchwords == null || selectedOperators == null) {
			result = CompetenceServiceWrapper.getCatchwordTree(null, null,
					course);
		} else {
			selectedCatchwordArray = selectedCatchwords.split(",");
			selectedOperatorsArray = selectedOperators.split(",");
			result = CompetenceServiceWrapper.getCatchwordTree(
					selectedCatchwordArray, selectedOperatorsArray, course);
		}

		Response response = buildCachedResponse(result,
				cache.equals("cached"));
		return response;
	}

	/**
	 * 
	 * @param course
	 * @param compulsory
	 *            (optional)
	 * @param competences
	 * @param requirements
	 * @return
	 */
	@Consumes(MediaType.APPLICATION_XML)
	@POST
	@Path("/coursecontext/create/xml/crossdomain/{course}/{compulsory}")
	public Response linkCompetencesToCourseContext(
			@PathParam("course") String course,
			@PathParam("compulsory") String compulsory,
			@QueryParam(value = "competences") final List<String> competences,
			@QueryParam(value = "requirements") String requirements) {
		CompetenceServiceWrapper.linkCompetencesToCourse(course, competences,
				compulsory, requirements);
		// todo stuff here
		return Response.ok("competences linked").build();
	}

	/**
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
	@Path("/coursecontext/create/json/crossdomain/{course}/{compulsory}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response linkCompetencesToCourseContextJson(
			@PathParam("course") String course,
			@PathParam("compulsory") String compulsory,
			@QueryParam(value = "competences") final List<String> competences,
			@QueryParam(value = "requirements") String requirements) {
		CompetenceServiceWrapper.linkCompetencesToCourse(course, competences,
				compulsory, requirements);
		// todo stuff here
		return Response.ok("competences linked").build();
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/coursecontext/delete/json/crossdomain/{course}")
	public Response deleteCourseContextJSON(@PathParam("course") String course) {
		CompetenceServiceWrapper.delete(course);
		// todo stuff here
		return Response.ok("competences deleted from course:" + course)
		// .header("Access-Control-Allow-Methods",
		// "POST, GET, UPDATE, DELETE, OPTIONS")
		// .header("Access-Control-Allow-Headers",
		// "content-type, x-http-method-override")
				.build();
	}

	// @Consumes(MediaType.APPLICATION_JSON)
	// @OPTIONS
	// @Produces(MediaType.APPLICATION_JSON)
	// @Path("/coursecontext/delete/json/crossdomain/{course}")
	// public Response deleteCourseContextJSONOptions(
	// @PathParam("course") String course) {
	// CompetenceServiceWrapper.delete(course);
	// // todo stuff here
	// return Response.ok().build();
	// }

	@Consumes(MediaType.APPLICATION_XML)
	@POST
	@Path("/coursecontext/delete/xml/crossdomain/{course}")
	public Response deleteCourseContext(@PathParam("course") String course) {
		CompetenceServiceWrapper.delete(course);
		// todo stuff here
		return Response.ok("competences deleted from course:" + course).build();
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
	public Response getRequirements(@PathParam("course") String course,
			@PathParam("cache") String cache) {
		String result = CompetenceServiceWrapper.getRequirements(course);
		return buildCachedResponse(result, cache.equals("cached"));
	}

	private Response buildCachedResponse(Object result, Boolean cache) {
		if (cache) {
			CacheControl control = new CacheControl();
			control.setMaxAge(10000);

			Response response = Response.status(200).entity(result)
					.cacheControl(control).build();
			return response;
		} else {
			Response response = Response.status(200).entity(result).build();
			return response;
		}

	}
}