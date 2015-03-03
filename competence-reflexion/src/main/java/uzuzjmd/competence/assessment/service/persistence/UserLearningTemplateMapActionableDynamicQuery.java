package uzuzjmd.competence.assessment.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import uzuzjmd.competence.assessment.model.UserLearningTemplateMap;
import uzuzjmd.competence.assessment.service.UserLearningTemplateMapLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class UserLearningTemplateMapActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public UserLearningTemplateMapActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(UserLearningTemplateMapLocalServiceUtil.getService());
        setClass(UserLearningTemplateMap.class);

        setClassLoader(uzuzjmd.competence.assessment.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("userLearningTemplateMapId");
    }
}
