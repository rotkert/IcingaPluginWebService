package mgr.mkaminski;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataHandler {
	private final static String SERVICE_CMD = "PROCESS_SERVICE_CHECK_RESULT";
	private final static String DELIM = ";";
	
	String reportServiceCheck(List<Check> checks) throws IOException {
		checks.stream().sorted((c1, c2) -> Long.compare(c1.getTimestamp(), c2.getTimestamp()));
		
		ArrayList<String> checkStrings = new ArrayList<>();
		for (Check check : checks) {
			String checkString = "[" + check.getTimestamp() + "] ";
			checkString += SERVICE_CMD + DELIM;
			checkString += check.getHostName() + DELIM;
			checkString += check.getServiceName() + DELIM;
			checkString += "0" + DELIM;
			checkString += check.getResult() + "|";
			checkString += check.getServiceName() + "=" +check.getResult();
			
			checkStrings.add(checkString);
		}
		
		
		String joinedChecks = checkStrings.stream().collect(Collectors.joining("\n"));
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream("/var/run/icinga2/cmd/icinga2.cmd")));
			pw.println(joinedChecks);
		} finally {
			if (pw != null)
				pw.close();
		}
		
		return joinedChecks;
	}
	
}
