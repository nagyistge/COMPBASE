package uzuzjmd.competence.recommender;

/**
 * Created by dehne on 31.03.2016.
 */
public class RecommenderFactory {
    public static CompetenceRecommender createCompetenceRecommender() {
        return new DummyCompetenceRecommender();
    }

    public static CourseRecommender createCourseRecommender() {
        return new DummyCourseRecommender();
    }
    public static ActivityRecommender createActivityRecommender() {
        return new DummyActivityRecommender();
    }
}
