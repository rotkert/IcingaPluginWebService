package mgr.mkaminski;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
 
@SuppressWarnings("restriction")
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class IcingaWebService {
 
 
    @WebMethod(operationName = "processChecks")
    public String processChecks(@WebParam(name="checks") ChecksWrapper checks){
 
        if(checks == null){
            return "Failed";
        }
        return "SUCCESS";
    }
}