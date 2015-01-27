package de.unipotsdam.elis.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import de.unipotsdam.elis.model.Evidence;

import java.util.List;

/**
 * The persistence utility for the evidence service. This utility wraps {@link EvidencePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EvidencePersistence
 * @see EvidencePersistenceImpl
 * @generated
 */
public class EvidenceUtil {
    private static EvidencePersistence _persistence;

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
    public static void clearCache(Evidence evidence) {
        getPersistence().clearCache(evidence);
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
    public static List<Evidence> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<Evidence> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<Evidence> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static Evidence update(Evidence evidence) throws SystemException {
        return getPersistence().update(evidence);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static Evidence update(Evidence evidence,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(evidence, serviceContext);
    }

    /**
    * Caches the evidence in the entity cache if it is enabled.
    *
    * @param evidence the evidence
    */
    public static void cacheResult(de.unipotsdam.elis.model.Evidence evidence) {
        getPersistence().cacheResult(evidence);
    }

    /**
    * Caches the evidences in the entity cache if it is enabled.
    *
    * @param evidences the evidences
    */
    public static void cacheResult(
        java.util.List<de.unipotsdam.elis.model.Evidence> evidences) {
        getPersistence().cacheResult(evidences);
    }

    /**
    * Creates a new evidence with the primary key. Does not add the evidence to the database.
    *
    * @param evidenceId the primary key for the new evidence
    * @return the new evidence
    */
    public static de.unipotsdam.elis.model.Evidence create(long evidenceId) {
        return getPersistence().create(evidenceId);
    }

    /**
    * Removes the evidence with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param evidenceId the primary key of the evidence
    * @return the evidence that was removed
    * @throws de.unipotsdam.elis.NoSuchEvidenceException if a evidence with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static de.unipotsdam.elis.model.Evidence remove(long evidenceId)
        throws com.liferay.portal.kernel.exception.SystemException,
            de.unipotsdam.elis.NoSuchEvidenceException {
        return getPersistence().remove(evidenceId);
    }

    public static de.unipotsdam.elis.model.Evidence updateImpl(
        de.unipotsdam.elis.model.Evidence evidence)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(evidence);
    }

    /**
    * Returns the evidence with the primary key or throws a {@link de.unipotsdam.elis.NoSuchEvidenceException} if it could not be found.
    *
    * @param evidenceId the primary key of the evidence
    * @return the evidence
    * @throws de.unipotsdam.elis.NoSuchEvidenceException if a evidence with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static de.unipotsdam.elis.model.Evidence findByPrimaryKey(
        long evidenceId)
        throws com.liferay.portal.kernel.exception.SystemException,
            de.unipotsdam.elis.NoSuchEvidenceException {
        return getPersistence().findByPrimaryKey(evidenceId);
    }

    /**
    * Returns the evidence with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param evidenceId the primary key of the evidence
    * @return the evidence, or <code>null</code> if a evidence with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static de.unipotsdam.elis.model.Evidence fetchByPrimaryKey(
        long evidenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(evidenceId);
    }

    /**
    * Returns all the evidences.
    *
    * @return the evidences
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<de.unipotsdam.elis.model.Evidence> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
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
    public static java.util.List<de.unipotsdam.elis.model.Evidence> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the evidences.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.unipotsdam.elis.model.impl.EvidenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of evidences
    * @param end the upper bound of the range of evidences (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of evidences
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<de.unipotsdam.elis.model.Evidence> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the evidences from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of evidences.
    *
    * @return the number of evidences
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static EvidencePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (EvidencePersistence) PortletBeanLocatorUtil.locate(de.unipotsdam.elis.service.ClpSerializer.getServletContextName(),
                    EvidencePersistence.class.getName());

            ReferenceRegistry.registerReference(EvidenceUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(EvidencePersistence persistence) {
    }
}
