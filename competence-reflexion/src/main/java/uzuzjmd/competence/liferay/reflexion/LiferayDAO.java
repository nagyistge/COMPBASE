package uzuzjmd.competence.liferay.reflexion;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;

public class LiferayDAO {
	
	public static User getUserLoggedIn() {
		User user = null;
		try {
			user = UserLocalServiceUtil.getUser(
					PrincipalThreadLocal.getUserId());			
		} catch (PortalException e) {			
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return user;
	}
}