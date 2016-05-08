package mgr.mkaminski.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import mgr.mkaminski.model.CounterRule;

public interface CounterRuleService {
	public void createCounterRule(CounterRule counterRule);

	public void updateCounterRule(CounterRule counterRule);

	public void deleteCounterRule(long id);

	public TreeMap<Long, ArrayList<CounterRule>> getGroupedCounterRules();

	public CounterRule getCounterRule(long id);

	public List<CounterRule> getCounterRulesForRuleId(int ruleId);
}
