package uzuzjmd.competence.assessment.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import uzuzjmd.competence.assessment.model.UserLearningTemplateMap;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UserLearningTemplateMap in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UserLearningTemplateMap
 * @generated
 */
public class UserLearningTemplateMapCacheModel implements CacheModel<UserLearningTemplateMap>,
    Externalizable {
    public String uuid;
    public long userLearningTemplateMapId;
    public long groupId;
    public long companyId;
    public long userId;
    public String userName;
    public long createDate;
    public long modifiedDate;
    public String learningTemplate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(19);

        sb.append("{uuid=");
        sb.append(uuid);
        sb.append(", userLearningTemplateMapId=");
        sb.append(userLearningTemplateMapId);
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
        sb.append(", learningTemplate=");
        sb.append(learningTemplate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public UserLearningTemplateMap toEntityModel() {
        UserLearningTemplateMapImpl userLearningTemplateMapImpl = new UserLearningTemplateMapImpl();

        if (uuid == null) {
            userLearningTemplateMapImpl.setUuid(StringPool.BLANK);
        } else {
            userLearningTemplateMapImpl.setUuid(uuid);
        }

        userLearningTemplateMapImpl.setUserLearningTemplateMapId(userLearningTemplateMapId);
        userLearningTemplateMapImpl.setGroupId(groupId);
        userLearningTemplateMapImpl.setCompanyId(companyId);
        userLearningTemplateMapImpl.setUserId(userId);

        if (userName == null) {
            userLearningTemplateMapImpl.setUserName(StringPool.BLANK);
        } else {
            userLearningTemplateMapImpl.setUserName(userName);
        }

        if (createDate == Long.MIN_VALUE) {
            userLearningTemplateMapImpl.setCreateDate(null);
        } else {
            userLearningTemplateMapImpl.setCreateDate(new Date(createDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            userLearningTemplateMapImpl.setModifiedDate(null);
        } else {
            userLearningTemplateMapImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (learningTemplate == null) {
            userLearningTemplateMapImpl.setLearningTemplate(StringPool.BLANK);
        } else {
            userLearningTemplateMapImpl.setLearningTemplate(learningTemplate);
        }

        userLearningTemplateMapImpl.resetOriginalValues();

        return userLearningTemplateMapImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        uuid = objectInput.readUTF();
        userLearningTemplateMapId = objectInput.readLong();
        groupId = objectInput.readLong();
        companyId = objectInput.readLong();
        userId = objectInput.readLong();
        userName = objectInput.readUTF();
        createDate = objectInput.readLong();
        modifiedDate = objectInput.readLong();
        learningTemplate = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (uuid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(uuid);
        }

        objectOutput.writeLong(userLearningTemplateMapId);
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

        if (learningTemplate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(learningTemplate);
        }
    }
}
