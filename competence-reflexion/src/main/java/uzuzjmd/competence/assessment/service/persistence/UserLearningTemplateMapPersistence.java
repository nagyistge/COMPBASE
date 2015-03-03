package uzuzjmd.competence.assessment.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import uzuzjmd.competence.assessment.model.UserLearningTemplateMap;

/**
 * The persistence interface for the user learning template map service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserLearningTemplateMapPersistenceImpl
 * @see UserLearningTemplateMapUtil
 * @generated
 */
public interface UserLearningTemplateMapPersistence extends BasePersistence<UserLearningTemplateMap> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link UserLearningTemplateMapUtil} to access the user learning template map persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the user learning template maps where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the matching user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findByUuid(
        java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findByUuid(
        java.lang.String uuid, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findByUuid(
        java.lang.String uuid, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first user learning template map in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching user learning template map
    * @throws uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap findByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException;

    /**
    * Returns the first user learning template map in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching user learning template map, or <code>null</code> if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap fetchByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last user learning template map in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching user learning template map
    * @throws uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap findByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException;

    /**
    * Returns the last user learning template map in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching user learning template map, or <code>null</code> if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap fetchByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap[] findByUuid_PrevAndNext(
        long userLearningTemplateMapId, java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException;

    /**
    * Removes all the user learning template maps where uuid = &#63; from the database.
    *
    * @param uuid the uuid
    * @throws SystemException if a system exception occurred
    */
    public void removeByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of user learning template maps where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the number of matching user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public int countByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the user learning template map where uuid = &#63; and groupId = &#63; or throws a {@link uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException} if it could not be found.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the matching user learning template map
    * @throws uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap findByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException;

    /**
    * Returns the user learning template map where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the matching user learning template map, or <code>null</code> if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap fetchByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the user learning template map where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching user learning template map, or <code>null</code> if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap fetchByUUID_G(
        java.lang.String uuid, long groupId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the user learning template map where uuid = &#63; and groupId = &#63; from the database.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the user learning template map that was removed
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap removeByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException;

    /**
    * Returns the number of user learning template maps where uuid = &#63; and groupId = &#63;.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the number of matching user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public int countByUUID_G(java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the user learning template maps where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @return the matching user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findByUuid_C(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findByUuid_C(
        java.lang.String uuid, long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findByUuid_C(
        java.lang.String uuid, long companyId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap findByUuid_C_First(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException;

    /**
    * Returns the first user learning template map in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching user learning template map, or <code>null</code> if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap fetchByUuid_C_First(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap findByUuid_C_Last(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException;

    /**
    * Returns the last user learning template map in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching user learning template map, or <code>null</code> if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap fetchByUuid_C_Last(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap[] findByUuid_C_PrevAndNext(
        long userLearningTemplateMapId, java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException;

    /**
    * Removes all the user learning template maps where uuid = &#63; and companyId = &#63; from the database.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByUuid_C(java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of user learning template maps where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @return the number of matching user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public int countByUuid_C(java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the user learning template map in the entity cache if it is enabled.
    *
    * @param userLearningTemplateMap the user learning template map
    */
    public void cacheResult(
        uzuzjmd.competence.assessment.model.UserLearningTemplateMap userLearningTemplateMap);

    /**
    * Caches the user learning template maps in the entity cache if it is enabled.
    *
    * @param userLearningTemplateMaps the user learning template maps
    */
    public void cacheResult(
        java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> userLearningTemplateMaps);

    /**
    * Creates a new user learning template map with the primary key. Does not add the user learning template map to the database.
    *
    * @param userLearningTemplateMapId the primary key for the new user learning template map
    * @return the new user learning template map
    */
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap create(
        long userLearningTemplateMapId);

    /**
    * Removes the user learning template map with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param userLearningTemplateMapId the primary key of the user learning template map
    * @return the user learning template map that was removed
    * @throws uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException if a user learning template map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap remove(
        long userLearningTemplateMapId)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException;

    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap updateImpl(
        uzuzjmd.competence.assessment.model.UserLearningTemplateMap userLearningTemplateMap)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the user learning template map with the primary key or throws a {@link uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException} if it could not be found.
    *
    * @param userLearningTemplateMapId the primary key of the user learning template map
    * @return the user learning template map
    * @throws uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException if a user learning template map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap findByPrimaryKey(
        long userLearningTemplateMapId)
        throws com.liferay.portal.kernel.exception.SystemException,
            uzuzjmd.competence.assessment.NoSuchUserLearningTemplateMapException;

    /**
    * Returns the user learning template map with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param userLearningTemplateMapId the primary key of the user learning template map
    * @return the user learning template map, or <code>null</code> if a user learning template map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap fetchByPrimaryKey(
        long userLearningTemplateMapId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the user learning template maps.
    *
    * @return the user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the user learning template maps from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of user learning template maps.
    *
    * @return the number of user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
