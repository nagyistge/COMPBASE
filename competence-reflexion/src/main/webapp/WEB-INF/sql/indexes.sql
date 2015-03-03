create index IX_6D32AF21 on competenceAssessment_ReflexionsAssessment (uuid_);
create index IX_AFA4667 on competenceAssessment_ReflexionsAssessment (uuid_, companyId);
create unique index IX_9F5F3AA9 on competenceAssessment_ReflexionsAssessment (uuid_, groupId);