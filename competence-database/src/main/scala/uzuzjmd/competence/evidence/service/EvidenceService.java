package uzuzjmd.competence.evidence.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import uzuzjmd.competence.evidence.model.Evidence;
import uzuzjmd.competence.evidence.model.MoodleEvidence;
import uzuzjmd.competence.evidence.service.moodle.MoodleContentResponse;
import uzuzjmd.competence.evidence.service.moodle.MoodleContentResponseList;
import uzuzjmd.competence.evidence.service.rest.dto.UserTree;

@WebService(name = "EvidenceService", targetNamespace = "http://service.evidence.competence.uzuzjmd/")
public interface EvidenceService {
	@WebMethod
	public MoodleEvidence[] getMoodleEvidences(String course, String user);

	@WebMethod
	public Evidence[] getEvidences(String user);

	@WebMethod
	MoodleEvidence[] getUserEvidencesforMoodleCourse(String course);

	/**
	 * Liefert eine XML-mappbaren UserTree zurück, der zeigt, welcher User
	 * welche Aktivität durchführen kann
	 * 
	 * @param course
	 * @return
	 */
	@WebMethod
	public abstract UserTree[] getUserTree(String course);

	// @WebMethod
	// public abstract MoodleEvidence[] getUserEvidencesforMoodleCourseAsTree(
	// String course);

	@WebMethod
	public abstract MoodleContentResponse[] getCourseContentXML(String course);

	@WebMethod
	public abstract MoodleContentResponseList getCourseContent(String course);
}
