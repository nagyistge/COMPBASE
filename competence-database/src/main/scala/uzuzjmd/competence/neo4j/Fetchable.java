package uzuzjmd.competence.neo4j;

/**
 * Created by dehne on 09.12.2015.
 */
public interface Fetchable<T> {
    /**
     * returns the individual from the database or null
     * if it does not exist
     * @return
     */
    public T fetchIfExists();
    public void create();
    public void update();
    public void delete();
}
