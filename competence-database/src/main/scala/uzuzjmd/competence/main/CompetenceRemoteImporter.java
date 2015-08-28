package uzuzjmd.competence.main;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import uzuzjmd.competence.csv.CompetenceBean;
import uzuzjmd.competence.owl.access.MagicStrings;

public class CompetenceRemoteImporter {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		if (args.length < 2) {
			System.err.println("Usage is java -jar CompetenceRemoteImporter path/to/kompetenzen.csv serverurl");
		}

		else {
			MagicStrings.CSVLOCATION = args[0];
			MagicStrings.RESTURLCompetence = args[1];
		}

		List<CompetenceBean> competenceBeans = CompetenceImporter.parseCompetenceBeans();

		Client client = ClientBuilder.newClient();
		// client.setConnectTimeout(300);
		// client.setReadTimeout(300);

		WebTarget webResource = client.target(MagicStrings.RESTURLCompetence + "/competences/xml/addCompetenceBean");
		System.out.println(webResource.getUri());
		webResource.request(MediaType.APPLICATION_XML).post(Entity.entity(competenceBeans.toArray(new CompetenceBean[0]), MediaType.APPLICATION_XML));

	}
}
