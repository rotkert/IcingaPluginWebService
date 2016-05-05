package mgr.mkaminski.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mgr.mkaminski.model.CounterRule;
import mgr.mkaminski.service.CounterRuleService;
 
@Controller
public class Management {
	
	@Autowired
	private CounterRuleService counterRuleService;
	
	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
		List<CounterRule> counterRules = counterRuleService.getAllCounterRules();
		String message = counterRules.get(0).getName();
		return new ModelAndView("welcome", "message", message);
	}
}

