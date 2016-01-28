package uzuzjmd.competence.persistence.dao;

import uzuzjmd.competence.persistence.ontology.Edge;

import java.util.List;

/**
 * Created by dehne on 11.01.2016.
 */
public class AbstractEvidenceLink extends DaoAbstractImpl implements Cascadable {

    public User creator;
    public User linkedUser;
    public CourseContext courseContext;
    public EvidenceActivity evidenceActivity;
    public Long dateCreated;
    public Boolean isValidated;
    public Competence competence;
    public List<Comment> comments;

    public AbstractEvidenceLink(String id) {
        super(id);
    }

    public AbstractEvidenceLink(User creator,
                                User linkedUser,
                                CourseContext courseContext,
                                EvidenceActivity evidenceActivity,
                                Long dateCreated,
                                Boolean isValidated,
                                Competence competence,
                                List<Comment> comments) {
        super(competence.getId() + evidenceActivity.getId());
        this.creator = creator;
        this.linkedUser = linkedUser;
        this.courseContext = courseContext;
        this.evidenceActivity = evidenceActivity;
        this.dateCreated = dateCreated;
        this.isValidated = isValidated;
        this.competence = competence;
        this.comments = comments;
    }



    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public Boolean getValidated() {
        return isValidated;
    }

    public void setValidated(Boolean validated) {
        isValidated = validated;
    }

    public Long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public EvidenceActivity getEvidenceActivity() {
        return evidenceActivity;
    }

    public void setEvidenceActivity(EvidenceActivity evidenceActivity) {
        this.evidenceActivity = evidenceActivity;
    }

    public CourseContext getCourseContext() {
        return courseContext;
    }

    public void setCourseContext(CourseContext courseContext) {
        this.courseContext = courseContext;
    }

    public User getLinkedUser() {
        return linkedUser;
    }

    public void setLinkedUser(User linkedUser) {
        this.linkedUser = linkedUser;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public void persistMore() throws Exception{
        linkedUser.persist();
        createEdgeWith(linkedUser, Edge.UserOfLink);
        creator.persist();
        createEdgeWith(Edge.createdBy, creator);
        courseContext.persist();
        createEdgeWith(Edge.LinkOfCourseContext, courseContext);
        evidenceActivity.persist();
        createEdgeWith(evidenceActivity, Edge.ActivityOf);
        competence.persist();
        createEdgeWith(Edge.linksCompetence, competence);
        this.persist();
    }


    public void linkComments(List<Comment> comments) throws Exception {
        for (Comment comment : comments) {
            linkComment(comment);
        }
    }

    public void linkComment(Comment comment) throws Exception {
            comment.persist();
            createEdgeWith(comment, Edge.CommentOf);
    }

    public List<User> getAllLinkedUsers() throws Exception {
            return getAssociatedDaosAsDomain(Edge.UserOfLink, User.class);
    }

    public List<EvidenceActivity> getAllActivities() throws Exception {
            return getAssociatedDaosAsDomain(Edge.ActivityOf, EvidenceActivity.class);
    }

    public List<CourseContext> getAllCourseContexts() throws Exception {
            return getAssociatedDaosAsRange(Edge.LinkOfCourseContext, CourseContext.class);
    }

    public List<Competence> getAllLinkedCompetences() throws Exception {
        return  getAssociatedDaosAsRange(Edge.linksCompetence, Competence.class);
    }


}
