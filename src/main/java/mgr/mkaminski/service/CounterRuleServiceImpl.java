package mgr.mkaminski.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mgr.mkaminski.dao.CounterRuleDAO;
import mgr.mkaminski.model.CounterRule;

@Service
@Transactional
public class CounterRuleServiceImpl implements CounterRuleService {

	@Autowired
	private CounterRuleDAO counterRuleDAO;

	@Override
	public long createCounterRule(CounterRule counterRule) {
		return counterRuleDAO.createCounterRule(counterRule);
	}

	@Override
	public CounterRule updateCounterRule(CounterRule counterRule) {
		return counterRuleDAO.updateCounterRule(counterRule);
	}

	@Override
	public void deleteCounterRule(long id) {
		counterRuleDAO.deleteCounterRule(id);
	}

	@Override
	public TreeMap<Integer, ArrayList<CounterRule>> getGroupedCounterRules() {
		TreeMap<Integer, ArrayList<CounterRule>> groupedCounterRules = new TreeMap<>();
		List<CounterRule> counterRules = counterRuleDAO.getAllCounterRules();

		for (CounterRule counterRule : counterRules) {
			int ruleId = counterRule.getRuleId();
			if (!groupedCounterRules.containsKey(ruleId)) {
				groupedCounterRules.put(ruleId, new ArrayList<>());
			}
			groupedCounterRules.get(ruleId).add(counterRule);
		}
		return groupedCounterRules;
	}

	@Override
	public CounterRule getCounterRule(long id) {
		return counterRuleDAO.getCounterRule(id);
	}

	@Override
	public List<CounterRule> getAllCounterRules(String counterRuleName) {
		return counterRuleDAO.getAllCounterRules(counterRuleName);
	}
}
