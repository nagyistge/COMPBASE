package arbeitsagentur.generated;

import arbeitsagentur.FullCompetence;
import arbeitsagentur.generated.berufe.Beruf;
import arbeitsagentur.generated.berufe.Berufe;
import arbeitsagentur.generated.berufe.Kompetenz;

public class BerufeThread extends Thread {
	private Berufe berufe;
	private FullCompetence fullCompetence;	

	public BerufeThread(Berufe berufe, FullCompetence fullCompetence) {
		this.berufe = berufe;
		this.fullCompetence = fullCompetence;
	}

	@Override
	public void run() {
		fillBerufe(berufe, fullCompetence);		
	}

	private void fillBerufe(Berufe berufe, FullCompetence fullCompetence) {
		for (Beruf beruf : berufe.getBeruf()) {
			for (Kompetenz kompetenz : beruf.getKompetenz()) {
				if (kompetenz.getIdref().equals(fullCompetence.getId())) {
					fullCompetence.berufe.getBeruf().add(beruf);
				}
			}
		}
	}
}
