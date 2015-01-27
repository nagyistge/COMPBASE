create table UPServices_Evidence (
	evidenceId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title VARCHAR(900) null,
	link VARCHAR(900) null,
	summary VARCHAR(900) null,
	activityTyp VARCHAR(75) null
);