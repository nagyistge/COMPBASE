package uzuzjmd.competence.main;



import uzuzjmd.competence.datasource.csv.CompetenceBean;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

public class CompetenceRemoteImporter {

	public static void main(String[] args)
			throws IOException {
		// TODO Auto-generated method stub

		// CompetenceImporter importer = new CompetenceImporter();
		List<CompetenceBean> competenceBeans = CompetenceImporter
				.parseCompetenceBeans();

		Client client = ClientBuilder.newClient();
		// client.setConnectTimeout(300);
		// client.setReadTimeout(300);

		WebTarget webResource = client
				.target("http://fleckenroller.cs.uni-potsdam.de/app/competence-servlet/competence/competences/xml/addCompetenceBean");
		// WebTarget webResource = client
		// .target("http://localhost:8080/competence-servlet/competence/competences/xml/addCompetenceBean");
		System.out.println(webResource.getUri());
		Response result = webResource.request(
				MediaType.APPLICATION_XML).post(
				Entity.entity(competenceBeans
						.toArray(new CompetenceBean[0]),
						MediaType.APPLICATION_XML));

		System.out.println(result);

	}
}
