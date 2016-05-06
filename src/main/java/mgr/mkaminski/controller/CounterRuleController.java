package mgr.mkaminski.controller;

import java.util.ArrayList;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mgr.mkaminski.model.CounterRule;
import mgr.mkaminski.service.CounterRuleService;
 
@Controller
public class CounterRuleController {
	
	@Autowired
	private CounterRuleService counterRuleService;
	
	@RequestMapping("/counterRules")
	public ModelAndView getCounterRules() {
		TreeMap<Integer, ArrayList<CounterRule>> counterRules = counterRuleService.getGroupedCounterRules();
		return new ModelAndView("counterRules", "counterRules", counterRules);
	}
}

