package uzuzjmd.competence.service.rest;

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
import uzuzjmd.competence.service.rest.dto.CatchwordXMLTree;
import uzuzjmd.competence.service.rest.dto.CompetenceXMLTree;
import uzuzjmd.competence.service.rest.dto.OperatorXMLTree;

/**
 * Root resource (exposed at "competences" path)
 */
@Path("/competences/xml")
public class CompetenceServiceRestXML {

	/**
	 * new competences can be added via this function. They must be encoded as
	 * Rdceo see somewhere...
	 * 
	 * @param input
	 */
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/add")
	public void addRcdeo(Rdceo input) {
		System.out.println("Competences inserted");
		CompetenceServiceImpl competenceServiceImpl = new CompetenceServiceImpl();
		competenceServiceImpl.insertCompetence(input);
	}

	/**
	 * Get the GUI Competence TREE
	 * 
	 * @param course
	 * @param compulsory
	 * @param cache
	 * @param selectedCatchwords
	 * @param selectedOperators
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/competencetree/{course}/{compulsory}/{cache}")
	public Response getCompetenceTree(@PathParam("course") String course, @PathParam("compulsory") String compulsory, @PathParam("cache") String cache,
			@QueryParam(value = "selectedCatchwords") List<String> selectedCatchwords, @QueryParam(value = "selectedOperators") List<String> selectedOperators) {

		long beforeTime = System.currentTimeMillis();
		CompetenceXMLTree[] result = null;
		if (compulsory.equals("all")) {
			result = CompetenceServiceWrapper.getCompetenceTree(selectedCatchwords, selectedOperators, course, null);
		} else {
			Boolean compulsoryBoolean = RestUtil.convertCompulsory(compulsory);
			result = CompetenceServiceWrapper.getCompetenceTree(selectedCatchwords, selectedOperators, course, compulsoryBoolean);
		}
		long afterTime = System.currentTimeMillis();
		System.out.println((afterTime - beforeTime));
		Response response = RestUtil.buildCachedResponse(result, cache.equals("cached"));
		return response;
	}

	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/competencetree/coursecontext/{course}/{compulsory}/{cache}")
	public Response getCompetenceTreeForCourse(@PathParam("course") String course, @PathParam("compulsory") String compulsory, @PathParam("cache") String cache,
			@QueryParam(value = "selectedCatchwords") List<String> selectedCatchwords, @QueryParam(value = "selectedOperators") List<String> selectedOperators) {

		CompetenceXMLTree[] result = null;
		if (compulsory.equals("all")) {
			result = CompetenceServiceWrapper.getCompetenceTreeForCourse(selectedCatchwords, selectedOperators, course, null);
		} else {
			Boolean compulsoryBoolean = RestUtil.convertCompulsory(compulsory);
			result = CompetenceServiceWrapper.getCompetenceTreeForCourse(selectedCatchwords, selectedOperators, course, compulsoryBoolean);
		}

		Response response = RestUtil.buildCachedResponse(result, cache.equals("cached"));
		return response;
	}

	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/competencetree/coursecontextnofilter/{course}/{compulsory}/{cache}")
	public Response getCompetenceTreeForCourseWithoutFilter(@PathParam("course") String course, @PathParam("compulsory") String compulsory, @PathParam("cache") String cache,
			@QueryParam(value = "selectedCatchwords") List<String> selectedCatchwords, @QueryParam(value = "selectedOperators") List<String> selectedOperators) {

		CompetenceXMLTree[] result = null;
		if (compulsory.equals("all")) {
			result = CompetenceServiceWrapper.getCompetenceTreeForCourseNoFilter(selectedCatchwords, selectedOperators, course, null);
		} else {
			Boolean compulsoryBoolean = RestUtil.convertCompulsory(compulsory);
			result = CompetenceServiceWrapper.getCompetenceTreeForCourseNoFilter(selectedCatchwords, selectedOperators, course, compulsoryBoolean);
		}

		Response response = RestUtil.buildCachedResponse(result, cache.equals("cached"));
		return response;
	}

	/**
	 * Get the GUI operator tree
	 * 
	 * @param course
	 * @param cache
	 * @param selectedCatchwords
	 * @param selectedOperators
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/operatortree/{course}/{cache}")
	public Response getOperatorTree(@PathParam("course") String course, @PathParam("cache") String cache, @QueryParam(value = "selectedCatchwords") List<String> selectedCatchwords,
			@QueryParam(value = "selectedOperators") List<String> selectedOperators) {

		OperatorXMLTree[] result = CompetenceServiceWrapper.getOperatorTree(selectedCatchwords, selectedOperators, course);

		Response response = RestUtil.buildCachedResponse(result, cache.equals("cached"));
		return response;
	}

	/**
	 * Get the GUI Catchword Tree
	 * 
	 * @param course
	 * @param cache
	 * @param selectedCatchwords
	 * @param selectedOperators
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/catchwordtree/{course}/{cache}")
	public Response getCatchwordTree(@PathParam("course") String course, @PathParam("cache") String cache, @QueryParam(value = "selectedCatchwords") List<String> selectedCatchwords,
			@QueryParam(value = "selectedOperators") List<String> selectedOperators) {
		CatchwordXMLTree[] result = CompetenceServiceWrapper.getCatchwordTree(selectedCatchwords, selectedOperators, course);
		Response response = RestUtil.buildCachedResponse(result, cache.equals("cached"));
		return response;
	}

}