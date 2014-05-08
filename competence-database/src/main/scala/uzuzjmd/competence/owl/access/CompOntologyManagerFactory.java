package uzuzjmd.competence.owl.access;

public class CompOntologyManagerFactory {
	public static CompOntologyManager startManager() {
		CompOntologyManager compOntologyManager = new CompOntologyManager();
		compOntologyManager.begin();
		compOntologyManager.getM().enterCriticalSection(false);
		return compOntologyManager;
	}
}
