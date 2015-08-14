package uzuzjmd.competence.main;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.MediaType;

import uzuzjmd.competence.csv.CompetenceBean;
import uzuzjmd.competence.owl.access.MagicStrings;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

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

		Client client = Client.create();
		// client.setConnectTimeout(300);
		// client.setReadTimeout(300);

		WebResource webResource = client.resource(MagicStrings.RESTURLCompetence + "/competences/xml/addCompetenceBean");

		System.out.println(webResource.getURI());

		ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).post(ClientResponse.class, competenceBeans.toArray(new CompetenceBean[0]));

		if (response.getStatus() != 200) {
			System.out.println(response.toString());
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		client.destroy();

	}

}
