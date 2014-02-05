package uzuzjmd.api_services.synonyms;

import interfaces.ISynonym;
import uzuzjmd.api_services.ApiKeyGenerator;
import uzuzjmd.api_services.UZUZWebservice;

import com.sun.jersey.api.client.WebResource;

public class BigThesaurusImpl extends UZUZWebservice implements ISynonym {
	
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
