package uzuzjmd.competence.liferay.util;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

public class ContextUtil {
	public static User getUserLoggedIn() {		
		try {
			return  UserLocalServiceUtil.getUser(
					PrincipalThreadLocal.getUserId());
		} catch (PortalException e) {			
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public static Group getGroup() {
		LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
		ServiceContext serviceContext = liferayFacesContext.getServiceContext();
		try {			
			return serviceContext.getScopeGroup();
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	} 
}
