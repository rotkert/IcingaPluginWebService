package mgr.mkaminski.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import mgr.mkaminski.api.config.CfgCounterDetails;
import mgr.mkaminski.api.config.CfgCounterRule;
import mgr.mkaminski.api.config.CfgCounterRulesWrapper;
import mgr.mkaminski.api.config.CfgRule;
import mgr.mkaminski.api.config.CfgRulesWrapper;
import mgr.mkaminski.api.receiving.CategoriesListFrom;
import mgr.mkaminski.api.receiving.CategoryFrom;
import mgr.mkaminski.model.CounterCategory;
import mgr.mkaminski.model.Workstation;
import mgr.mkaminski.service.CounterCategoryService;
import mgr.mkaminski.service.WorkstationService;

@Service("IcingaWebService")
@WebService(serviceName="IcingaWebService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class IcingaWebService extends SpringBeanAutowiringSupport{

	private DataHandler dataHandler = new DataHandler();
	
	@Autowired
	private WorkstationService workstationService;
	
	@Autowired
	private CounterCategoryService counterCategoryService;

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
	public CfgRulesWrapper getConfig(@WebParam(name = "token") UUID token, @WebParam(name = "name") String name, @WebParam(name = "desc") String desc) {
		Workstation workstation = null;

		if (token == null) {
			workstation = new Workstation();
			workstation.setName(name);
			workstation.setDescription(desc);
			workstationService.createWorkstation(workstation);
		} else {
			workstation = workstationService.getWorkstationByToken(token);
		}

		CfgRulesWrapper configRulesWrapper = new CfgRulesWrapper();

		configRulesWrapper.setRequestedCounters(workstation.getRequestedCounters());
		configRulesWrapper.setToken(workstation.getToken());

		ArrayList<CfgCounterDetails> counters = new ArrayList<>();
		CfgCounterDetails counterDetails = new CfgCounterDetails();
		counterDetails.setCategory("Procesor");
		counterDetails.setInstance("_Total");
		counterDetails.setCounter("Czas procesora (%)");
		counterDetails.setServiceName("diagnostics");
		counterDetails.setMaxChecks(4);
		counterDetails.setMinChecks(1);
		counters.add(counterDetails);
		configRulesWrapper.setCfgCounters(counters);

		ArrayList<CfgRule> configRules = new ArrayList<>();
		CfgRule configRule = new CfgRule();

		CfgCounterRulesWrapper counterRulesWrapper = new CfgCounterRulesWrapper();
		ArrayList<CfgCounterRule> counterRules = new ArrayList<>();
		CfgCounterRule counterRule = new CfgCounterRule();
		counterRule.setCounterId(0);
		counterRule.setAbove(true);
		counterRule.setCriticalValue(10);
		counterRules.add(counterRule);
		counterRulesWrapper.setCfgCounterRules(counterRules);
		configRule.setCfgCounterRulesWrapper(counterRulesWrapper);
		configRules.add(configRule);
		configRulesWrapper.setCfgRules(configRules);

		return configRulesWrapper;
	}
	
	@WebMethod(operationName="uploadCounters")
	public void uploadCounters(@WebParam(name="categoriesList") CategoriesListFrom categoriesList) {
		List<CategoryFrom> categories = categoriesList.getCategories();
		for (CategoryFrom categoryFrom : categories) {
			CounterCategory counterCategory = new CounterCategory();
			counterCategory.setName(categoryFrom.getName());
			counterCategoryService.createCounterCategory(counterCategory);
		}
	}
}