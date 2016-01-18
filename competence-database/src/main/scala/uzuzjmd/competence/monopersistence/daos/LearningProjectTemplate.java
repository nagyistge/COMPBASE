package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.persistence.ontology.CompObjectProperties;
import uzuzjmd.competence.shared.dto.LearningTemplateResultSet;

import java.util.List;

/**
 * Created by dehne on 11.01.2016.
 */
public class LearningProjectTemplate extends AbstractLearningProjectTemplate implements HasDefinition, Cascadable{

    public LearningProjectTemplate(String id) {
        super(id);
    }

    public LearningProjectTemplate(String id, List<Competence> associatedCompetences) {
        super(id);
        this.associatedCompetences = associatedCompetences;
    }





    /**
     * Definition here is the same as the readable Name
     * @return
     */
    @Override
    public String getDefinition() {
        return this.getId();
    }

    @Override
    public void persistMore() throws Exception {
        this.persist();
        if (associatedCompetences != null) {
            for (Competence associatedCompetence : associatedCompetences) {
                addCompetenceToProject(associatedCompetence);
            }
        }
    }


    public List<Competence> getAssociatedCompetences() throws Exception {
        return getAssociatedDaosAsDomain(CompObjectProperties.LearningProjectTemplateOf, Competence.class);
    }


    public void addCompetenceToProject(Competence competence) throws Exception {
        createEdgeWith(CompObjectProperties.LearningProjectTemplateOf, competence);
    }
}
