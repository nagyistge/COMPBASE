package uzuzjmd.competence.gui.client.tabs;

import java.util.Map;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.ContextFactory;
import uzuzjmd.competence.gui.client.competenceSelection.CompetenceSelectionWidget;
import uzuzjmd.competence.gui.client.progressView.ProgressEntry;
import uzuzjmd.competence.gui.shared.JsonUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ProgressTab extends Composite {

	private static ProgressTabUiBinder uiBinder = GWT
			.create(ProgressTabUiBinder.class);

	@UiField
	SimplePanel HrPanelContainer;
	@UiField
	VerticalPanel progressPlaceHolder;
	@UiField
	SimplePanel hrPanelContainer2;
	@UiField
	SimplePanel tabExplainationPanel;
	@UiField
	Panel competenceSelectionPanelPlaceholder;
	@UiField
	FocusPanel warningPlaceholder;
	private CompetenceSelectionWidget competenceSelectionWidget;
	private ContextFactory contextFactory;

	interface ProgressTabUiBinder extends UiBinder<Widget, ProgressTab> {
	}

	public ProgressTab(final ContextFactory contextFactory) {
		initWidget(uiBinder.createAndBindUi(this));

		this.contextFactory = contextFactory;
		this.tabExplainationPanel
				.add(new Label(
						"Hier können Sie die Zuordnung von den Kompetenzen und den Teilnehmern einsehen. Die Balken zeigen an, wie viele der ausgewählten Kompetenzen mit einer Aktivität eines Teilnehmers verknüpft wurden."));

		HTML html = new HTML(
				"<hr class=\"fancy-line\" style=\"width:100%;\" />");
		this.HrPanelContainer.add(html);

		competenceSelectionWidget = new CompetenceSelectionWidget(
				contextFactory, null, "coursecontext/");

		competenceSelectionPanelPlaceholder.add(competenceSelectionWidget);

		Resource resource = new Resource(contextFactory.getServerURL()
				+ "/competences/json/link/progress/"
				+ contextFactory.getCourseId());
		resource.get().send(new JsonCallback() {

			@Override
			public void onSuccess(Method arg0, JSONValue arg1) {
				Map<String, String> userProgressMap = JsonUtil.toMap(arg1);
				for (String userName : userProgressMap.keySet()) {
					progressPlaceHolder.add(new ProgressEntry(userName, Double
							.valueOf(userProgressMap.get(userName))));
				}
			}

			@Override
			public void onFailure(Method arg0, Throwable arg1) {
				GWT.log("could not get progress map from server for course context"
						+ contextFactory.getCourseId());

			}
		});

	}
}
