package de.unipotsdam.elis.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import de.unipotsdam.elis.NoSuchEvidenceException;
import de.unipotsdam.elis.model.Evidence;
import de.unipotsdam.elis.model.impl.EvidenceImpl;
import de.unipotsdam.elis.model.impl.EvidenceModelImpl;
import de.unipotsdam.elis.service.persistence.EvidencePersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the evidence service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EvidencePersistence
 * @see EvidenceUtil
 * @generated
 */
public class EvidencePersistenceImpl extends BasePersistenceImpl<Evidence>
    implements EvidencePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link EvidenceUtil} to access the evidence persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = EvidenceImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EvidenceModelImpl.ENTITY_CACHE_ENABLED,
            EvidenceModelImpl.FINDER_CACHE_ENABLED, EvidenceImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EvidenceModelImpl.ENTITY_CACHE_ENABLED,
            EvidenceModelImpl.FINDER_CACHE_ENABLED, EvidenceImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EvidenceModelImpl.ENTITY_CACHE_ENABLED,
            EvidenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_EVIDENCE = "SELECT evidence FROM Evidence evidence";
    private static final String _SQL_COUNT_EVIDENCE = "SELECT COUNT(evidence) FROM Evidence evidence";
    private static final String _ORDER_BY_ENTITY_ALIAS = "evidence.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Evidence exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(EvidencePersistenceImpl.class);
    private static Evidence _nullEvidence = new EvidenceImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<Evidence> toCacheModel() {
                return _nullEvidenceCacheModel;
            }
        };

    private static CacheModel<Evidence> _nullEvidenceCacheModel = new CacheModel<Evidence>() {
            @Override
            public Evidence toEntityModel() {
                return _nullEvidence;
            }
        };

    public EvidencePersistenceImpl() {
        setModelClass(Evidence.class);
    }

    /**
     * Caches the evidence in the entity cache if it is enabled.
     *
     * @param evidence the evidence
     */
    @Override
    public void cacheResult(Evidence evidence) {
        EntityCacheUtil.putResult(EvidenceModelImpl.ENTITY_CACHE_ENABLED,
            EvidenceImpl.class, evidence.getPrimaryKey(), evidence);

        evidence.resetOriginalValues();
    }

    /**
     * Caches the evidences in the entity cache if it is enabled.
     *
     * @param evidences the evidences
     */
    @Override
    public void cacheResult(List<Evidence> evidences) {
        for (Evidence evidence : evidences) {
            if (EntityCacheUtil.getResult(
                        EvidenceModelImpl.ENTITY_CACHE_ENABLED,
                        EvidenceImpl.class, evidence.getPrimaryKey()) == null) {
                cacheResult(evidence);
            } else {
                evidence.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all evidences.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(EvidenceImpl.class.getName());
        }

        EntityCacheUtil.clearCache(EvidenceImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the evidence.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(Evidence evidence) {
        EntityCacheUtil.removeResult(EvidenceModelImpl.ENTITY_CACHE_ENABLED,
            EvidenceImpl.class, evidence.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<Evidence> evidences) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (Evidence evidence : evidences) {
            EntityCacheUtil.removeResult(EvidenceModelImpl.ENTITY_CACHE_ENABLED,
                EvidenceImpl.class, evidence.getPrimaryKey());
        }
    }

    /**
     * Creates a new evidence with the primary key. Does not add the evidence to the database.
     *
     * @param evidenceId the primary key for the new evidence
     * @return the new evidence
     */
    @Override
    public Evidence create(long evidenceId) {
        Evidence evidence = new EvidenceImpl();

        evidence.setNew(true);
        evidence.setPrimaryKey(evidenceId);

        return evidence;
    }

    /**
     * Removes the evidence with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param evidenceId the primary key of the evidence
     * @return the evidence that was removed
     * @throws de.unipotsdam.elis.NoSuchEvidenceException if a evidence with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Evidence remove(long evidenceId)
        throws NoSuchEvidenceException, SystemException {
        return remove((Serializable) evidenceId);
    }

    /**
     * Removes the evidence with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the evidence
     * @return the evidence that was removed
     * @throws de.unipotsdam.elis.NoSuchEvidenceException if a evidence with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Evidence remove(Serializable primaryKey)
        throws NoSuchEvidenceException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Evidence evidence = (Evidence) session.get(EvidenceImpl.class,
                    primaryKey);

            if (evidence == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchEvidenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(evidence);
        } catch (NoSuchEvidenceException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected Evidence removeImpl(Evidence evidence) throws SystemException {
        evidence = toUnwrappedModel(evidence);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(evidence)) {
                evidence = (Evidence) session.get(EvidenceImpl.class,
                        evidence.getPrimaryKeyObj());
            }

            if (evidence != null) {
                session.delete(evidence);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (evidence != null) {
            clearCache(evidence);
        }

        return evidence;
    }

    @Override
    public Evidence updateImpl(de.unipotsdam.elis.model.Evidence evidence)
        throws SystemException {
        evidence = toUnwrappedModel(evidence);

        boolean isNew = evidence.isNew();

        Session session = null;

        try {
            session = openSession();

            if (evidence.isNew()) {
                session.save(evidence);

                evidence.setNew(false);
            } else {
                session.merge(evidence);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(EvidenceModelImpl.ENTITY_CACHE_ENABLED,
            EvidenceImpl.class, evidence.getPrimaryKey(), evidence);

        return evidence;
    }

    protected Evidence toUnwrappedModel(Evidence evidence) {
        if (evidence instanceof EvidenceImpl) {
            return evidence;
        }

        EvidenceImpl evidenceImpl = new EvidenceImpl();

        evidenceImpl.setNew(evidence.isNew());
        evidenceImpl.setPrimaryKey(evidence.getPrimaryKey());

        evidenceImpl.setEvidenceId(evidence.getEvidenceId());
        evidenceImpl.setGroupId(evidence.getGroupId());
        evidenceImpl.setCompanyId(evidence.getCompanyId());
        evidenceImpl.setUserId(evidence.getUserId());
        evidenceImpl.setUserName(evidence.getUserName());
        evidenceImpl.setCreateDate(evidence.getCreateDate());
        evidenceImpl.setModifiedDate(evidence.getModifiedDate());
        evidenceImpl.setTitle(evidence.getTitle());
        evidenceImpl.setLink(evidence.getLink());
        evidenceImpl.setSummary(evidence.getSummary());
        evidenceImpl.setActivityTyp(evidence.getActivityTyp());

        return evidenceImpl;
    }

    /**
     * Returns the evidence with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the evidence
     * @return the evidence
     * @throws de.unipotsdam.elis.NoSuchEvidenceException if a evidence with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Evidence findByPrimaryKey(Serializable primaryKey)
        throws NoSuchEvidenceException, SystemException {
        Evidence evidence = fetchByPrimaryKey(primaryKey);

        if (evidence == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchEvidenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return evidence;
    }

    /**
     * Returns the evidence with the primary key or throws a {@link de.unipotsdam.elis.NoSuchEvidenceException} if it could not be found.
     *
     * @param evidenceId the primary key of the evidence
     * @return the evidence
     * @throws de.unipotsdam.elis.NoSuchEvidenceException if a evidence with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Evidence findByPrimaryKey(long evidenceId)
        throws NoSuchEvidenceException, SystemException {
        return findByPrimaryKey((Serializable) evidenceId);
    }

    /**
     * Returns the evidence with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the evidence
     * @return the evidence, or <code>null</code> if a evidence with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Evidence fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        Evidence evidence = (Evidence) EntityCacheUtil.getResult(EvidenceModelImpl.ENTITY_CACHE_ENABLED,
                EvidenceImpl.class, primaryKey);

        if (evidence == _nullEvidence) {
            return null;
        }

        if (evidence == null) {
            Session session = null;

            try {
                session = openSession();

                evidence = (Evidence) session.get(EvidenceImpl.class, primaryKey);

                if (evidence != null) {
                    cacheResult(evidence);
                } else {
                    EntityCacheUtil.putResult(EvidenceModelImpl.ENTITY_CACHE_ENABLED,
                        EvidenceImpl.class, primaryKey, _nullEvidence);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(EvidenceModelImpl.ENTITY_CACHE_ENABLED,
                    EvidenceImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return evidence;
    }

    /**
     * Returns the evidence with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param evidenceId the primary key of the evidence
     * @return the evidence, or <code>null</code> if a evidence with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Evidence fetchByPrimaryKey(long evidenceId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) evidenceId);
    }

    /**
     * Returns all the evidences.
     *
     * @return the evidences
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Evidence> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    public List<Evidence> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
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
    @Override
    public List<Evidence> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<Evidence> list = (List<Evidence>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_EVIDENCE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_EVIDENCE;

                if (pagination) {
                    sql = sql.concat(EvidenceModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<Evidence>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Evidence>(list);
                } else {
                    list = (List<Evidence>) QueryUtil.list(q, getDialect(),
                            start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the evidences from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (Evidence evidence : findAll()) {
            remove(evidence);
        }
    }

    /**
     * Returns the number of evidences.
     *
     * @return the number of evidences
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_EVIDENCE);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Initializes the evidence persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.de.unipotsdam.elis.model.Evidence")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Evidence>> listenersList = new ArrayList<ModelListener<Evidence>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Evidence>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(EvidenceImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
