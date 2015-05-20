package uzuzjmd.competence.evidence.service;

import org.junit.Test;

import uzuzjmd.competence.evidence.model.LMSSystems;
import uzuzjmd.competence.service.rest.client.UserCourseListItem;
import uzuzjmd.competence.service.rest.client.UserCourseListResponse;

public class EvidenceServiceImplTest {

	// @Test
	public void test1() {
		MoodleEvidenceRestServiceImpl evidenceServiceImpl = new MoodleEvidenceRestServiceImpl("localhost", "bitnami_moodle", "root", "voyager");
		evidenceServiceImpl.getMoodleEvidences("2", "2");
	}

	@Test
	public void test2() {
		MoodleEvidenceRestServiceImpl evidenceServiceImpl = new MoodleEvidenceRestServiceImpl("localhost", "bitnami_moodle", "root", "voyager", "root", "voyager");
		UserCourseListResponse result = evidenceServiceImpl.getCourses("julian.dehne@gmail.com", LMSSystems.moodle.toString(), "Universität Potsdam");
		for (UserCourseListItem item : result) {
			System.out.println(item.getName());
			System.out.println(item.getCourseid());
		}
	}
}
