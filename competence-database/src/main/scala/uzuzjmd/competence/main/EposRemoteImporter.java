package uzuzjmd.competence.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;

import uzuzjmd.competence.owl.access.MagicStrings;
import uzuzjmd.competence.shared.DESCRIPTORSETType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class EposRemoteImporter {

	public static void main(String[] args) throws JAXBException, IOException {

		if (args.length < 2) {
			System.err.println("Usage is java -jar EposRemoteImporter path/to/epos.xml serverurl");
		}

		else {
			MagicStrings.EPOSLocation = args[0];
			MagicStrings.RESTURLCompetence = args[1];
		}

		List<DESCRIPTORSETType> eposCompetences = EposImporter.parseEPOSXML();

		Client client = Client.create();
		// client.setConnectTimeout(300);
		// client.setReadTimeout(300);

		WebResource webResource = client.resource(MagicStrings.RESTURLCompetence + "/competences/xml/learningtemplates/addEpos");

		System.out.println(webResource.getURI());

		ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).post(ClientResponse.class, eposCompetences.toArray(new DESCRIPTORSETType[0]));

		if (response.getStatus() != 200) {
			System.out.println(response.toString());
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		InputStream output = response.getEntityInputStream();
		String result = IOUtils.toString(output, "UTF-8");

		System.out.println(result);

		client.destroy();
	}
}
