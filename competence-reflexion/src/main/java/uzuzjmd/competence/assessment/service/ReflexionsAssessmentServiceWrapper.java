package uzuzjmd.competence.assessment.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ReflexionsAssessmentService}.
 *
 * @author Brian Wing Shun Chan
 * @see ReflexionsAssessmentService
 * @generated
 */
public class ReflexionsAssessmentServiceWrapper
    implements ReflexionsAssessmentService,
        ServiceWrapper<ReflexionsAssessmentService> {
    private ReflexionsAssessmentService _reflexionsAssessmentService;

    public ReflexionsAssessmentServiceWrapper(
        ReflexionsAssessmentService reflexionsAssessmentService) {
        _reflexionsAssessmentService = reflexionsAssessmentService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _reflexionsAssessmentService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _reflexionsAssessmentService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _reflexionsAssessmentService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> getReflexionsAssessments(
        long userId, java.lang.String competenceId) {
        return _reflexionsAssessmentService.getReflexionsAssessments(userId,
            competenceId);
    }

    @Override
    public java.lang.String dummy() {
        return _reflexionsAssessmentService.dummy();
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ReflexionsAssessmentService getWrappedReflexionsAssessmentService() {
        return _reflexionsAssessmentService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedReflexionsAssessmentService(
        ReflexionsAssessmentService reflexionsAssessmentService) {
        _reflexionsAssessmentService = reflexionsAssessmentService;
    }

    @Override
    public ReflexionsAssessmentService getWrappedService() {
        return _reflexionsAssessmentService;
    }

    @Override
    public void setWrappedService(
        ReflexionsAssessmentService reflexionsAssessmentService) {
        _reflexionsAssessmentService = reflexionsAssessmentService;
    }
}
