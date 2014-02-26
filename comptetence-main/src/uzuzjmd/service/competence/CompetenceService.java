package uzuzjmd.service.competence;

import java.util.Collection;

import uzuzjmd.rcd.generated.Rdceo;

public interface CompetenceService {
	public Collection<Rdceo> getCompetences();
	public void insertCompetence(Rdceo rcdeo);
}
