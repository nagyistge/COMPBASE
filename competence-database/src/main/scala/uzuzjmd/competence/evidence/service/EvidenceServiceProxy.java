package uzuzjmd.competence.evidence.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.ws.rs.core.Response;

import uzuzjmd.competence.evidence.model.LMSSystems;
import uzuzjmd.competence.owl.access.MagicStrings;
import uzuzjmd.competence.service.rest.client.dto.UserCourseListResponse;
import uzuzjmd.competence.service.rest.client.dto.UserTree;

public class EvidenceServiceProxy implements EvidenceService {

	private EvidenceProviderMap evidenceProviderMap;

	public EvidenceServiceProxy() {
		this.evidenceProviderMap = new EvidenceProviderMap();

		Properties prop = new Properties();
		String propfFileName = "evidenceserver.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propfFileName);
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (prop.get("liferayEnabled").equals("true")) {
			String adminUserName = prop.getProperty("adminUserName");
			String adminPassword = prop.getProperty("adminPassword");
			String liferayURL = prop.getProperty("liferayURL");
			LiferayEvidenceRestServiceImpl evidenceRestServiceImpl = new LiferayEvidenceRestServiceImpl(adminUserName, adminPassword, liferayURL);
			evidenceProviderMap.evidenceMap.put(LMSSystems.liferay.toString(), evidenceRestServiceImpl);
		}

		if (prop.get("moodleEnabled").equals("true")) {
			try {
				MagicStrings.MOODLEURL = prop.getProperty("moodleURL").toString();
				EvidenceService evidenceRestServiceImpl = new MoodleEvidenceRestServiceImpl(prop.get("moodledatabaseurl").toString(), prop.get("moodledb").toString(), prop.get("moodleadminname")
						.toString(), prop.get("moodleadminpassword").toString(), prop.get("moodleadminLogin").toString(), prop.get("moodleadminLoginPassword").toString());
				evidenceProviderMap.evidenceMap.put(LMSSystems.moodle.toString(), evidenceRestServiceImpl);
			} catch (NullPointerException e) {
				System.err.println("moodle configuration mistake");
			}
		}

	}

	@Override
	public String[] getOrganizations() {
		return new String[] { "university" };
	}

	@Override
	public String[] getLMSSystems() {
		return evidenceProviderMap.evidenceMap.keySet().toArray(new String[0]);
	}

	@Override
	public UserCourseListResponse getCourses(String user, String lmsSystem, String organization) {
		if (!evidenceProviderMap.evidenceMap.containsKey(lmsSystem)) {
			throw new BadParameterException("Anwendungsplattform " + lmsSystem + " wurde nicht konfiguriert");
		}
		return evidenceProviderMap.evidenceMap.get(lmsSystem).getCourses(user, lmsSystem, organization);
	}

	@Override
	public UserTree[] getUserTree(String course, String lmssystem, String organization) {
		if (!evidenceProviderMap.evidenceMap.containsKey(lmssystem)) {
			throw new BadParameterException("Anwendungsplattform " + lmssystem + " wurde nicht konfiguriert");
		}

		return evidenceProviderMap.evidenceMap.get(lmssystem).getUserTree(course, lmssystem, organization);
	}

	@Override
	public Response getUserTreeCrossDomain(String course, String lmssystem, String organization) {
		throw new Error("decorator called");
	}

	@Override
	public Boolean exists(String user, String password, String lmsSystem, String organization) {
		if (!evidenceProviderMap.evidenceMap.containsKey(lmsSystem)) {
			throw new BadParameterException("Anwendungsplattform " + lmsSystem + " wurde nicht konfiguriert");
		}

		return evidenceProviderMap.evidenceMap.get(lmsSystem).exists(user, password, lmsSystem, organization);
	}

	@Override
	public void addUserTree(String course, UserTree[] usertree, String lmssystem, String organization) {
		if (!evidenceProviderMap.evidenceMap.containsKey(lmssystem)) {
			throw new BadParameterException("Anwendungsplattform " + lmssystem + " wurde nicht konfiguriert");
		}
		this.evidenceProviderMap.evidenceMap.put(lmssystem, new InMemoryEvidenceService());
		evidenceProviderMap.evidenceMap.get(lmssystem).addUserTree(course, usertree, lmssystem, organization);
	}

	@Override
	public void addCourses(String user, UserCourseListResponse usertree, String lmssystem, String organization) {
		if (!evidenceProviderMap.evidenceMap.containsKey(lmssystem)) {
			throw new BadParameterException("Anwendungsplattform " + lmssystem + " wurde nicht konfiguriert");
		}
		this.evidenceProviderMap.evidenceMap.put(lmssystem, new InMemoryEvidenceService());
		evidenceProviderMap.evidenceMap.get(lmssystem).addCourses(user, usertree, lmssystem, organization);
	}

}