package uzuzjmd.api_services.alchemy;

import interfaces.ITextAnalysis;
import uzuzjmd.api_services.ApiKeyGenerator;
import uzuzjmd.api_services.UZUZWebservice;

import com.sun.jersey.api.client.WebResource;

public class AlchemyImpl extends UZUZWebservice implements ITextAnalysis {
	
	
	@Override
	public Results getRankedConcepts(String text) {
		text = encodeText(text);
		WebResource webResource = client.resource("http://access.alchemyapi.com/calls/text/TextGetRankedConcepts?apikey=" + ApiKeyGenerator.getAlchemyAPIAccessKey()+"&text="+text);
		return webResource.get(Results.class);		
	}

	private String encodeText(String text) {
		return text.replace(" ", "%20");
	}

}
