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

import uzuzjmd.competence.rcd.generated.Rdceo;
import uzuzjmd.competence.service.CompetenceServiceImpl;

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
		return new ArrayList<Rdceo>(Arrays.asList(competenceServiceImpl
				.getCompetences()));
	}

		

	/**
	 * Link the competences to a course context
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
	public Response linkCompetencesToCourseContextJson(
			@PathParam("course") String course,
			@PathParam("compulsory") String compulsory,
			@QueryParam(value = "competences") final List<String> competences,
			@QueryParam(value = "requirements") String requirements) {
		
		Boolean compulsoryBoolean = RestUtil.convertCompulsory(compulsory);
		CompetenceServiceWrapper.linkCompetencesToCourse(course, competences,
				compulsoryBoolean, requirements);
		// todo stuff here
		return Response.ok("competences linked").build();
	}

	/**
	 * DELETE the course context
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
	 * @param course
	 * @param cache
	 * @return
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/coursecontext/requirements/{course}/{cache}")
	public Response getRequirements(@PathParam("course") String course,
			@PathParam("cache") String cache) {
		String result = CompetenceServiceWrapper.getRequirements(course);
		return RestUtil.buildCachedResponse(result, cache.equals("cached"));
	}

	
}