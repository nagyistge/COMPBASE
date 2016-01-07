package uzuzjmd.competence.main;


import uzuzjmd.competence.datasource.csv.CompetenceBean;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * provides a possibility to import competences via the rest interface if server is deployed
 */
public class CompetenceRemoteImporter {

	public static void main(String[] args)
			throws IOException {

		List<CompetenceBean> competenceBeans = CompetenceImporter
				.parseCompetenceBeans();
		Client client = ClientBuilder.newClient();
		WebTarget webResource = client
				.target("http://fleckenroller.cs.uni-potsdam.de/app/competence-servlet/competence/competences/xml/addCompetenceBean");
		webResource.request(
				MediaType.APPLICATION_XML).post(
				Entity.entity(competenceBeans
						.toArray(new CompetenceBean[0]),
						MediaType.APPLICATION_XML));

	}
}
