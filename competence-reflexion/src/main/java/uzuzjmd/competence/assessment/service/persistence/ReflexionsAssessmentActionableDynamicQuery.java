package uzuzjmd.competence.assessment.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import uzuzjmd.competence.assessment.model.ReflexionsAssessment;
import uzuzjmd.competence.assessment.service.ReflexionsAssessmentLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ReflexionsAssessmentActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ReflexionsAssessmentActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ReflexionsAssessmentLocalServiceUtil.getService());
        setClass(ReflexionsAssessment.class);

        setClassLoader(uzuzjmd.competence.assessment.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("assessmentId");
    }
}
