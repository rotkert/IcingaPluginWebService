package mgr.mkaminski.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mgr.mkaminski.dao.CounterRuleDAO;
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
	public List<CounterRule> getCounterRulesForRuleId(long ruleId) {
		return hibernateUtil.fetchByColumn("ruleId", ruleId, CounterRule.class);
	}
}
