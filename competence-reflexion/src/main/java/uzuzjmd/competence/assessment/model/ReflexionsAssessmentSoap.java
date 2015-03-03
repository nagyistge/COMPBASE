package uzuzjmd.competence.assessment.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link uzuzjmd.competence.assessment.service.http.ReflexionsAssessmentServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see uzuzjmd.competence.assessment.service.http.ReflexionsAssessmentServiceSoap
 * @generated
 */
public class ReflexionsAssessmentSoap implements Serializable {
    private String _uuid;
    private long _assessmentId;
    private long _groupId;
    private long _companyId;
    private long _userId;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;
    private String _competenceDescription;
    private String _competenceId;
    private int _assessmentIndex;
    private boolean _isLearningGoal;

    public ReflexionsAssessmentSoap() {
    }

    public static ReflexionsAssessmentSoap toSoapModel(
        ReflexionsAssessment model) {
        ReflexionsAssessmentSoap soapModel = new ReflexionsAssessmentSoap();

        soapModel.setUuid(model.getUuid());
        soapModel.setAssessmentId(model.getAssessmentId());
        soapModel.setGroupId(model.getGroupId());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setUserId(model.getUserId());
        soapModel.setUserName(model.getUserName());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setCompetenceDescription(model.getCompetenceDescription());
        soapModel.setCompetenceId(model.getCompetenceId());
        soapModel.setAssessmentIndex(model.getAssessmentIndex());
        soapModel.setIsLearningGoal(model.getIsLearningGoal());

        return soapModel;
    }

    public static ReflexionsAssessmentSoap[] toSoapModels(
        ReflexionsAssessment[] models) {
        ReflexionsAssessmentSoap[] soapModels = new ReflexionsAssessmentSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ReflexionsAssessmentSoap[][] toSoapModels(
        ReflexionsAssessment[][] models) {
        ReflexionsAssessmentSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ReflexionsAssessmentSoap[models.length][models[0].length];
        } else {
            soapModels = new ReflexionsAssessmentSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ReflexionsAssessmentSoap[] toSoapModels(
        List<ReflexionsAssessment> models) {
        List<ReflexionsAssessmentSoap> soapModels = new ArrayList<ReflexionsAssessmentSoap>(models.size());

        for (ReflexionsAssessment model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ReflexionsAssessmentSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _assessmentId;
    }

    public void setPrimaryKey(long pk) {
        setAssessmentId(pk);
    }

    public String getUuid() {
        return _uuid;
    }

    public void setUuid(String uuid) {
        _uuid = uuid;
    }

    public long getAssessmentId() {
        return _assessmentId;
    }

    public void setAssessmentId(long assessmentId) {
        _assessmentId = assessmentId;
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

    public String getCompetenceDescription() {
        return _competenceDescription;
    }

    public void setCompetenceDescription(String competenceDescription) {
        _competenceDescription = competenceDescription;
    }

    public String getCompetenceId() {
        return _competenceId;
    }

    public void setCompetenceId(String competenceId) {
        _competenceId = competenceId;
    }

    public int getAssessmentIndex() {
        return _assessmentIndex;
    }

    public void setAssessmentIndex(int assessmentIndex) {
        _assessmentIndex = assessmentIndex;
    }

    public boolean getIsLearningGoal() {
        return _isLearningGoal;
    }

    public boolean isIsLearningGoal() {
        return _isLearningGoal;
    }

    public void setIsLearningGoal(boolean isLearningGoal) {
        _isLearningGoal = isLearningGoal;
    }
}
