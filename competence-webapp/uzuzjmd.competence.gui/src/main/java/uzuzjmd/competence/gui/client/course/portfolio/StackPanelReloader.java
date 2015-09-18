package uzuzjmd.competence.gui.client.course.portfolio;

import java.util.ArrayList;
import java.util.List;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.course.portfolio.EvidenceStackPanel.CompetenceLinksMapCodec;
import uzuzjmd.competence.gui.client.viewcontroller.Controller;
import uzuzjmd.competence.service.rest.client.dto.CommentEntry;
import uzuzjmd.competence.service.rest.client.dto.CompetenceLinksMap;
import uzuzjmd.competence.service.rest.client.dto.CompetenceLinksView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

class StackPanelReloader {
	private DecoratedStackPanel decoratedStackPanel;
	private String username;
	public Boolean commentEntryWasSuccess;
	public String commentEntryIdLastUpdated = "";
	private int selected;

	public StackPanelReloader(DecoratedStackPanel decoratedStackPanel,
			String username, Boolean commentEntryWasSuccess) {
		this.decoratedStackPanel = decoratedStackPanel;
		this.username = username;
		this.commentEntryWasSuccess = commentEntryWasSuccess;
	}

	public void reload() {
		GWT.log("start reloading Stackpanel");
		selected = decoratedStackPanel.getSelectedIndex();
		this.decoratedStackPanel.clear();
		loadEvidencesFromServer(username);
		GWT.log("finish reloading Stackpanel");
	}

	public void setCommentEntryWasSuccess(Boolean commentEntryWasSuccess) {
		this.commentEntryWasSuccess = commentEntryWasSuccess;
	}

	private void loadEvidencesFromServer(final String userName) {
		Resource resource = new Resource(
				Controller.contextFactory.getServerURL()
						+ "/competences/json/link/overview/" + userName);
		resource.get().send(new MyJsonCallBack(this));
	}

	class MyJsonCallBack implements JsonCallback {

		private StackPanelReloader stackPanelReloader;

		public MyJsonCallBack(StackPanelReloader stackPanelReloader) {
			this.stackPanelReloader = stackPanelReloader;
		}

		@Override
		public void onSuccess(Method arg0, JSONValue arg1) {
			GWT.log("start decoding result");

			JSONValue tmp1 = arg1.isObject().get("mapUserCompetenceLinks");
			Integer tmp2 = tmp1.isObject().keySet().size();
			GWT.log("size of keyset is: " + tmp2);
			CompetenceLinksMapCodec codec = GWT
					.create(CompetenceLinksMapCodec.class);
			CompetenceLinksMap competenceLinksMap = codec.decode(arg1);
			// GWT.log("finish decoding decoding result"
			// + competenceLinksMap.getMapUserCompetenceLinks().keySet()
			// .size());
			for (String competenceName : tmp1.isObject().keySet()) {
				GWT.log("start loop");
				GWT.log(competenceLinksMap.toString());
				GWT.log("start get map");
				JSONArray competenceLinkViews = tmp1.isObject()
						.get(competenceName).isArray();
				GWT.log("start get map2");
				GWT.log("competencename is: " + competenceName);

				CompetenceLinksView[] links = new CompetenceLinksView[competenceLinkViews
						.size()];
				for (int i = 0; i < competenceLinkViews.size(); i++) {
					JSONObject tmpObject = competenceLinkViews.get(i)
							.isObject();

					JSONArray commentsObject = tmpObject.get("comments")
							.isArray();
					List<CommentEntry> comments = new ArrayList<CommentEntry>();
					for (int j = 0; j < commentsObject.size(); j++) {
						JSONObject tmp4 = commentsObject.get(j).isObject();
						CommentEntry comment = new CommentEntry(tmp4
								.get("userName").isString().stringValue()
								.replaceAll("\"", ""), tmp4.get("commentName")
								.isString().stringValue().replaceAll("\"", ""),
								Long.parseLong(tmp4.get("created").isNumber()
										.doubleValue()
										+ ""));
						comments.add(comment);
					}

					links[i] = new CompetenceLinksView(tmpObject
							.get("abstractLinkId").isString().stringValue()
							.replaceAll("\"", ""), tmpObject
							.get("evidenceTitel").isString().stringValue()
							.replaceAll("\"", ""), tmpObject.get("evidenceUrl")
							.isString().stringValue().replaceAll("\"", ""),
							comments, tmpObject.get("validated").isBoolean()
									.booleanValue());
				}

				Panel panel = new VerticalPanel();
				// panel.setStylePrimaryName("accordion-group");
				EvidenceLinkWidget evidenceWidget = new EvidenceLinkWidget(
						links, stackPanelReloader, username);
				panel.add(evidenceWidget);
				GWT.log("add div to panel");
				decoratedStackPanel.add(panel,
						"<div class=\"stackTitlePicture\"></div>"
								+ "<div class=\"stackTitle\">" + competenceName
								+ "</div>", true);
				GWT.log("finish add div to panel");
				decoratedStackPanel.showStack(selected);
			}

		}

		@Override
		public void onFailure(Method arg0, Throwable arg1) {
			// TODO Auto-generated method stub
			Window.alert("wuff:-(1");
		}

	}
}