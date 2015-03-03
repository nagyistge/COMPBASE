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
import uzuzjmd.competence.assessment.service.ReflexionsAssessmentLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ReflexionsAssessmentClp extends BaseModelImpl<ReflexionsAssessment>
    implements ReflexionsAssessment {
    private String _uuid;
    private long _assessmentId;
    private long _groupId;
    private long _companyId;
    private long _userId;
    private String _userUuid;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;
    private String _competenceDescription;
    private String _competenceId;
    private int _assessmentIndex;
    private boolean _isLearningGoal;
    private BaseModel<?> _reflexionsAssessmentRemoteModel;

    public ReflexionsAssessmentClp() {
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
    public long getPrimaryKey() {
        return _assessmentId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setAssessmentId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _assessmentId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
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

    @Override
    public String getUuid() {
        return _uuid;
    }

    @Override
    public void setUuid(String uuid) {
        _uuid = uuid;

        if (_reflexionsAssessmentRemoteModel != null) {
            try {
                Class<?> clazz = _reflexionsAssessmentRemoteModel.getClass();

                Method method = clazz.getMethod("setUuid", String.class);

                method.invoke(_reflexionsAssessmentRemoteModel, uuid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getAssessmentId() {
        return _assessmentId;
    }

    @Override
    public void setAssessmentId(long assessmentId) {
        _assessmentId = assessmentId;

        if (_reflexionsAssessmentRemoteModel != null) {
            try {
                Class<?> clazz = _reflexionsAssessmentRemoteModel.getClass();

                Method method = clazz.getMethod("setAssessmentId", long.class);

                method.invoke(_reflexionsAssessmentRemoteModel, assessmentId);
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

        if (_reflexionsAssessmentRemoteModel != null) {
            try {
                Class<?> clazz = _reflexionsAssessmentRemoteModel.getClass();

                Method method = clazz.getMethod("setGroupId", long.class);

                method.invoke(_reflexionsAssessmentRemoteModel, groupId);
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

        if (_reflexionsAssessmentRemoteModel != null) {
            try {
                Class<?> clazz = _reflexionsAssessmentRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", long.class);

                method.invoke(_reflexionsAssessmentRemoteModel, companyId);
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

        if (_reflexionsAssessmentRemoteModel != null) {
            try {
                Class<?> clazz = _reflexionsAssessmentRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_reflexionsAssessmentRemoteModel, userId);
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

        if (_reflexionsAssessmentRemoteModel != null) {
            try {
                Class<?> clazz = _reflexionsAssessmentRemoteModel.getClass();

                Method method = clazz.getMethod("setUserName", String.class);

                method.invoke(_reflexionsAssessmentRemoteModel, userName);
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

        if (_reflexionsAssessmentRemoteModel != null) {
            try {
                Class<?> clazz = _reflexionsAssessmentRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_reflexionsAssessmentRemoteModel, createDate);
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

        if (_reflexionsAssessmentRemoteModel != null) {
            try {
                Class<?> clazz = _reflexionsAssessmentRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_reflexionsAssessmentRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompetenceDescription() {
        return _competenceDescription;
    }

    @Override
    public void setCompetenceDescription(String competenceDescription) {
        _competenceDescription = competenceDescription;

        if (_reflexionsAssessmentRemoteModel != null) {
            try {
                Class<?> clazz = _reflexionsAssessmentRemoteModel.getClass();

                Method method = clazz.getMethod("setCompetenceDescription",
                        String.class);

                method.invoke(_reflexionsAssessmentRemoteModel,
                    competenceDescription);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompetenceId() {
        return _competenceId;
    }

    @Override
    public void setCompetenceId(String competenceId) {
        _competenceId = competenceId;

        if (_reflexionsAssessmentRemoteModel != null) {
            try {
                Class<?> clazz = _reflexionsAssessmentRemoteModel.getClass();

                Method method = clazz.getMethod("setCompetenceId", String.class);

                method.invoke(_reflexionsAssessmentRemoteModel, competenceId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAssessmentIndex() {
        return _assessmentIndex;
    }

    @Override
    public void setAssessmentIndex(int assessmentIndex) {
        _assessmentIndex = assessmentIndex;

        if (_reflexionsAssessmentRemoteModel != null) {
            try {
                Class<?> clazz = _reflexionsAssessmentRemoteModel.getClass();

                Method method = clazz.getMethod("setAssessmentIndex", int.class);

                method.invoke(_reflexionsAssessmentRemoteModel, assessmentIndex);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getIsLearningGoal() {
        return _isLearningGoal;
    }

    @Override
    public boolean isIsLearningGoal() {
        return _isLearningGoal;
    }

    @Override
    public void setIsLearningGoal(boolean isLearningGoal) {
        _isLearningGoal = isLearningGoal;

        if (_reflexionsAssessmentRemoteModel != null) {
            try {
                Class<?> clazz = _reflexionsAssessmentRemoteModel.getClass();

                Method method = clazz.getMethod("setIsLearningGoal",
                        boolean.class);

                method.invoke(_reflexionsAssessmentRemoteModel, isLearningGoal);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public StagedModelType getStagedModelType() {
        return new StagedModelType(PortalUtil.getClassNameId(
                ReflexionsAssessment.class.getName()));
    }

    public BaseModel<?> getReflexionsAssessmentRemoteModel() {
        return _reflexionsAssessmentRemoteModel;
    }

    public void setReflexionsAssessmentRemoteModel(
        BaseModel<?> reflexionsAssessmentRemoteModel) {
        _reflexionsAssessmentRemoteModel = reflexionsAssessmentRemoteModel;
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

        Class<?> remoteModelClass = _reflexionsAssessmentRemoteModel.getClass();

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

        Object returnValue = method.invoke(_reflexionsAssessmentRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ReflexionsAssessmentLocalServiceUtil.addReflexionsAssessment(this);
        } else {
            ReflexionsAssessmentLocalServiceUtil.updateReflexionsAssessment(this);
        }
    }

    @Override
    public ReflexionsAssessment toEscapedModel() {
        return (ReflexionsAssessment) ProxyUtil.newProxyInstance(ReflexionsAssessment.class.getClassLoader(),
            new Class[] { ReflexionsAssessment.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ReflexionsAssessmentClp clone = new ReflexionsAssessmentClp();

        clone.setUuid(getUuid());
        clone.setAssessmentId(getAssessmentId());
        clone.setGroupId(getGroupId());
        clone.setCompanyId(getCompanyId());
        clone.setUserId(getUserId());
        clone.setUserName(getUserName());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setCompetenceDescription(getCompetenceDescription());
        clone.setCompetenceId(getCompetenceId());
        clone.setAssessmentIndex(getAssessmentIndex());
        clone.setIsLearningGoal(getIsLearningGoal());

        return clone;
    }

    @Override
    public int compareTo(ReflexionsAssessment reflexionsAssessment) {
        int value = 0;

        value = getCompetenceId()
                    .compareTo(reflexionsAssessment.getCompetenceId());

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

        if (!(obj instanceof ReflexionsAssessmentClp)) {
            return false;
        }

        ReflexionsAssessmentClp reflexionsAssessment = (ReflexionsAssessmentClp) obj;

        long primaryKey = reflexionsAssessment.getPrimaryKey();

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
        StringBundler sb = new StringBundler(25);

        sb.append("{uuid=");
        sb.append(getUuid());
        sb.append(", assessmentId=");
        sb.append(getAssessmentId());
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
        sb.append(", competenceDescription=");
        sb.append(getCompetenceDescription());
        sb.append(", competenceId=");
        sb.append(getCompetenceId());
        sb.append(", assessmentIndex=");
        sb.append(getAssessmentIndex());
        sb.append(", isLearningGoal=");
        sb.append(getIsLearningGoal());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("uzuzjmd.competence.assessment.model.ReflexionsAssessment");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>uuid</column-name><column-value><![CDATA[");
        sb.append(getUuid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>assessmentId</column-name><column-value><![CDATA[");
        sb.append(getAssessmentId());
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
            "<column><column-name>competenceDescription</column-name><column-value><![CDATA[");
        sb.append(getCompetenceDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>competenceId</column-name><column-value><![CDATA[");
        sb.append(getCompetenceId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>assessmentIndex</column-name><column-value><![CDATA[");
        sb.append(getAssessmentIndex());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isLearningGoal</column-name><column-value><![CDATA[");
        sb.append(getIsLearningGoal());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
