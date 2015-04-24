package uzuzjmd.competence.gui.client.viewcontroller;

import uzuzjmd.competence.gui.client.tabs.CompetenceCreationTab;
import uzuzjmd.competence.gui.client.tabs.CompetenceDeleteWidget;
import uzuzjmd.competence.gui.client.tabs.CompetenceHierarchieTab;
import uzuzjmd.competence.gui.client.tabs.CompetenceTab;
import uzuzjmd.competence.gui.client.tabs.GraphTab;
import uzuzjmd.competence.gui.client.tabs.LinkEvidenceTab;
import uzuzjmd.competence.gui.client.tabs.ProgressTab;

public class ReloadController {
	private CompetenceTab competenceTab;
	private LinkEvidenceTab linkTab;
	private ProgressTab progressTab;
	private CompetenceCreationTab competenceCreationTab;
	private CompetenceDeleteWidget competenceDeleteTab;
	private CompetenceHierarchieTab competenceHierarchieTab;
	public static GraphTab graphTab;

	public ReloadController(CompetenceTab competenceTab,
			LinkEvidenceTab linkTab, ProgressTab progressTab,
			CompetenceCreationTab competenceCreationTab,
			CompetenceDeleteWidget competenceDeleteTab,
			CompetenceHierarchieTab competenceHierarchieTab) {
		this.competenceTab = competenceTab;
		this.linkTab = linkTab;
		this.progressTab = progressTab;
		this.competenceCreationTab = competenceCreationTab;
		this.competenceDeleteTab = competenceDeleteTab;
		this.competenceHierarchieTab = competenceHierarchieTab;
	}

	public void reload() {
		linkTab.reload();
		progressTab.reload();
		if (graphTab != null) {
			graphTab.reloadPopups();
		}
		competenceCreationTab.reload();
		competenceDeleteTab.reload();
		competenceHierarchieTab.reload();
	}

	public CompetenceTab getCompetenceTab() {
		return competenceTab;
	}

	public void setCompetenceTab(CompetenceTab competenceTab) {
		this.competenceTab = competenceTab;
	}

	public LinkEvidenceTab getLinkTab() {
		return linkTab;
	}

	public void setLinkTab(LinkEvidenceTab linkTab) {
		this.linkTab = linkTab;
	}

	public ProgressTab getProgressTab() {
		return progressTab;
	}

	public void setProgressTab(ProgressTab progressTab) {
		this.progressTab = progressTab;
	}

}
