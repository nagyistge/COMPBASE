package uzuzjmd.service.competence.rest;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uzuzjmd.rcd.generated.Langstring;
import uzuzjmd.rcd.generated.Rdceo;

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
		ArrayList<Rdceo> rdceos = new ArrayList<Rdceo>();
		Rdceo rdceo = new Rdceo();		
		rdceos.add(rdceo);
		return rdceos;
	}

	@POST
	@Path("/add")
	public void addRcdeo(@WebParam Rdceo input) {		
		System.out.println("Competences inserted");		
	}
}