package mgr.mkaminski.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mgr.mkaminski.model.Workstation;
import mgr.mkaminski.model.WorkstationsGroup;
import mgr.mkaminski.service.WorkstationService;
import mgr.mkaminski.service.WorkstationsGroupService;

@Controller
public class WorkstationController {
	
	@Autowired
	private WorkstationService wsService;
	
	@Autowired
	private WorkstationsGroupService wsGroupService;
	
	@RequestMapping("workstations")
	public ModelAndView getWorkstations(@RequestParam(required=false) Integer groupId) {
		if(groupId == null) {
			groupId = 0;
		}
		
		List<WorkstationsGroup> groups = wsGroupService.getWorkstationsGroups();
		HashMap<String, Object> modelMap = new HashMap<>();
		modelMap.put("groups", groups);
		modelMap.put("selectedGroup", groupId);
		
		return new ModelAndView("workstations", "modelMap", modelMap);
	}
	
	@RequestMapping(value="getWorkstationsForGroup")
	@ResponseBody
	public String getWorkstationsForGroup(@RequestParam int groupId) {
		List<Workstation> workstations = new ArrayList<>();
		workstations.addAll(wsGroupService.getWorkstations(groupId));
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(workstations);
	}
	
	@RequestMapping(value="containsCounters")
	@ResponseBody
	public boolean isGropuWaitingForCounters(@RequestParam int groupId) {
		WorkstationsGroup workstationsGroup = wsGroupService.getWorkstationsGroupById(groupId);
		return workstationsGroup.isContainCounters();
	}
	
	@RequestMapping(value="moveWorkstation")
	public ModelAndView moveWorkstation(@RequestParam int workstationId, @RequestParam int groupId) {
		wsGroupService.changeWorkstationGroup(workstationId, groupId);
		return new ModelAndView("redirect:workstations?groupId=" + groupId);
	}
	
	@RequestMapping(value="createWorkstationsGroup")
	public ModelAndView createWorkstationsGroup(@RequestParam int workstationId, @RequestParam String name, @RequestParam(required=false) String desc) {
		if(desc == null) {
			desc = "";
		}
		int workstationGroupId = wsGroupService.createWorkstationsGroup(workstationId, name, desc);
		return new ModelAndView("redirect:workstations?groupId=" + workstationGroupId);
	}
	
	@RequestMapping(value="discardWorkstation")
	@ResponseBody
	public void discardWorkstation(@RequestParam int workstationId) {
		Workstation workstation = wsService.getWorkstationById(workstationId);
		wsService.deleteWorkstation(workstation);
	}
	
	@RequestMapping(value="removeWorkstationsGroup")
	@ResponseBody
	public  boolean removeWorkstationsGroup(@RequestParam int groupId) {
		WorkstationsGroup group = wsGroupService.getWorkstationsGroupById(groupId);
		return false;
	}
}
