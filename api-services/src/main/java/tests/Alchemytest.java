package tests;

import org.junit.Test;

import uzuzjmd.api_services.alchemy.AlchemyImpl;
import uzuzjmd.api_services.alchemy.Results;

public class Alchemytest {

	//@Test
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
		Results results = alchemyImpl
				.getRankedConcepts("understanding women and their problems and needing a lot of input to create some useful information");
		for (Results.Concepts.Concept concept : results.getConcepts().getConcept()) {
			System.out.println(concept.getText());
		}
	}
	

}
