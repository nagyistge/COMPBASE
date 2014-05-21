package uzuzjmd.competence.gui.client.evidenceView;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.ContextFactory;
import uzuzjmd.competence.gui.client.evidenceView.EvidenceStackPanel.CompetenceLinksMapCodec;
import uzuzjmd.competence.gui.shared.dto.CompetenceLinksMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DecoratedStackPanel;

class StackPanelReloader {
	private DecoratedStackPanel decoratedStackPanel;
	private String username;
	private ContextFactory context;

	public StackPanelReloader(DecoratedStackPanel decoratedStackPanel,
			String username, ContextFactory context) {
		this.decoratedStackPanel = decoratedStackPanel;
		this.username = username;
		this.context = context;
	}

	public void reload() {
		this.decoratedStackPanel.clear();
		loadEvidencesFromServer(username, context);
	}

	private void loadEvidencesFromServer(final String userName,
			final ContextFactory context) {
		Resource resource = new Resource(context.getServerURL()
				+ "/competences/json/link/overview/" + userName);
		resource.get().send(new JsonCallback() {

			@Override
			public void onSuccess(Method arg0, JSONValue arg1) {
				CompetenceLinksMapCodec codec = GWT
						.create(CompetenceLinksMapCodec.class);
				CompetenceLinksMap competenceLinksMap = codec.decode(arg1);
				for (String competenceName : competenceLinksMap
						.getMapUserCompetenceLinks().keySet()) {
					decoratedStackPanel.add(
							new EvidenceLinkWidget(competenceLinksMap
									.getMapUserCompetenceLinks().get(
											competenceName),
									new StackPanelReloader(decoratedStackPanel,
											username, context), userName),
							competenceName);
				}

			}

			@Override
			public void onFailure(Method arg0, Throwable arg1) {
				// TODO Auto-generated method stub
				Window.alert("wuff:-(1");

			}
		});
	}

}