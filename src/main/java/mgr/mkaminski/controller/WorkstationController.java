package mgr.mkaminski.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import mgr.mkaminski.model.WorkstationsGroup;
import mgr.mkaminski.model.Workstation;
import mgr.mkaminski.service.WorkstationService;

@Controller
public class WorkstationController {
	
	@Autowired
	private WorkstationService workstationService;
	
	@RequestMapping("workstations")
	public ModelAndView getWorkstations() {
		List<WorkstationsGroup> groups = new ArrayList<>();
		WorkstationsGroup group1 = new WorkstationsGroup();
		group1.setDescription("trolo");
		group1.setName("Moj");
		groups.add(group1);
		
		WorkstationsGroup group2 = new WorkstationsGroup();
		group2.setDescription("Group for new workstations");
		group2.setName("Pending");
		groups.add(group2);
		
		return new ModelAndView("workstations", "groups", groups);
	}
	
	@RequestMapping(value="getWorkstations", produces = "application/json")
	@ResponseBody
	public String getWorkstations(@RequestParam int groupId) {
		List<Workstation> workstations = workstationService.getWorkStationsByGroupId(groupId);
		return new Gson().toJson(workstations);
	}
}
