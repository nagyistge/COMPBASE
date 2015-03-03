package uzuzjmd.competence.assessment.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import uzuzjmd.competence.assessment.service.ClpSerializer;
import uzuzjmd.competence.assessment.service.UserLearningTemplateMapLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class UserLearningTemplateMapClp extends BaseModelImpl<UserLearningTemplateMap>
    implements UserLearningTemplateMap {
    private String _uuid;
    private long _userLearningTemplateMapId;
    private long _groupId;
    private long _companyId;
    private long _userId;
    private String _userUuid;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;
    private String _learningTemplate;
    private BaseModel<?> _userLearningTemplateMapRemoteModel;

    public UserLearningTemplateMapClp() {
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
    public long getPrimaryKey() {
        return _userLearningTemplateMapId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setUserLearningTemplateMapId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _userLearningTemplateMapId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
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

    @Override
    public String getUuid() {
        return _uuid;
    }

    @Override
    public void setUuid(String uuid) {
        _uuid = uuid;

        if (_userLearningTemplateMapRemoteModel != null) {
            try {
                Class<?> clazz = _userLearningTemplateMapRemoteModel.getClass();

                Method method = clazz.getMethod("setUuid", String.class);

                method.invoke(_userLearningTemplateMapRemoteModel, uuid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getUserLearningTemplateMapId() {
        return _userLearningTemplateMapId;
    }

    @Override
    public void setUserLearningTemplateMapId(long userLearningTemplateMapId) {
        _userLearningTemplateMapId = userLearningTemplateMapId;

        if (_userLearningTemplateMapRemoteModel != null) {
            try {
                Class<?> clazz = _userLearningTemplateMapRemoteModel.getClass();

                Method method = clazz.getMethod("setUserLearningTemplateMapId",
                        long.class);

                method.invoke(_userLearningTemplateMapRemoteModel,
                    userLearningTemplateMapId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getGroupId() {
        return _groupId;
    }

    @Override
    public void setGroupId(long groupId) {
        _groupId = groupId;

        if (_userLearningTemplateMapRemoteModel != null) {
            try {
                Class<?> clazz = _userLearningTemplateMapRemoteModel.getClass();

                Method method = clazz.getMethod("setGroupId", long.class);

                method.invoke(_userLearningTemplateMapRemoteModel, groupId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getCompanyId() {
        return _companyId;
    }

    @Override
    public void setCompanyId(long companyId) {
        _companyId = companyId;

        if (_userLearningTemplateMapRemoteModel != null) {
            try {
                Class<?> clazz = _userLearningTemplateMapRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", long.class);

                method.invoke(_userLearningTemplateMapRemoteModel, companyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(long userId) {
        _userId = userId;

        if (_userLearningTemplateMapRemoteModel != null) {
            try {
                Class<?> clazz = _userLearningTemplateMapRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_userLearningTemplateMapRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    @Override
    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
    }

    @Override
    public String getUserName() {
        return _userName;
    }

    @Override
    public void setUserName(String userName) {
        _userName = userName;

        if (_userLearningTemplateMapRemoteModel != null) {
            try {
                Class<?> clazz = _userLearningTemplateMapRemoteModel.getClass();

                Method method = clazz.getMethod("setUserName", String.class);

                method.invoke(_userLearningTemplateMapRemoteModel, userName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreateDate() {
        return _createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        _createDate = createDate;

        if (_userLearningTemplateMapRemoteModel != null) {
            try {
                Class<?> clazz = _userLearningTemplateMapRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_userLearningTemplateMapRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;

        if (_userLearningTemplateMapRemoteModel != null) {
            try {
                Class<?> clazz = _userLearningTemplateMapRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_userLearningTemplateMapRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLearningTemplate() {
        return _learningTemplate;
    }

    @Override
    public void setLearningTemplate(String learningTemplate) {
        _learningTemplate = learningTemplate;

        if (_userLearningTemplateMapRemoteModel != null) {
            try {
                Class<?> clazz = _userLearningTemplateMapRemoteModel.getClass();

                Method method = clazz.getMethod("setLearningTemplate",
                        String.class);

                method.invoke(_userLearningTemplateMapRemoteModel,
                    learningTemplate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public StagedModelType getStagedModelType() {
        return new StagedModelType(PortalUtil.getClassNameId(
                UserLearningTemplateMap.class.getName()));
    }

    public BaseModel<?> getUserLearningTemplateMapRemoteModel() {
        return _userLearningTemplateMapRemoteModel;
    }

    public void setUserLearningTemplateMapRemoteModel(
        BaseModel<?> userLearningTemplateMapRemoteModel) {
        _userLearningTemplateMapRemoteModel = userLearningTemplateMapRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _userLearningTemplateMapRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_userLearningTemplateMapRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            UserLearningTemplateMapLocalServiceUtil.addUserLearningTemplateMap(this);
        } else {
            UserLearningTemplateMapLocalServiceUtil.updateUserLearningTemplateMap(this);
        }
    }

    @Override
    public UserLearningTemplateMap toEscapedModel() {
        return (UserLearningTemplateMap) ProxyUtil.newProxyInstance(UserLearningTemplateMap.class.getClassLoader(),
            new Class[] { UserLearningTemplateMap.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        UserLearningTemplateMapClp clone = new UserLearningTemplateMapClp();

        clone.setUuid(getUuid());
        clone.setUserLearningTemplateMapId(getUserLearningTemplateMapId());
        clone.setGroupId(getGroupId());
        clone.setCompanyId(getCompanyId());
        clone.setUserId(getUserId());
        clone.setUserName(getUserName());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setLearningTemplate(getLearningTemplate());

        return clone;
    }

    @Override
    public int compareTo(UserLearningTemplateMap userLearningTemplateMap) {
        int value = 0;

        if (getUserId() < userLearningTemplateMap.getUserId()) {
            value = -1;
        } else if (getUserId() > userLearningTemplateMap.getUserId()) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof UserLearningTemplateMapClp)) {
            return false;
        }

        UserLearningTemplateMapClp userLearningTemplateMap = (UserLearningTemplateMapClp) obj;

        long primaryKey = userLearningTemplateMap.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(19);

        sb.append("{uuid=");
        sb.append(getUuid());
        sb.append(", userLearningTemplateMapId=");
        sb.append(getUserLearningTemplateMapId());
        sb.append(", groupId=");
        sb.append(getGroupId());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", userName=");
        sb.append(getUserName());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", learningTemplate=");
        sb.append(getLearningTemplate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(31);

        sb.append("<model><model-name>");
        sb.append("uzuzjmd.competence.assessment.model.UserLearningTemplateMap");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>uuid</column-name><column-value><![CDATA[");
        sb.append(getUuid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userLearningTemplateMapId</column-name><column-value><![CDATA[");
        sb.append(getUserLearningTemplateMapId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupId</column-name><column-value><![CDATA[");
        sb.append(getGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userName</column-name><column-value><![CDATA[");
        sb.append(getUserName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>learningTemplate</column-name><column-value><![CDATA[");
        sb.append(getLearningTemplate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
