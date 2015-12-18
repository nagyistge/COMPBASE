package uzuzjmd.competence.persistence.owl;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.config.MagicStrings;
import uzuzjmd.competence.persistence.abstractlayer.CompOntologyAccess;
import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager;
import uzuzjmd.competence.persistence.abstractlayer.CompetenceQueries;
import uzuzjmd.competence.persistence.abstractlayer.SimpleRulesReasoner;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;
import uzuzjmd.competence.persistence.ontology.CompOntClass;
import uzuzjmd.competence.persistence.owl.reasoning.CompRulesReasonerJenaImpl;
import uzuzjmd.competence.persistence.owl.reasoning.ModelChangeListener;
import uzuzjmd.competence.persistence.owl.reasoning.RuleFactory;
import java.io.IOException;

public class CompOntologyManagerJenaImpl implements CompOntologyManager {

	static final Logger logger = LogManager
			.getLogger(CompOntologyManagerJenaImpl.class.getName());

	private CompOntologyAccess util;
	private OntModel m;
	private CompRulesReasonerJenaImpl rulesReasoner;
	private CompetenceQueries queries;
	private ModelChangeListener modelChangedListener;
	private Dataset dataset;

	/**
	 * should be singleton
	 */
	public CompOntologyManagerJenaImpl() {
		this.queries = new CompetenceQueriesJenaImpl(getM());
		this.util = new CompOntologyAccessJenaImpl(getM(),
				getQueries(), this);
	}


	/**
	 * Using rdf rules to infer new facts
	 * @param debugOn
     */
	@Override
	public void startReasoning(Boolean debugOn) {
		// init simple Rules Reasoner
		initReasoner();
		if (debugOn) {
			rulesReasoner.switchOnDebug();
		} else {
			rulesReasoner.switchOffDebug();
		}
		initRulesFactory(rulesReasoner);

		// apply rules whenever the model is changed
		this.modelChangedListener = new ModelChangeListener(
				rulesReasoner, this);

		// defaultmäßig ist derReasoner angeschaltet
		registerReasoner();
	}


	/**
	 * Get the model
	 * @return
     */
	@Override
	public OntModel getM() {
		return this.m;
	}

	/**
	 * Close the connection to the model
	 */
	@Override
	@Deprecated
	public void close() {
		dataset.commit();
		dataset.end();
	}

	/**
	 * Commit the dataset
	 */
	@Override
	public void commit() {
		dataset.commit();
	}

	/**
	 * close the dataset
	 */
	@Override
	public void end() {
		dataset.end();
	}

	/**
	 * register the reasoner listener to the model
	 */
	public void registerReasoner() {
		m.register(modelChangedListener);
	}

	private void initReasoner() {
		try {
			rulesReasoner = new CompRulesReasonerJenaImpl(this,
					false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates basic classes and relations
	 * @return
     */
	public OntModel createBaseOntology() {
		beginWrite();
		initClasses();
		initObjectProperties();
		logger.info("Base Ontology created");
		close();
		return getM();
	}

	private void initObjectProperties() {
		getUtil().createObjectProperty(
				CompOntClass.Learner,
				CompOntClass.Competence,
				CompObjectProperties.LearnerOf);
		getUtil().createObjectProperty(
				CompOntClass.Competence,
				CompOntClass.Learner,
				CompObjectProperties.LearnerOfInverse);
		getUtil().createObjectProperty(
				CompOntClass.Catchword,
				CompOntClass.Competence,
				CompObjectProperties.CatchwordOf);
		getUtil().createObjectProperty(
				CompOntClass.Competence,
				CompOntClass.Catchword,
				CompObjectProperties.CatchwordOfInverse);
		getUtil().createObjectProperty(
				CompOntClass.Evidence,
				CompOntClass.Competence,
				CompObjectProperties.EvidencOf);
		getUtil().createObjectProperty(
				CompOntClass.Competence,
				CompOntClass.Evidence,
				CompObjectProperties.EvidencOfInverse);
		getUtil().createObjectProperty(
				CompOntClass.Operator,
				CompOntClass.Competence,
				CompObjectProperties.OperatorOf);
		getUtil().createObjectProperty(
				CompOntClass.Operator,
				CompOntClass.Competence,
				CompObjectProperties.OperatorOfInverse);
		getUtil().createObjectProperty(
				CompOntClass.DescriptionElement,
				CompOntClass.CompetenceDescription,
				CompObjectProperties.DescriptionElementOf);
		getUtil()
				.createObjectProperty(
						CompOntClass.CompetenceDescription,
						CompOntClass.DescriptionElement,
						CompObjectProperties.DescriptionElementOfInverse);
		getUtil()
				.createObjectProperty(
						CompOntClass.CompetenceDescription,
						CompOntClass.Competence,
						CompObjectProperties.CompetenceDescriptionOf);
		getUtil()
				.createObjectProperty(
						CompOntClass.Competence,
						CompOntClass.CompetenceDescription,
						CompObjectProperties.CompetenceDescriptionOfInverse);
		getUtil().createObjectProperty(
				CompOntClass.Competence,
				CompOntClass.CompetenceSpec,
				CompObjectProperties.SpecifiedBy);
		getUtil().createObjectProperty(
				CompOntClass.CompetenceSpec,
				CompOntClass.Competence,
				CompObjectProperties.SpecifiedByInverse);
		getUtil().createObjectProperty(
				CompOntClass.Competence,
				CompOntClass.Competence,
				CompObjectProperties.SimilarTo);
		getUtil().createObjectProperty(
				CompOntClass.CourseContext,
				CompOntClass.Competence,
				CompObjectProperties.CourseContextOf);
		getUtil().createObjectProperty(
				CompOntClass.CourseContext,
				CompOntClass.Competence,
				CompObjectProperties.CompulsoryOf);
		getUtil().createObjectProperty(
				CompOntClass.AbstractEvidenceLink,
				CompOntClass.CourseContext,
				CompObjectProperties.LinkOfCourseContext);
		getUtil().createObjectProperty(
				CompOntClass.EvidenceActivity,
				CompOntClass.AbstractEvidenceLink,
				CompObjectProperties.ActivityOf);
		getUtil().createObjectProperty(CompOntClass.User,
				CompOntClass.AbstractEvidenceLink,
				CompObjectProperties.UserOfLink);
		getUtil().createObjectProperty(
				CompOntClass.AbstractEvidenceLink,
				CompOntClass.User,
				CompObjectProperties.createdBy);
		getUtil().createObjectProperty(CompOntClass.User,
				CompOntClass.Comment,
				CompObjectProperties.UserOfComment);
		getUtil().createObjectProperty(
				CompOntClass.Comment,
				CompOntClass.AbstractEvidenceLink,
				CompObjectProperties.CommentOf);
		getUtil().createObjectProperty(CompOntClass.Role,
				CompOntClass.User,
				CompObjectProperties.RoleOf);
		getUtil().createObjectProperty(
				CompOntClass.Competence,
				CompOntClass.Competence,
				CompObjectProperties.PrerequisiteOf);
		getUtil().createObjectProperty(
				CompOntClass.Competence,
				CompOntClass.Competence,
				CompObjectProperties.NotPrerequisiteOf);
		getUtil().createObjectProperty(CompOntClass.User,
				CompOntClass.Competence,
				CompObjectProperties.NotAllowedToView);
		getUtil()
				.createObjectProperty(
						CompOntClass.Competence,
						CompOntClass.Competence,
						CompObjectProperties.SuggestedCompetencePrerequisiteOf);
		getUtil()
				.createObjectProperty(
						CompOntClass.LearningProjectTemplate,
						CompOntClass.Competence,
						CompObjectProperties.LearningProjectTemplateOf);
		getUtil()
				.createObjectProperty(
						CompOntClass.CourseContext,
						CompOntClass.SelectedLearningProjectTemplate,
						CompObjectProperties.CourseContextOfSelectedLearningProjectTemplate);
		getUtil()
				.createObjectProperty(
						CompOntClass.SelectedLearningProjectTemplate,
						CompOntClass.LearningProjectTemplate,
						CompObjectProperties.SelectedTemplateOfLearningTemplate);
		getUtil().createObjectProperty(
				CompOntClass.SelfAssessment,
				CompOntClass.User,
				CompObjectProperties.AssessmentOfUser);
		getUtil()
				.createObjectProperty(
						CompOntClass.SelfAssessment,
						CompOntClass.User,
						CompObjectProperties.AssessmentOfCompetence);

		getUtil().createObjectProperty(CompOntClass.CourseContext, CompOntClass.Competence, CompObjectProperties.SuggestedCourseForCompetence);
		getUtil().createObjectProperty(CompOntClass.EvidenceActivity, CompOntClass.Competence, CompObjectProperties.SuggestedActivityForCompetence);
		getUtil().createObjectProperty(CompOntClass.User, CompOntClass.CourseContext, CompObjectProperties.ActivityOf.belongsToCourseContext);
	}

	private void initClasses() {
		for (CompOntClass compOntClass : CompOntClass
				.values()) {
			getUtil().createOntClass(compOntClass, false);
		}
	}


	/**
	 * starts a write-transaction
	 */
	@Override
	public void beginWrite() {
		dataset = TDBFactory
				.createDataset(MagicStrings.TDBLocationPath);
		dataset.begin(ReadWrite.WRITE);
		initModel();
	}


	/**
	 * starts a read-transaction
	 */
	public void beginRead() {
		dataset = TDBFactory
				.createDataset(MagicStrings.TDBLocationPath);
		dataset.begin(ReadWrite.READ);
		initModel();
	}

	private void initModel() {
		Model tdb = dataset.getDefaultModel();
		setM(ModelFactory.createOntologyModel(
				OntModelSpec.OWL_MEM_MICRO_RULE_INF, tdb));
	}

	private SimpleRulesReasoner initRulesFactory(
			SimpleRulesReasoner rulesReasoner) {
		RuleFactory factory = new RuleFactory();
		for (String ruleString : factory.getRuleStrings()) {
			rulesReasoner.addRuleAsString(ruleString);
		}
		return rulesReasoner;
	}

	/**
	 * return access class to the persistence layer
	 * @return
     */
	@Override
	public CompOntologyAccess getUtil() {
		return util;
	}

	/**
	 * set the model (can be used to set inmemory model instead of tdb store)
	 * @param m
     */
	public void setM(OntModel m) {
		this.m = m;
	}

	@Override
	public CompetenceQueries getQueries() {
		return queries;
	}

}
