package de.unipotsdam.elis.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Evidence}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Evidence
 * @generated
 */
public class EvidenceWrapper implements Evidence, ModelWrapper<Evidence> {
    private Evidence _evidence;

    public EvidenceWrapper(Evidence evidence) {
        _evidence = evidence;
    }

    @Override
    public Class<?> getModelClass() {
        return Evidence.class;
    }

    @Override
    public String getModelClassName() {
        return Evidence.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("evidenceId", getEvidenceId());
        attributes.put("groupId", getGroupId());
        attributes.put("companyId", getCompanyId());
        attributes.put("userId", getUserId());
        attributes.put("userName", getUserName());
        attributes.put("createDate", getCreateDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("title", getTitle());
        attributes.put("link", getLink());
        attributes.put("summary", getSummary());
        attributes.put("activityTyp", getActivityTyp());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long evidenceId = (Long) attributes.get("evidenceId");

        if (evidenceId != null) {
            setEvidenceId(evidenceId);
        }

        Long groupId = (Long) attributes.get("groupId");

        if (groupId != null) {
            setGroupId(groupId);
        }

        Long companyId = (Long) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String userName = (String) attributes.get("userName");

        if (userName != null) {
            setUserName(userName);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String title = (String) attributes.get("title");

        if (title != null) {
            setTitle(title);
        }

        String link = (String) attributes.get("link");

        if (link != null) {
            setLink(link);
        }

        String summary = (String) attributes.get("summary");

        if (summary != null) {
            setSummary(summary);
        }

        String activityTyp = (String) attributes.get("activityTyp");

        if (activityTyp != null) {
            setActivityTyp(activityTyp);
        }
    }

    /**
    * Returns the primary key of this evidence.
    *
    * @return the primary key of this evidence
    */
    @Override
    public long getPrimaryKey() {
        return _evidence.getPrimaryKey();
    }

    /**
    * Sets the primary key of this evidence.
    *
    * @param primaryKey the primary key of this evidence
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _evidence.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the evidence ID of this evidence.
    *
    * @return the evidence ID of this evidence
    */
    @Override
    public long getEvidenceId() {
        return _evidence.getEvidenceId();
    }

    /**
    * Sets the evidence ID of this evidence.
    *
    * @param evidenceId the evidence ID of this evidence
    */
    @Override
    public void setEvidenceId(long evidenceId) {
        _evidence.setEvidenceId(evidenceId);
    }

    /**
    * Returns the group ID of this evidence.
    *
    * @return the group ID of this evidence
    */
    @Override
    public long getGroupId() {
        return _evidence.getGroupId();
    }

    /**
    * Sets the group ID of this evidence.
    *
    * @param groupId the group ID of this evidence
    */
    @Override
    public void setGroupId(long groupId) {
        _evidence.setGroupId(groupId);
    }

    /**
    * Returns the company ID of this evidence.
    *
    * @return the company ID of this evidence
    */
    @Override
    public long getCompanyId() {
        return _evidence.getCompanyId();
    }

    /**
    * Sets the company ID of this evidence.
    *
    * @param companyId the company ID of this evidence
    */
    @Override
    public void setCompanyId(long companyId) {
        _evidence.setCompanyId(companyId);
    }

    /**
    * Returns the user ID of this evidence.
    *
    * @return the user ID of this evidence
    */
    @Override
    public long getUserId() {
        return _evidence.getUserId();
    }

    /**
    * Sets the user ID of this evidence.
    *
    * @param userId the user ID of this evidence
    */
    @Override
    public void setUserId(long userId) {
        _evidence.setUserId(userId);
    }

    /**
    * Returns the user uuid of this evidence.
    *
    * @return the user uuid of this evidence
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _evidence.getUserUuid();
    }

    /**
    * Sets the user uuid of this evidence.
    *
    * @param userUuid the user uuid of this evidence
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _evidence.setUserUuid(userUuid);
    }

    /**
    * Returns the user name of this evidence.
    *
    * @return the user name of this evidence
    */
    @Override
    public java.lang.String getUserName() {
        return _evidence.getUserName();
    }

    /**
    * Sets the user name of this evidence.
    *
    * @param userName the user name of this evidence
    */
    @Override
    public void setUserName(java.lang.String userName) {
        _evidence.setUserName(userName);
    }

    /**
    * Returns the create date of this evidence.
    *
    * @return the create date of this evidence
    */
    @Override
    public java.util.Date getCreateDate() {
        return _evidence.getCreateDate();
    }

    /**
    * Sets the create date of this evidence.
    *
    * @param createDate the create date of this evidence
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _evidence.setCreateDate(createDate);
    }

    /**
    * Returns the modified date of this evidence.
    *
    * @return the modified date of this evidence
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _evidence.getModifiedDate();
    }

    /**
    * Sets the modified date of this evidence.
    *
    * @param modifiedDate the modified date of this evidence
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _evidence.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the title of this evidence.
    *
    * @return the title of this evidence
    */
    @Override
    public java.lang.String getTitle() {
        return _evidence.getTitle();
    }

    /**
    * Sets the title of this evidence.
    *
    * @param title the title of this evidence
    */
    @Override
    public void setTitle(java.lang.String title) {
        _evidence.setTitle(title);
    }

    /**
    * Returns the link of this evidence.
    *
    * @return the link of this evidence
    */
    @Override
    public java.lang.String getLink() {
        return _evidence.getLink();
    }

    /**
    * Sets the link of this evidence.
    *
    * @param link the link of this evidence
    */
    @Override
    public void setLink(java.lang.String link) {
        _evidence.setLink(link);
    }

    /**
    * Returns the summary of this evidence.
    *
    * @return the summary of this evidence
    */
    @Override
    public java.lang.String getSummary() {
        return _evidence.getSummary();
    }

    /**
    * Sets the summary of this evidence.
    *
    * @param summary the summary of this evidence
    */
    @Override
    public void setSummary(java.lang.String summary) {
        _evidence.setSummary(summary);
    }

    /**
    * Returns the activity typ of this evidence.
    *
    * @return the activity typ of this evidence
    */
    @Override
    public java.lang.String getActivityTyp() {
        return _evidence.getActivityTyp();
    }

    /**
    * Sets the activity typ of this evidence.
    *
    * @param activityTyp the activity typ of this evidence
    */
    @Override
    public void setActivityTyp(java.lang.String activityTyp) {
        _evidence.setActivityTyp(activityTyp);
    }

    @Override
    public boolean isNew() {
        return _evidence.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _evidence.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _evidence.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _evidence.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _evidence.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _evidence.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _evidence.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _evidence.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _evidence.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _evidence.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _evidence.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new EvidenceWrapper((Evidence) _evidence.clone());
    }

    @Override
    public int compareTo(de.unipotsdam.elis.model.Evidence evidence) {
        return _evidence.compareTo(evidence);
    }

    @Override
    public int hashCode() {
        return _evidence.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<de.unipotsdam.elis.model.Evidence> toCacheModel() {
        return _evidence.toCacheModel();
    }

    @Override
    public de.unipotsdam.elis.model.Evidence toEscapedModel() {
        return new EvidenceWrapper(_evidence.toEscapedModel());
    }

    @Override
    public de.unipotsdam.elis.model.Evidence toUnescapedModel() {
        return new EvidenceWrapper(_evidence.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _evidence.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _evidence.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _evidence.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof EvidenceWrapper)) {
            return false;
        }

        EvidenceWrapper evidenceWrapper = (EvidenceWrapper) obj;

        if (Validator.equals(_evidence, evidenceWrapper._evidence)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public Evidence getWrappedEvidence() {
        return _evidence;
    }

    @Override
    public Evidence getWrappedModel() {
        return _evidence;
    }

    @Override
    public void resetOriginalValues() {
        _evidence.resetOriginalValues();
    }
}
