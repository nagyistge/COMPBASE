package uzuzjmd.competence.evidence.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.core.Response;

import uzuzjmd.competence.evidence.service.rest.dto.UserTree;

@WebService(name = "EvidenceService", targetNamespace = "http://service.evidence.competence.uzuzjmd/")
public interface EvidenceService {

	/**
	 * Liefert eine XML-mappbaren UserTree zurück, der zeigt, welcher User
	 * welche Aktivität durchführen kann
	 * 
	 * @param course
	 * @return
	 */
	@WebMethod
	public abstract UserTree[] getUserTree(String course);

	@WebMethod
	public abstract Response getUserTreeCrossDomain(String course);

	public abstract String[] getOrganizations();

	public abstract String[] getLMSSystems();

	public abstract String[] getCourses(String user, String lmsSystem, String organization);

	public abstract Boolean exists(String user, String password);

}
