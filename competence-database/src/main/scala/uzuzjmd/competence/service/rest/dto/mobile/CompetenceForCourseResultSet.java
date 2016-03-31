
package uzuzjmd.competence.service.rest.dto.mobile;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


/**
 * comptenceslist for a user
 * <p/>
 * the list of earned competences for a given user
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "competences"
})
public class CompetenceForCourseResultSet {

    /**
     * the comptences list
     */
    @JsonProperty("competences")
    @JsonDeserialize(as = java.util.LinkedHashSet.class)
    private Set<Competence> competences = new LinkedHashSet<Competence>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * the comptences list
     *
     * @return The competences
     */
    @JsonProperty("competences")
    public Set<Competence> getCompetences() {
        return competences;
    }

    /**
     * the comptences list
     *
     * @param competences The competences
     */
    @JsonProperty("competences")
    public void setCompetences(Set<Competence> competences) {
        this.competences = competences;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
