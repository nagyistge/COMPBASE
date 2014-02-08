package arbeitsagentur.generated;

import java.math.BigInteger;

import arbeitsagentur.FullCompetence;
import arbeitsagentur.generated.kompetenzen.Kompmerkmal;
import arbeitsagentur.generated.kompsuchworte.Suchwort;
import arbeitsagentur.generated.kompsuchworte.Suchworte;

public class SuchworteThread extends Thread {
	private Suchworte suchworte;
	private Kompmerkmal kompmerkmal;
	private FullCompetence fullCompetence;

	public SuchworteThread(Suchworte suchworte, Kompmerkmal kompmerkmal,
			FullCompetence fullCompetence) {
		this.suchworte = suchworte;
		this.kompmerkmal = kompmerkmal;
		this.fullCompetence = fullCompetence;
	}
	
	@Override
	public void run() {
		fillSuchwort(suchworte, kompmerkmal, fullCompetence);		
	}

	private void fillSuchwort(Suchworte suchworte, Kompmerkmal kompmerkmal,
			FullCompetence fullCompetence) {
		for (Suchwort suchwort : suchworte.getSuchwort()) {
			for (BigInteger integer : kompmerkmal.getSuchw()) {
				if (suchwort.getId().equals(integer)) {
					fullCompetence.suchworte.getSuchwort().add(suchwort);
				}
			}
		}
	}
}
