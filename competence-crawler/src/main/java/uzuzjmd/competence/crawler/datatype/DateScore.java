package uzuzjmd.competence.crawler.datatype;

/**
 * Created by carl on 06.01.16.
 */
public class DateScore {
    public String metaVar = "";
    public String content = "";
    public float solrScore = (float) 0.0;
    public String url = "";

    public DateScore() {
    }
    public DateScore(String metaVar) {
        this.metaVar = metaVar;
    }
}
