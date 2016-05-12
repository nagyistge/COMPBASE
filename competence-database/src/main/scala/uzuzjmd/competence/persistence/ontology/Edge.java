package uzuzjmd.competence.persistence.ontology;

/**
 *
 */
public enum Edge {
    /*groups catchwords in metakategories - not used at the moment */
    MetaCatchwordOf,
    /*competences are grouped as a taxonomy*/
    SubCompetenceOf,
    /*operator(the verb of the competences) are grouped in taxonomies - not used*/
    SubOperatorOf,
    /*operator(the verb of the competences) */
    OperatorOf,
    /*a catchword is a tag of acompetence*/
    CatchwordOf,
    /* the evidence of the competence should be a web link or equivalent concept*/
    EvidencOf,
    /*description of a competence - not used*/
    CompetenceDescriptionOf,
    /* the learner of the competence */
    LearnerOf,
    /* groups a number of operators */
    MetaOperatorOf,
    /*The course in the lms the competence belongs to (works with single lms so far)*/
    CourseContextOfCompetence,
    /*if the competence is compulsory to acquire in the course*/
    CompulsoryOf,
    /*the comment of a evidence*/
    CommentOfEvidence,
    /* the comment of a competence*/
    CommentOfCompetence,
    /* the comment of a course*/
    CommentOfCourse,
    /* the evidence link of the course context*/
    LinkOfCourseContext,
    /*The evidence relates to the user*/
    UserOfLink,
    /*the activity of an evidence link*/
    ActivityOf,
    /*links the evidence link the creator*/
    createdBy,
    /*Links the User with the comment he created*/
    UserOfComment,
    /* LInks the competence with the evidence link*/
    linksCompetence,
    /*a competence can be the requirement for another (in a learnpath)*/
    PrerequisiteOf,
    /*opposite of the above*/
    NotPrerequisiteOf,
    /*User is not allowed to view a competence (for example in learnpath)*/
    NotAllowedToView,
    /*a competence can be suggested to be prerequisite of*/
    SuggestedCompetencePrerequisiteOf,
    /*competences can be grouped by catchword and context to a learningproject*/
    LearningProjectTemplateOf,
    /*Learning templates may be attached to a certain course*/
    CourseContextOfSelectedLearningProjectTemplate,
    /*User may decide to adopt a learning project as his own (or be assigned)*/
    UserOfLearningProjectTemplate,
    /*assessment has a user and a competence*/
    AssessmentOfUser,
    AssessmentOfCompetence,
    /* user has performed (fully) a competence*/
    UserHasPerformed,
    /*activities may be suggested for acompetence*/
    SuggestedActivityForCompetence,
    /*courses may be recommended for a user*/
    RecommendedCourseForUser,
    /*competence may be a subclass of another */
    subClassOf,
    /* user may belong to a couse context*/
    CourseContextOfUser,
    /* 2 competences may be similar based on a weight */
    SimilarTo,
    /* hide competence for User*/
    HiddenFor
}
