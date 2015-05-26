package uzuzjmd.competence.gui.client.taxonomy;

import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.context.LmsContextFactory;
import uzuzjmd.competence.gui.client.shared.widgets.taxonomy.CompetenceSelectionWidget;
import uzuzjmd.competence.gui.client.viewcontroller.Controller;
import uzuzjmd.competence.service.rest.client.dto.HierarchieChangeSet;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class CompetenceHierarchieTab extends Composite {

	private static CompetenceHierarchieTabUiBinder uiBinder = GWT
			.create(CompetenceHierarchieTabUiBinder.class);

	interface CompetenceHierarchieTabUiBinder extends
			UiBinder<Widget, CompetenceHierarchieTab> {
	}

	@UiField
	SimplePanel competenceHierarchieHolder;
	@UiField
	Button competenceHierarchieButton;

	@UiField
	Alert successAlert;

	@UiField
	Alert errorAlert;

	private CompetenceSelectionWidget competenceSelectionWidget;

	public CompetenceHierarchieTab(LmsContextFactory lmsContextFactory) {
		initWidget(uiBinder.createAndBindUi(this));
		competenceSelectionWidget = new CompetenceSelectionWidget(
				lmsContextFactory, "all", null, "Aktuelle Kompetenztaxonomie",
				false, true);
		competenceHierarchieHolder.add(competenceSelectionWidget);

	}

	@UiHandler("competenceHierarchieButton")
	void onButtonClick(ClickEvent event) {
		successAlert.setVisible(false);
		errorAlert.setVisible(false);
		HierarchieChangeSet changes = competenceSelectionWidget.getChanges();
		GWT.log("gotten changes count: " + changes.getElements().size());

		Resource resource = new Resource(
				Controller.contextFactory.getServerURL()
						+ "/competences/json/updateHierarchie");
		try {
			resource.addQueryParams("changes", changes.convertToListString())
					.post().send(new RequestCallback() {

						@Override
						public void onResponseReceived(Request request,
								Response response) {
							successAlert.setVisible(true);
							competenceSelectionWidget.clearChanges();
							Controller.reloadController.reload();
						}

						@Override
						public void onError(Request request, Throwable exception) {
							errorAlert.setVisible(true);
						};
					});
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorAlert.setVisible(true);
		}

	}

	public void reload() {
		competenceSelectionWidget.reload();
	}
}
