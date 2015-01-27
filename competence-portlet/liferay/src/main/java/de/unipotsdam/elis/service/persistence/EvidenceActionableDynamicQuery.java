package de.unipotsdam.elis.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import de.unipotsdam.elis.model.Evidence;
import de.unipotsdam.elis.service.EvidenceLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class EvidenceActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public EvidenceActionableDynamicQuery() throws SystemException {
        setBaseLocalService(EvidenceLocalServiceUtil.getService());
        setClass(Evidence.class);

        setClassLoader(de.unipotsdam.elis.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("evidenceId");
    }
}
