package uzuzjmd.competence.assessment.model;

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UserLearningTemplateMap}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserLearningTemplateMap
 * @generated
 */
public class UserLearningTemplateMapWrapper implements UserLearningTemplateMap,
    ModelWrapper<UserLearningTemplateMap> {
    private UserLearningTemplateMap _userLearningTemplateMap;

    public UserLearningTemplateMapWrapper(
        UserLearningTemplateMap userLearningTemplateMap) {
        _userLearningTemplateMap = userLearningTemplateMap;
    }

    @Override
    public Class<?> getModelClass() {
        return UserLearningTemplateMap.class;
    }

    @Override
    public String getModelClassName() {
        return UserLearningTemplateMap.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("uuid", getUuid());
        attributes.put("userLearningTemplateMapId",
            getUserLearningTemplateMapId());
        attributes.put("groupId", getGroupId());
        attributes.put("companyId", getCompanyId());
        attributes.put("userId", getUserId());
        attributes.put("userName", getUserName());
        attributes.put("createDate", getCreateDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("learningTemplate", getLearningTemplate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String uuid = (String) attributes.get("uuid");

        if (uuid != null) {
            setUuid(uuid);
        }

        Long userLearningTemplateMapId = (Long) attributes.get(
                "userLearningTemplateMapId");

        if (userLearningTemplateMapId != null) {
            setUserLearningTemplateMapId(userLearningTemplateMapId);
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

        String learningTemplate = (String) attributes.get("learningTemplate");

        if (learningTemplate != null) {
            setLearningTemplate(learningTemplate);
        }
    }

    /**
    * Returns the primary key of this user learning template map.
    *
    * @return the primary key of this user learning template map
    */
    @Override
    public long getPrimaryKey() {
        return _userLearningTemplateMap.getPrimaryKey();
    }

    /**
    * Sets the primary key of this user learning template map.
    *
    * @param primaryKey the primary key of this user learning template map
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _userLearningTemplateMap.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the uuid of this user learning template map.
    *
    * @return the uuid of this user learning template map
    */
    @Override
    public java.lang.String getUuid() {
        return _userLearningTemplateMap.getUuid();
    }

    /**
    * Sets the uuid of this user learning template map.
    *
    * @param uuid the uuid of this user learning template map
    */
    @Override
    public void setUuid(java.lang.String uuid) {
        _userLearningTemplateMap.setUuid(uuid);
    }

    /**
    * Returns the user learning template map ID of this user learning template map.
    *
    * @return the user learning template map ID of this user learning template map
    */
    @Override
    public long getUserLearningTemplateMapId() {
        return _userLearningTemplateMap.getUserLearningTemplateMapId();
    }

    /**
    * Sets the user learning template map ID of this user learning template map.
    *
    * @param userLearningTemplateMapId the user learning template map ID of this user learning template map
    */
    @Override
    public void setUserLearningTemplateMapId(long userLearningTemplateMapId) {
        _userLearningTemplateMap.setUserLearningTemplateMapId(userLearningTemplateMapId);
    }

    /**
    * Returns the group ID of this user learning template map.
    *
    * @return the group ID of this user learning template map
    */
    @Override
    public long getGroupId() {
        return _userLearningTemplateMap.getGroupId();
    }

    /**
    * Sets the group ID of this user learning template map.
    *
    * @param groupId the group ID of this user learning template map
    */
    @Override
    public void setGroupId(long groupId) {
        _userLearningTemplateMap.setGroupId(groupId);
    }

    /**
    * Returns the company ID of this user learning template map.
    *
    * @return the company ID of this user learning template map
    */
    @Override
    public long getCompanyId() {
        return _userLearningTemplateMap.getCompanyId();
    }

    /**
    * Sets the company ID of this user learning template map.
    *
    * @param companyId the company ID of this user learning template map
    */
    @Override
    public void setCompanyId(long companyId) {
        _userLearningTemplateMap.setCompanyId(companyId);
    }

    /**
    * Returns the user ID of this user learning template map.
    *
    * @return the user ID of this user learning template map
    */
    @Override
    public long getUserId() {
        return _userLearningTemplateMap.getUserId();
    }

    /**
    * Sets the user ID of this user learning template map.
    *
    * @param userId the user ID of this user learning template map
    */
    @Override
    public void setUserId(long userId) {
        _userLearningTemplateMap.setUserId(userId);
    }

    /**
    * Returns the user uuid of this user learning template map.
    *
    * @return the user uuid of this user learning template map
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _userLearningTemplateMap.getUserUuid();
    }

    /**
    * Sets the user uuid of this user learning template map.
    *
    * @param userUuid the user uuid of this user learning template map
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _userLearningTemplateMap.setUserUuid(userUuid);
    }

    /**
    * Returns the user name of this user learning template map.
    *
    * @return the user name of this user learning template map
    */
    @Override
    public java.lang.String getUserName() {
        return _userLearningTemplateMap.getUserName();
    }

    /**
    * Sets the user name of this user learning template map.
    *
    * @param userName the user name of this user learning template map
    */
    @Override
    public void setUserName(java.lang.String userName) {
        _userLearningTemplateMap.setUserName(userName);
    }

    /**
    * Returns the create date of this user learning template map.
    *
    * @return the create date of this user learning template map
    */
    @Override
    public java.util.Date getCreateDate() {
        return _userLearningTemplateMap.getCreateDate();
    }

    /**
    * Sets the create date of this user learning template map.
    *
    * @param createDate the create date of this user learning template map
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _userLearningTemplateMap.setCreateDate(createDate);
    }

    /**
    * Returns the modified date of this user learning template map.
    *
    * @return the modified date of this user learning template map
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _userLearningTemplateMap.getModifiedDate();
    }

    /**
    * Sets the modified date of this user learning template map.
    *
    * @param modifiedDate the modified date of this user learning template map
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _userLearningTemplateMap.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the learning template of this user learning template map.
    *
    * @return the learning template of this user learning template map
    */
    @Override
    public java.lang.String getLearningTemplate() {
        return _userLearningTemplateMap.getLearningTemplate();
    }

    /**
    * Sets the learning template of this user learning template map.
    *
    * @param learningTemplate the learning template of this user learning template map
    */
    @Override
    public void setLearningTemplate(java.lang.String learningTemplate) {
        _userLearningTemplateMap.setLearningTemplate(learningTemplate);
    }

    @Override
    public boolean isNew() {
        return _userLearningTemplateMap.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _userLearningTemplateMap.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _userLearningTemplateMap.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _userLearningTemplateMap.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _userLearningTemplateMap.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _userLearningTemplateMap.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _userLearningTemplateMap.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _userLearningTemplateMap.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _userLearningTemplateMap.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _userLearningTemplateMap.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _userLearningTemplateMap.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new UserLearningTemplateMapWrapper((UserLearningTemplateMap) _userLearningTemplateMap.clone());
    }

    @Override
    public int compareTo(
        uzuzjmd.competence.assessment.model.UserLearningTemplateMap userLearningTemplateMap) {
        return _userLearningTemplateMap.compareTo(userLearningTemplateMap);
    }

    @Override
    public int hashCode() {
        return _userLearningTemplateMap.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<uzuzjmd.competence.assessment.model.UserLearningTemplateMap> toCacheModel() {
        return _userLearningTemplateMap.toCacheModel();
    }

    @Override
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap toEscapedModel() {
        return new UserLearningTemplateMapWrapper(_userLearningTemplateMap.toEscapedModel());
    }

    @Override
    public uzuzjmd.competence.assessment.model.UserLearningTemplateMap toUnescapedModel() {
        return new UserLearningTemplateMapWrapper(_userLearningTemplateMap.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _userLearningTemplateMap.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _userLearningTemplateMap.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _userLearningTemplateMap.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof UserLearningTemplateMapWrapper)) {
            return false;
        }

        UserLearningTemplateMapWrapper userLearningTemplateMapWrapper = (UserLearningTemplateMapWrapper) obj;

        if (Validator.equals(_userLearningTemplateMap,
                    userLearningTemplateMapWrapper._userLearningTemplateMap)) {
            return true;
        }

        return false;
    }

    @Override
    public StagedModelType getStagedModelType() {
        return _userLearningTemplateMap.getStagedModelType();
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public UserLearningTemplateMap getWrappedUserLearningTemplateMap() {
        return _userLearningTemplateMap;
    }

    @Override
    public UserLearningTemplateMap getWrappedModel() {
        return _userLearningTemplateMap;
    }

    @Override
    public void resetOriginalValues() {
        _userLearningTemplateMap.resetOriginalValues();
    }
}
