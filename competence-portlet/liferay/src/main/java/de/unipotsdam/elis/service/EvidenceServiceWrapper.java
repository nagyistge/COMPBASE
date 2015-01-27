package de.unipotsdam.elis.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EvidenceService}.
 *
 * @author Brian Wing Shun Chan
 * @see EvidenceService
 * @generated
 */
public class EvidenceServiceWrapper implements EvidenceService,
    ServiceWrapper<EvidenceService> {
    private EvidenceService _evidenceService;

    public EvidenceServiceWrapper(EvidenceService evidenceService) {
        _evidenceService = evidenceService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _evidenceService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _evidenceService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _evidenceService.invokeMethod(name, parameterTypes, arguments);
    }

    @Override
    public void helloWorld() {
        _evidenceService.helloWorld();
    }

    @Override
    public java.util.List<de.unipotsdam.elis.model.Evidence> getGroupEvidences(
        long groupId) {
        return _evidenceService.getGroupEvidences(groupId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public EvidenceService getWrappedEvidenceService() {
        return _evidenceService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedEvidenceService(EvidenceService evidenceService) {
        _evidenceService = evidenceService;
    }

    @Override
    public EvidenceService getWrappedService() {
        return _evidenceService;
    }

    @Override
    public void setWrappedService(EvidenceService evidenceService) {
        _evidenceService = evidenceService;
    }
}
