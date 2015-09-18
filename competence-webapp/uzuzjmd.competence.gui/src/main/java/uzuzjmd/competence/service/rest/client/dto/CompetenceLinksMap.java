package uzuzjmd.competence.service.rest.client.dto;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompetenceLinksMap {

	/*
	 * maps linked competence to the view
	 */
	private HashMap<String, CompetenceLinksView[]> mapUserCompetenceLinks = new HashMap<String, CompetenceLinksView[]>();

	public CompetenceLinksMap() {

	}

	public CompetenceLinksMap(
			HashMap<String, CompetenceLinksView[]> mapUserCompetenceLinks) {
		super();
		this.mapUserCompetenceLinks = mapUserCompetenceLinks;
	}

	public HashMap<String, CompetenceLinksView[]> getMapUserCompetenceLinks() {
		return mapUserCompetenceLinks;
	}

	public void setMapUserCompetenceLinks(
			Map<String, CompetenceLinksView[]> mapUserCompetenceLinks) {
		this.mapUserCompetenceLinks.putAll(mapUserCompetenceLinks);
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
