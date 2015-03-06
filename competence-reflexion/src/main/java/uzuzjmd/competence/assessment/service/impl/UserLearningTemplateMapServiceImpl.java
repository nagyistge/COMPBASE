package uzuzjmd.competence.assessment.service.impl;

import uzuzjmd.competence.assessment.service.base.UserLearningTemplateMapServiceBaseImpl;

/**
 * The implementation of the user learning template map remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link uzuzjmd.competence.assessment.service.UserLearningTemplateMapService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see uzuzjmd.competence.assessment.service.base.UserLearningTemplateMapServiceBaseImpl
 * @see uzuzjmd.competence.assessment.service.UserLearningTemplateMapServiceUtil
 */
public class UserLearningTemplateMapServiceImpl
    extends UserLearningTemplateMapServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link uzuzjmd.competence.assessment.service.UserLearningTemplateMapServiceUtil} to access the user learning template map remote service.
     */
	public String dummy() {
		return "hello";		
	}
}
