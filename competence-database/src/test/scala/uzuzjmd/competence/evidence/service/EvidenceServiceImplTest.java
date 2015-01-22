package uzuzjmd.competence.evidence.service;

import org.junit.Test;

public class EvidenceServiceImplTest {

	@Test
	public void test() {
		MoodleEvidenceRestServiceImpl evidenceServiceImpl = new MoodleEvidenceRestServiceImpl(
				"localhost", "bitnami_moodle", "root", "voyager");
		evidenceServiceImpl.getMoodleEvidences("2", "2");
	}

}
