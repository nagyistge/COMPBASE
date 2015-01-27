package de.unipotsdam.elis.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link de.unipotsdam.elis.service.http.EvidenceServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see de.unipotsdam.elis.service.http.EvidenceServiceSoap
 * @generated
 */
public class EvidenceSoap implements Serializable {
    private long _evidenceId;
    private long _groupId;
    private long _companyId;
    private long _userId;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;
    private String _title;
    private String _link;
    private String _summary;
    private String _activityTyp;

    public EvidenceSoap() {
    }

    public static EvidenceSoap toSoapModel(Evidence model) {
        EvidenceSoap soapModel = new EvidenceSoap();

        soapModel.setEvidenceId(model.getEvidenceId());
        soapModel.setGroupId(model.getGroupId());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setUserId(model.getUserId());
        soapModel.setUserName(model.getUserName());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setTitle(model.getTitle());
        soapModel.setLink(model.getLink());
        soapModel.setSummary(model.getSummary());
        soapModel.setActivityTyp(model.getActivityTyp());

        return soapModel;
    }

    public static EvidenceSoap[] toSoapModels(Evidence[] models) {
        EvidenceSoap[] soapModels = new EvidenceSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static EvidenceSoap[][] toSoapModels(Evidence[][] models) {
        EvidenceSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new EvidenceSoap[models.length][models[0].length];
        } else {
            soapModels = new EvidenceSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static EvidenceSoap[] toSoapModels(List<Evidence> models) {
        List<EvidenceSoap> soapModels = new ArrayList<EvidenceSoap>(models.size());

        for (Evidence model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new EvidenceSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _evidenceId;
    }

    public void setPrimaryKey(long pk) {
        setEvidenceId(pk);
    }

    public long getEvidenceId() {
        return _evidenceId;
    }

    public void setEvidenceId(long evidenceId) {
        _evidenceId = evidenceId;
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

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getLink() {
        return _link;
    }

    public void setLink(String link) {
        _link = link;
    }

    public String getSummary() {
        return _summary;
    }

    public void setSummary(String summary) {
        _summary = summary;
    }

    public String getActivityTyp() {
        return _activityTyp;
    }

    public void setActivityTyp(String activityTyp) {
        _activityTyp = activityTyp;
    }
}
