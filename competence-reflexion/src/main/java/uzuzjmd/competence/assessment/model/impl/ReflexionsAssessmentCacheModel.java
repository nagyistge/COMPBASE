package uzuzjmd.competence.assessment.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import uzuzjmd.competence.assessment.model.ReflexionsAssessment;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ReflexionsAssessment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ReflexionsAssessment
 * @generated
 */
public class ReflexionsAssessmentCacheModel implements CacheModel<ReflexionsAssessment>,
    Externalizable {
    public String uuid;
    public long assessmentId;
    public long groupId;
    public long companyId;
    public long userId;
    public String userName;
    public long createDate;
    public long modifiedDate;
    public String competenceDescription;
    public String competenceId;
    public int assessmentIndex;
    public boolean isLearningGoal;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{uuid=");
        sb.append(uuid);
        sb.append(", assessmentId=");
        sb.append(assessmentId);
        sb.append(", groupId=");
        sb.append(groupId);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", userName=");
        sb.append(userName);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", competenceDescription=");
        sb.append(competenceDescription);
        sb.append(", competenceId=");
        sb.append(competenceId);
        sb.append(", assessmentIndex=");
        sb.append(assessmentIndex);
        sb.append(", isLearningGoal=");
        sb.append(isLearningGoal);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ReflexionsAssessment toEntityModel() {
        ReflexionsAssessmentImpl reflexionsAssessmentImpl = new ReflexionsAssessmentImpl();

        if (uuid == null) {
            reflexionsAssessmentImpl.setUuid(StringPool.BLANK);
        } else {
            reflexionsAssessmentImpl.setUuid(uuid);
        }

        reflexionsAssessmentImpl.setAssessmentId(assessmentId);
        reflexionsAssessmentImpl.setGroupId(groupId);
        reflexionsAssessmentImpl.setCompanyId(companyId);
        reflexionsAssessmentImpl.setUserId(userId);

        if (userName == null) {
            reflexionsAssessmentImpl.setUserName(StringPool.BLANK);
        } else {
            reflexionsAssessmentImpl.setUserName(userName);
        }

        if (createDate == Long.MIN_VALUE) {
            reflexionsAssessmentImpl.setCreateDate(null);
        } else {
            reflexionsAssessmentImpl.setCreateDate(new Date(createDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            reflexionsAssessmentImpl.setModifiedDate(null);
        } else {
            reflexionsAssessmentImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (competenceDescription == null) {
            reflexionsAssessmentImpl.setCompetenceDescription(StringPool.BLANK);
        } else {
            reflexionsAssessmentImpl.setCompetenceDescription(competenceDescription);
        }

        if (competenceId == null) {
            reflexionsAssessmentImpl.setCompetenceId(StringPool.BLANK);
        } else {
            reflexionsAssessmentImpl.setCompetenceId(competenceId);
        }

        reflexionsAssessmentImpl.setAssessmentIndex(assessmentIndex);
        reflexionsAssessmentImpl.setIsLearningGoal(isLearningGoal);

        reflexionsAssessmentImpl.resetOriginalValues();

        return reflexionsAssessmentImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        uuid = objectInput.readUTF();
        assessmentId = objectInput.readLong();
        groupId = objectInput.readLong();
        companyId = objectInput.readLong();
        userId = objectInput.readLong();
        userName = objectInput.readUTF();
        createDate = objectInput.readLong();
        modifiedDate = objectInput.readLong();
        competenceDescription = objectInput.readUTF();
        competenceId = objectInput.readUTF();
        assessmentIndex = objectInput.readInt();
        isLearningGoal = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (uuid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(uuid);
        }

        objectOutput.writeLong(assessmentId);
        objectOutput.writeLong(groupId);
        objectOutput.writeLong(companyId);
        objectOutput.writeLong(userId);

        if (userName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userName);
        }

        objectOutput.writeLong(createDate);
        objectOutput.writeLong(modifiedDate);

        if (competenceDescription == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(competenceDescription);
        }

        if (competenceId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(competenceId);
        }

        objectOutput.writeInt(assessmentIndex);
        objectOutput.writeBoolean(isLearningGoal);
    }
}
