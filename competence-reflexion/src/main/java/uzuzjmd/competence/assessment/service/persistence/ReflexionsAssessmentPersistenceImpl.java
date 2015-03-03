package uzuzjmd.competence.assessment.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException;
import uzuzjmd.competence.assessment.model.ReflexionsAssessment;
import uzuzjmd.competence.assessment.model.impl.ReflexionsAssessmentImpl;
import uzuzjmd.competence.assessment.model.impl.ReflexionsAssessmentModelImpl;
import uzuzjmd.competence.assessment.service.persistence.ReflexionsAssessmentPersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the reflexions assessment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReflexionsAssessmentPersistence
 * @see ReflexionsAssessmentUtil
 * @generated
 */
public class ReflexionsAssessmentPersistenceImpl extends BasePersistenceImpl<ReflexionsAssessment>
    implements ReflexionsAssessmentPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ReflexionsAssessmentUtil} to access the reflexions assessment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ReflexionsAssessmentImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
            ReflexionsAssessmentModelImpl.FINDER_CACHE_ENABLED,
            ReflexionsAssessmentImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
            ReflexionsAssessmentModelImpl.FINDER_CACHE_ENABLED,
            ReflexionsAssessmentImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
            ReflexionsAssessmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
            ReflexionsAssessmentModelImpl.FINDER_CACHE_ENABLED,
            ReflexionsAssessmentImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
            ReflexionsAssessmentModelImpl.FINDER_CACHE_ENABLED,
            ReflexionsAssessmentImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
            new String[] { String.class.getName() },
            ReflexionsAssessmentModelImpl.UUID_COLUMN_BITMASK |
            ReflexionsAssessmentModelImpl.COMPETENCEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
            ReflexionsAssessmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_UUID_UUID_1 = "reflexionsAssessment.uuid IS NULL";
    private static final String _FINDER_COLUMN_UUID_UUID_2 = "reflexionsAssessment.uuid = ?";
    private static final String _FINDER_COLUMN_UUID_UUID_3 = "(reflexionsAssessment.uuid IS NULL OR reflexionsAssessment.uuid = '')";
    public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
            ReflexionsAssessmentModelImpl.FINDER_CACHE_ENABLED,
            ReflexionsAssessmentImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByUUID_G",
            new String[] { String.class.getName(), Long.class.getName() },
            ReflexionsAssessmentModelImpl.UUID_COLUMN_BITMASK |
            ReflexionsAssessmentModelImpl.GROUPID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
            ReflexionsAssessmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
            new String[] { String.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "reflexionsAssessment.uuid IS NULL AND ";
    private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "reflexionsAssessment.uuid = ? AND ";
    private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(reflexionsAssessment.uuid IS NULL OR reflexionsAssessment.uuid = '') AND ";
    private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "reflexionsAssessment.groupId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
            ReflexionsAssessmentModelImpl.FINDER_CACHE_ENABLED,
            ReflexionsAssessmentImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
            new String[] {
                String.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
        new FinderPath(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
            ReflexionsAssessmentModelImpl.FINDER_CACHE_ENABLED,
            ReflexionsAssessmentImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
            new String[] { String.class.getName(), Long.class.getName() },
            ReflexionsAssessmentModelImpl.UUID_COLUMN_BITMASK |
            ReflexionsAssessmentModelImpl.COMPANYID_COLUMN_BITMASK |
            ReflexionsAssessmentModelImpl.COMPETENCEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
            ReflexionsAssessmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
            new String[] { String.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "reflexionsAssessment.uuid IS NULL AND ";
    private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "reflexionsAssessment.uuid = ? AND ";
    private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(reflexionsAssessment.uuid IS NULL OR reflexionsAssessment.uuid = '') AND ";
    private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "reflexionsAssessment.companyId = ?";
    private static final String _SQL_SELECT_REFLEXIONSASSESSMENT = "SELECT reflexionsAssessment FROM ReflexionsAssessment reflexionsAssessment";
    private static final String _SQL_SELECT_REFLEXIONSASSESSMENT_WHERE = "SELECT reflexionsAssessment FROM ReflexionsAssessment reflexionsAssessment WHERE ";
    private static final String _SQL_COUNT_REFLEXIONSASSESSMENT = "SELECT COUNT(reflexionsAssessment) FROM ReflexionsAssessment reflexionsAssessment";
    private static final String _SQL_COUNT_REFLEXIONSASSESSMENT_WHERE = "SELECT COUNT(reflexionsAssessment) FROM ReflexionsAssessment reflexionsAssessment WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "reflexionsAssessment.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ReflexionsAssessment exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ReflexionsAssessment exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ReflexionsAssessmentPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "uuid"
            });
    private static ReflexionsAssessment _nullReflexionsAssessment = new ReflexionsAssessmentImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ReflexionsAssessment> toCacheModel() {
                return _nullReflexionsAssessmentCacheModel;
            }
        };

    private static CacheModel<ReflexionsAssessment> _nullReflexionsAssessmentCacheModel =
        new CacheModel<ReflexionsAssessment>() {
            @Override
            public ReflexionsAssessment toEntityModel() {
                return _nullReflexionsAssessment;
            }
        };

    public ReflexionsAssessmentPersistenceImpl() {
        setModelClass(ReflexionsAssessment.class);
    }

    /**
     * Returns all the reflexions assessments where uuid = &#63;.
     *
     * @param uuid the uuid
     * @return the matching reflexions assessments
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ReflexionsAssessment> findByUuid(String uuid)
        throws SystemException {
        return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    @Override
    public List<ReflexionsAssessment> findByUuid(String uuid, int start, int end)
        throws SystemException {
        return findByUuid(uuid, start, end, null);
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
    @Override
    public List<ReflexionsAssessment> findByUuid(String uuid, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
            finderArgs = new Object[] { uuid };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
            finderArgs = new Object[] { uuid, start, end, orderByComparator };
        }

        List<ReflexionsAssessment> list = (List<ReflexionsAssessment>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ReflexionsAssessment reflexionsAssessment : list) {
                if (!Validator.equals(uuid, reflexionsAssessment.getUuid())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_REFLEXIONSASSESSMENT_WHERE);

            boolean bindUuid = false;

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_UUID_1);
            } else if (uuid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_UUID_UUID_3);
            } else {
                bindUuid = true;

                query.append(_FINDER_COLUMN_UUID_UUID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ReflexionsAssessmentModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUuid) {
                    qPos.add(uuid);
                }

                if (!pagination) {
                    list = (List<ReflexionsAssessment>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ReflexionsAssessment>(list);
                } else {
                    list = (List<ReflexionsAssessment>) QueryUtil.list(q,
                            getDialect(), start, end);
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
     * Returns the first reflexions assessment in the ordered set where uuid = &#63;.
     *
     * @param uuid the uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching reflexions assessment
     * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a matching reflexions assessment could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReflexionsAssessment findByUuid_First(String uuid,
        OrderByComparator orderByComparator)
        throws NoSuchReflexionsAssessmentException, SystemException {
        ReflexionsAssessment reflexionsAssessment = fetchByUuid_First(uuid,
                orderByComparator);

        if (reflexionsAssessment != null) {
            return reflexionsAssessment;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("uuid=");
        msg.append(uuid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchReflexionsAssessmentException(msg.toString());
    }

    /**
     * Returns the first reflexions assessment in the ordered set where uuid = &#63;.
     *
     * @param uuid the uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching reflexions assessment, or <code>null</code> if a matching reflexions assessment could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReflexionsAssessment fetchByUuid_First(String uuid,
        OrderByComparator orderByComparator) throws SystemException {
        List<ReflexionsAssessment> list = findByUuid(uuid, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public ReflexionsAssessment findByUuid_Last(String uuid,
        OrderByComparator orderByComparator)
        throws NoSuchReflexionsAssessmentException, SystemException {
        ReflexionsAssessment reflexionsAssessment = fetchByUuid_Last(uuid,
                orderByComparator);

        if (reflexionsAssessment != null) {
            return reflexionsAssessment;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("uuid=");
        msg.append(uuid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchReflexionsAssessmentException(msg.toString());
    }

    /**
     * Returns the last reflexions assessment in the ordered set where uuid = &#63;.
     *
     * @param uuid the uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching reflexions assessment, or <code>null</code> if a matching reflexions assessment could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReflexionsAssessment fetchByUuid_Last(String uuid,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByUuid(uuid);

        if (count == 0) {
            return null;
        }

        List<ReflexionsAssessment> list = findByUuid(uuid, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public ReflexionsAssessment[] findByUuid_PrevAndNext(long assessmentId,
        String uuid, OrderByComparator orderByComparator)
        throws NoSuchReflexionsAssessmentException, SystemException {
        ReflexionsAssessment reflexionsAssessment = findByPrimaryKey(assessmentId);

        Session session = null;

        try {
            session = openSession();

            ReflexionsAssessment[] array = new ReflexionsAssessmentImpl[3];

            array[0] = getByUuid_PrevAndNext(session, reflexionsAssessment,
                    uuid, orderByComparator, true);

            array[1] = reflexionsAssessment;

            array[2] = getByUuid_PrevAndNext(session, reflexionsAssessment,
                    uuid, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ReflexionsAssessment getByUuid_PrevAndNext(Session session,
        ReflexionsAssessment reflexionsAssessment, String uuid,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_REFLEXIONSASSESSMENT_WHERE);

        boolean bindUuid = false;

        if (uuid == null) {
            query.append(_FINDER_COLUMN_UUID_UUID_1);
        } else if (uuid.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_UUID_UUID_3);
        } else {
            bindUuid = true;

            query.append(_FINDER_COLUMN_UUID_UUID_2);
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(ReflexionsAssessmentModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindUuid) {
            qPos.add(uuid);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(reflexionsAssessment);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ReflexionsAssessment> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the reflexions assessments where uuid = &#63; from the database.
     *
     * @param uuid the uuid
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByUuid(String uuid) throws SystemException {
        for (ReflexionsAssessment reflexionsAssessment : findByUuid(uuid,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(reflexionsAssessment);
        }
    }

    /**
     * Returns the number of reflexions assessments where uuid = &#63;.
     *
     * @param uuid the uuid
     * @return the number of matching reflexions assessments
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByUuid(String uuid) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

        Object[] finderArgs = new Object[] { uuid };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_REFLEXIONSASSESSMENT_WHERE);

            boolean bindUuid = false;

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_UUID_1);
            } else if (uuid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_UUID_UUID_3);
            } else {
                bindUuid = true;

                query.append(_FINDER_COLUMN_UUID_UUID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUuid) {
                    qPos.add(uuid);
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
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
    @Override
    public ReflexionsAssessment findByUUID_G(String uuid, long groupId)
        throws NoSuchReflexionsAssessmentException, SystemException {
        ReflexionsAssessment reflexionsAssessment = fetchByUUID_G(uuid, groupId);

        if (reflexionsAssessment == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("uuid=");
            msg.append(uuid);

            msg.append(", groupId=");
            msg.append(groupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchReflexionsAssessmentException(msg.toString());
        }

        return reflexionsAssessment;
    }

    /**
     * Returns the reflexions assessment where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param uuid the uuid
     * @param groupId the group ID
     * @return the matching reflexions assessment, or <code>null</code> if a matching reflexions assessment could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReflexionsAssessment fetchByUUID_G(String uuid, long groupId)
        throws SystemException {
        return fetchByUUID_G(uuid, groupId, true);
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
    @Override
    public ReflexionsAssessment fetchByUUID_G(String uuid, long groupId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { uuid, groupId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
                    finderArgs, this);
        }

        if (result instanceof ReflexionsAssessment) {
            ReflexionsAssessment reflexionsAssessment = (ReflexionsAssessment) result;

            if (!Validator.equals(uuid, reflexionsAssessment.getUuid()) ||
                    (groupId != reflexionsAssessment.getGroupId())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_REFLEXIONSASSESSMENT_WHERE);

            boolean bindUuid = false;

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_G_UUID_1);
            } else if (uuid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_UUID_G_UUID_3);
            } else {
                bindUuid = true;

                query.append(_FINDER_COLUMN_UUID_G_UUID_2);
            }

            query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUuid) {
                    qPos.add(uuid);
                }

                qPos.add(groupId);

                List<ReflexionsAssessment> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
                        finderArgs, list);
                } else {
                    ReflexionsAssessment reflexionsAssessment = list.get(0);

                    result = reflexionsAssessment;

                    cacheResult(reflexionsAssessment);

                    if ((reflexionsAssessment.getUuid() == null) ||
                            !reflexionsAssessment.getUuid().equals(uuid) ||
                            (reflexionsAssessment.getGroupId() != groupId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
                            finderArgs, reflexionsAssessment);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (ReflexionsAssessment) result;
        }
    }

    /**
     * Removes the reflexions assessment where uuid = &#63; and groupId = &#63; from the database.
     *
     * @param uuid the uuid
     * @param groupId the group ID
     * @return the reflexions assessment that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReflexionsAssessment removeByUUID_G(String uuid, long groupId)
        throws NoSuchReflexionsAssessmentException, SystemException {
        ReflexionsAssessment reflexionsAssessment = findByUUID_G(uuid, groupId);

        return remove(reflexionsAssessment);
    }

    /**
     * Returns the number of reflexions assessments where uuid = &#63; and groupId = &#63;.
     *
     * @param uuid the uuid
     * @param groupId the group ID
     * @return the number of matching reflexions assessments
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByUUID_G(String uuid, long groupId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

        Object[] finderArgs = new Object[] { uuid, groupId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_REFLEXIONSASSESSMENT_WHERE);

            boolean bindUuid = false;

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_G_UUID_1);
            } else if (uuid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_UUID_G_UUID_3);
            } else {
                bindUuid = true;

                query.append(_FINDER_COLUMN_UUID_G_UUID_2);
            }

            query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUuid) {
                    qPos.add(uuid);
                }

                qPos.add(groupId);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the reflexions assessments where uuid = &#63; and companyId = &#63;.
     *
     * @param uuid the uuid
     * @param companyId the company ID
     * @return the matching reflexions assessments
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ReflexionsAssessment> findByUuid_C(String uuid, long companyId)
        throws SystemException {
        return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
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
    @Override
    public List<ReflexionsAssessment> findByUuid_C(String uuid, long companyId,
        int start, int end) throws SystemException {
        return findByUuid_C(uuid, companyId, start, end, null);
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
    @Override
    public List<ReflexionsAssessment> findByUuid_C(String uuid, long companyId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
            finderArgs = new Object[] { uuid, companyId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
            finderArgs = new Object[] {
                    uuid, companyId,
                    
                    start, end, orderByComparator
                };
        }

        List<ReflexionsAssessment> list = (List<ReflexionsAssessment>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ReflexionsAssessment reflexionsAssessment : list) {
                if (!Validator.equals(uuid, reflexionsAssessment.getUuid()) ||
                        (companyId != reflexionsAssessment.getCompanyId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_REFLEXIONSASSESSMENT_WHERE);

            boolean bindUuid = false;

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_C_UUID_1);
            } else if (uuid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_UUID_C_UUID_3);
            } else {
                bindUuid = true;

                query.append(_FINDER_COLUMN_UUID_C_UUID_2);
            }

            query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ReflexionsAssessmentModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUuid) {
                    qPos.add(uuid);
                }

                qPos.add(companyId);

                if (!pagination) {
                    list = (List<ReflexionsAssessment>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ReflexionsAssessment>(list);
                } else {
                    list = (List<ReflexionsAssessment>) QueryUtil.list(q,
                            getDialect(), start, end);
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
     * Returns the first reflexions assessment in the ordered set where uuid = &#63; and companyId = &#63;.
     *
     * @param uuid the uuid
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching reflexions assessment
     * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a matching reflexions assessment could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReflexionsAssessment findByUuid_C_First(String uuid, long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchReflexionsAssessmentException, SystemException {
        ReflexionsAssessment reflexionsAssessment = fetchByUuid_C_First(uuid,
                companyId, orderByComparator);

        if (reflexionsAssessment != null) {
            return reflexionsAssessment;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("uuid=");
        msg.append(uuid);

        msg.append(", companyId=");
        msg.append(companyId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchReflexionsAssessmentException(msg.toString());
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
    @Override
    public ReflexionsAssessment fetchByUuid_C_First(String uuid,
        long companyId, OrderByComparator orderByComparator)
        throws SystemException {
        List<ReflexionsAssessment> list = findByUuid_C(uuid, companyId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public ReflexionsAssessment findByUuid_C_Last(String uuid, long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchReflexionsAssessmentException, SystemException {
        ReflexionsAssessment reflexionsAssessment = fetchByUuid_C_Last(uuid,
                companyId, orderByComparator);

        if (reflexionsAssessment != null) {
            return reflexionsAssessment;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("uuid=");
        msg.append(uuid);

        msg.append(", companyId=");
        msg.append(companyId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchReflexionsAssessmentException(msg.toString());
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
    @Override
    public ReflexionsAssessment fetchByUuid_C_Last(String uuid, long companyId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByUuid_C(uuid, companyId);

        if (count == 0) {
            return null;
        }

        List<ReflexionsAssessment> list = findByUuid_C(uuid, companyId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public ReflexionsAssessment[] findByUuid_C_PrevAndNext(long assessmentId,
        String uuid, long companyId, OrderByComparator orderByComparator)
        throws NoSuchReflexionsAssessmentException, SystemException {
        ReflexionsAssessment reflexionsAssessment = findByPrimaryKey(assessmentId);

        Session session = null;

        try {
            session = openSession();

            ReflexionsAssessment[] array = new ReflexionsAssessmentImpl[3];

            array[0] = getByUuid_C_PrevAndNext(session, reflexionsAssessment,
                    uuid, companyId, orderByComparator, true);

            array[1] = reflexionsAssessment;

            array[2] = getByUuid_C_PrevAndNext(session, reflexionsAssessment,
                    uuid, companyId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ReflexionsAssessment getByUuid_C_PrevAndNext(Session session,
        ReflexionsAssessment reflexionsAssessment, String uuid, long companyId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_REFLEXIONSASSESSMENT_WHERE);

        boolean bindUuid = false;

        if (uuid == null) {
            query.append(_FINDER_COLUMN_UUID_C_UUID_1);
        } else if (uuid.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_UUID_C_UUID_3);
        } else {
            bindUuid = true;

            query.append(_FINDER_COLUMN_UUID_C_UUID_2);
        }

        query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(ReflexionsAssessmentModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindUuid) {
            qPos.add(uuid);
        }

        qPos.add(companyId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(reflexionsAssessment);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ReflexionsAssessment> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the reflexions assessments where uuid = &#63; and companyId = &#63; from the database.
     *
     * @param uuid the uuid
     * @param companyId the company ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByUuid_C(String uuid, long companyId)
        throws SystemException {
        for (ReflexionsAssessment reflexionsAssessment : findByUuid_C(uuid,
                companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(reflexionsAssessment);
        }
    }

    /**
     * Returns the number of reflexions assessments where uuid = &#63; and companyId = &#63;.
     *
     * @param uuid the uuid
     * @param companyId the company ID
     * @return the number of matching reflexions assessments
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByUuid_C(String uuid, long companyId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

        Object[] finderArgs = new Object[] { uuid, companyId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_REFLEXIONSASSESSMENT_WHERE);

            boolean bindUuid = false;

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_C_UUID_1);
            } else if (uuid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_UUID_C_UUID_3);
            } else {
                bindUuid = true;

                query.append(_FINDER_COLUMN_UUID_C_UUID_2);
            }

            query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUuid) {
                    qPos.add(uuid);
                }

                qPos.add(companyId);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Caches the reflexions assessment in the entity cache if it is enabled.
     *
     * @param reflexionsAssessment the reflexions assessment
     */
    @Override
    public void cacheResult(ReflexionsAssessment reflexionsAssessment) {
        EntityCacheUtil.putResult(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
            ReflexionsAssessmentImpl.class,
            reflexionsAssessment.getPrimaryKey(), reflexionsAssessment);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
            new Object[] {
                reflexionsAssessment.getUuid(),
                reflexionsAssessment.getGroupId()
            }, reflexionsAssessment);

        reflexionsAssessment.resetOriginalValues();
    }

    /**
     * Caches the reflexions assessments in the entity cache if it is enabled.
     *
     * @param reflexionsAssessments the reflexions assessments
     */
    @Override
    public void cacheResult(List<ReflexionsAssessment> reflexionsAssessments) {
        for (ReflexionsAssessment reflexionsAssessment : reflexionsAssessments) {
            if (EntityCacheUtil.getResult(
                        ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
                        ReflexionsAssessmentImpl.class,
                        reflexionsAssessment.getPrimaryKey()) == null) {
                cacheResult(reflexionsAssessment);
            } else {
                reflexionsAssessment.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all reflexions assessments.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ReflexionsAssessmentImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ReflexionsAssessmentImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the reflexions assessment.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ReflexionsAssessment reflexionsAssessment) {
        EntityCacheUtil.removeResult(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
            ReflexionsAssessmentImpl.class, reflexionsAssessment.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(reflexionsAssessment);
    }

    @Override
    public void clearCache(List<ReflexionsAssessment> reflexionsAssessments) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ReflexionsAssessment reflexionsAssessment : reflexionsAssessments) {
            EntityCacheUtil.removeResult(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
                ReflexionsAssessmentImpl.class,
                reflexionsAssessment.getPrimaryKey());

            clearUniqueFindersCache(reflexionsAssessment);
        }
    }

    protected void cacheUniqueFindersCache(
        ReflexionsAssessment reflexionsAssessment) {
        if (reflexionsAssessment.isNew()) {
            Object[] args = new Object[] {
                    reflexionsAssessment.getUuid(),
                    reflexionsAssessment.getGroupId()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
                Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
                reflexionsAssessment);
        } else {
            ReflexionsAssessmentModelImpl reflexionsAssessmentModelImpl = (ReflexionsAssessmentModelImpl) reflexionsAssessment;

            if ((reflexionsAssessmentModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        reflexionsAssessment.getUuid(),
                        reflexionsAssessment.getGroupId()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
                    Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
                    reflexionsAssessment);
            }
        }
    }

    protected void clearUniqueFindersCache(
        ReflexionsAssessment reflexionsAssessment) {
        ReflexionsAssessmentModelImpl reflexionsAssessmentModelImpl = (ReflexionsAssessmentModelImpl) reflexionsAssessment;

        Object[] args = new Object[] {
                reflexionsAssessment.getUuid(),
                reflexionsAssessment.getGroupId()
            };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

        if ((reflexionsAssessmentModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
            args = new Object[] {
                    reflexionsAssessmentModelImpl.getOriginalUuid(),
                    reflexionsAssessmentModelImpl.getOriginalGroupId()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
        }
    }

    /**
     * Creates a new reflexions assessment with the primary key. Does not add the reflexions assessment to the database.
     *
     * @param assessmentId the primary key for the new reflexions assessment
     * @return the new reflexions assessment
     */
    @Override
    public ReflexionsAssessment create(long assessmentId) {
        ReflexionsAssessment reflexionsAssessment = new ReflexionsAssessmentImpl();

        reflexionsAssessment.setNew(true);
        reflexionsAssessment.setPrimaryKey(assessmentId);

        String uuid = PortalUUIDUtil.generate();

        reflexionsAssessment.setUuid(uuid);

        return reflexionsAssessment;
    }

    /**
     * Removes the reflexions assessment with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param assessmentId the primary key of the reflexions assessment
     * @return the reflexions assessment that was removed
     * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a reflexions assessment with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReflexionsAssessment remove(long assessmentId)
        throws NoSuchReflexionsAssessmentException, SystemException {
        return remove((Serializable) assessmentId);
    }

    /**
     * Removes the reflexions assessment with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the reflexions assessment
     * @return the reflexions assessment that was removed
     * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a reflexions assessment with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReflexionsAssessment remove(Serializable primaryKey)
        throws NoSuchReflexionsAssessmentException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ReflexionsAssessment reflexionsAssessment = (ReflexionsAssessment) session.get(ReflexionsAssessmentImpl.class,
                    primaryKey);

            if (reflexionsAssessment == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchReflexionsAssessmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(reflexionsAssessment);
        } catch (NoSuchReflexionsAssessmentException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ReflexionsAssessment removeImpl(
        ReflexionsAssessment reflexionsAssessment) throws SystemException {
        reflexionsAssessment = toUnwrappedModel(reflexionsAssessment);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(reflexionsAssessment)) {
                reflexionsAssessment = (ReflexionsAssessment) session.get(ReflexionsAssessmentImpl.class,
                        reflexionsAssessment.getPrimaryKeyObj());
            }

            if (reflexionsAssessment != null) {
                session.delete(reflexionsAssessment);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (reflexionsAssessment != null) {
            clearCache(reflexionsAssessment);
        }

        return reflexionsAssessment;
    }

    @Override
    public ReflexionsAssessment updateImpl(
        uzuzjmd.competence.assessment.model.ReflexionsAssessment reflexionsAssessment)
        throws SystemException {
        reflexionsAssessment = toUnwrappedModel(reflexionsAssessment);

        boolean isNew = reflexionsAssessment.isNew();

        ReflexionsAssessmentModelImpl reflexionsAssessmentModelImpl = (ReflexionsAssessmentModelImpl) reflexionsAssessment;

        if (Validator.isNull(reflexionsAssessment.getUuid())) {
            String uuid = PortalUUIDUtil.generate();

            reflexionsAssessment.setUuid(uuid);
        }

        Session session = null;

        try {
            session = openSession();

            if (reflexionsAssessment.isNew()) {
                session.save(reflexionsAssessment);

                reflexionsAssessment.setNew(false);
            } else {
                session.merge(reflexionsAssessment);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ReflexionsAssessmentModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((reflexionsAssessmentModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        reflexionsAssessmentModelImpl.getOriginalUuid()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
                    args);

                args = new Object[] { reflexionsAssessmentModelImpl.getUuid() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
                    args);
            }

            if ((reflexionsAssessmentModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        reflexionsAssessmentModelImpl.getOriginalUuid(),
                        reflexionsAssessmentModelImpl.getOriginalCompanyId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
                    args);

                args = new Object[] {
                        reflexionsAssessmentModelImpl.getUuid(),
                        reflexionsAssessmentModelImpl.getCompanyId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
                    args);
            }
        }

        EntityCacheUtil.putResult(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
            ReflexionsAssessmentImpl.class,
            reflexionsAssessment.getPrimaryKey(), reflexionsAssessment);

        clearUniqueFindersCache(reflexionsAssessment);
        cacheUniqueFindersCache(reflexionsAssessment);

        return reflexionsAssessment;
    }

    protected ReflexionsAssessment toUnwrappedModel(
        ReflexionsAssessment reflexionsAssessment) {
        if (reflexionsAssessment instanceof ReflexionsAssessmentImpl) {
            return reflexionsAssessment;
        }

        ReflexionsAssessmentImpl reflexionsAssessmentImpl = new ReflexionsAssessmentImpl();

        reflexionsAssessmentImpl.setNew(reflexionsAssessment.isNew());
        reflexionsAssessmentImpl.setPrimaryKey(reflexionsAssessment.getPrimaryKey());

        reflexionsAssessmentImpl.setUuid(reflexionsAssessment.getUuid());
        reflexionsAssessmentImpl.setAssessmentId(reflexionsAssessment.getAssessmentId());
        reflexionsAssessmentImpl.setGroupId(reflexionsAssessment.getGroupId());
        reflexionsAssessmentImpl.setCompanyId(reflexionsAssessment.getCompanyId());
        reflexionsAssessmentImpl.setUserId(reflexionsAssessment.getUserId());
        reflexionsAssessmentImpl.setUserName(reflexionsAssessment.getUserName());
        reflexionsAssessmentImpl.setCreateDate(reflexionsAssessment.getCreateDate());
        reflexionsAssessmentImpl.setModifiedDate(reflexionsAssessment.getModifiedDate());
        reflexionsAssessmentImpl.setCompetenceDescription(reflexionsAssessment.getCompetenceDescription());
        reflexionsAssessmentImpl.setCompetenceId(reflexionsAssessment.getCompetenceId());
        reflexionsAssessmentImpl.setAssessmentIndex(reflexionsAssessment.getAssessmentIndex());
        reflexionsAssessmentImpl.setIsLearningGoal(reflexionsAssessment.isIsLearningGoal());

        return reflexionsAssessmentImpl;
    }

    /**
     * Returns the reflexions assessment with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the reflexions assessment
     * @return the reflexions assessment
     * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a reflexions assessment with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReflexionsAssessment findByPrimaryKey(Serializable primaryKey)
        throws NoSuchReflexionsAssessmentException, SystemException {
        ReflexionsAssessment reflexionsAssessment = fetchByPrimaryKey(primaryKey);

        if (reflexionsAssessment == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchReflexionsAssessmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return reflexionsAssessment;
    }

    /**
     * Returns the reflexions assessment with the primary key or throws a {@link uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException} if it could not be found.
     *
     * @param assessmentId the primary key of the reflexions assessment
     * @return the reflexions assessment
     * @throws uzuzjmd.competence.assessment.NoSuchReflexionsAssessmentException if a reflexions assessment with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReflexionsAssessment findByPrimaryKey(long assessmentId)
        throws NoSuchReflexionsAssessmentException, SystemException {
        return findByPrimaryKey((Serializable) assessmentId);
    }

    /**
     * Returns the reflexions assessment with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the reflexions assessment
     * @return the reflexions assessment, or <code>null</code> if a reflexions assessment with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReflexionsAssessment fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ReflexionsAssessment reflexionsAssessment = (ReflexionsAssessment) EntityCacheUtil.getResult(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
                ReflexionsAssessmentImpl.class, primaryKey);

        if (reflexionsAssessment == _nullReflexionsAssessment) {
            return null;
        }

        if (reflexionsAssessment == null) {
            Session session = null;

            try {
                session = openSession();

                reflexionsAssessment = (ReflexionsAssessment) session.get(ReflexionsAssessmentImpl.class,
                        primaryKey);

                if (reflexionsAssessment != null) {
                    cacheResult(reflexionsAssessment);
                } else {
                    EntityCacheUtil.putResult(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
                        ReflexionsAssessmentImpl.class, primaryKey,
                        _nullReflexionsAssessment);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ReflexionsAssessmentModelImpl.ENTITY_CACHE_ENABLED,
                    ReflexionsAssessmentImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return reflexionsAssessment;
    }

    /**
     * Returns the reflexions assessment with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param assessmentId the primary key of the reflexions assessment
     * @return the reflexions assessment, or <code>null</code> if a reflexions assessment with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReflexionsAssessment fetchByPrimaryKey(long assessmentId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) assessmentId);
    }

    /**
     * Returns all the reflexions assessments.
     *
     * @return the reflexions assessments
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ReflexionsAssessment> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    @Override
    public List<ReflexionsAssessment> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
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
    @Override
    public List<ReflexionsAssessment> findAll(int start, int end,
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

        List<ReflexionsAssessment> list = (List<ReflexionsAssessment>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_REFLEXIONSASSESSMENT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_REFLEXIONSASSESSMENT;

                if (pagination) {
                    sql = sql.concat(ReflexionsAssessmentModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ReflexionsAssessment>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ReflexionsAssessment>(list);
                } else {
                    list = (List<ReflexionsAssessment>) QueryUtil.list(q,
                            getDialect(), start, end);
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
     * Removes all the reflexions assessments from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ReflexionsAssessment reflexionsAssessment : findAll()) {
            remove(reflexionsAssessment);
        }
    }

    /**
     * Returns the number of reflexions assessments.
     *
     * @return the number of reflexions assessments
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

                Query q = session.createQuery(_SQL_COUNT_REFLEXIONSASSESSMENT);

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

    @Override
    protected Set<String> getBadColumnNames() {
        return _badColumnNames;
    }

    /**
     * Initializes the reflexions assessment persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.uzuzjmd.competence.assessment.model.ReflexionsAssessment")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ReflexionsAssessment>> listenersList = new ArrayList<ModelListener<ReflexionsAssessment>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ReflexionsAssessment>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ReflexionsAssessmentImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
