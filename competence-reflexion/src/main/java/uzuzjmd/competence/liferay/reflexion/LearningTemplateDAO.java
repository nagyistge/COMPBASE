package uzuzjmd.competence.liferay.reflexion;

import java.util.Date;

import uzuzjmd.competence.assessment.model.UserLearningTemplateMap;
import uzuzjmd.competence.assessment.service.UserLearningTemplateMapLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;


public class LearningTemplateDAO extends LiferayDAO {
	public static void persist(String selectedLearningTemplate) {
		UserLearningTemplateMap userLearningTemplateMap = null;
		try {			
			try {
				userLearningTemplateMap = UserLearningTemplateMapLocalServiceUtil.getUserLearningTemplateMap(selectedLearningTemplate.hashCode());
			} catch (PortalException e1) {
				userLearningTemplateMap = UserLearningTemplateMapLocalServiceUtil.createUserLearningTemplateMap(selectedLearningTemplate.hashCode());
			}
			
			userLearningTemplateMap.setCompanyId(1l);			
			userLearningTemplateMap.setCreateDate(new Date(System.currentTimeMillis()));
			userLearningTemplateMap.setLearningTemplate(selectedLearningTemplate);
			try {
				userLearningTemplateMap.setGroupId(getUserLoggedIn().getGroupId());
			} catch (PortalException e) {				
				e.printStackTrace();
			}
			userLearningTemplateMap.setUserId(getUserLoggedIn().getUserId());
			userLearningTemplateMap.setModifiedDate(new Date(System.currentTimeMillis()));
		UserLearningTemplateMapLocalServiceUtil.updateUserLearningTemplateMap(userLearningTemplateMap);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
