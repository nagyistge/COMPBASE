create table competenceAssessment_ReflexionsAssessment (
	uuid_ VARCHAR(75) null,
	assessmentId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	competenceDescription VARCHAR(75) null,
	competenceId VARCHAR(75) null,
	assessmentIndex INTEGER,
	isLearningGoal BOOLEAN
);

create table competenceAssessment_UserLearningTemplateMap (
	uuid_ VARCHAR(75) null,
	userLearningTemplateMapId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	learningTemplate VARCHAR(75) null
);