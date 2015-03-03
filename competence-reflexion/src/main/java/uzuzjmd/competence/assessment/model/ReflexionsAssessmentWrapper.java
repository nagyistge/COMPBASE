package uzuzjmd.competence.assessment.model;

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ReflexionsAssessment}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReflexionsAssessment
 * @generated
 */
public class ReflexionsAssessmentWrapper implements ReflexionsAssessment,
    ModelWrapper<ReflexionsAssessment> {
    private ReflexionsAssessment _reflexionsAssessment;

    public ReflexionsAssessmentWrapper(
        ReflexionsAssessment reflexionsAssessment) {
        _reflexionsAssessment = reflexionsAssessment;
    }

    @Override
    public Class<?> getModelClass() {
        return ReflexionsAssessment.class;
    }

    @Override
    public String getModelClassName() {
        return ReflexionsAssessment.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("uuid", getUuid());
        attributes.put("assessmentId", getAssessmentId());
        attributes.put("groupId", getGroupId());
        attributes.put("companyId", getCompanyId());
        attributes.put("userId", getUserId());
        attributes.put("userName", getUserName());
        attributes.put("createDate", getCreateDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("competenceDescription", getCompetenceDescription());
        attributes.put("competenceId", getCompetenceId());
        attributes.put("assessmentIndex", getAssessmentIndex());
        attributes.put("isLearningGoal", getIsLearningGoal());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String uuid = (String) attributes.get("uuid");

        if (uuid != null) {
            setUuid(uuid);
        }

        Long assessmentId = (Long) attributes.get("assessmentId");

        if (assessmentId != null) {
            setAssessmentId(assessmentId);
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

        String competenceDescription = (String) attributes.get(
                "competenceDescription");

        if (competenceDescription != null) {
            setCompetenceDescription(competenceDescription);
        }

        String competenceId = (String) attributes.get("competenceId");

        if (competenceId != null) {
            setCompetenceId(competenceId);
        }

        Integer assessmentIndex = (Integer) attributes.get("assessmentIndex");

        if (assessmentIndex != null) {
            setAssessmentIndex(assessmentIndex);
        }

        Boolean isLearningGoal = (Boolean) attributes.get("isLearningGoal");

        if (isLearningGoal != null) {
            setIsLearningGoal(isLearningGoal);
        }
    }

    /**
    * Returns the primary key of this reflexions assessment.
    *
    * @return the primary key of this reflexions assessment
    */
    @Override
    public long getPrimaryKey() {
        return _reflexionsAssessment.getPrimaryKey();
    }

    /**
    * Sets the primary key of this reflexions assessment.
    *
    * @param primaryKey the primary key of this reflexions assessment
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _reflexionsAssessment.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the uuid of this reflexions assessment.
    *
    * @return the uuid of this reflexions assessment
    */
    @Override
    public java.lang.String getUuid() {
        return _reflexionsAssessment.getUuid();
    }

    /**
    * Sets the uuid of this reflexions assessment.
    *
    * @param uuid the uuid of this reflexions assessment
    */
    @Override
    public void setUuid(java.lang.String uuid) {
        _reflexionsAssessment.setUuid(uuid);
    }

    /**
    * Returns the assessment ID of this reflexions assessment.
    *
    * @return the assessment ID of this reflexions assessment
    */
    @Override
    public long getAssessmentId() {
        return _reflexionsAssessment.getAssessmentId();
    }

    /**
    * Sets the assessment ID of this reflexions assessment.
    *
    * @param assessmentId the assessment ID of this reflexions assessment
    */
    @Override
    public void setAssessmentId(long assessmentId) {
        _reflexionsAssessment.setAssessmentId(assessmentId);
    }

    /**
    * Returns the group ID of this reflexions assessment.
    *
    * @return the group ID of this reflexions assessment
    */
    @Override
    public long getGroupId() {
        return _reflexionsAssessment.getGroupId();
    }

    /**
    * Sets the group ID of this reflexions assessment.
    *
    * @param groupId the group ID of this reflexions assessment
    */
    @Override
    public void setGroupId(long groupId) {
        _reflexionsAssessment.setGroupId(groupId);
    }

    /**
    * Returns the company ID of this reflexions assessment.
    *
    * @return the company ID of this reflexions assessment
    */
    @Override
    public long getCompanyId() {
        return _reflexionsAssessment.getCompanyId();
    }

    /**
    * Sets the company ID of this reflexions assessment.
    *
    * @param companyId the company ID of this reflexions assessment
    */
    @Override
    public void setCompanyId(long companyId) {
        _reflexionsAssessment.setCompanyId(companyId);
    }

    /**
    * Returns the user ID of this reflexions assessment.
    *
    * @return the user ID of this reflexions assessment
    */
    @Override
    public long getUserId() {
        return _reflexionsAssessment.getUserId();
    }

    /**
    * Sets the user ID of this reflexions assessment.
    *
    * @param userId the user ID of this reflexions assessment
    */
    @Override
    public void setUserId(long userId) {
        _reflexionsAssessment.setUserId(userId);
    }

    /**
    * Returns the user uuid of this reflexions assessment.
    *
    * @return the user uuid of this reflexions assessment
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _reflexionsAssessment.getUserUuid();
    }

    /**
    * Sets the user uuid of this reflexions assessment.
    *
    * @param userUuid the user uuid of this reflexions assessment
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _reflexionsAssessment.setUserUuid(userUuid);
    }

    /**
    * Returns the user name of this reflexions assessment.
    *
    * @return the user name of this reflexions assessment
    */
    @Override
    public java.lang.String getUserName() {
        return _reflexionsAssessment.getUserName();
    }

    /**
    * Sets the user name of this reflexions assessment.
    *
    * @param userName the user name of this reflexions assessment
    */
    @Override
    public void setUserName(java.lang.String userName) {
        _reflexionsAssessment.setUserName(userName);
    }

    /**
    * Returns the create date of this reflexions assessment.
    *
    * @return the create date of this reflexions assessment
    */
    @Override
    public java.util.Date getCreateDate() {
        return _reflexionsAssessment.getCreateDate();
    }

    /**
    * Sets the create date of this reflexions assessment.
    *
    * @param createDate the create date of this reflexions assessment
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _reflexionsAssessment.setCreateDate(createDate);
    }

    /**
    * Returns the modified date of this reflexions assessment.
    *
    * @return the modified date of this reflexions assessment
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _reflexionsAssessment.getModifiedDate();
    }

    /**
    * Sets the modified date of this reflexions assessment.
    *
    * @param modifiedDate the modified date of this reflexions assessment
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _reflexionsAssessment.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the competence description of this reflexions assessment.
    *
    * @return the competence description of this reflexions assessment
    */
    @Override
    public java.lang.String getCompetenceDescription() {
        return _reflexionsAssessment.getCompetenceDescription();
    }

    /**
    * Sets the competence description of this reflexions assessment.
    *
    * @param competenceDescription the competence description of this reflexions assessment
    */
    @Override
    public void setCompetenceDescription(java.lang.String competenceDescription) {
        _reflexionsAssessment.setCompetenceDescription(competenceDescription);
    }

    /**
    * Returns the competence ID of this reflexions assessment.
    *
    * @return the competence ID of this reflexions assessment
    */
    @Override
    public java.lang.String getCompetenceId() {
        return _reflexionsAssessment.getCompetenceId();
    }

    /**
    * Sets the competence ID of this reflexions assessment.
    *
    * @param competenceId the competence ID of this reflexions assessment
    */
    @Override
    public void setCompetenceId(java.lang.String competenceId) {
        _reflexionsAssessment.setCompetenceId(competenceId);
    }

    /**
    * Returns the assessment index of this reflexions assessment.
    *
    * @return the assessment index of this reflexions assessment
    */
    @Override
    public int getAssessmentIndex() {
        return _reflexionsAssessment.getAssessmentIndex();
    }

    /**
    * Sets the assessment index of this reflexions assessment.
    *
    * @param assessmentIndex the assessment index of this reflexions assessment
    */
    @Override
    public void setAssessmentIndex(int assessmentIndex) {
        _reflexionsAssessment.setAssessmentIndex(assessmentIndex);
    }

    /**
    * Returns the is learning goal of this reflexions assessment.
    *
    * @return the is learning goal of this reflexions assessment
    */
    @Override
    public boolean getIsLearningGoal() {
        return _reflexionsAssessment.getIsLearningGoal();
    }

    /**
    * Returns <code>true</code> if this reflexions assessment is is learning goal.
    *
    * @return <code>true</code> if this reflexions assessment is is learning goal; <code>false</code> otherwise
    */
    @Override
    public boolean isIsLearningGoal() {
        return _reflexionsAssessment.isIsLearningGoal();
    }

    /**
    * Sets whether this reflexions assessment is is learning goal.
    *
    * @param isLearningGoal the is learning goal of this reflexions assessment
    */
    @Override
    public void setIsLearningGoal(boolean isLearningGoal) {
        _reflexionsAssessment.setIsLearningGoal(isLearningGoal);
    }

    @Override
    public boolean isNew() {
        return _reflexionsAssessment.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _reflexionsAssessment.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _reflexionsAssessment.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _reflexionsAssessment.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _reflexionsAssessment.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _reflexionsAssessment.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _reflexionsAssessment.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _reflexionsAssessment.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _reflexionsAssessment.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _reflexionsAssessment.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _reflexionsAssessment.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ReflexionsAssessmentWrapper((ReflexionsAssessment) _reflexionsAssessment.clone());
    }

    @Override
    public int compareTo(
        uzuzjmd.competence.assessment.model.ReflexionsAssessment reflexionsAssessment) {
        return _reflexionsAssessment.compareTo(reflexionsAssessment);
    }

    @Override
    public int hashCode() {
        return _reflexionsAssessment.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<uzuzjmd.competence.assessment.model.ReflexionsAssessment> toCacheModel() {
        return _reflexionsAssessment.toCacheModel();
    }

    @Override
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment toEscapedModel() {
        return new ReflexionsAssessmentWrapper(_reflexionsAssessment.toEscapedModel());
    }

    @Override
    public uzuzjmd.competence.assessment.model.ReflexionsAssessment toUnescapedModel() {
        return new ReflexionsAssessmentWrapper(_reflexionsAssessment.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _reflexionsAssessment.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _reflexionsAssessment.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _reflexionsAssessment.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ReflexionsAssessmentWrapper)) {
            return false;
        }

        ReflexionsAssessmentWrapper reflexionsAssessmentWrapper = (ReflexionsAssessmentWrapper) obj;

        if (Validator.equals(_reflexionsAssessment,
                    reflexionsAssessmentWrapper._reflexionsAssessment)) {
            return true;
        }

        return false;
    }

    @Override
    public StagedModelType getStagedModelType() {
        return _reflexionsAssessment.getStagedModelType();
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ReflexionsAssessment getWrappedReflexionsAssessment() {
        return _reflexionsAssessment;
    }

    @Override
    public ReflexionsAssessment getWrappedModel() {
        return _reflexionsAssessment;
    }

    @Override
    public void resetOriginalValues() {
        _reflexionsAssessment.resetOriginalValues();
    }
}
