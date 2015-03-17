package uzuzjmd.competence.liferay.reflexion;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import uzuzjmd.competence.liferay.util.ContextUtil;
import uzuzjmd.competence.liferay.util.SOAUtil;

public class SelectedLearningTemplateDAO {
	public static void persist(String selectedLearningTemplate) {

		Client client = com.sun.jersey.api.client.Client.create();
		WebResource webResource = client.resource(SOAUtil.getRestserverUrl()
				+ "/competences/xml/learningtemplates/add");
		webResource
				.queryParam("userId",
						ContextUtil.getUserLoggedIn().getUserId() + "")
				.queryParam("groupId", ContextUtil.getGroup().getGroupId() + "")
				.queryParam("selectedTemplate", selectedLearningTemplate)
				.accept(MediaType.APPLICATION_XML).post();

	}
}
