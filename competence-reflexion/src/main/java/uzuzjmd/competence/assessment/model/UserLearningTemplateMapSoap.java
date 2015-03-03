package uzuzjmd.competence.assessment.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link uzuzjmd.competence.assessment.service.http.UserLearningTemplateMapServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see uzuzjmd.competence.assessment.service.http.UserLearningTemplateMapServiceSoap
 * @generated
 */
public class UserLearningTemplateMapSoap implements Serializable {
    private String _uuid;
    private long _userLearningTemplateMapId;
    private long _groupId;
    private long _companyId;
    private long _userId;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;
    private String _learningTemplate;

    public UserLearningTemplateMapSoap() {
    }

    public static UserLearningTemplateMapSoap toSoapModel(
        UserLearningTemplateMap model) {
        UserLearningTemplateMapSoap soapModel = new UserLearningTemplateMapSoap();

        soapModel.setUuid(model.getUuid());
        soapModel.setUserLearningTemplateMapId(model.getUserLearningTemplateMapId());
        soapModel.setGroupId(model.getGroupId());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setUserId(model.getUserId());
        soapModel.setUserName(model.getUserName());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setLearningTemplate(model.getLearningTemplate());

        return soapModel;
    }

    public static UserLearningTemplateMapSoap[] toSoapModels(
        UserLearningTemplateMap[] models) {
        UserLearningTemplateMapSoap[] soapModels = new UserLearningTemplateMapSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static UserLearningTemplateMapSoap[][] toSoapModels(
        UserLearningTemplateMap[][] models) {
        UserLearningTemplateMapSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new UserLearningTemplateMapSoap[models.length][models[0].length];
        } else {
            soapModels = new UserLearningTemplateMapSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static UserLearningTemplateMapSoap[] toSoapModels(
        List<UserLearningTemplateMap> models) {
        List<UserLearningTemplateMapSoap> soapModels = new ArrayList<UserLearningTemplateMapSoap>(models.size());

        for (UserLearningTemplateMap model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new UserLearningTemplateMapSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _userLearningTemplateMapId;
    }

    public void setPrimaryKey(long pk) {
        setUserLearningTemplateMapId(pk);
    }

    public String getUuid() {
        return _uuid;
    }

    public void setUuid(String uuid) {
        _uuid = uuid;
    }

    public long getUserLearningTemplateMapId() {
        return _userLearningTemplateMapId;
    }

    public void setUserLearningTemplateMapId(long userLearningTemplateMapId) {
        _userLearningTemplateMapId = userLearningTemplateMapId;
    }

    public long getGroupId() {
        return _groupId;
    }

    public void setGroupId(long groupId) {
        _groupId = groupId;
    }

    public long getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(long companyId) {
        _companyId = companyId;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public String getUserName() {
        return _userName;
    }

    public void setUserName(String userName) {
        _userName = userName;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getLearningTemplate() {
        return _learningTemplate;
    }

    public void setLearningTemplate(String learningTemplate) {
        _learningTemplate = learningTemplate;
    }
}
