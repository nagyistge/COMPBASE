package uzuzjmd.competence.service.rest.client.dto;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompetenceLinksMap {

	/*
	 * maps linked competence to the view
	 */
	private Map<String, List<CompetenceLinksView>> mapUserCompetenceLinks = new TreeMap<String, List<CompetenceLinksView>>();

	public CompetenceLinksMap() {

	}

	public CompetenceLinksMap(Map<String, List<CompetenceLinksView>> mapUserCompetenceLinks) {
		super();
		this.mapUserCompetenceLinks = mapUserCompetenceLinks;
	}

	public Map<String, List<CompetenceLinksView>> getMapUserCompetenceLinks() {
		return mapUserCompetenceLinks;
	}

	public void setMapUserCompetenceLinks(Map<String, List<CompetenceLinksView>> mapUserCompetenceLinks) {
		this.mapUserCompetenceLinks = mapUserCompetenceLinks;
	}

	// public CompetenceLinksMap(
	// TreeMap<String, SortedSet<CompetenceLinksView>> mapUserCompetenceLinks) {
	// super();
	// for (String element : mapUserCompetenceLinks.keySet()) {
	// List<CompetenceLinksView> output = new LinkedList<CompetenceLinksView>();
	// output.addAll(mapUserCompetenceLinks.get(element));
	// this.mapUserCompetenceLinks.put(element, output);
	// }
	//
	// }
}