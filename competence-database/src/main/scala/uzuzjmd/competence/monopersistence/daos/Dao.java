package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.persistence.ontology.CompObjectProperties;
import uzuzjmd.competence.persistence.ontology.CompOntClass;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dehne on 11.01.2016.
 */
public interface Dao {
    /**
     * should be updated if already exists
     * @throws Exception
     */
    void persist() throws Exception;
    void delete() throws Exception;
    void createEdgeWith(CompObjectProperties edge, Dao range) throws Exception;
    void createEdgeWith(Dao domain, CompObjectProperties edge) throws Exception;
    Boolean hasEdge(CompObjectProperties edge, Dao range) throws Exception;
    Boolean hasEdge(Dao domain, CompObjectProperties edge) throws Exception;
    void deleteEdgeWith(Dao domain, CompObjectProperties edge) throws Exception;
    String getId();
    Boolean exists() throws Exception;
    CompOntClass getLabel();
    void setFullDao(HashMap<String, String> props);
    <T extends Dao> T getFullDao(HashMap<String, String> props);
    <T extends Dao> T getFullDao() throws Exception;
    List<String> getAssociatedDaoIdsAsDomain(CompObjectProperties edge) throws Exception;
    List<String> getAssociatedDaoIdsAsRange(CompObjectProperties edge) throws Exception;
    <P extends Dao> List<P> getAssociatedDaosAsDomain(CompObjectProperties edge, Class<P> clazz) throws Exception;
    <T extends Dao> List<T> getAssociatedDaosAsRange(CompObjectProperties edge, Class<T> clazz) throws Exception;
    <P extends Dao> P getAssociatedDaoAsDomain(CompObjectProperties edge, Class<P> clazz) throws Exception;
    <T extends Dao> T getAssociatedDaoAsRange(CompObjectProperties edge, Class<T> clazz) throws Exception;
}
