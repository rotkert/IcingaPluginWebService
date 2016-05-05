package mgr.mkaminski.service;

import java.util.List;

import mgr.mkaminski.model.CounterRule;

public interface CounterRuleService {
	public long createCounterRule(CounterRule counterRule);
	public CounterRule updateCounterRule(CounterRule counterRule);
	public void deleteCounterRule(long id);
	public List<CounterRule> getAllCounterRules();
	public CounterRule getCounterRule(long id); 
	public List<CounterRule> getAllCounterRules(String counterRuleName);
}
