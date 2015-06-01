package uzuzjmd.competence.evidence.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.core.Response;

import uzuzjmd.competence.service.rest.client.dto.UserCourseListResponse;
import uzuzjmd.competence.service.rest.client.dto.UserTree;

@WebService(name = "EvidenceService", targetNamespace = "http://service.evidence.competence.uzuzjmd/")
public interface EvidenceService {

	/**
	 * (Mögliche) Aktivitäten der User in dem Kontext
	 * 
	 * Liefert eine XML-Baum zurück, der zeigt, welcher User in dem Context
	 * welche Aktivität durchführen kann.
	 * 
	 * @param course
	 * @param lmssystem
	 * @param organization
	 * @return
	 */
	@WebMethod
	public abstract UserTree[] getUserTree(String course, String lmssystem, String organization);

	/**
	 * TODO comment
	 * 
	 * @param course
	 * @param usertree
	 * @param lmssystem
	 * @param organization
	 */
	public abstract void addUserTree(String course, UserTree[] usertree, String lmssystem, String organization);

	/**
	 * (Mögliche) Aktivitäten der User in dem Kontext
	 * 
	 * Liefert das gleiche Ergebnis wie getUserTree aber mit Cache.
	 * 
	 * @param course
	 * @param lmssystem
	 * @param organization
	 * @return
	 */
	@WebMethod
	public abstract Response getUserTreeCrossDomain(String course, String lmssystem, String organization);

	public abstract String[] getOrganizations();

	public abstract String[] getLMSSystems();

	public abstract UserCourseListResponse getCourses(String user, String lmsSystem, String organization);

	public abstract void addCourses(String user, UserCourseListResponse usertree, String lmssystem, String organization);

	public abstract Boolean exists(String user, String password, String lmsSystem, String organization);

}
