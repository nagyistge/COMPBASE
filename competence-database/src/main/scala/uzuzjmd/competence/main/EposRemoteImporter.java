package uzuzjmd.competence.main;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import uzuzjmd.competence.shared.DESCRIPTORSETType;
import uzuzjmd.competence.shared.dto.EPOSTypeWrapper;


/**
 * provides a possibility to import competences via the rest interface if server is deployed
 */
public class EposRemoteImporter {

	public static void main(String[] args)
			throws JAXBException, IOException {

		List<DESCRIPTORSETType> eposCompetences = EposImporter
				.parseEPOSXML();
		// List<DESCRIPTORSETType> eposCompetences = null;
		Client client = ClientBuilder.newClient();
		// client.setConnectTimeout(300);
		// client.setReadTimeout(300);
		WebTarget webResource = client
				.target("http://fleckenroller.cs.uni-potsdam.de/app/competence-servlet/competence/competences/xml/learningtemplates/addEpos");
		System.out.println(webResource.getUri());
		EPOSTypeWrapper typeWrapper = new EPOSTypeWrapper();
		typeWrapper.setEposCompetences(eposCompetences
				.toArray(new DESCRIPTORSETType[0]));
		Response result = webResource.request(
				MediaType.APPLICATION_XML).post(
				Entity.entity(typeWrapper,
						MediaType.APPLICATION_XML));
		System.out.println(result);
	}
}
