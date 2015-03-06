package uzuzjmd.competence.assessment.service.impl;

import java.util.List;

import uzuzjmd.competence.assessment.model.ReflexionsAssessment;
import uzuzjmd.competence.assessment.service.ReflexionsAssessmentLocalServiceUtil;
import uzuzjmd.competence.assessment.service.base.ReflexionsAssessmentServiceBaseImpl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;

/**
 * The implementation of the reflexions assessment remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link uzuzjmd.competence.assessment.service.ReflexionsAssessmentService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see uzuzjmd.competence.assessment.service.base.ReflexionsAssessmentServiceBaseImpl
 * @see uzuzjmd.competence.assessment.service.ReflexionsAssessmentServiceUtil
 */
public class ReflexionsAssessmentServiceImpl
    extends ReflexionsAssessmentServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link uzuzjmd.competence.assessment.service.ReflexionsAssessmentServiceUtil} to access the reflexions assessment remote service.
     */
	
	public List<ReflexionsAssessment> getReflexionsAssessments(long userId, String competenceId) {
		DynamicQuery dynamicQuery = new DynamicQueryFactoryUtil().forClass(ReflexionsAssessment.class, PortletClassLoaderUtil.getClassLoader());
		dynamicQuery.add(PropertyFactoryUtil.forName("userId").eq(userId));
		dynamicQuery.add(PropertyFactoryUtil.forName("competenceId").eq(competenceId));
		try {
			return ReflexionsAssessmentLocalServiceUtil.dynamicQuery(dynamicQuery);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	} 
	
	public String dummy() {
		return "hello";		
	}
	

}
