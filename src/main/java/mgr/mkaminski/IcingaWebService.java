package mgr.mkaminski;

import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
 
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
    public String uploadReport(@WebParam(name = "reportName") String reportName,@WebParam(name = "reportContent") byte[] reportContent) {	
    	if (reportName == null || reportContent == null) {
    		return "FAILED";
    	}
    	
    	try {
			return dataHandler.saveReport(reportName, reportContent);
		} catch (IOException e) {
			return "FAILED " + e.getMessage();
		}
    }
    
    
}