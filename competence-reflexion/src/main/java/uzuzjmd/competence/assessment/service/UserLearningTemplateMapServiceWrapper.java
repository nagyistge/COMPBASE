package uzuzjmd.competence.assessment.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserLearningTemplateMapService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserLearningTemplateMapService
 * @generated
 */
public class UserLearningTemplateMapServiceWrapper
    implements UserLearningTemplateMapService,
        ServiceWrapper<UserLearningTemplateMapService> {
    private UserLearningTemplateMapService _userLearningTemplateMapService;

    public UserLearningTemplateMapServiceWrapper(
        UserLearningTemplateMapService userLearningTemplateMapService) {
        _userLearningTemplateMapService = userLearningTemplateMapService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _userLearningTemplateMapService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _userLearningTemplateMapService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _userLearningTemplateMapService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public UserLearningTemplateMapService getWrappedUserLearningTemplateMapService() {
        return _userLearningTemplateMapService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedUserLearningTemplateMapService(
        UserLearningTemplateMapService userLearningTemplateMapService) {
        _userLearningTemplateMapService = userLearningTemplateMapService;
    }

    @Override
    public UserLearningTemplateMapService getWrappedService() {
        return _userLearningTemplateMapService;
    }

    @Override
    public void setWrappedService(
        UserLearningTemplateMapService userLearningTemplateMapService) {
        _userLearningTemplateMapService = userLearningTemplateMapService;
    }
}
