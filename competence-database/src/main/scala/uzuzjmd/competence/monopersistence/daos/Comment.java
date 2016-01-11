package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.exceptions.NoUserGivenException;
import uzuzjmd.competence.monopersistence.Cascadable;
import uzuzjmd.competence.monopersistence.DaoAbstractImpl;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;

/**
 * Created by dehne on 11.01.2016.
 */
public class Comment extends DaoAbstractImpl implements Cascadable {

    private Long dateCreated;
    private String text;
    private User creator;
    // TODO add creator property

    public Comment(String id) {
        super(id);
    }

    public Comment(Long dateCreated, String text, User creator) {
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
