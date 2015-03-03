package uzuzjmd.competence.assessment.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import uzuzjmd.competence.assessment.model.UserLearningTemplateMap;

import java.util.List;

/**
 * The persistence utility for the user learning template map service. This utility wraps {@link UserLearningTemplateMapPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserLearningTemplateMapPersistence
 * @see UserLearningTemplateMapPersistenceImpl
 * @generated
 */
public class UserLearningTemplateMapUtil {
    private static UserLearningTemplateMapPersistence _persistence;

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
    public static void clearCache(
        UserLearningTemplateMap userLearningTemplateMap) {
        getPersistence().clearCache(userLearningTemplateMap);
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
    public static List<UserLearningTemplateMap> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<UserLearningTemplateMap> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<UserLearningTemplateMap> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static UserLearningTemplateMap update(
        UserLearningTemplateMap userLearningTemplateMap)
        throws SystemException {
        return getPersistence().update(userLearningTemplateMap);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static UserLearningTemplateMap update(
        UserLearningTemplateMap userLearningTemplateMap,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(userLearningTemplateMap, serviceContext);
    }

    /**
    * Returns all the user learning template maps where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the matching user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findByUuid(
        java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid(uuid);
    }

    /**
    * Returns a range of all the user learning template maps where uuid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link uzuzjmd.competence.assessment.model.impl.UserLearningTemplateMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param start the lower bound of the range of user learning template maps
    * @param end the upper bound of the range of user learning template maps (not inclusive)
    * @return the range of matching user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findByUuid(
        java.lang.String uuid, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid(uuid, start, end);
    }

    /**
    * Returns an ordered range of all the user learning template maps where uuid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link uzuzjmd.competence.assessment.model.impl.UserLearningTemplateMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param start the lower bound of the range of user learning template maps
    * @param end the upper bound of the range of user learning template maps (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findByUuid(
        java.lang.String uuid, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid(uuid, start, end, orderByComparator);
    }

    /**
    * Returns the first user learning template map in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching user learning template map
    * @throws uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap findByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException {
        return getPersistence().findByUuid_First(uuid, orderByComparator);
    }

    /**
    * Returns the first user learning template map in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching user learning template map, or <code>null</code> if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap fetchByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUuid_First(uuid, orderByComparator);
    }

    /**
    * Returns the last user learning template map in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching user learning template map
    * @throws uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap findByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException {
        return getPersistence().findByUuid_Last(uuid, orderByComparator);
    }

    /**
    * Returns the last user learning template map in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching user learning template map, or <code>null</code> if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap fetchByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
    }

    /**
    * Returns the user learning template maps before and after the current user learning template map in the ordered set where uuid = &#63;.
    *
    * @param userLearningTemplateMapId the primary key of the current user learning template map
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next user learning template map
    * @throws uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException if a user learning template map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap[] findByUuid_PrevAndNext(
        long userLearningTemplateMapId, java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException {
        return getPersistence()
                   .findByUuid_PrevAndNext(userLearningTemplateMapId, uuid,
            orderByComparator);
    }

    /**
    * Removes all the user learning template maps where uuid = &#63; from the database.
    *
    * @param uuid the uuid
    * @throws SystemException if a system exception occurred
    */
    public static void removeByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByUuid(uuid);
    }

    /**
    * Returns the number of user learning template maps where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the number of matching user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public static int countByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUuid(uuid);
    }

    /**
    * Returns the user learning template map where uuid = &#63; and groupId = &#63; or throws a {@link uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException} if it could not be found.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the matching user learning template map
    * @throws uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap findByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException {
        return getPersistence().findByUUID_G(uuid, groupId);
    }

    /**
    * Returns the user learning template map where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the matching user learning template map, or <code>null</code> if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap fetchByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUUID_G(uuid, groupId);
    }

    /**
    * Returns the user learning template map where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching user learning template map, or <code>null</code> if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap fetchByUUID_G(
        java.lang.String uuid, long groupId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
    }

    /**
    * Removes the user learning template map where uuid = &#63; and groupId = &#63; from the database.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the user learning template map that was removed
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap removeByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException {
        return getPersistence().removeByUUID_G(uuid, groupId);
    }

    /**
    * Returns the number of user learning template maps where uuid = &#63; and groupId = &#63;.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the number of matching user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public static int countByUUID_G(java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUUID_G(uuid, groupId);
    }

    /**
    * Returns all the user learning template maps where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @return the matching user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findByUuid_C(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid_C(uuid, companyId);
    }

    /**
    * Returns a range of all the user learning template maps where uuid = &#63; and companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link uzuzjmd.competence.assessment.model.impl.UserLearningTemplateMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param start the lower bound of the range of user learning template maps
    * @param end the upper bound of the range of user learning template maps (not inclusive)
    * @return the range of matching user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findByUuid_C(
        java.lang.String uuid, long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid_C(uuid, companyId, start, end);
    }

    /**
    * Returns an ordered range of all the user learning template maps where uuid = &#63; and companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link uzuzjmd.competence.assessment.model.impl.UserLearningTemplateMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param start the lower bound of the range of user learning template maps
    * @param end the upper bound of the range of user learning template maps (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findByUuid_C(
        java.lang.String uuid, long companyId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
    }

    /**
    * Returns the first user learning template map in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching user learning template map
    * @throws uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap findByUuid_C_First(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException {
        return getPersistence()
                   .findByUuid_C_First(uuid, companyId, orderByComparator);
    }

    /**
    * Returns the first user learning template map in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching user learning template map, or <code>null</code> if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap fetchByUuid_C_First(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
    }

    /**
    * Returns the last user learning template map in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching user learning template map
    * @throws uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap findByUuid_C_Last(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException {
        return getPersistence()
                   .findByUuid_C_Last(uuid, companyId, orderByComparator);
    }

    /**
    * Returns the last user learning template map in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching user learning template map, or <code>null</code> if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap fetchByUuid_C_Last(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
    }

    /**
    * Returns the user learning template maps before and after the current user learning template map in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param userLearningTemplateMapId the primary key of the current user learning template map
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next user learning template map
    * @throws uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException if a user learning template map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap[] findByUuid_C_PrevAndNext(
        long userLearningTemplateMapId, java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException {
        return getPersistence()
                   .findByUuid_C_PrevAndNext(userLearningTemplateMapId, uuid,
            companyId, orderByComparator);
    }

    /**
    * Removes all the user learning template maps where uuid = &#63; and companyId = &#63; from the database.
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
    * Returns the number of user learning template maps where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @return the number of matching user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public static int countByUuid_C(java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUuid_C(uuid, companyId);
    }

    /**
    * Caches the user learning template map in the entity cache if it is enabled.
    *
    * @param userLearningTemplateMap the user learning template map
    */
    public static void cacheResult(
        uzuzjmd.competence.assessment.model.UserLearningTemplateMap userLearningTemplateMap) {
        getPersistence().cacheResult(userLearningTemplateMap);
    }

    /**
    * Caches the user learning template maps in the entity cache if it is enabled.
    *
    * @param userLearningTemplateMaps the user learning template maps
    */
    public static void cacheResult(
        java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> userLearningTemplateMaps) {
        getPersistence().cacheResult(userLearningTemplateMaps);
    }

    /**
    * Creates a new user learning template map with the primary key. Does not add the user learning template map to the database.
    *
    * @param userLearningTemplateMapId the primary key for the new user learning template map
    * @return the new user learning template map
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap create(
        long userLearningTemplateMapId) {
        return getPersistence().create(userLearningTemplateMapId);
    }

    /**
    * Removes the user learning template map with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param userLearningTemplateMapId the primary key of the user learning template map
    * @return the user learning template map that was removed
    * @throws uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException if a user learning template map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap remove(
        long userLearningTemplateMapId)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException {
        return getPersistence().remove(userLearningTemplateMapId);
    }

    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap updateImpl(
        uzuzjmd.competence.assessment.model.UserLearningTemplateMap userLearningTemplateMap)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(userLearningTemplateMap);
    }

    /**
    * Returns the user learning template map with the primary key or throws a {@link uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException} if it could not be found.
    *
    * @param userLearningTemplateMapId the primary key of the user learning template map
    * @return the user learning template map
    * @throws uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException if a user learning template map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap findByPrimaryKey(
        long userLearningTemplateMapId)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException {
        return getPersistence().findByPrimaryKey(userLearningTemplateMapId);
    }

    /**
    * Returns the user learning template map with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param userLearningTemplateMapId the primary key of the user learning template map
    * @return the user learning template map, or <code>null</code> if a user learning template map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap fetchByPrimaryKey(
        long userLearningTemplateMapId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(userLearningTemplateMapId);
    }

    /**
    * Returns all the user learning template maps.
    *
    * @return the user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the user learning template maps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link uzuzjmd.competence.assessment.model.impl.UserLearningTemplateMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of user learning template maps
    * @param end the upper bound of the range of user learning template maps (not inclusive)
    * @return the range of user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the user learning template maps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link uzuzjmd.competence.assessment.model.impl.UserLearningTemplateMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of user learning template maps
    * @param end the upper bound of the range of user learning template maps (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the user learning template maps from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of user learning template maps.
    *
    * @return the number of user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static UserLearningTemplateMapPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (UserLearningTemplateMapPersistence) PortletBeanLocatorUtil.locate(uzuzjmd.competence.assessment.service.ClpSerializer.getServletContextName(),
                    UserLearningTemplateMapPersistence.class.getName());

            ReferenceRegistry.registerReference(UserLearningTemplateMapUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(UserLearningTemplateMapPersistence persistence) {
    }
}
