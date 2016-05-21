package mgr.mkaminski.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import mgr.mkaminski.model.Counter;
import mgr.mkaminski.model.CounterInstance;
import mgr.mkaminski.model.CounterCategory;
import mgr.mkaminski.model.CounterRule;
import mgr.mkaminski.service.CounterInstanceService;
import mgr.mkaminski.service.CounterCategoryService;
import mgr.mkaminski.service.CounterRuleService;
import mgr.mkaminski.service.CounterService;
import mgr.mkaminski.service.RuleIdSeqService;
 
@Controller
public class CounterRuleController {
	
	@Autowired
	private CounterRuleService counterRuleService;
	
	@Autowired
	private RuleIdSeqService ruleIdSeqService;
	
	@Autowired
	private CounterCategoryService counterObjectService;
	
	@Autowired
	private CounterService counterService;
	
	@Autowired
	private CounterInstanceService counterInstanceService;
	
	@RequestMapping("counterRules")
	public ModelAndView getCounterRules() {
		TreeMap<Long, ArrayList<CounterRule>> counterRules = counterRuleService.getGroupedCounterRules();
		return new ModelAndView("counterRules", "counterRules", counterRules);
	}
	
	@RequestMapping("counterRuleDetails")
	public ModelAndView openCounterRuleDetails(@RequestParam int ruleId, @ModelAttribute CounterRule counterRule) {
		List<CounterRule> counterRules = counterRuleService.getCounterRulesForRuleId(ruleId);
		List<CounterCategory> counterObjects = counterObjectService.getCounterCategories();
		
		Map<String, List<?>> modelMap = new HashMap<>();
		modelMap.put("counterRules", counterRules);
		modelMap.put("counterObjects", counterObjects);
		return new ModelAndView("counterRuleDetails", "modelMap", modelMap);
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
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="getCountersForCategory", produces = "application/json")
	@ResponseBody
	public String getCountersForCategory(@RequestParam int categoryId) {
		HashMap<String, List> responseMap = new HashMap<>();
		
		List<Counter> counters = counterService.getCountersForCategory(categoryId);
		Collections.sort(counters, (o1, o2) -> o1.getName().compareTo(o2.getName()));
		responseMap.put("counters", counters);
		
		List<CounterInstance> instances = counterInstanceService.getCounterInstancesForCategory(categoryId);
		Collections.sort(instances, (o1, o2) -> o1.getName().compareTo(o2.getName()));
		responseMap.put("instances", instances);
		
		return new Gson().toJson(responseMap);
	}
}

