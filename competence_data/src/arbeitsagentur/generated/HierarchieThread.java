package arbeitsagentur.generated;

import java.util.List;

import arbeitsagentur.generated.komphierarchie.Ebene1;
import arbeitsagentur.generated.komphierarchie.Ebene2;
import arbeitsagentur.generated.komphierarchie.Ebene3;
import arbeitsagentur.generated.komphierarchie.Hierarchie;

public class HierarchieThread extends Thread{

	private Hierarchie hierarchie;
	private List<HierachieTriple> triple;



	public HierarchieThread(Hierarchie hierarchie, List<HierachieTriple> triple) {
		this.hierarchie = hierarchie;
		this.triple = triple;
	}
	
	
	@Override
	public void run() {
		parseHierarchieTriples(hierarchie, triple);
	}
	
	

	private void parseHierarchieTriples(Hierarchie hierarchie,
			List<HierachieTriple> triple) {
		for (Ebene1 ebene1 : hierarchie.getEbene1()) {				
			for (arbeitsagentur.generated.komphierarchie.Kompetenz kompetenz: ebene1.getKompetenz()) {
				triple.add(new HierachieTriple(ebene1.getId(), kompetenz.getIdref()));
			}
			for (Ebene2 ebene2: ebene1.getEbene2()) {
				for (arbeitsagentur.generated.komphierarchie.Kompetenz kompetenz: ebene2.getKompetenz()) {
					triple.add(new HierachieTriple(ebene2.getId(), kompetenz.getIdref()));
				}
				for (Ebene3 ebene3 : ebene2.getEbene3()) {
					for (arbeitsagentur.generated.komphierarchie.Kompetenz kompetenz: ebene3.getKompetenz()) {
						triple.add(new HierachieTriple(ebene3.getId(), kompetenz.getIdref()));
					}					
				}
			}
		}
	}
	
}
