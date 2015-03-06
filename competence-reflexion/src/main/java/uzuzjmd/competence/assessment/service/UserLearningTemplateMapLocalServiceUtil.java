package uzuzjmd.competence.assessment.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for UserLearningTemplateMap. This utility wraps
 * {@link uzuzjmd.competence.assessment.service.impl.UserLearningTemplateMapLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserLearningTemplateMapLocalService
 * @see uzuzjmd.competence.assessment.service.base.UserLearningTemplateMapLocalServiceBaseImpl
 * @see uzuzjmd.competence.assessment.service.impl.UserLearningTemplateMapLocalServiceImpl
 * @generated
 */
public class UserLearningTemplateMapLocalServiceUtil {
    private static UserLearningTemplateMapLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link uzuzjmd.competence.assessment.service.impl.UserLearningTemplateMapLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the user learning template map to the database. Also notifies the appropriate model listeners.
    *
    * @param userLearningTemplateMap the user learning template map
    * @return the user learning template map that was added
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap addUserLearningTemplateMap(
        uzuzjmd.competence.assessment.model.UserLearningTemplateMap userLearningTemplateMap)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addUserLearningTemplateMap(userLearningTemplateMap);
    }

    /**
    * Creates a new user learning template map with the primary key. Does not add the user learning template map to the database.
    *
    * @param userLearningTemplateMapId the primary key for the new user learning template map
    * @return the new user learning template map
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap createUserLearningTemplateMap(
        long userLearningTemplateMapId) {
        return getService()
                   .createUserLearningTemplateMap(userLearningTemplateMapId);
    }

    /**
    * Deletes the user learning template map with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param userLearningTemplateMapId the primary key of the user learning template map
    * @return the user learning template map that was removed
    * @throws PortalException if a user learning template map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap deleteUserLearningTemplateMap(
        long userLearningTemplateMapId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .deleteUserLearningTemplateMap(userLearningTemplateMapId);
    }

    /**
    * Deletes the user learning template map from the database. Also notifies the appropriate model listeners.
    *
    * @param userLearningTemplateMap the user learning template map
    * @return the user learning template map that was removed
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap deleteUserLearningTemplateMap(
        uzuzjmd.competence.assessment.model.UserLearningTemplateMap userLearningTemplateMap)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .deleteUserLearningTemplateMap(userLearningTemplateMap);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link uzuzjmd.competence.assessment.model.impl.UserLearningTemplateMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link uzuzjmd.competence.assessment.model.impl.UserLearningTemplateMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap fetchUserLearningTemplateMap(
        long userLearningTemplateMapId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .fetchUserLearningTemplateMap(userLearningTemplateMapId);
    }

    /**
    * Returns the user learning template map with the matching UUID and company.
    *
    * @param uuid the user learning template map's UUID
    * @param companyId the primary key of the company
    * @return the matching user learning template map, or <code>null</code> if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap fetchUserLearningTemplateMapByUuidAndCompanyId(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .fetchUserLearningTemplateMapByUuidAndCompanyId(uuid,
            companyId);
    }

    /**
    * Returns the user learning template map matching the UUID and group.
    *
    * @param uuid the user learning template map's UUID
    * @param groupId the primary key of the group
    * @return the matching user learning template map, or <code>null</code> if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap fetchUserLearningTemplateMapByUuidAndGroupId(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .fetchUserLearningTemplateMapByUuidAndGroupId(uuid, groupId);
    }

    /**
    * Returns the user learning template map with the primary key.
    *
    * @param userLearningTemplateMapId the primary key of the user learning template map
    * @return the user learning template map
    * @throws PortalException if a user learning template map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap getUserLearningTemplateMap(
        long userLearningTemplateMapId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getUserLearningTemplateMap(userLearningTemplateMapId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns the user learning template map with the matching UUID and company.
    *
    * @param uuid the user learning template map's UUID
    * @param companyId the primary key of the company
    * @return the matching user learning template map
    * @throws PortalException if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap getUserLearningTemplateMapByUuidAndCompanyId(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUserLearningTemplateMapByUuidAndCompanyId(uuid, companyId);
    }

    /**
    * Returns the user learning template map matching the UUID and group.
    *
    * @param uuid the user learning template map's UUID
    * @param groupId the primary key of the group
    * @return the matching user learning template map
    * @throws PortalException if a matching user learning template map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap getUserLearningTemplateMapByUuidAndGroupId(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUserLearningTemplateMapByUuidAndGroupId(uuid, groupId);
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
    public static java.util.List<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> getUserLearningTemplateMaps(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUserLearningTemplateMaps(start, end);
    }

    /**
    * Returns the number of user learning template maps.
    *
    * @return the number of user learning template maps
    * @throws SystemException if a system exception occurred
    */
    public static int getUserLearningTemplateMapsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUserLearningTemplateMapsCount();
    }

    /**
    * Updates the user learning template map in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param userLearningTemplateMap the user learning template map
    * @return the user learning template map that was updated
    * @throws SystemException if a system exception occurred
    */
    public static uzuzjmd.competence.assessment.model.UserLearningTemplateMap updateUserLearningTemplateMap(
        uzuzjmd.competence.assessment.model.UserLearningTemplateMap userLearningTemplateMap)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateUserLearningTemplateMap(userLearningTemplateMap);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static java.lang.String dummy() {
        return getService().dummy();
    }

    public static void clearService() {
        _service = null;
    }

    public static UserLearningTemplateMapLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    UserLearningTemplateMapLocalService.class.getName());

            if (invokableLocalService instanceof UserLearningTemplateMapLocalService) {
                _service = (UserLearningTemplateMapLocalService) invokableLocalService;
            } else {
                _service = new UserLearningTemplateMapLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(UserLearningTemplateMapLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(UserLearningTemplateMapLocalService service) {
    }
}
