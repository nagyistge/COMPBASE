package uzuzjmd.competence.liferay.reflexion;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import uzuzjmd.competence.liferay.util.ContextUtil;
import uzuzjmd.competence.liferay.util.SOAUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

public class SuggestedCompetenceGridDAO {
	public static SuggestedCompetenceGrid getGrid(String selectedLearningTemplate) {	
		Client client = com.sun.jersey.api.client.Client.create();
		WebResource webResource = client.resource(SOAUtil
				.getRestserverUrl()
				+ "/competences/xml/learningtemplates/gridview");		
		SuggestedCompetenceGrid result = null;
		try {
			result = webResource
					.queryParam("userId",
							ContextUtil.getUserLoggedIn().getLogin() + "")
					.queryParam("groupId",
							ContextUtil.getGroup().getGroupId() + "")
					.queryParam("selectedTemplate",
							selectedLearningTemplate)
					.accept(MediaType.APPLICATION_XML)
					.get(SuggestedCompetenceGrid.class);
		} catch (UniformInterfaceException e) {			
			e.printStackTrace();			
		} catch (ClientHandlerException e) {			
			e.printStackTrace();			
		} catch (PortalException e) {			
			e.printStackTrace();			
		} catch (SystemException e) {		
			e.printStackTrace();			
		}
		return result;			
}

}
