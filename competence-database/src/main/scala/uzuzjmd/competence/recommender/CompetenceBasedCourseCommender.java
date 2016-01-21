package uzuzjmd.competence.recommender;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dehne on 20.01.2016.
 */
public class CompetenceBasedCourseCommender implements CourseRecommender {

    /**
     *
     * @param userEmail the email of the user for whom courses are recommended
     * @return the courseIds of the courses that are recommended and the normalized score for the recommendation (1.0 is perfect recommendation)
     */
    @Override
    public List<HashMap<String, Double>> recommendCourse(String userEmail) {
        throw new NotImplementedException();
    }



    /**
     *
     * @param inputData the metadata available to the competence database or external
     * @param userEmail the Email of the user
     * @return the courseIds of the courses that are recommended and the normalized score for the recommendation (1.0 is perfect recommendation)
     */
    public List<HashMap<String, Double>> recommendCourse(CompetenceRecommendationsData inputData, String userEmail) {
        throw new NotImplementedException();
    }
}
