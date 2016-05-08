package mgr.mkaminski.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mgr.mkaminski.model.CounterRule;
import mgr.mkaminski.util.HibernateUtil;

@Repository
public class CounterRuleDAOImpl implements CounterRuleDAO{
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Override
	public long createCounterRule(CounterRule counterRule) {
		return (long) hibernateUtil.create(counterRule);
	}

	@Override
	public CounterRule updateCounterRule(CounterRule counterRule) {
		return hibernateUtil.update(counterRule);
	}

	@Override
	public void deleteCounterRule(long id) {
		CounterRule counterRule = new CounterRule();
		counterRule.setId(id);
		hibernateUtil.delete(counterRule);
		
	}

	@Override
	public List<CounterRule> getAllCounterRules() {
		return hibernateUtil.fetchAll(CounterRule.class);
	}

	@Override
	public CounterRule getCounterRule(long id) {
		return hibernateUtil.fetchById(id, CounterRule.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CounterRule> getCounterRulesForRuleId(int ruleId) {
		String query = "select * from counter_rule where ruleId = " + ruleId;
		List<Object[]> counterRuleObjects = hibernateUtil.fetchAll(query);
		List<CounterRule> counterRules = new ArrayList<>();
		
		for (Object[] counterRuleObject : counterRuleObjects) {
			CounterRule counterRule = new CounterRule();
			counterRule.setId(((BigInteger)counterRuleObject[0]).longValue());
			counterRule.setCategory((String)counterRuleObject[1]);
			counterRule.setInstance((String)counterRuleObject[2]);
			counterRule.setAbove((boolean)counterRuleObject[3]);
			counterRule.setMinPeriod((int)counterRuleObject[4]);
			counterRule.setName((String)counterRuleObject[5]);
			counterRule.setRuleId((int)counterRuleObject[6]);
			counterRule.setThreshold((double)counterRuleObject[7]);
			counterRules.add(counterRule);
		}
		return counterRules;
	}

	
}
