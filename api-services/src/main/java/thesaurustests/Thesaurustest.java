package thesaurustests;

import java.util.List;

import junit.framework.TestCase;
import uzuzjmd.api_services.synonyms.BigThesaurusImpl;
import uzuzjmd.api_services.synonyms.Words;


public class Thesaurustest extends TestCase {
	
	public void testgetSynonyms() {
		BigThesaurusImpl bigThesaurusImpl = new BigThesaurusImpl();
		Words words = bigThesaurusImpl.getSynonyms("run");
		for (Words.W synonym : words.getW()) {
			System.out.println(synonym.getValue());
		}
	}
}
