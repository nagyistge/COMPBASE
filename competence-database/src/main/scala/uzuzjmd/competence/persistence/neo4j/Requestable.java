package uzuzjmd.competence.persistence.neo4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by carl on 16.12.15.
 */
public interface Requestable<T> {
    public T doRequest(String queryType) throws Exception;
}
