package uzuzjmd.competence.neo4j;

import com.hp.hpl.jena.ontology.Individual;
import uzuzjmd.competence.persistence.ontology.CompOntClass;

/**
 * Created by dehne on 10.12.2015.
 */
public class SubClassRelation {
    private Individual subClass;
    private Individual superClass;
    private CompOntClass labelOfIndividuals;

    public SubClassRelation(Individual subClass, Individual superClass, CompOntClass labelOfIndividuals) {
        this.subClass = subClass;
        this.superClass = superClass;
        this.labelOfIndividuals = labelOfIndividuals;
    }

    public Individual getSubClass() {
        return subClass;
    }

    public void setSubClass(Individual subClass) {
        this.subClass = subClass;
    }

    public Individual getSuperClass() {
        return superClass;
    }

    public void setSuperClass(Individual superClass) {
        this.superClass = superClass;
    }

    public CompOntClass getLabelOfIndividuals() {
        return labelOfIndividuals;
    }

    public void setLabelOfIndividuals(CompOntClass labelOfIndividuals) {
        this.labelOfIndividuals = labelOfIndividuals;
    }


}
