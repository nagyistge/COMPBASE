package de.unipotsdam.elis.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import de.unipotsdam.elis.service.ClpSerializer;
import de.unipotsdam.elis.service.EvidenceLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class EvidenceClp extends BaseModelImpl<Evidence> implements Evidence {
    private long _evidenceId;
    private long _groupId;
    private long _companyId;
    private long _userId;
    private String _userUuid;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;
    private String _title;
    private String _link;
    private String _summary;
    private String _activityTyp;
    private BaseModel<?> _evidenceRemoteModel;

    public EvidenceClp() {
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
    public long getPrimaryKey() {
        return _evidenceId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setEvidenceId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _evidenceId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
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

    @Override
    public long getEvidenceId() {
        return _evidenceId;
    }

    @Override
    public void setEvidenceId(long evidenceId) {
        _evidenceId = evidenceId;

        if (_evidenceRemoteModel != null) {
            try {
                Class<?> clazz = _evidenceRemoteModel.getClass();

                Method method = clazz.getMethod("setEvidenceId", long.class);

                method.invoke(_evidenceRemoteModel, evidenceId);
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

        if (_evidenceRemoteModel != null) {
            try {
                Class<?> clazz = _evidenceRemoteModel.getClass();

                Method method = clazz.getMethod("setGroupId", long.class);

                method.invoke(_evidenceRemoteModel, groupId);
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

        if (_evidenceRemoteModel != null) {
            try {
                Class<?> clazz = _evidenceRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", long.class);

                method.invoke(_evidenceRemoteModel, companyId);
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

        if (_evidenceRemoteModel != null) {
            try {
                Class<?> clazz = _evidenceRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_evidenceRemoteModel, userId);
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

        if (_evidenceRemoteModel != null) {
            try {
                Class<?> clazz = _evidenceRemoteModel.getClass();

                Method method = clazz.getMethod("setUserName", String.class);

                method.invoke(_evidenceRemoteModel, userName);
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

        if (_evidenceRemoteModel != null) {
            try {
                Class<?> clazz = _evidenceRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_evidenceRemoteModel, createDate);
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

        if (_evidenceRemoteModel != null) {
            try {
                Class<?> clazz = _evidenceRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_evidenceRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTitle() {
        return _title;
    }

    @Override
    public void setTitle(String title) {
        _title = title;

        if (_evidenceRemoteModel != null) {
            try {
                Class<?> clazz = _evidenceRemoteModel.getClass();

                Method method = clazz.getMethod("setTitle", String.class);

                method.invoke(_evidenceRemoteModel, title);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLink() {
        return _link;
    }

    @Override
    public void setLink(String link) {
        _link = link;

        if (_evidenceRemoteModel != null) {
            try {
                Class<?> clazz = _evidenceRemoteModel.getClass();

                Method method = clazz.getMethod("setLink", String.class);

                method.invoke(_evidenceRemoteModel, link);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSummary() {
        return _summary;
    }

    @Override
    public void setSummary(String summary) {
        _summary = summary;

        if (_evidenceRemoteModel != null) {
            try {
                Class<?> clazz = _evidenceRemoteModel.getClass();

                Method method = clazz.getMethod("setSummary", String.class);

                method.invoke(_evidenceRemoteModel, summary);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getActivityTyp() {
        return _activityTyp;
    }

    @Override
    public void setActivityTyp(String activityTyp) {
        _activityTyp = activityTyp;

        if (_evidenceRemoteModel != null) {
            try {
                Class<?> clazz = _evidenceRemoteModel.getClass();

                Method method = clazz.getMethod("setActivityTyp", String.class);

                method.invoke(_evidenceRemoteModel, activityTyp);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getEvidenceRemoteModel() {
        return _evidenceRemoteModel;
    }

    public void setEvidenceRemoteModel(BaseModel<?> evidenceRemoteModel) {
        _evidenceRemoteModel = evidenceRemoteModel;
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

        Class<?> remoteModelClass = _evidenceRemoteModel.getClass();

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

        Object returnValue = method.invoke(_evidenceRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            EvidenceLocalServiceUtil.addEvidence(this);
        } else {
            EvidenceLocalServiceUtil.updateEvidence(this);
        }
    }

    @Override
    public Evidence toEscapedModel() {
        return (Evidence) ProxyUtil.newProxyInstance(Evidence.class.getClassLoader(),
            new Class[] { Evidence.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        EvidenceClp clone = new EvidenceClp();

        clone.setEvidenceId(getEvidenceId());
        clone.setGroupId(getGroupId());
        clone.setCompanyId(getCompanyId());
        clone.setUserId(getUserId());
        clone.setUserName(getUserName());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setTitle(getTitle());
        clone.setLink(getLink());
        clone.setSummary(getSummary());
        clone.setActivityTyp(getActivityTyp());

        return clone;
    }

    @Override
    public int compareTo(Evidence evidence) {
        long primaryKey = evidence.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof EvidenceClp)) {
            return false;
        }

        EvidenceClp evidence = (EvidenceClp) obj;

        long primaryKey = evidence.getPrimaryKey();

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
        StringBundler sb = new StringBundler(23);

        sb.append("{evidenceId=");
        sb.append(getEvidenceId());
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
        sb.append(", title=");
        sb.append(getTitle());
        sb.append(", link=");
        sb.append(getLink());
        sb.append(", summary=");
        sb.append(getSummary());
        sb.append(", activityTyp=");
        sb.append(getActivityTyp());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(37);

        sb.append("<model><model-name>");
        sb.append("de.unipotsdam.elis.model.Evidence");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>evidenceId</column-name><column-value><![CDATA[");
        sb.append(getEvidenceId());
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
            "<column><column-name>title</column-name><column-value><![CDATA[");
        sb.append(getTitle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>link</column-name><column-value><![CDATA[");
        sb.append(getLink());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>summary</column-name><column-value><![CDATA[");
        sb.append(getSummary());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>activityTyp</column-name><column-value><![CDATA[");
        sb.append(getActivityTyp());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
