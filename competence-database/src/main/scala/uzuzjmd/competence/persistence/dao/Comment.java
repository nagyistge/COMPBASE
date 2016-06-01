package uzuzjmd.competence.persistence.dao;

import uzuzjmd.competence.exceptions.NoUserGivenException;
import uzuzjmd.competence.persistence.ontology.Edge;
import uzuzjmd.competence.service.rest.dto.CommentData;

/**
 * Created by dehne on 11.01.2016.
 */
public class Comment extends AbstractComment implements Cascadable {

    public Long dateCreated;
    public String text;

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

    public CommentData getData(){
        CommentData commentData = new CommentData(null, this.getText(), null, null, null, null);
        return commentData;
    }

    @Override
    public void persistMore() throws Exception {
        this.persist();
        if (creator == null) {
            throw new NoUserGivenException();
        }
        createEdgeWith(creator, Edge.UserOfComment);
    }




}
