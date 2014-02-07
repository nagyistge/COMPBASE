package arbeitsagentur;

import java.util.ArrayList;
import java.util.List;

import arbeitsagentur.generated.berufe.Beruf;
import arbeitsagentur.generated.berufe.Berufe;
import arbeitsagentur.generated.kompetenzen.Kompmerkmal;
import arbeitsagentur.generated.kompsuchworte.Suchwort;
import arbeitsagentur.generated.kompsuchworte.Suchworte;

public class FullCompetence extends Kompmerkmal {
	public Suchworte suchworte;
	public Berufe berufe;
	public List<String> kontexte;
	public List<FullCompetence> unterKompetenzen;

	public FullCompetence(Kompmerkmal kom) {
		this.art = kom.getArt();
		this.berufe = new Berufe();
		this.bezeichnung = kom.getBezeichnung();
		this.id = kom.getId();
		this.kompParent = kom.getKompParent();
		this.kontexte = new ArrayList<String>();
		this.kursCodenr = kom.getKursCodenr();
		this.rev = kom.getRev();
		this.suchw = kom.getSuchw();
		this.suchworte = new Suchworte();
		this.unterKompetenzen = new ArrayList<FullCompetence>();
	}

	public FullCompetence(Suchworte suchworte, Berufe berufe,
			List<String> kontexte, List<FullCompetence> unterKompetenzen) {
		super();
		this.suchworte = suchworte;
		this.berufe = berufe;
		this.kontexte = kontexte;
		this.unterKompetenzen = unterKompetenzen;
	}

	@Override
	public String toString() {
		String result = "Kompetenz: " + super.bezeichnung + "\n";
		result += "		Berufe: ";
		for (Beruf beruf : berufe.getBeruf()) {
			result += beruf.getName();
			result += " ";
		}
		result += "\n		Suchworte: ";
		for (Suchwort suchwort : suchworte.getSuchwort()) {
			result += suchwort.getContent();
			result += ", ";
		}
		result += "\n		Kontexte: ";
		for (String string : kontexte) {
			result += string;
			result += " ";
		}
		result += "\n		Unterkompetenzen: ";
		for (FullCompetence fullCompetence : unterKompetenzen) {
			result += fullCompetence.getBezeichnung();
			result += " ";
		}
		return result;
	}
}
