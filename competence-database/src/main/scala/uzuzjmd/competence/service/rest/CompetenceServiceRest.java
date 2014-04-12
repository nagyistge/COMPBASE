package uzuzjmd.competence.service.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uzuzjmd.competence.rcd.generated.Rdceo;
import uzuzjmd.competence.service.CompetenceServiceImpl;

/**
 * Root resource (exposed at "rcdeo" path)
 */
@Path("/rcdeo")
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
}