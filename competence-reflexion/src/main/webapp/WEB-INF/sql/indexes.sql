create index IX_6D32AF21 on competenceAssessment_ReflexionsAssessment (uuid_);
create index IX_AFA4667 on competenceAssessment_ReflexionsAssessment (uuid_, companyId);
create unique index IX_9F5F3AA9 on competenceAssessment_ReflexionsAssessment (uuid_, groupId);

create index IX_4E2AD309 on competenceAssessment_UserLearningTemplateMap (uuid_);
create index IX_A19C217F on competenceAssessment_UserLearningTemplateMap (uuid_, companyId);
create unique index IX_C26CFBC1 on competenceAssessment_UserLearningTemplateMap (uuid_, groupId);