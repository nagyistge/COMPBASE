package uzuzjmd.competence.service.rest;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import uzuzjmd.competence.csv.CompetenceBean;
import uzuzjmd.competence.main.CompetenceImporter;
import uzuzjmd.competence.main.EposImporter;
import uzuzjmd.competence.mapper.gui.read.Ont2LearningTemplates;
import uzuzjmd.competence.mapper.gui.read.Ont2SelectedLearningTemplate;
import uzuzjmd.competence.mapper.gui.read.Ont2SuggestedCompetenceGrid;
import uzuzjmd.competence.mapper.gui.write.LearningTemplateToOnt;
import uzuzjmd.competence.mapper.gui.write.ReflectiveAssessmentHolder2Ont;
import uzuzjmd.competence.mapper.rest.read.Ont2LearningTemplateResultSet;
import uzuzjmd.competence.mapper.rest.write.DeleteLearningTemplateinOnt;
import uzuzjmd.competence.mapper.rest.write.DeleteTemplateInOnt;
import uzuzjmd.competence.rcd.generated.Rdceo;
import uzuzjmd.competence.service.CompetenceServiceImpl;
import uzuzjmd.competence.service.rest.database.dto.CatchwordXMLTree;
import uzuzjmd.competence.service.rest.database.dto.CompetenceXMLTree;
import uzuzjmd.competence.service.rest.database.dto.OperatorXMLTree;
import uzuzjmd.competence.service.rest.model.dto.LearningTemplateData;
import uzuzjmd.competence.service.rest.model.dto.ReflectiveAssessmentChangeData;
import uzuzjmd.competence.shared.ReflectiveAssessmentsListHolder;
import uzuzjmd.competence.shared.StringList;
import uzuzjmd.competence.shared.SuggestedCompetenceGrid;
import uzuzjmd.competence.shared.dto.EPOSTypeWrapper;
import uzuzjmd.competence.shared.dto.LearningTemplateResultSet;

/**
 * Root resource (exposed at "competences" path)
 */
@Path("/competences/xml")
public class CompetenceServiceRestXML extends
		CompetenceOntologyInterface {

	private Logger logger = LogManager
			.getLogger(getClass());

	/**
	 * new competences can be added via this function. They must be encoded as
	 * Rdceo see somewhere...
	 * 
	 * @param input
	 */
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/add")
	public void addRcdeo(Rdceo input) {
		CompetenceServiceImpl competenceServiceImpl = new CompetenceServiceImpl();
		competenceServiceImpl.insertCompetence(input);
		logger.debug("Competences inserted in rcdeo format");
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/addCompetenceBean")
	public Response addCompetenceBean(
			CompetenceBean[] competenceBean) {
		logger.trace("Now inserting in xml bean format");
		CompetenceImporter
				.competenceBeanToDatabase(competenceBean);
		logger.trace("Competences inserted in xml bean format");
		return Response.ok("competences updated").build();
	}

	/**
	 * Get the GUI Competence TREE
	 * 
	 * @param course
	 *            the (course) context of the competences ("university") for no
	 *            filter
	 * @param compulsory
	 *            flag whether only competences marked as compulsory should be
	 *            loaded
	 * @param cache
	 *            can be either "cache" or "nocache"
	 * @param selectedCatchwords
	 * @param selectedOperators
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/competencetree/{context}/{compulsory}/{cache}")
	public Response getCompetenceTree(
			@PathParam("context") String course,
			@PathParam("compulsory") String compulsory,
			@PathParam("cache") String cache,
			@QueryParam(value = "selectedCatchwords") List<String> selectedCatchwords,
			@QueryParam(value = "selectedOperators") List<String> selectedOperators,
			@QueryParam("textFilter") String textFilter) {

		CompetenceXMLTree[] result = null;
		if (compulsory.equals("all")) {
			result = CompetenceServiceWrapper
					.getCompetenceTree(selectedCatchwords,
							selectedOperators, course,
							null, textFilter);
		} else {
			Boolean compulsoryBoolean = RestUtil
					.convertCompulsory(compulsory);
			result = CompetenceServiceWrapper
					.getCompetenceTree(selectedCatchwords,
							selectedOperators, course,
							compulsoryBoolean, textFilter);
		}

		Response response = RestUtil.buildCachedResponse(
				result, cache.equals("cached"));
		return response;
	}

	/**
	 * This method is only for illustration of the output of the
	 * /competencetree/{context}/{compulsory}/{cache} method and the
	 * /competencetree/coursecontext/{context}/{compulsory}/{cache} method and
	 * the /competencetree/coursecontextnofilter/{course}/{compulsory}/{cache}
	 * method
	 * 
	 * @param course
	 * @param compulsory
	 * @param cache
	 *            can be either "cache" or "nocache"
	 * @param selectedCatchwords
	 * @param selectedOperators
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/competencetree/{context}/{compulsory}/{cache}/example")
	public List<CompetenceXMLTree> getCompetenceTreeExample(
			@PathParam("context") String course,
			@PathParam("compulsory") String compulsory,
			@PathParam("cache") String cache,
			@QueryParam(value = "selectedCatchwords") List<String> selectedCatchwords,
			@QueryParam(value = "selectedOperators") List<String> selectedOperators,
			@QueryParam("textFilter") String textFilter) {

		return null;
	}

	/**
	 * 
	 * @param course
	 *            the (course) context of the competences ("university") for no
	 *            filter
	 * @param compulsory
	 * @param cache
	 *            can be either "cache" or "nocache"
	 * @param selectedCatchwords
	 * @param selectedOperators
	 * @param textFilter
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/competencetree/coursecontext/{context}/{compulsory}/{cache}")
	public Response getCompetenceTreeForCourse(
			@PathParam("context") String course,
			@PathParam("compulsory") String compulsory,
			@PathParam("cache") String cache,
			@QueryParam(value = "selectedCatchwords") List<String> selectedCatchwords,
			@QueryParam(value = "selectedOperators") List<String> selectedOperators,
			@QueryParam("textFilter") String textFilter) {

		CompetenceXMLTree[] result = null;
		if (compulsory.equals("all")) {
			result = CompetenceServiceWrapper
					.getCompetenceTreeForCourse(
							selectedCatchwords,
							selectedOperators, course,
							null, textFilter);
		} else {
			Boolean compulsoryBoolean = RestUtil
					.convertCompulsory(compulsory);
			result = CompetenceServiceWrapper
					.getCompetenceTreeForCourse(
							selectedCatchwords,
							selectedOperators, course,
							compulsoryBoolean, textFilter);
		}

		Response response = RestUtil.buildCachedResponse(
				result, cache.equals("cached"));
		return response;
	}

	/**
	 * Get the GUI operator tree
	 * 
	 * It has the same format as the competence tree
	 * 
	 * @param course
	 *            the (course) context of the competences ("university") for no
	 *            filter
	 * @param cache
	 *            can be either "cache" or "nocache"
	 * @param selectedCatchwords
	 * @param selectedOperators
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/operatortree/{course}/{cache}")
	public Response getOperatorTree(
			@PathParam("course") String course,
			@PathParam("cache") String cache,
			@QueryParam(value = "selectedCatchwords") List<String> selectedCatchwords,
			@QueryParam(value = "selectedOperators") List<String> selectedOperators) {

		OperatorXMLTree[] result = CompetenceServiceWrapper
				.getOperatorTree(selectedCatchwords,
						selectedOperators, course);

		Response response = RestUtil.buildCachedResponse(
				result, cache.equals("cached"));
		return response;
	}

	/**
	 * Get the GUI Catchword Tree
	 * 
	 * It has the same format as the competence tree
	 * 
	 * @param course
	 *            the (course) context of the competences ("university") for no
	 *            filter
	 * @param cache
	 *            can be either "cache" or "nocache"
	 * @param selectedCatchwords
	 * @param selectedOperators
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/catchwordtree/{course}/{cache}")
	public Response getCatchwordTree(
			@PathParam("course") String course,
			@PathParam("cache") String cache,
			@QueryParam(value = "selectedCatchwords") List<String> selectedCatchwords,
			@QueryParam(value = "selectedOperators") List<String> selectedOperators) {
		CatchwordXMLTree[] result = CompetenceServiceWrapper
				.getCatchwordTree(selectedCatchwords,
						selectedOperators, course);
		Response response = RestUtil.buildCachedResponse(
				result, cache.equals("cached"));
		return response;
	}

	/**
	 * Get all the learning templates
	 * 
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/learningtemplates")
	public StringList getAllLearningTemplates() {
		StringList learningTemplates = Ont2LearningTemplates
				.convert();
		return learningTemplates;
	}

	/**
	 * Get all the learningTemplates without cache. Learning Templates are
	 * groups of competences selected to be learned together.
	 * 
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/learningtemplates/cache")
	public Response getAllLearningTemplates2() {
		StringList learningTemplates = Ont2LearningTemplates
				.convert();
		Response response = RestUtil.buildCachedResponse(
				learningTemplates, true);
		return response;
	}

	/**
	 * A user selects a learning template as his project in portfolio
	 * 
	 * @param userName
	 *            the use selecting the template
	 * 
	 * @param groupId
	 *            (or course context) is the context the learning template is
	 *            selected
	 * @param selectedTemplate
	 *            the learning template selected
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@POST
	@Path("/learningtemplates/add")
	public Response addLearningTemplateSelection(
			@QueryParam(value = "userId") String userName,
			@QueryParam(value = "groupId") String groupId,
			@QueryParam(value = "selectedTemplate") String selectedTemplate) {
		LearningTemplateData data = new LearningTemplateData(
				userName, groupId, selectedTemplate);
		LearningTemplateToOnt.convert(data);
		return Response.ok("templateSelection updated")
				.build();
	}

	/**
	 * get the selected learning templates for a given user
	 * 
	 * @param userName
	 * @param groupId
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/learningtemplates/selected")
	public Response getSelectedLearningTemplates(
			@QueryParam(value = "userId") String userName,
			@QueryParam(value = "groupId") String groupId) {
		LearningTemplateData data = new LearningTemplateData(
				userName, groupId, null);
		StringList result = Ont2SelectedLearningTemplate
				.convert(data);
		return RestUtil.buildCachedResponse(result, false);
	}

	/**
	 * get the selected learning templates for a given user
	 * 
	 * @param userName
	 * @param groupId
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("/learningtemplates/selected/cache")
	public Response getSelectedLearningTemplates2(
			@QueryParam(value = "userId") String userName,
			@QueryParam(value = "groupId") String groupId) {
		LearningTemplateData data = new LearningTemplateData(
				userName, groupId, null);
		StringList result = Ont2SelectedLearningTemplate
				.convert(data);
		return RestUtil.buildCachedResponse(result, false);
	}

	/**
	 * Delete the selection of a learning template of a user.
	 * 
	 * @param userName
	 * @param groupId
	 * @param selectedTemplate
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@POST
	@Path("/learningtemplates/delete")
	public Response deleteSelectedLearningTemplate(
			@QueryParam(value = "userId") String userName,
			@QueryParam(value = "groupId") String groupId,
			@QueryParam(value = "selectedTemplate") String selectedTemplate) {

		LearningTemplateData data = new LearningTemplateData(
				userName, groupId, selectedTemplate);
		DeleteTemplateInOnt.convert(data);
		return Response.ok(
				"templateSelection updated after delete")
				.build();
	}

	/**
	 * Returns a gridview of learning templates (mainly used in epos port).
	 * Users and their selfevaluation are presented.
	 * 
	 * @param userName
	 * @param groupId
	 *            (or course context)
	 * @param selectedTemplate
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("learningtemplates/gridview")
	public SuggestedCompetenceGrid getGridView(
			@QueryParam(value = "userId") String userName,
			@QueryParam(value = "groupId") String groupId,
			@QueryParam(value = "selectedTemplate") String selectedTemplate) {

		LearningTemplateData data = new LearningTemplateData(
				userName, groupId, selectedTemplate);
		SuggestedCompetenceGrid result = Ont2SuggestedCompetenceGrid
				.convert(data);

		logger.trace("GRIDVIEW");
		logger.trace("getting grid for username: "
				+ userName + ", groupId" + groupId
				+ "selectedTemplate: " + selectedTemplate);
		logger.trace("returning the grid " + result);
		return result;
	}

	/**
	 * Returns a gridview of learning templates (mainly used in epos port).
	 * Users and their selfevaluation are presented.
	 * 
	 * @param userName
	 * @param groupId
	 *            (or course context)
	 * @param selectedTemplate
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("learningtemplates/gridview/cache")
	public Response getGridView2(
			@QueryParam(value = "userId") String userName,
			@QueryParam(value = "groupId") String groupId,
			@QueryParam(value = "selectedTemplate") String selectedTemplate) {
		LearningTemplateData data = new LearningTemplateData(
				userName, groupId, selectedTemplate);
		SuggestedCompetenceGrid result = Ont2SuggestedCompetenceGrid
				.convert(data);
		return RestUtil.buildCachedResponse(result, false);
	}

	/**
	 * Allows to persist the users self evaluation (persisting the complete grid
	 * view that was changed in the ui)
	 * 
	 * 
	 * @param userName
	 *            the user who self-evaluated
	 * @param groupId
	 *            or course context (depending on the evidence source)
	 * @param reflectiveAssessmentHolder
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@POST
	@Path("learningtemplates/gridview/update")
	public Response updateGridView(
			@QueryParam(value = "userId") String userName,
			@QueryParam(value = "groupId") String groupId,
			ReflectiveAssessmentsListHolder reflectiveAssessmentHolder) {

		ReflectiveAssessmentChangeData assessmentChangeData = new ReflectiveAssessmentChangeData(
				userName, groupId,
				reflectiveAssessmentHolder);
		ReflectiveAssessmentHolder2Ont
				.convert(assessmentChangeData);
		return Response.ok("reflexion updated").build();
	}

	/**
	 * This Method allows to import Competences in the EPOS-Format
	 * 
	 * @param eposCompetences
	 * @return
	 */
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@POST
	@Path("/learningtemplates/addEpos")
	public Response importEpos(
			@BeanParam EPOSTypeWrapper wrapper) {

		EposImporter.importEposCompetences(Arrays
				.asList(wrapper.getEposCompetences()));
		return Response.ok("epos templates updated")
				.build();
	}

	// private Response dummyGridResult() {
	// SuggestedCompetenceGrid result = new SuggestedCompetenceGrid();
	// SuggestedCompetenceRow row = new SuggestedCompetenceRow();
	// SuggestedCompetenceColumn column = new SuggestedCompetenceColumn();
	// column.setProgressInPercent(33);
	// column.setTestOutput("deimudday is here");
	// row.setSuggestedCompetenceColumns(Collections.singletonList(column));
	// result.setSuggestedCompetenceRows(Collections.singletonList(row));
	// return RestUtil.buildCachedResponse(result, false);
	// }

	/**
	 * This allows to add competences for reflection in the epos ui-format
	 * 
	 * 
	 * @param graph
	 *            the triples describe the suggested prerequisite relationships
	 *            between the competences. The directed and the label properties
	 *            may be ignored in this case
	 * 
	 *            public class Graph { public Set<GraphTriple> triples; public
	 *            Set<GraphNode> nodes; }
	 * 
	 * @param catchwordMap
	 *            This map is necessary to ensure that all competences in a
	 *            prerequisite relationship share a common catchword to order
	 *            them vertically All the triples contained in the graph must be
	 *            present in the catchword map
	 * 
	 *            MapWrapper<GraphTriple, List<String>> catchwordMap
	 * 
	 *            public class MapWrapper<KEY, VALUE> { private HashMap<KEY,
	 *            VALUE> map; }
	 * 
	 * @param learningTemplateName
	 *            the name of the learning template as String
	 * @return
	 */
	@Consumes(MediaType.APPLICATION_XML)
	@POST
	@Path("/learningtemplate/add/{learningTemplateName}")
	@Produces(MediaType.APPLICATION_XML)
	public Response addLearningTemplate(
			@PathParam("learningTemplateName") String learningTemplateName,
			LearningTemplateResultSet learningTemplateResultSet) {
		if (learningTemplateResultSet == null) {
			learningTemplateResultSet = new LearningTemplateResultSet();
		}
		learningTemplateResultSet
				.setNameOfTheLearningTemplate(learningTemplateName);
		LearningTemplateToOnt
				.convertLearningTemplateResultSet(learningTemplateResultSet);
		return Response.ok("learningTemplate added")
				.build();

	}

	/**
	 * The LearningTemplate
	 * 
	 * @param learningTemplateName
	 *            String learningTemplateName
	 * @return
	 * 
	 *         public class LearningTemplateResultSet { private GraphNode root;
	 *         // root is set if graph consists of one node private Graph
	 *         resultGraph; private HashMap<GraphTriple, List<String>>
	 *         catchwordMap; private String nameOfTheLearningTemplate;
	 * 
	 *         also look at: /learningtemplate/add
	 */
	@Consumes(MediaType.APPLICATION_XML)
	@GET
	@Path("/learningtemplate/get/{learningTemplateName}")
	@Produces(MediaType.APPLICATION_XML)
	public LearningTemplateResultSet getLearningTemplate(
			@PathParam("learningTemplateName") String learningTemplateName) {
		// OntologyWriter.convert();
		LearningTemplateResultSet result = Ont2LearningTemplateResultSet
				.convert(learningTemplateName);
		return result;
	}

	@Consumes(MediaType.APPLICATION_XML)
	@POST
	@Path("/learningtemplate/delete/{learningTemplateName}")
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteLearningTemplate(
			@PathParam("learningTemplateName") String learningTemplateName) {
		DeleteLearningTemplateinOnt
				.convert(learningTemplateName);

		// change for testcommit to gitup
		return Response.ok("learningTemplate deleted")
				.build();
	}

}