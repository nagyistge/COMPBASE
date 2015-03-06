package uzuzjmd.competence.assessment.service.impl;

import uzuzjmd.competence.assessment.service.base.ReflexionsAssessmentLocalServiceBaseImpl;

/**
 * The implementation of the reflexions assessment local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link uzuzjmd.competence.assessment.service.ReflexionsAssessmentLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see uzuzjmd.competence.assessment.service.base.ReflexionsAssessmentLocalServiceBaseImpl
 * @see uzuzjmd.competence.assessment.service.ReflexionsAssessmentLocalServiceUtil
 */
public class ReflexionsAssessmentLocalServiceImpl
    extends ReflexionsAssessmentLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link uzuzjmd.competence.assessment.service.ReflexionsAssessmentLocalServiceUtil} to access the reflexions assessment local service.
     */
	
	public String dummy() {
		return "hello";		
	}
}
