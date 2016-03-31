package uzuzjmd.competence.recommender;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by dehne on 31.03.2016.
 */
public class DummyActivityRecommender implements ActivityRecommender {
    @Override
    public HashMap<Evidence, Double> recommendActivities(String email, String competenceToReach, String userEmail) {
        HashMap<Evidence, Double> map = new HashMap<Evidence, Double>();
        map.put(new Evidence("schöne Aktivität", "http://testthis1.com", userEmail), 1.0);
        map.put(new Evidence("schöne Aktivität 2", "http://testthis2.com", userEmail), 0.3);
        return map;
    }
}
