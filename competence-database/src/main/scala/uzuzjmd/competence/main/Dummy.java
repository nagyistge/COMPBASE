package uzuzjmd.competence.main;

import uzuzjmd.competence.owl.access.CompOntologyManager;
import uzuzjmd.competence.owl.dao.StudentRole;
import uzuzjmd.competence.owl.dao.User;

public class Dummy {
	public static void main(String[] args) {
		CompOntologyManager compOntologyManager = new CompOntologyManager();
		compOntologyManager.begin();
		User user = new User(compOntologyManager, "geßner", new StudentRole(compOntologyManager), null, null);
		user.delete();

		User user2 = new User(compOntologyManager, "student", new StudentRole(compOntologyManager), null, null);
		user2.delete();

		compOntologyManager.close();
	}
}
