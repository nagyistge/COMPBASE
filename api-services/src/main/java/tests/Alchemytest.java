package tests;

import org.junit.Test;

import uzuzjmd.api_services.alchemy.AlchemyImpl;
import uzuzjmd.api_services.alchemy.Results;

public class Alchemytest {

	@Test
	public void test() {
		AlchemyImpl alchemyImpl = new AlchemyImpl();
		Results results = alchemyImpl
				.getRankedConcepts("After the first exercise plan the pupils "
						+ "A project together. Establish on a schedule and know that" 
						+ "It must comply with certain time frame to achieve the project objective.");
		for (Results.Concepts.Concept concept : results.getConcepts().getConcept()) {
			System.out.println(concept.getText());
		}
	}
	
	
	@Test
	public void test2() {
		AlchemyImpl alchemyImpl = new AlchemyImpl();
		String s= "formulate a situation an independent judgment using expertise and specialized methods and justify";
		Results results = alchemyImpl
				.getRankedConcepts(s);
		for (Results.Concepts.Concept concept : results.getConcepts().getConcept()) {
			System.out.println(concept.getText());
		}
	}
	

}
