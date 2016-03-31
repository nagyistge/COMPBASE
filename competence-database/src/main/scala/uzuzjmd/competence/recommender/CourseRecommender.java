package uzuzjmd.competence.recommender;

import uzuzjmd.competence.service.rest.dto.CourseData;
import uzuzjmd.competence.shared.dto.UserCourseListItem;
import uzuzjmd.competence.shared.dto.UserCourseListResponse;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dehne on 20.01.2016.
 */
public interface CourseRecommender {
    /**
     * @param userEmail the email of the user for whom courses are recommended
     * @return the courseIds of the courses that are recommended and the normalized score for the recommendation (1.0 is perfect recommendation)
     */
    HashMap<UserCourseListItem, Double> recommendCourse(String userEmail);
}
