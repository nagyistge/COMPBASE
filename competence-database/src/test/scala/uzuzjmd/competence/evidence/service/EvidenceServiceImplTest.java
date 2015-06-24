package uzuzjmd.competence.evidence.service;

import uzuzjmd.competence.evidence.model.LMSSystems;
import uzuzjmd.competence.service.rest.client.dto.UserCourseListItem;
import uzuzjmd.competence.service.rest.client.dto.UserCourseListResponse;

public class EvidenceServiceImplTest {

	// @Test
	public void test2() {
		MoodleEvidenceRestServiceImpl evidenceServiceImpl = new MoodleEvidenceRestServiceImpl("root", "voyager");
		UserCourseListResponse result = evidenceServiceImpl.getCourses("julian.dehne@gmail.com", LMSSystems.moodle.toString(), "Universität Potsdam");
		for (UserCourseListItem item : result) {
			System.out.println(item.getName());
			System.out.println(item.getCourseid());
		}
	}
}
