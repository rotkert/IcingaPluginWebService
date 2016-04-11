package mgr.mkaminski;

import java.io.IOException;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import mgr.mkaminski.config.CfgRule;
import mgr.mkaminski.config.CfgRulesWrapper;
import mgr.mkaminski.config.CfgCounterDetails;
import mgr.mkaminski.config.CfgCounterRule;
import mgr.mkaminski.config.CfgCounterRulesWrapper;
 
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class IcingaWebService {
	
	private DataHandler dataHandler = new DataHandler();
 
    @WebMethod(operationName = "processChecks")
    public String processChecks(@WebParam(name="checks") ChecksWrapper checks){
 
        if(checks == null){
            return "FAILED";
        }
        
        try {
			return dataHandler.processServiceCheck(checks.getChecks());
		} catch (IOException e) {
			return "FAILED " + e.getMessage(); 
		}
    }
    
    @WebMethod(operationName = "uploadReport")
    public String uploadReport(@WebParam(name="hostName") String hostname, @WebParam(name="timestamp") long timestamp,@WebParam(name = "reportName") String reportName,@WebParam(name = "reportContent") byte[] reportContent) {	
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
    public CfgRulesWrapper getConfig() {
    	CfgRulesWrapper configRulesWrapper = new CfgRulesWrapper();
    	
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
    	CfgCounterRule counterRule= new CfgCounterRule();
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
}