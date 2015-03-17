package uzuzjmd.competence.liferay.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

public class SOAUtil {
	public static String getRestserverUrl() {
		String competenceRestServerUrl = GetterUtil.getString(PortletProps.get("competenceRestServerUrl"));				
		return competenceRestServerUrl;
		
//		String evidenceRestServerUrl = GetterUtil.getString(PortletProps.get("evidenceRestServerUrl"));
//		System.out.println("connecting to:" + evidenceRestServerUrl + " to fetch evidences");
	}
}
