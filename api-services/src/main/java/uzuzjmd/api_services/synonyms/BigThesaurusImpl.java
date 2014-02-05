package uzuzjmd.api_services.synonyms;

import uzuzjmd.api_services.ApiKeyGenerator;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class BigThesaurusImpl implements ISynonym {
	private Client client;

	public BigThesaurusImpl() {
		ClientConfig clientConfig = new DefaultClientConfig();
		client = Client.create(clientConfig);
	}

	public Words getSynonyms(String word) {	
		WebResource webResource = client
				.resource("http://words.bighugelabs.com/api/2/"
						+ ApiKeyGenerator.getThesaursusApiKey() + "/" + word
						+ "/" + "xml");
		// WEBRESOURCE.ACCEPT(MEDIATYPE.APPLICATION_XML);
		Words synonyms = webResource.get(Words.class);
		return synonyms;
	}

	public Boolean areSynonmys(String string1, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

}
