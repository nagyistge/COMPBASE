package de.unipotsdam.elis.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EvidenceLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EvidenceLocalService
 * @generated
 */
public class EvidenceLocalServiceWrapper implements EvidenceLocalService,
    ServiceWrapper<EvidenceLocalService> {
    private EvidenceLocalService _evidenceLocalService;

    public EvidenceLocalServiceWrapper(
        EvidenceLocalService evidenceLocalService) {
        _evidenceLocalService = evidenceLocalService;
    }

    /**
    * Adds the evidence to the database. Also notifies the appropriate model listeners.
    *
    * @param evidence the evidence
    * @return the evidence that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public de.unipotsdam.elis.model.Evidence addEvidence(
        de.unipotsdam.elis.model.Evidence evidence)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _evidenceLocalService.addEvidence(evidence);
    }

    /**
    * Creates a new evidence with the primary key. Does not add the evidence to the database.
    *
    * @param evidenceId the primary key for the new evidence
    * @return the new evidence
    */
    @Override
    public de.unipotsdam.elis.model.Evidence createEvidence(long evidenceId) {
        return _evidenceLocalService.createEvidence(evidenceId);
    }

    /**
    * Deletes the evidence with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param evidenceId the primary key of the evidence
    * @return the evidence that was removed
    * @throws PortalException if a evidence with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public de.unipotsdam.elis.model.Evidence deleteEvidence(long evidenceId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _evidenceLocalService.deleteEvidence(evidenceId);
    }

    /**
    * Deletes the evidence from the database. Also notifies the appropriate model listeners.
    *
    * @param evidence the evidence
    * @return the evidence that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public de.unipotsdam.elis.model.Evidence deleteEvidence(
        de.unipotsdam.elis.model.Evidence evidence)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _evidenceLocalService.deleteEvidence(evidence);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _evidenceLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _evidenceLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.unipotsdam.elis.model.impl.EvidenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _evidenceLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.unipotsdam.elis.model.impl.EvidenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _evidenceLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _evidenceLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _evidenceLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public de.unipotsdam.elis.model.Evidence fetchEvidence(long evidenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _evidenceLocalService.fetchEvidence(evidenceId);
    }

    /**
    * Returns the evidence with the primary key.
    *
    * @param evidenceId the primary key of the evidence
    * @return the evidence
    * @throws PortalException if a evidence with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public de.unipotsdam.elis.model.Evidence getEvidence(long evidenceId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _evidenceLocalService.getEvidence(evidenceId);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _evidenceLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the evidences.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.unipotsdam.elis.model.impl.EvidenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of evidences
    * @param end the upper bound of the range of evidences (not inclusive)
    * @return the range of evidences
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<de.unipotsdam.elis.model.Evidence> getEvidences(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _evidenceLocalService.getEvidences(start, end);
    }

    /**
    * Returns the number of evidences.
    *
    * @return the number of evidences
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getEvidencesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _evidenceLocalService.getEvidencesCount();
    }

    /**
    * Updates the evidence in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param evidence the evidence
    * @return the evidence that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public de.unipotsdam.elis.model.Evidence updateEvidence(
        de.unipotsdam.elis.model.Evidence evidence)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _evidenceLocalService.updateEvidence(evidence);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _evidenceLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _evidenceLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _evidenceLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public void helloWorld() {
        _evidenceLocalService.helloWorld();
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public EvidenceLocalService getWrappedEvidenceLocalService() {
        return _evidenceLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedEvidenceLocalService(
        EvidenceLocalService evidenceLocalService) {
        _evidenceLocalService = evidenceLocalService;
    }

    @Override
    public EvidenceLocalService getWrappedService() {
        return _evidenceLocalService;
    }

    @Override
    public void setWrappedService(EvidenceLocalService evidenceLocalService) {
        _evidenceLocalService = evidenceLocalService;
    }
}
