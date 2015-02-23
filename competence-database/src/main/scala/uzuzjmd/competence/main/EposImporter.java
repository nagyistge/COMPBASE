package uzuzjmd.competence.main;

import uzuzjmd.competence.owl.access.MagicStrings;

public class EposImporter {

	public static void main(String[] args) {
		if (!(args.length < 1)) {
			MagicStrings.EPOSLocation = args[0];
		}

		// todo import epos-competences to ontology

	}

}
