package uzuzjmd.competence.shared.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dehne on 30.05.2016.
 */
@XmlRootElement
public class UserProgress {
    private java.util.List<UserCompetenceProgress> userCompetenceProgressList;

    public UserProgress() {
    }

    public UserProgress(List<UserCompetenceProgress> userCompetenceProgressList) {
        this.userCompetenceProgressList = userCompetenceProgressList;
    }

    public UserProgress(UserCompetenceProgress userCompetenceProgress) {
        userCompetenceProgressList = new ArrayList<>();
        userCompetenceProgressList.add(userCompetenceProgress);
    }

    public List<UserCompetenceProgress> getUserCompetenceProgressList() {
        return userCompetenceProgressList;
    }

    public void setUserCompetenceProgressList(List<UserCompetenceProgress> userCompetenceProgressList) {
        this.userCompetenceProgressList = userCompetenceProgressList;
    }
}
