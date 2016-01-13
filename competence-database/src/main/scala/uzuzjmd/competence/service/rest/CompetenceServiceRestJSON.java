package uzuzjmd.competence.service.rest;

import uzuzjmd.competence.mapper.rest.read.*;
import uzuzjmd.competence.mapper.rest.write.*;
import uzuzjmd.competence.service.rest.dto.*;
import uzuzjmd.competence.shared.dto.CompetenceLinksMap;
import uzuzjmd.competence.shared.dto.Graph;
import uzuzjmd.competence.shared.dto.HierarchyChangeSet;
import uzuzjmd.competence.shared.dto.ProgressMap;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;


/**
 * Root resource (exposed at "competences" path)
 */
@Path("/competences/json")
public class CompetenceServiceRestJSON {



    /**
     * use /updateHierarchie2 instead
     *
     * @param changes
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateHierarchie")
    @Deprecated
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateHierarchie(
            @QueryParam("changes") List<String> changes) {

        HierarchyChangeSet changeSet = new HierarchyChangeSet()
                .convertListToModel(changes);
        HierarchieChangesToOnt.convert(changeSet);
        return Response.ok("updated taxonomy").build();
    }

    /**
     * updates the competence hierarchy
     *
     * @param changes of type HierarchieChangeObject @see updateHierarchie2
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateHierarchie2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateHierarchie2(
            @QueryParam("changes") HierarchyChangeSet changes) {
        HierarchieChangesToOnt.convert(changes);
        return Response.ok("updated taxonomy").build();
    }

    /**
     * This is an example for the format needed for updating the hierarchie
     *
     * @param changes
     * @return
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateHierarchie2/example")
    @Produces(MediaType.APPLICATION_JSON)
    public HierarchyChangeSet updateHierarchieExample(
            @QueryParam("changes") HierarchyChangeSet changes) {
        return changes;
    }

    /**
     * Link the competences to a course context.
     * <p/>
     * This allows for selecting competences for a given context so that the
     * application can deal with a subset of the competence database.
     *
     * @param course       (the id of the course) or any name. Prefered id format is
     *                     number.
     * @param compulsory   (optional) indicates whether the competence is compulsory for
     *                     the context in terms of passing the course.
     * @param competences  the competences linked to the course
     * @param requirements a plain text string explaining why this competences are
     *                     necessary for the course
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/coursecontext/create/{course}/{compulsory}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response linkCompetencesToCourseContextJson(
            @PathParam("course") String course,
            @PathParam("compulsory") String compulsory,
            @QueryParam(value = "competences") final List<String> competences,
            @QueryParam(value = "requirements") String requirements) {

        Boolean compulsoryBoolean = RestUtil
                .convertCompulsory(compulsory);
        CompetenceServiceWrapper.linkCompetencesToCourse(
                course, competences, compulsoryBoolean,
                requirements);
        return Response.ok("competences linked to course")
                .build();
    }

    /**
     * @param user should be email-address or other unique identifier
     * @param role can be "student or teacher"
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/user/create/{user}/{role}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(
            @PathParam("user") String user,
            @PathParam("role") String role,
            @QueryParam("groupId") String courseContext) {
        UserData data = new UserData(user, courseContext,
                role);
        User2Ont.convert(data);
        if (courseContext != null) {
            CompetenceServiceWrapper
                    .linkCompetencesToCourse(courseContext,
                            new LinkedList(), false, "");
        }
        // TODO finish
        return Response.ok("user created").build();
    }

    /**
     * Deletes the course context.
     * <p/>
     * All competences linked to this context will be removed from it. This
     * should be used as a companion with coursecontext/create
     *
     * @param course
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/coursecontext/delete/{course}")
    public Response deleteCourseContext(
            @PathParam("course") String course) {
        // TODO implement
        return Response
                .ok("competences deleted from course:"
                        + course).build();
    }

    /**
     * Get the description of requirements for the course.
     * <p/>
     * The requirement string specifying why this subset of competences was
     * selected for the course is returned.
     *
     * @param course the context of the competences
     * @return the requirement string
     */
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    @Path("/coursecontext/requirements/{course}")
    public String getRequirements(
            @PathParam("course") String course) {
        String result = CompetenceServiceWrapper
                .getRequirements(course);
        return result;
    }

    /**
     * Returns all the competences linked to a course context. It is deprecated
     * /coursecontext/selected should be used.
     *
     * @param course
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/selected/{course}")
    @Deprecated
    public String[] getSelected(
            @PathParam("course") String course) {
        return CompetenceServiceWrapper.getSelected(course);
    }

    /**
     * Get competences linked to (course) context.
     * <p/>
     * Returns all the competences linked to a course context.
     *
     * @param course
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/coursecontext/selected/{course}")
    public String[] getSelectedCompetencesForCourse(
            @PathParam("course") String course) {
        return CompetenceServiceWrapper.getSelected(course);
    }

    /**
     * Creates an evidence as a proof that competences have been acquired by the
     * user by certain activities
     *
     * @param course      (the context of the acquirement)
     * @param creator     the user who created the link
     * @param role        the role of the user who created the link (can be either
     *                    "teacher" or "student")
     * @param linkedUser  the user who has acquired the competences
     * @param competences the competences acquired
     * @param evidences   the activities that stand as evidences in the form [url,
     *                    speakingname]
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/link/create/{course}/{creator}/{role}/{linkedUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response linkCompetencesToUser(
            @PathParam("course") String course,
            @PathParam("creator") String creator,
            @PathParam("role") String role,
            @PathParam("linkedUser") String linkedUser,
            @QueryParam(value = "competences") List<String> competences,
            @QueryParam(value = "evidences") List<String> evidences) {

        CompetenceLinkData data = new CompetenceLinkData(
                course, creator, role, linkedUser,
                competences, evidences);
        Link2Ont.writeLinkToDatabase(data);
        return Response.ok(
                "competences linked to evidences").build();
    }

    /**
     * Add a comment to an evidence link
     * <p/>
     * Have a look at @see linkCompetencesToUser in order to better
     * understand the model of a evidence link.
     *
     * @param linkId        the id of the link
     * @param user          the user who creates the comment
     * @param text          the text of the comment
     * @param courseContext the course context the comment is created in
     * @param role          the role of the user
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/link/comment/{linkId}/{user}/{courseContext}/{role}")
    public Response commentCompetence(
            @PathParam("linkId") String linkId,
            @PathParam("user") String user,
            @QueryParam("text") String text,
            @PathParam("courseContext") String courseContext,
            @PathParam("role") String role) {
        UserData userData = new UserData(user,
                courseContext, role);
        User2Ont.convert(userData);

        CommentData commentData = new CommentData(linkId,
                user, text, courseContext, role);
        Comment2Ont.convert(commentData);

        return Response.ok("link commented").build();
    }

    /**
     * Validate an evidence link.
     * <p/>
     * Have a look at for the nature of the
     * evidence link.
     * <p/>
     * This should only be done by teacher role (which should be checked in the
     * frontend)
     *
     * @param linkId
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/link/validate/{linkId}")
    public Response validateLink(
            @PathParam("linkId") String linkId) {
        Boolean isvalid = true;
        return handleLinkValidation(linkId, isvalid);
    }

    /**
     * Invalidate a link
     * <p/>
     * (this should only be done by teacher role (which should be checked in the
     * frontend)
     *
     * @param linkId
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/link/invalidate/{linkId}")
    public Response invalidateLink(
            @PathParam("linkId") String linkId) {
        Boolean isvalid = false;
        return handleLinkValidation(linkId, isvalid);
    }

    /**
     * Delete an evidence link
     *
     * @param linkId the id of the link to be deleted
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/link/delete/{linkId}")
    public Response deleteLink(
            @PathParam("linkId") String linkId) {
        AbstractEvidenceLink2Ont.convert(linkId);
        return Response.ok("link deleted").build();
    }


    /**
     * Deletes one or more competences
     *
     * @param competences
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/competence/delete")
    public Response deleteCompetence(
            @QueryParam("competences") List<String> competences) {

        DeleteCompetenceInOnt.convert(competences);
        return Response.ok("competences deleted").build();
    }

    /**
     * Deletes competences and all their subcompetences
     *
     * @param competences the competences to be deleted
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/competence/deleteTree")
    public Response deleteCompetenceTree(
            @QueryParam("competences") List<String> competences) {
        DeleteCompetenceTreeInOnt.convert(competences);
        return Response.ok("competences deleted").build();
    }

    /**
     * returns a map competence->evidences
     * <p/>
     * Returns all the evidences for a user in a form that they can be presented
     *
     * @param user the user who has acquired the competences
     * @return
     */
    @GET
    @Path("/link/overview/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public CompetenceLinksMap getCompetenceLinksMap(
            @PathParam("user") String user) {
        return Ont2CompetenceLinkMap.convert(user);
    }

    /**
     * Shows overview of the progress a user has made in a course
     *
     * @param course              the course the overview is generated for
     * @param selectedCompetences a filter: the percentate of acquired competences is calculated
     *                            taking into account the competences visible to the user
     * @return
     */
    @GET
    @Path("/link/progress/{course}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProgressMap getProgressM(
            @PathParam("course") String course,
            @QueryParam("competences") List<String> selectedCompetences) {
        return GetProgressMInOnt.convert(new CourseData(
                course, selectedCompetences));
    }

    /**
     * This creates a "prerequisite" relation between the
     * selectedCompetences->linkedCompetences
     *
     * @param course              the course context the link is created in (may be "university"
     *                            for global context")
     * @param linkedCompetence    the pre competences
     * @param selectedCompetences the post competences
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/prerequisite/create/{course}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPrerequisite(
            @PathParam("course") String course,
            @QueryParam("linkedCompetence") String linkedCompetence,
            @QueryParam("selectedCompetences") List<String> selectedCompetences) {
        CreatePrerequisiteInOnt
                .convert(new PrerequisiteData(course,
                        linkedCompetence,
                        selectedCompetences));

        return Response.ok("prerequisite created").build();
    }

    /**
     * Deletes the "prerequisite" link between the competences
     *
     * @param course              the course context the link is created in (may be "university"
     *                            for global context")
     * @param linkedCompetence    the pre competences
     * @param selectedCompetences the post competences
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/prerequisite/delete/{course}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePrerequisite(
            @PathParam("course") String course,
            @QueryParam("linkedCompetence") String linkedCompetence,
            @QueryParam("competences") List<String> selectedCompetences) {
        DeletePrerequisiteInOnt
                .convert(new PrerequisiteData(course,
                        linkedCompetence,
                        selectedCompetences));
        return Response.ok("prerequisite deleted").build();
    }

    /**
     * returns the whole prerequisite graph (given filter)
     *
     * @param selectedCompetences the competences that selected that filter the graph
     * @param course
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    @Path("/prerequisite/graph/{course}")
    @Produces(MediaType.APPLICATION_JSON)
    public Graph getPrerequisiteGraph(
            @QueryParam("selectedCompetences") List<String> selectedCompetences,
            @PathParam("course") String course) {
        /*GraphFilterData graphFilterData = new GraphFilterData(selectedCompetences, course);*/
        /*Graph result= Ont2CompetenceGraph.convert(graphFilterData);*/
        //return result;
        return null;

    }

    /**
     * gets the prerequisites for the given competence
     *
     * @param forCompetence
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    @Path("/prerequisite/required/{course}")
    @Produces(MediaType.APPLICATION_JSON)
    public String[] getRequiredCompetences(
            @QueryParam("competence") String forCompetence) {
        return GetRequiredCompetencesInOnt
                .convert(forCompetence);
    }

    /**
     * GET the operator for a given competence
     *
     * @param forCompetence
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    @Path("/operator")
    public String getOperatorForCompetence(
            @QueryParam("competence") String forCompetence) {
        // Ont2Operator.
        return Ont2Operator.convert(forCompetence);
    }

    /**
     * Get all the catchwords for a given competence
     *
     * @param forCompetence
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    @Path("/catchwords")
    public String getCatchwordsForCompetence(
            @QueryParam("competence") String forCompetence) {
        return Ont2Catchwords.convert(forCompetence);
    }

    /**
     * add a competence to the model
     *
     * @param forCompetence        the name of the competences as a String (necessary)
     * @param operator             the verb of the competence (necessary)
     * @param catchwords           (at least one)
     * @param superCompetences     (optional)
     * @param subCompetences       (optional)
     * @param learningTemplateName (optional) the name of the learningTemplate it is associated
     *                             with
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/addOne")
    public Response addCompetenceToModel(
            @QueryParam("competence") String forCompetence,
            @QueryParam("operator") String operator,
            @QueryParam("catchwords") List<String> catchwords,
            @QueryParam("superCompetences") List<String> superCompetences,
            @QueryParam("subCompetences") List<String> subCompetences,
            @QueryParam("learningTemplateName") String learningTemplateName) {
        CompetenceData competenceData = new CompetenceData(
                operator, catchwords, superCompetences,
                subCompetences, learningTemplateName,
                forCompetence);
        String resultMessage = Competence2Ont
                .convert(competenceData);
        return Response.ok(resultMessage).build();
    }

    /**
     * Edit competence metadata
     * <p/>
     * (competence text may not be changed without changes in the hierarchy
     * following)
     *
     * @param forCompetence
     * @param operator
     * @param catchwords
     * @param superCompetences
     * @param subCompetences
     * @param originalCompetence
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/editOne")
    public Response editCompetenceToModel(
            @QueryParam("competence") String forCompetence,
            @QueryParam("operator") String operator,
            @QueryParam("catchwords") List<String> catchwords,
            @QueryParam("superCompetences") List<String> superCompetences,
            @QueryParam("subCompetences") List<String> subCompetences,
            @QueryParam("originalCompetence") String originalCompetence) {

        /**
         * TODO: Competence should be updated and not deleted
         */
        CompetenceData competenceData = new CompetenceData(
                operator, catchwords, superCompetences,
                subCompetences, null, forCompetence);
        String resultMessage = Competence2Ont
                .convert(competenceData);
        return Response.ok(resultMessage).build();
    }


    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/SuggestedCourseForCompetence/create")
    public Response createSuggestedCourseForCompetence(@QueryParam("competence") String competence, @QueryParam("course") String course) {
        SuggestedCourseForCompetence2Ont.write(course, competence);
        return Response.ok("edge created").build();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/SuggestedActivityForCompetence/create")
    public Response createSuggestedActivityForCompetence(@QueryParam("competence") String competence, @QueryParam("activityURL") String activityUrl) {
        SuggestedActivityForCompetence2Ont.write(activityUrl, competence);
        return Response.ok("edge created").build();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/SuggestedCourseForCompetence/delete")
    public Response deleteSuggestedCourseForCompetence(@QueryParam("competence") String competence, @QueryParam("course") String course) {
        SuggestedCourseForCompetence2Ont.delete(course, competence);
        return Response.ok("edge created").build();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/SuggestedActivityForCompetence/delete")
    public Response deleteSuggestedActivityForCompetence(@QueryParam("competence") String competence, @QueryParam("activityURL") String activityURL) {
        SuggestedActivityForCompetence2Ont.delete(activityURL, competence);
        return Response.ok("edge created").build();
    }

    private Response handleLinkValidation(String linkId,
                                          Boolean isValid) {
        HandleLinkValidationInOnt
                .convert(new LinkValidationData(linkId,
                        isValid));
        return Response.ok("link updated").build();
    }

}