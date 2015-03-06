package uzuzjmd.competence.assessment.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * Provides the local service interface for ReflexionsAssessment. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ReflexionsAssessmentLocalServiceUtil
 * @see uzuzjmd.competence.assessment.service.base.ReflexionsAssessmentLocalServiceBaseImpl
 * @see uzuzjmd.competence.assessment.service.impl.ReflexionsAssessmentLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ReflexionsAssessmentLocalService extends BaseLocalService,
    InvokableLocalService, PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ReflexionsAssessmentLocalServiceUtil} to access the reflexions assessment local service. Add custom service methods to {@link uzuzjmd.competence.assessment.service.impl.ReflexionsAssessmentLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the reflexions assessment to the database. Also notifies the appropriate model listeners.
    *
    * @param reflexionsAssessment the reflexions assessment
    * @return the reflexions assessment that was added
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment addReflexionsAssessment(
        uzuzjmd.competence.assessment.model.ReflexionsAssessment reflexionsAssessment)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new reflexions assessment with the primary key. Does not add the reflexions assessment to the database.
    *
    * @param assessmentId the primary key for the new reflexions assessment
    * @return the new reflexions assessment
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment createReflexionsAssessment(
        long assessmentId);

    /**
    * Deletes the reflexions assessment with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param assessmentId the primary key of the reflexions assessment
    * @return the reflexions assessment that was removed
    * @throws PortalException if a reflexions assessment with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment deleteReflexionsAssessment(
        long assessmentId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the reflexions assessment from the database. Also notifies the appropriate model listeners.
    *
    * @param reflexionsAssessment the reflexions assessment
    * @return the reflexions assessment that was removed
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment deleteReflexionsAssessment(
        uzuzjmd.competence.assessment.model.ReflexionsAssessment reflexionsAssessment)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link uzuzjmd.competence.assessment.model.impl.ReflexionsAssessmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link uzuzjmd.competence.assessment.model.impl.ReflexionsAssessmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment fetchReflexionsAssessment(
        long assessmentId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the reflexions assessment with the matching UUID and company.
    *
    * @param uuid the reflexions assessment's UUID
    * @param companyId the primary key of the company
    * @return the matching reflexions assessment, or <code>null</code> if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment fetchReflexionsAssessmentByUuidAndCompanyId(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the reflexions assessment matching the UUID and group.
    *
    * @param uuid the reflexions assessment's UUID
    * @param groupId the primary key of the group
    * @return the matching reflexions assessment, or <code>null</code> if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment fetchReflexionsAssessmentByUuidAndGroupId(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the reflexions assessment with the primary key.
    *
    * @param assessmentId the primary key of the reflexions assessment
    * @return the reflexions assessment
    * @throws PortalException if a reflexions assessment with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment getReflexionsAssessment(
        long assessmentId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the reflexions assessment with the matching UUID and company.
    *
    * @param uuid the reflexions assessment's UUID
    * @param companyId the primary key of the company
    * @return the matching reflexions assessment
    * @throws PortalException if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment getReflexionsAssessmentByUuidAndCompanyId(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the reflexions assessment matching the UUID and group.
    *
    * @param uuid the reflexions assessment's UUID
    * @param groupId the primary key of the group
    * @return the matching reflexions assessment
    * @throws PortalException if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment getReflexionsAssessmentByUuidAndGroupId(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the reflexions assessments.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link uzuzjmd.competence.assessment.model.impl.ReflexionsAssessmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of reflexions assessments
    * @param end the upper bound of the range of reflexions assessments (not inclusive)
    * @return the range of reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> getReflexionsAssessments(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of reflexions assessments.
    *
    * @return the number of reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getReflexionsAssessmentsCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the reflexions assessment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param reflexionsAssessment the reflexions assessment
    * @return the reflexions assessment that was updated
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment updateReflexionsAssessment(
        uzuzjmd.competence.assessment.model.ReflexionsAssessment reflexionsAssessment)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable;

    public java.lang.String dummy();
}
