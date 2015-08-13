package uzuzjmd.competence.liferay.reflexion;

import javax.ws.rs.core.MediaType;

import uzuzjmd.competence.liferay.util.ContextUtil;
import uzuzjmd.competence.liferay.util.SOAUtil;
import uzuzjmd.competence.shared.ReflectiveAssessmentsListHolder;
import uzuzjmd.competence.shared.SuggestedCompetenceGrid;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

public class SuggestedCompetenceGridDAO {

	// public static synchronized SuggestedCompetenceGrid getGrid(
	// String selectedLearningTemplate) {
	// System.out.println("fetching grid for:" + selectedLearningTemplate);
	//
	// if (selectedLearningTemplate.equals("test")) {
	// SuggestedCompetenceGrid result = new SuggestedCompetenceGrid();
	// SuggestedCompetenceRow row = new SuggestedCompetenceRow();
	// SuggestedCompetenceColumn column = new SuggestedCompetenceColumn();
	// column.setProgressInPercent(33);
	// column.setTestOutput("deimudday is here");
	// row.setSuggestedCompetenceColumns(Collections.singletonList(column));
	// result.setSuggestedCompetenceRows(Collections.singletonList(row));
	// return result;
	// }
	//
	// return new SuggestedCompetenceGrid();
	// }

	public static synchronized SuggestedCompetenceGrid getGrid(
			String selectedLearningTemplate) {

		System.out.println("fetching grid for: " + selectedLearningTemplate);

		Client client = com.sun.jersey.api.client.Client.create();
		WebResource webResource = client.resource(SOAUtil.getRestserverUrl()
				+ "/competences/xml/learningtemplates/gridview");
		SuggestedCompetenceGrid result = null;
		try {
			result = webResource
					.queryParam("userId",
							ContextUtil.getUserLoggedIn().getLogin() + "")
					.queryParam("groupId",
							ContextUtil.getGroup().getGroupId() + "")
					.queryParam("selectedTemplate", selectedLearningTemplate)
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
		} finally {
			client.destroy();
		}
		return result;
	}

	public static synchronized void updateReflexion(
			ReflectiveAssessmentsListHolder holder) {
		System.out.println("updating: " + holder.getSuggestedMetaCompetence());

		Client client = com.sun.jersey.api.client.Client.create();
		WebResource webResource = client.resource(SOAUtil.getRestserverUrl()
				+ "/competences/xml/learningtemplates/gridview/update");
		try {
			webResource
					.queryParam("userId",
							ContextUtil.getUserLoggedIn().getLogin() + "")
					.queryParam("groupId",
							ContextUtil.getGroup().getGroupId() + "")
					.type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML)
					.post(ReflectiveAssessmentsListHolder.class, holder);
		} catch (UniformInterfaceException e) {
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			e.printStackTrace();
		} catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		} finally {
			client.destroy();
		}
	}
}
