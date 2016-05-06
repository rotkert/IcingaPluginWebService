package mgr.mkaminski.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import mgr.mkaminski.model.CounterRule;

public interface CounterRuleService {
	public long createCounterRule(CounterRule counterRule);
	public CounterRule updateCounterRule(CounterRule counterRule);
	public void deleteCounterRule(long id);
	public TreeMap<Integer, ArrayList<CounterRule>> getGroupedCounterRules();
	public CounterRule getCounterRule(long id); 
	public List<CounterRule> getAllCounterRules(String counterRuleName);
}
