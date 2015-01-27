package de.unipotsdam.elis.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import de.unipotsdam.elis.model.Evidence;

/**
 * The persistence interface for the evidence service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EvidencePersistenceImpl
 * @see EvidenceUtil
 * @generated
 */
public interface EvidencePersistence extends BasePersistence<Evidence> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link EvidenceUtil} to access the evidence persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the evidence in the entity cache if it is enabled.
    *
    * @param evidence the evidence
    */
    public void cacheResult(de.unipotsdam.elis.model.Evidence evidence);

    /**
    * Caches the evidences in the entity cache if it is enabled.
    *
    * @param evidences the evidences
    */
    public void cacheResult(
        java.util.List<de.unipotsdam.elis.model.Evidence> evidences);

    /**
    * Creates a new evidence with the primary key. Does not add the evidence to the database.
    *
    * @param evidenceId the primary key for the new evidence
    * @return the new evidence
    */
    public de.unipotsdam.elis.model.Evidence create(long evidenceId);

    /**
    * Removes the evidence with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param evidenceId the primary key of the evidence
    * @return the evidence that was removed
    * @throws de.unipotsdam.elis.NoSuchEvidenceException if a evidence with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public de.unipotsdam.elis.model.Evidence remove(long evidenceId)
        throws com.liferay.portal.kernel.exception.SystemException,
            de.unipotsdam.elis.NoSuchEvidenceException;

    public de.unipotsdam.elis.model.Evidence updateImpl(
        de.unipotsdam.elis.model.Evidence evidence)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the evidence with the primary key or throws a {@link de.unipotsdam.elis.NoSuchEvidenceException} if it could not be found.
    *
    * @param evidenceId the primary key of the evidence
    * @return the evidence
    * @throws de.unipotsdam.elis.NoSuchEvidenceException if a evidence with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public de.unipotsdam.elis.model.Evidence findByPrimaryKey(long evidenceId)
        throws com.liferay.portal.kernel.exception.SystemException,
            de.unipotsdam.elis.NoSuchEvidenceException;

    /**
    * Returns the evidence with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param evidenceId the primary key of the evidence
    * @return the evidence, or <code>null</code> if a evidence with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public de.unipotsdam.elis.model.Evidence fetchByPrimaryKey(long evidenceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the evidences.
    *
    * @return the evidences
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<de.unipotsdam.elis.model.Evidence> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<de.unipotsdam.elis.model.Evidence> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<de.unipotsdam.elis.model.Evidence> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the evidences from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of evidences.
    *
    * @return the number of evidences
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
