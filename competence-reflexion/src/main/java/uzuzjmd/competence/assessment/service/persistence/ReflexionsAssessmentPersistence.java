package uzuzjmd.competence.assessment.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import uzuzjmd.competence.assessment.model.ReflexionsAssessment;

/**
 * The persistence interface for the reflexions assessment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReflexionsAssessmentPersistenceImpl
 * @see ReflexionsAssessmentUtil
 * @generated
 */
public interface ReflexionsAssessmentPersistence extends BasePersistence<ReflexionsAssessment> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ReflexionsAssessmentUtil} to access the reflexions assessment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the reflexions assessments where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the matching reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findByUuid(
        java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the reflexions assessments where uuid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link uzuzjmd.competence.assessment.model.impl.ReflexionsAssessmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param start the lower bound of the range of reflexions assessments
    * @param end the upper bound of the range of reflexions assessments (not inclusive)
    * @return the range of matching reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findByUuid(
        java.lang.String uuid, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the reflexions assessments where uuid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link uzuzjmd.competence.assessment.model.impl.ReflexionsAssessmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param start the lower bound of the range of reflexions assessments
    * @param end the upper bound of the range of reflexions assessments (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findByUuid(
        java.lang.String uuid, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first reflexions assessment in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching reflexions assessment
    * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment findByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException;

    /**
    * Returns the first reflexions assessment in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching reflexions assessment, or <code>null</code> if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment fetchByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last reflexions assessment in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching reflexions assessment
    * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment findByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException;

    /**
    * Returns the last reflexions assessment in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching reflexions assessment, or <code>null</code> if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment fetchByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the reflexions assessments before and after the current reflexions assessment in the ordered set where uuid = &#63;.
    *
    * @param assessmentId the primary key of the current reflexions assessment
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next reflexions assessment
    * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a reflexions assessment with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment[] findByUuid_PrevAndNext(
        long assessmentId, java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException;

    /**
    * Removes all the reflexions assessments where uuid = &#63; from the database.
    *
    * @param uuid the uuid
    * @throws SystemException if a system exception occurred
    */
    public void removeByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of reflexions assessments where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the number of matching reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public int countByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the reflexions assessment where uuid = &#63; and groupId = &#63; or throws a {@link uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException} if it could not be found.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the matching reflexions assessment
    * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment findByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException;

    /**
    * Returns the reflexions assessment where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the matching reflexions assessment, or <code>null</code> if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment fetchByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the reflexions assessment where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching reflexions assessment, or <code>null</code> if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment fetchByUUID_G(
        java.lang.String uuid, long groupId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the reflexions assessment where uuid = &#63; and groupId = &#63; from the database.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the reflexions assessment that was removed
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment removeByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException;

    /**
    * Returns the number of reflexions assessments where uuid = &#63; and groupId = &#63;.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the number of matching reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public int countByUUID_G(java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the reflexions assessments where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @return the matching reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findByUuid_C(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the reflexions assessments where uuid = &#63; and companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link uzuzjmd.competence.assessment.model.impl.ReflexionsAssessmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param start the lower bound of the range of reflexions assessments
    * @param end the upper bound of the range of reflexions assessments (not inclusive)
    * @return the range of matching reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findByUuid_C(
        java.lang.String uuid, long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the reflexions assessments where uuid = &#63; and companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link uzuzjmd.competence.assessment.model.impl.ReflexionsAssessmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param start the lower bound of the range of reflexions assessments
    * @param end the upper bound of the range of reflexions assessments (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findByUuid_C(
        java.lang.String uuid, long companyId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first reflexions assessment in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching reflexions assessment
    * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment findByUuid_C_First(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException;

    /**
    * Returns the first reflexions assessment in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching reflexions assessment, or <code>null</code> if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment fetchByUuid_C_First(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last reflexions assessment in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching reflexions assessment
    * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment findByUuid_C_Last(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException;

    /**
    * Returns the last reflexions assessment in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching reflexions assessment, or <code>null</code> if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment fetchByUuid_C_Last(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the reflexions assessments before and after the current reflexions assessment in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param assessmentId the primary key of the current reflexions assessment
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next reflexions assessment
    * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a reflexions assessment with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment[] findByUuid_C_PrevAndNext(
        long assessmentId, java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException;

    /**
    * Removes all the reflexions assessments where uuid = &#63; and companyId = &#63; from the database.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByUuid_C(java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of reflexions assessments where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @return the number of matching reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public int countByUuid_C(java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the reflexions assessment in the entity cache if it is enabled.
    *
    * @param reflexionsAssessment the reflexions assessment
    */
    public void cacheResult(
        uzuzjmd.competence.assessment.model.ReflexionsAssessment reflexionsAssessment);

    /**
    * Caches the reflexions assessments in the entity cache if it is enabled.
    *
    * @param reflexionsAssessments the reflexions assessments
    */
    public void cacheResult(
        java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> reflexionsAssessments);

    /**
    * Creates a new reflexions assessment with the primary key. Does not add the reflexions assessment to the database.
    *
    * @param assessmentId the primary key for the new reflexions assessment
    * @return the new reflexions assessment
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment create(
        long assessmentId);

    /**
    * Removes the reflexions assessment with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param assessmentId the primary key of the reflexions assessment
    * @return the reflexions assessment that was removed
    * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a reflexions assessment with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment remove(
        long assessmentId)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException;

    public uzuzjmd.competence.assessment.model.ReflexionsAssessment updateImpl(
        uzuzjmd.competence.assessment.model.ReflexionsAssessment reflexionsAssessment)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the reflexions assessment with the primary key or throws a {@link uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException} if it could not be found.
    *
    * @param assessmentId the primary key of the reflexions assessment
    * @return the reflexions assessment
    * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a reflexions assessment with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment findByPrimaryKey(
        long assessmentId)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException;

    /**
    * Returns the reflexions assessment with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param assessmentId the primary key of the reflexions assessment
    * @return the reflexions assessment, or <code>null</code> if a reflexions assessment with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment fetchByPrimaryKey(
        long assessmentId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the reflexions assessments.
    *
    * @return the reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the reflexions assessments.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link uzuzjmd.competence.assessment.model.impl.ReflexionsAssessmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of reflexions assessments
    * @param end the upper bound of the range of reflexions assessments (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the reflexions assessments from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of reflexions assessments.
    *
    * @return the number of reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
