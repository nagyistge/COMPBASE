package de.unipotsdam.elis.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import de.unipotsdam.elis.model.Evidence;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Evidence in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Evidence
 * @generated
 */
public class EvidenceCacheModel implements CacheModel<Evidence>, Externalizable {
    public long evidenceId;
    public long groupId;
    public long companyId;
    public long userId;
    public String userName;
    public long createDate;
    public long modifiedDate;
    public String title;
    public String link;
    public String summary;
    public String activityTyp;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(23);

        sb.append("{evidenceId=");
        sb.append(evidenceId);
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
        sb.append(", title=");
        sb.append(title);
        sb.append(", link=");
        sb.append(link);
        sb.append(", summary=");
        sb.append(summary);
        sb.append(", activityTyp=");
        sb.append(activityTyp);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public Evidence toEntityModel() {
        EvidenceImpl evidenceImpl = new EvidenceImpl();

        evidenceImpl.setEvidenceId(evidenceId);
        evidenceImpl.setGroupId(groupId);
        evidenceImpl.setCompanyId(companyId);
        evidenceImpl.setUserId(userId);

        if (userName == null) {
            evidenceImpl.setUserName(StringPool.BLANK);
        } else {
            evidenceImpl.setUserName(userName);
        }

        if (createDate == Long.MIN_VALUE) {
            evidenceImpl.setCreateDate(null);
        } else {
            evidenceImpl.setCreateDate(new Date(createDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            evidenceImpl.setModifiedDate(null);
        } else {
            evidenceImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (title == null) {
            evidenceImpl.setTitle(StringPool.BLANK);
        } else {
            evidenceImpl.setTitle(title);
        }

        if (link == null) {
            evidenceImpl.setLink(StringPool.BLANK);
        } else {
            evidenceImpl.setLink(link);
        }

        if (summary == null) {
            evidenceImpl.setSummary(StringPool.BLANK);
        } else {
            evidenceImpl.setSummary(summary);
        }

        if (activityTyp == null) {
            evidenceImpl.setActivityTyp(StringPool.BLANK);
        } else {
            evidenceImpl.setActivityTyp(activityTyp);
        }

        evidenceImpl.resetOriginalValues();

        return evidenceImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        evidenceId = objectInput.readLong();
        groupId = objectInput.readLong();
        companyId = objectInput.readLong();
        userId = objectInput.readLong();
        userName = objectInput.readUTF();
        createDate = objectInput.readLong();
        modifiedDate = objectInput.readLong();
        title = objectInput.readUTF();
        link = objectInput.readUTF();
        summary = objectInput.readUTF();
        activityTyp = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(evidenceId);
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

        if (title == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(title);
        }

        if (link == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(link);
        }

        if (summary == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(summary);
        }

        if (activityTyp == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(activityTyp);
        }
    }
}
