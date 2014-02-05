package interfaces;

import uzuzjmd.api_services.alchemy.Results;

public interface ITextAnalysis {
	 Results getRankedConcepts(String text);
}
