package mgr.mkaminski.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mgr.mkaminski.model.CounterRule;
import mgr.mkaminski.service.CounterRuleService;
import mgr.mkaminski.service.RuleIdSeqService;
 
@Controller
public class CounterRuleController {
	
	@Autowired
	private CounterRuleService counterRuleService;
	
	@Autowired
	private RuleIdSeqService ruleIdSeqService;
	
	@RequestMapping("counterRules")
	public ModelAndView getCounterRules() {
		TreeMap<Long, ArrayList<CounterRule>> counterRules = counterRuleService.getGroupedCounterRules();
		return new ModelAndView("counterRules", "counterRules", counterRules);
	}
	
	@RequestMapping("counterRuleDetails")
	public ModelAndView openCounterRuleDetails(@RequestParam int ruleId, @ModelAttribute CounterRule counterRule) {
		List<CounterRule> counterRules = counterRuleService.getCounterRulesForRuleId(ruleId);
		return new ModelAndView("counterRuleDetails", "counterRules", counterRules);
	}
	
	@RequestMapping(value="saveCounterRule")
	public ModelAndView saveCounterRule(@ModelAttribute CounterRule counterRule) {
		long ruleId = counterRule.getRuleId();
		if(ruleId == -1) {
			ruleId = ruleIdSeqService.nextValue();
			counterRule.setRuleId(ruleId);
		}
		counterRuleService.createCounterRule(counterRule);
		
		return new ModelAndView("redirect:counterRuleDetails?ruleId=" + ruleId);
	}
	
	@RequestMapping(value="deleteCounterRule")
	public ModelAndView deleteCounterRule(@RequestParam long id, @RequestParam long ruleId) {
		counterRuleService.deleteCounterRule(id);
		return new ModelAndView("redirect:counterRuleDetails?ruleId=" + ruleId);
	}
}

