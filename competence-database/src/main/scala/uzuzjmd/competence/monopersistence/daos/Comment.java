package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.exceptions.NoUserGivenException;
import uzuzjmd.competence.monopersistence.Cascadable;
import uzuzjmd.competence.monopersistence.DaoAbstractImpl;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;

/**
 * Created by dehne on 11.01.2016.
 */
public class Comment extends AbstractComment implements Cascadable {

    private Long dateCreated;
    private String text;

    public Comment(String id) {
        super(id);
    }

    public Comment(String text, User creator, Long dateCreated) {
        super(dateCreated + text);
        this.creator = creator;
    }

    public Long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void persistMore() throws Exception {
        this.persist();
        if (creator == null) {
            throw new NoUserGivenException();
        }
        createEdgeWith(creator, CompObjectProperties.UserOfComment);
    }




}
