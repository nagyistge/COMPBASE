package interfaces;

import java.util.List;

import uzuzjmd.api_services.synonyms.Words;

public interface ISynonym {
	Words getSynonyms(String word);
	Boolean areSynonmys(String string1, String string2);
}
