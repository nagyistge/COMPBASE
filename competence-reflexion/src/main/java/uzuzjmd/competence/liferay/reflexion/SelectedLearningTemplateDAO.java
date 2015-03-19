package uzuzjmd.competence.liferay.reflexion;

import javax.ws.rs.core.MediaType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import uzuzjmd.competence.liferay.util.ContextUtil;
import uzuzjmd.competence.liferay.util.SOAUtil;

public class SelectedLearningTemplateDAO {
	public static void persist(String selectedLearningTemplate) {

		Client client = com.sun.jersey.api.client.Client.create();
		WebResource webResource = client.resource(SOAUtil.getRestserverUrl()
				+ "/competences/xml/learningtemplates/add");
		try {
			try {
				webResource
						.queryParam("userId",
								ContextUtil.getUserLoggedIn().getLogin() + "")
						.queryParam("groupId", ContextUtil.getGroup().getGroupId() + "")
						.queryParam("selectedTemplate", selectedLearningTemplate)
						.accept(MediaType.APPLICATION_XML).post();
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UniformInterfaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
}
