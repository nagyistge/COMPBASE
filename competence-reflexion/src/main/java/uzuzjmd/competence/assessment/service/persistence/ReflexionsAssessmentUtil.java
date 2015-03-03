package uzuzjmd.competence.assessment.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import uzuzjmd.competence.assessment.model.ReflexionsAssessment;

import java.util.List;

/**
 * The persistence utility for the reflexions assessment service. This utility wraps {@link ReflexionsAssessmentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReflexionsAssessmentPersistence
 * @see ReflexionsAssessmentPersistenceImpl
 * @generated
 */
public class ReflexionsAssessmentUtil {
    private static ReflexionsAssessmentPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(ReflexionsAssessment reflexionsAssessment) {
        getPersistence().clearCache(reflexionsAssessment);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<ReflexionsAssessment> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ReflexionsAssessment> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ReflexionsAssessment> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ReflexionsAssessment update(
        ReflexionsAssessment reflexionsAssessment) throws SystemException {
        return getPersistence().update(reflexionsAssessment);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ReflexionsAssessment update(
        ReflexionsAssessment reflexionsAssessment, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(reflexionsAssessment, serviceContext);
    }

    /**
    * Returns all the reflexions assessments where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the matching reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findByUuid(
        java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid(uuid);
    }

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
    public static java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findByUuid(
        java.lang.String uuid, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid(uuid, start, end);
    }

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
    public static java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findByUuid(
        java.lang.String uuid, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid(uuid, start, end, orderByComparator);
    }

    /**
    * Returns the first reflexions assessment in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching reflexions assessment
    * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment findByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException {
        return getPersistence().findByUuid_First(uuid, orderByComparator);
    }

    /**
    * Returns the first reflexions assessment in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching reflexions assessment, or <code>null</code> if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment fetchByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUuid_First(uuid, orderByComparator);
    }

    /**
    * Returns the last reflexions assessment in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching reflexions assessment
    * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment findByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException {
        return getPersistence().findByUuid_Last(uuid, orderByComparator);
    }

    /**
    * Returns the last reflexions assessment in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching reflexions assessment, or <code>null</code> if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment fetchByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
    }

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
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment[] findByUuid_PrevAndNext(
        long assessmentId, java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException {
        return getPersistence()
                   .findByUuid_PrevAndNext(assessmentId, uuid, orderByComparator);
    }

    /**
    * Removes all the reflexions assessments where uuid = &#63; from the database.
    *
    * @param uuid the uuid
    * @throws SystemException if a system exception occurred
    */
    public static void removeByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByUuid(uuid);
    }

    /**
    * Returns the number of reflexions assessments where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the number of matching reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public static int countByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUuid(uuid);
    }

    /**
    * Returns the reflexions assessment where uuid = &#63; and groupId = &#63; or throws a {@link uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException} if it could not be found.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the matching reflexions assessment
    * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment findByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException {
        return getPersistence().findByUUID_G(uuid, groupId);
    }

    /**
    * Returns the reflexions assessment where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the matching reflexions assessment, or <code>null</code> if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment fetchByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUUID_G(uuid, groupId);
    }

    /**
    * Returns the reflexions assessment where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching reflexions assessment, or <code>null</code> if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment fetchByUUID_G(
        java.lang.String uuid, long groupId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
    }

    /**
    * Removes the reflexions assessment where uuid = &#63; and groupId = &#63; from the database.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the reflexions assessment that was removed
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment removeByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException {
        return getPersistence().removeByUUID_G(uuid, groupId);
    }

    /**
    * Returns the number of reflexions assessments where uuid = &#63; and groupId = &#63;.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the number of matching reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public static int countByUUID_G(java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUUID_G(uuid, groupId);
    }

    /**
    * Returns all the reflexions assessments where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @return the matching reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findByUuid_C(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid_C(uuid, companyId);
    }

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
    public static java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findByUuid_C(
        java.lang.String uuid, long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid_C(uuid, companyId, start, end);
    }

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
    public static java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findByUuid_C(
        java.lang.String uuid, long companyId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
    }

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
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment findByUuid_C_First(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException {
        return getPersistence()
                   .findByUuid_C_First(uuid, companyId, orderByComparator);
    }

    /**
    * Returns the first reflexions assessment in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching reflexions assessment, or <code>null</code> if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment fetchByUuid_C_First(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
    }

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
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment findByUuid_C_Last(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException {
        return getPersistence()
                   .findByUuid_C_Last(uuid, companyId, orderByComparator);
    }

    /**
    * Returns the last reflexions assessment in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching reflexions assessment, or <code>null</code> if a matching reflexions assessment could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment fetchByUuid_C_Last(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
    }

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
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment[] findByUuid_C_PrevAndNext(
        long assessmentId, java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException {
        return getPersistence()
                   .findByUuid_C_PrevAndNext(assessmentId, uuid, companyId,
            orderByComparator);
    }

    /**
    * Removes all the reflexions assessments where uuid = &#63; and companyId = &#63; from the database.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByUuid_C(java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByUuid_C(uuid, companyId);
    }

    /**
    * Returns the number of reflexions assessments where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @return the number of matching reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public static int countByUuid_C(java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUuid_C(uuid, companyId);
    }

    /**
    * Caches the reflexions assessment in the entity cache if it is enabled.
    *
    * @param reflexionsAssessment the reflexions assessment
    */
    public static void cacheResult(
        uzuzjmd.competence.assessment.model.ReflexionsAssessment reflexionsAssessment) {
        getPersistence().cacheResult(reflexionsAssessment);
    }

    /**
    * Caches the reflexions assessments in the entity cache if it is enabled.
    *
    * @param reflexionsAssessments the reflexions assessments
    */
    public static void cacheResult(
        java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> reflexionsAssessments) {
        getPersistence().cacheResult(reflexionsAssessments);
    }

    /**
    * Creates a new reflexions assessment with the primary key. Does not add the reflexions assessment to the database.
    *
    * @param assessmentId the primary key for the new reflexions assessment
    * @return the new reflexions assessment
    */
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment create(
        long assessmentId) {
        return getPersistence().create(assessmentId);
    }

    /**
    * Removes the reflexions assessment with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param assessmentId the primary key of the reflexions assessment
    * @return the reflexions assessment that was removed
    * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a reflexions assessment with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment remove(
        long assessmentId)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException {
        return getPersistence().remove(assessmentId);
    }

    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment updateImpl(
        uzuzjmd.competence.assessment.model.ReflexionsAssessment reflexionsAssessment)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(reflexionsAssessment);
    }

    /**
    * Returns the reflexions assessment with the primary key or throws a {@link uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException} if it could not be found.
    *
    * @param assessmentId the primary key of the reflexions assessment
    * @return the reflexions assessment
    * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a reflexions assessment with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment findByPrimaryKey(
        long assessmentId)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException {
        return getPersistence().findByPrimaryKey(assessmentId);
    }

    /**
    * Returns the reflexions assessment with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param assessmentId the primary key of the reflexions assessment
    * @return the reflexions assessment, or <code>null</code> if a reflexions assessment with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.ReflexionsAssessment fetchByPrimaryKey(
        long assessmentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(assessmentId);
    }

    /**
    * Returns all the reflexions assessments.
    *
    * @return the reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the reflexions assessments from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of reflexions assessments.
    *
    * @return the number of reflexions assessments
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ReflexionsAssessmentPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ReflexionsAssessmentPersistence) PortletBeanLocatorUtil.locate(uzuzjmd.competence.assessment.service.ClpSerializer.getServletContextName(),
                    ReflexionsAssessmentPersistence.class.getName());

            ReferenceRegistry.registerReference(ReflexionsAssessmentUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ReflexionsAssessmentPersistence persistence) {
    }
}
