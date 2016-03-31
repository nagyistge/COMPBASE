package uzuzjmd.competence.service.rest;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uzuzjmd.competence.recommender.ActivityRecommender;
import uzuzjmd.competence.recommender.CompetenceRecommender;
import uzuzjmd.competence.recommender.CourseRecommender;
import uzuzjmd.competence.recommender.RecommenderFactory;

/**
 * Created by dehne on 31.03.2016.
 */
public class RecommenderServiceRestTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRecommendCompetences() throws Exception {
        CompetenceRecommender recommend = RecommenderFactory.createCompetenceRecommender();
        Assert.assertFalse(recommend.recommendCompetences(null, null, null).isEmpty());
    }

    @Test
    public void testRecommendActivities() throws Exception {
        ActivityRecommender recommender = RecommenderFactory.createActivityRecommender();
        Assert.assertFalse(recommender.recommendActivities(null, null, null).isEmpty());
    }

    @Test
    public void testRecommendCourses() throws Exception {
        CourseRecommender recommender = RecommenderFactory.createCourseRecommender();
        Assert.assertFalse(recommender.recommendCourse(null).isEmpty());
    }
}