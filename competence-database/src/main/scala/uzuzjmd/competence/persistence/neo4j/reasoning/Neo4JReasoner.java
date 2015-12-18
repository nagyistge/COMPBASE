package uzuzjmd.competence.persistence.neo4j.reasoning;

import uzuzjmd.competence.persistence.abstractlayer.SimpleRulesReasoner;
import uzuzjmd.competence.persistence.neo4j.Neo4JQueryManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dehne on 17.12.2015.
 */
public class Neo4JReasoner implements SimpleRulesReasoner {

    List<String> rules = new LinkedList<String>();

    @Override
    public void reason() {
        Method[] methods = Neo4jRules.class.getMethods();
        for (Method method: methods) {
            try {
                String rule = method.invoke(null).toString();
                rules.add(rule);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        Neo4JQueryManager neo4JQueryManager = new Neo4JQueryManager();
        try {
            neo4JQueryManager.executeReasoning(rules.toArray(new String[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRuleAsString(String rule) {
        rules.add(rule);
    }

    @Override
    public void addRuleAsString(String rule, String rulename) {
        rules.add(rule);
    }
}
