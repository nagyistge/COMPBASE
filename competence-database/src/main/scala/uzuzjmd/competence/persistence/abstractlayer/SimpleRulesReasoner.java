package uzuzjmd.competence.persistence.abstractlayer;

/**
 * Created by dehne on 17.12.2015.
 */
public interface SimpleRulesReasoner {
	void reason();

	void addRuleAsString(String rule);

	void addRuleAsString(String rule,
						 String rulename);
}
