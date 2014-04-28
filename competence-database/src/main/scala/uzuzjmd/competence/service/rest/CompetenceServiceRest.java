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
	@Path("/tree/xml/crossdomain/{course}")
	public Response getCompetenceTree(@PathParam("course") String course, @QueryParam(value = "selectedCatchwords") String selectedCatchwords,
			@QueryParam(value = "selectedOperators") String selectedOperators) {

		CompetenceXMLTree[] result = null;
		String[] selectedCatchwordArray = null;
		String[] selectedOperatorsArray = null;
		if (selectedCatchwords == null || selectedOperators == null) {
			result = CompetenceServiceWrapper.getCompetenceTree(null, null);
		} else {
			selectedCatchwordArray = selectedCatchwords.split(",");
			selectedOperatorsArray = selectedOperators.split(",");
			result = CompetenceServiceWrapper.getCompetenceTree(selectedCatchwordArray, selectedOperatorsArray);
		}

		Response response = Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}

	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/operatortree/xml/crossdomain/{course}")
	public Response getOperatorTree(@PathParam("course") String course, @QueryParam(value = "selectedCatchwords") String selectedCatchwords,
			@QueryParam(value = "selectedOperators") String selectedOperators) {

		OperatorXMLTree[] result = null;
		String[] selectedCatchwordArray = null;
		String[] selectedOperatorsArray = null;
		if (selectedCatchwords == null || selectedOperators == null) {
			result = CompetenceServiceWrapper.getOperatorTree(null, null);
		} else {
			selectedCatchwordArray = selectedCatchwords.split(",");
			selectedOperatorsArray = selectedOperators.split(",");
			result = CompetenceServiceWrapper.getOperatorTree(selectedCatchwordArray, selectedOperatorsArray);
		}

		Response response = Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}

	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/catchwordtree/xml/crossdomain/{course}")
	public Response getCatchwordTree(@PathParam("course") String course, @QueryParam(value = "selectedCatchwords") String selectedCatchwords,
			@QueryParam(value = "selectedOperators") String selectedOperators) {
		CatchwordXMLTree[] result = null;
		String[] selectedCatchwordArray = null;
		String[] selectedOperatorsArray = null;
		if (selectedCatchwords == null || selectedOperators == null) {
			result = CompetenceServiceWrapper.getCatchwordTree(null, null);
		} else {
			selectedCatchwordArray = selectedCatchwords.split(",");
			selectedOperatorsArray = selectedOperators.split(",");
			result = CompetenceServiceWrapper.getCatchwordTree(selectedCatchwordArray, selectedOperatorsArray);
		}

		Response response = buildCrossDomainResponse(result);
		return response;
	}

	@Consumes(MediaType.APPLICATION_XML)
	@POST
	@Path("/coursecontext/xml/crossdomain/{course}")
	public Response linkCompetencesToCourseContext(@QueryParam(value = "competences") List<String> competences) {
		System.out.println("found" + competences);

		// todo stuff here
		return Response.ok("it has worked").header("Access-Control-Allow-Origin", "*").build();
	}

	private Response buildCrossDomainResponse(CatchwordXMLTree[] result) {
		Response response = Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}

}