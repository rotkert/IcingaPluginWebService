package mgr.mkaminski.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mgr.mkaminski.dao.CounterRuleDAO;
import mgr.mkaminski.model.CounterRule;
import mgr.mkaminski.service.CounterRuleService;

@Service
@Transactional
public class CounterRuleServiceImpl implements CounterRuleService {

	@Autowired
	private CounterRuleDAO counterRuleDAO;

	@Override
	public void createCounterRule(CounterRule counterRule) {
		counterRuleDAO.createCounterRule(counterRule);
	}

	@Override
	public void updateCounterRule(CounterRule counterRule) {
		// to do
	}

	@Override
	public void deleteCounterRule(long id) {
		counterRuleDAO.deleteCounterRule(id);
	}

	@Override
	public TreeMap<Long, ArrayList<CounterRule>> getGroupedCounterRules(int groupId) {
		TreeMap<Long, ArrayList<CounterRule>> groupedCounterRules = new TreeMap<>();
		List<CounterRule> counterRules = counterRuleDAO.getCounterRulesByGroupId(groupId);

		for (CounterRule counterRule : counterRules) {
			long ruleId = counterRule.getRuleId();
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
	public List<CounterRule> getCounterRulesForRuleId(int ruleId) {
		return counterRuleDAO.getCounterRulesForRuleId(ruleId);
	}

}
