package uzuzjmd.competence.evidence.service;

import java.util.HashMap;

import javax.ws.rs.core.Response;

import uzuzjmd.competence.service.rest.client.dto.UserCourseListResponse;
import uzuzjmd.competence.service.rest.client.dto.UserTree;

public class InMemoryEvidenceService extends AbstractEvidenceService {

	public HashMap<String, UserTree[]> courseUserTreeMap = new HashMap<String, UserTree[]>();
	public HashMap<String, UserCourseListResponse> userCoursesMap = new HashMap<String, UserCourseListResponse>();

	// public HashMap<String, HashMap<String, UserTree[]>> lmsCourseHashMap =
	// new HashMap<String, HashMap<String, UserTree[]>>;

	public InMemoryEvidenceService() {

	}

	@Override
	public UserTree[] getUserTree(String course, String lmssystem, String organization) {
		if (!courseUserTreeMap.containsKey(course)) {
			throw new BadParameterException("course " + course + " wurde nicht in dem System " + lmssystem + " gefunden");
		}
		return courseUserTreeMap.get(course);
	}

	@Override
	public Response getUserTreeCrossDomain(String course, String lmssystem, String organization) {
		throw new Error("docorator called");
	}

	@Override
	public UserCourseListResponse getCourses(String user, String lmsSystem, String organization) {
		if (!courseUserTreeMap.containsKey(user)) {
			throw new BadParameterException("user " + user + " wurde nicht in dem System " + lmsSystem + " gefunden");
		}
		return userCoursesMap.get(user);
	}

	@Override
	public Boolean exists(String user, String password, String lmsSystem, String organization) {
		return !userCoursesMap.keySet().isEmpty();
	}

	@Override
	public void addUserTree(String course, UserTree[] usertree, String lmssystem, String organization) {
		courseUserTreeMap.put(course, usertree);
	}

	@Override
	public void addCourses(String user, UserCourseListResponse usertree, String lmssystem, String organization) {
		userCoursesMap.put(user, usertree);

	}

}