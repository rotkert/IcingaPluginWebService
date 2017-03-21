package mgr.mkaminski.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import mgr.mkaminski.api.receiving.CategoriesListFrom;
import mgr.mkaminski.api.receiving.CategoryFrom;
import mgr.mkaminski.api.sending.GetConfigResponse;
import mgr.mkaminski.api.sending.RegisterWorkstationResponse;
import mgr.mkaminski.api.sending.config.CfgCheckedCounter;
import mgr.mkaminski.api.sending.config.CfgCounterRule;
import mgr.mkaminski.api.sending.config.CfgCounterRulesWrapper;
import mgr.mkaminski.api.sending.config.CfgRule;
import mgr.mkaminski.model.Counter;
import mgr.mkaminski.model.CounterCategory;
import mgr.mkaminski.model.CounterInstance;
import mgr.mkaminski.model.CounterRule;
import mgr.mkaminski.model.Workstation;
import mgr.mkaminski.model.WorkstationsGroup;
import mgr.mkaminski.service.CounterCategoryService;
import mgr.mkaminski.service.CounterInstanceService;
import mgr.mkaminski.service.CounterRuleService;
import mgr.mkaminski.service.CounterService;
import mgr.mkaminski.service.WorkstationService;
import mgr.mkaminski.service.WorkstationsGroupService;

@Service("IcingaWebService")
@WebService(serviceName="IcingaWebService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class IcingaWebService extends SpringBeanAutowiringSupport{

	private DataHandler dataHandler = new DataHandler();
	
	@Autowired
	private WorkstationService wsService;
	
	@Autowired
	private WorkstationsGroupService wsGroupService;
	
	@Autowired
	private CounterCategoryService counterCategoryService;
	
	@Autowired
	private CounterInstanceService counterInstanceService;
	
	@Autowired
	private CounterService counterService;
	
	@Autowired
	private CounterRuleService counterRuleService;

	@WebMethod(operationName = "processChecks")
	public String processChecks(@WebParam(name = "checks") ChecksWrapper checks) {

		if (checks == null) {
			return "FAILED";
		}

		try {
			return dataHandler.processServiceCheck(checks.getChecks());
		} catch (IOException e) {
			return "FAILED " + e.getMessage();
		}
	}

	@WebMethod(operationName = "uploadReport")
	public String uploadReport(@WebParam(name = "hostName") String hostname, @WebParam(name = "timestamp") long timestamp,
			@WebParam(name = "reportName") String reportName, @WebParam(name = "reportContent") byte[] reportContent) {
		if (reportName == null || reportContent == null) {
			return "FAILED";
		}

		try {
			return dataHandler.saveReport(hostname, timestamp, reportName, reportContent);
		} catch (IOException e) {
			return "FAILED " + e.getMessage();
		}
	}

	@WebMethod(operationName = "getConfig")
	@WebResult(name = "configRules")
	public GetConfigResponse getConfig(@WebParam(name = "token") UUID token) {
		Workstation workstation = wsService.getWorkstationByToken(token);
		if(workstation == null) {
			return null;
		}

		GetConfigResponse getConfigResponse = new GetConfigResponse();
		if(workstation.getRequestedCounters()) {
			getConfigResponse.setRequestedCounters(true);
			return getConfigResponse;
		}
		
		HashMap<String, CfgCheckedCounter> checkedCounters = new HashMap<>();
		ArrayList<CfgRule> cfgRules = new ArrayList<>();
		TreeMap<Long, ArrayList<CounterRule>> counterRules = counterRuleService.getGroupedCounterRules(workstation.getWorkstationsGroup().getId());
		for (Long ruleId : counterRules.keySet()) {
			ArrayList<CfgCounterRule> cfgCounterRules = new ArrayList<>();
			for (CounterRule counterRule : counterRules.get(ruleId)) {
				String category = counterRule.getCategory();
				String instance = counterRule.getInstance();
				String name = counterRule.getName();
				String counterPath = category + instance + name;
				
				int id;
				if(!checkedCounters.containsKey(counterPath)) {
					id = checkedCounters.values().size() + 1;
					CfgCheckedCounter cfgCheckedCounter = new CfgCheckedCounter(id, category, instance, name);
					checkedCounters.put(counterPath, cfgCheckedCounter);
				} else {
					id = checkedCounters.get(counterPath).getId();
				}
				
				CfgCounterRule cfgCounterRule = new CfgCounterRule(id, counterRule.getThreshold(), counterRule.getIsAbove());
				cfgCounterRules.add(cfgCounterRule);
			}
			CfgCounterRulesWrapper counterRulesWrapper = new CfgCounterRulesWrapper();
			counterRulesWrapper.setCfgCounterRules(cfgCounterRules);
			CfgRule cfgRule = new CfgRule();
			cfgRule.setCfgCounterRulesWrapper(counterRulesWrapper);
			cfgRules.add(cfgRule);
		}
		
		getConfigResponse.setCfgRules(cfgRules);
		getConfigResponse.setCfgCounters(new ArrayList<>(checkedCounters.values()));
		return getConfigResponse;
	}
	
	@WebMethod(operationName="uploadCounters")
	public String uploadCounters(@WebParam(name="token") UUID token, @WebParam(name="categoriesList") CategoriesListFrom categoriesList) {
		Workstation workstation = wsService.getWorkstationByToken(token);

		if(workstation == null) {
			return "FAILED";
		}
		
		int groupId = workstation.getWorkstationsGroup().getId();
		
		if(groupId < 1) {
			return "FAILED";
		}
		
		List<CategoryFrom> categories = categoriesList.getCategories();
		for (CategoryFrom categoryFrom : categories) {
			String categoryName = categoryFrom.getName();
			List<String> instances = categoryFrom.getInstances();
			List<String> counters = categoryFrom.getCounters();
			
			int categoryId = createCategory(groupId, categoryName);
			createInstances(categoryId, instances);
			createCounters(categoryId, counters);
		}
		
		WorkstationsGroup group = wsGroupService.getWorkstationsGroupById(groupId);
		group.setContainCounters();
		workstation.setRequestedCounters(false);
		wsGroupService.updateWorkstationsGroup(group);
		wsService.updateWorkstation(workstation);
		
		return "SUCCESS";
	}
	
	@WebMethod(operationName="registerWorkstation")
	@WebResult(name="registerWorkstationResponse")
	public RegisterWorkstationResponse registerWorkstation(@WebParam(name = "token") UUID token, @WebParam(name = "name") String name, @WebParam(name = "desc") String desc) {
		RegisterWorkstationResponse registerWorkstationResoponse = new RegisterWorkstationResponse();
		Workstation workstation = null;

		if (token == null) {
			workstation = new Workstation(name, desc);
			wsGroupService.addWorkstation(workstation, 0);
			registerWorkstationResoponse.setToken(workstation.getToken());
		} else {
			workstation = wsService.getWorkstationByToken(token);
		}
		
		registerWorkstationResoponse.setCountersRequested(workstation.getRequestedCounters());
		return registerWorkstationResoponse;
	}
	
	private int createCategory(int groupId, String name) {
		CounterCategory counterCategory = new CounterCategory();
		counterCategory.setName(name);
		counterCategory.setGroupId(groupId);
		counterCategoryService.createCounterCategory(counterCategory);
		return counterCategory.getId();
	}
	
	private void createInstances(int categoryId, List<String> instances) {
		if(instances == null) {
			return;
		}
		
		for (String name : instances) {
			CounterInstance counterInstance = new CounterInstance();
			counterInstance.setCounterCategoryId(categoryId);
			counterInstance.setName(name);
			counterInstanceService.createCounterInstance(counterInstance);
		}
	}
	
	private void createCounters(int categoryId, List<String> counters) {
		for (String name : counters) {
			Counter counter = new Counter();
			counter.setCounterCategoryId(categoryId);
			counter.setName(name);
			counterService.createCounter(counter);
		}
	}
}