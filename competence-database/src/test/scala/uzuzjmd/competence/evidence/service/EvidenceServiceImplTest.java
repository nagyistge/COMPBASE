package uzuzjmd.competence.evidence.service;

import org.junit.Test;

public class EvidenceServiceImplTest {

	@Test
	public void test() {
		MoodleEvidenceServiceImpl evidenceServiceImpl = new MoodleEvidenceServiceImpl(
				"localhost", "bitnami_moodle", "root", "voyager");
		evidenceServiceImpl.getMoodleEvidences("2", "2");
	}

}
