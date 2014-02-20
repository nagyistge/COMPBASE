package arbeitsagentur.generated;

import arbeitsagentur.FullCompetence;
import arbeitsagentur.generated.komphierarchie.Ebene1;
import arbeitsagentur.generated.komphierarchie.Ebene2;
import arbeitsagentur.generated.komphierarchie.Ebene3;
import arbeitsagentur.generated.komphierarchie.Hierarchie;

public class KontexteThread extends Thread {

	private Hierarchie hierarchie;
	private FullCompetence fullcompetence;	

	public KontexteThread(Hierarchie hierarchie, FullCompetence fullCompetence) {
		this.hierarchie = hierarchie;
		this.fullcompetence = fullCompetence;		
	}

	@Override
	public void run() {
		fillKontexte(hierarchie, fullcompetence);		
	}

	private void fillKontexte(Hierarchie hierarchie,
			FullCompetence fullCompetence) {
		for (Ebene1 ebene1 : hierarchie.getEbene1()) {
			extractKontextLevel1(fullCompetence, ebene1);
			for (Ebene2 ebene2 : ebene1.getEbene2()) {
				extractKontextLevel2(fullCompetence, ebene2);
				for (Ebene3 ebene3 : ebene2.getEbene3()) {
					extractKontextLevel3(fullCompetence, ebene3);
				}
			}
		}
	}

	private void extractKontextLevel3(FullCompetence fullCompetence,
			Ebene3 ebene3) {
		for (arbeitsagentur.generated.komphierarchie.Kompetenz kompetenz : ebene3
				.getKompetenz()) {
			if (kompetenz.getIdref().equals(fullCompetence.getId())) {
				fullCompetence.kontexte.add(ebene3.getBezeichnung());
			}
		}
	}

	private void extractKontextLevel2(FullCompetence fullCompetence,
			Ebene2 ebene2) {
		for (arbeitsagentur.generated.komphierarchie.Kompetenz kompetenz : ebene2
				.getKompetenz()) {
			if (kompetenz.getIdref().equals(fullCompetence.getId())) {
				fullCompetence.kontexte.add(ebene2.getBezeichnung());
			}
		}
	}

	private void extractKontextLevel1(FullCompetence fullCompetence,
			Ebene1 ebene1) {
		for (arbeitsagentur.generated.komphierarchie.Kompetenz kompetenz : ebene1
				.getKompetenz()) {
			if (kompetenz.getIdref().equals(fullCompetence.getId())) {
				fullCompetence.kontexte.add(ebene1.getBezeichnung());
			}
		}
	}

}
