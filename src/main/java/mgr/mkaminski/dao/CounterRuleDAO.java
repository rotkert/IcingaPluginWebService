package mgr.mkaminski.dao;

import java.util.List;

import mgr.mkaminski.model.CounterRule;

public interface CounterRuleDAO {
	public long createCounterRule(CounterRule counterRule);
	public CounterRule updateCounterRule(CounterRule counterRule);
	public void deleteCounterRule(long id);
	public List<CounterRule> getCounterRulesByGroupId(int groupId);
	public CounterRule getCounterRule(long id); 
	public List<CounterRule> getCounterRulesForRuleId(long ruleId);
}
