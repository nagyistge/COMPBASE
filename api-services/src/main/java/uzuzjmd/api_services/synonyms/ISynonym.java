package uzuzjmd.api_services.synonyms;

import java.util.List;

public interface ISynonym {
	Words getSynonyms(String word);
	Boolean areSynonmys(String string1, String string2);
}
