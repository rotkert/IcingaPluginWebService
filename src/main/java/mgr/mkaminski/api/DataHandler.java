package mgr.mkaminski.api;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataHandler {
	private final static String SERVICE_CMD = "PROCESS_SERVICE_CHECK_RESULT";
	private final static String DELIM = ";";

	String processServiceCheck(List<Check> checks) throws IOException {
		checks.stream().sorted((c1, c2) -> Long.compare(c1.getTimestamp(), c2.getTimestamp()));

		ArrayList<String> checkStrings = new ArrayList<>();
		for (Check check : checks) {
			String checkString = "[" + check.getTimestamp() + "] ";
			checkString += SERVICE_CMD + DELIM;
			checkString += check.getHostName() + DELIM;
			checkString += check.getServiceName() + DELIM;
			checkString += check.getState() + DELIM;
			checkString += check.getServiceName() + " = " + check.getResult() + "|";
			checkString += check.getServiceName() + "=" + check.getResult() + " ";
			checkString += "hasExceeded=" + (check.isHasExceeded() ? "1" : "0");

			checkStrings.add(checkString);
		}

		String joinedChecks = checkStrings.stream().collect(Collectors.joining("\n"));
		wtriteToCmdPipe(joinedChecks);
		return joinedChecks;
	}

	String saveReport(String hostname, long timestamp, String reportName, byte[] reportContent) throws IOException {
		String reportPath = "/var/www/html/reports/" + reportName;

		FileOutputStream fos = null;
		BufferedOutputStream outputStream = null;

		try {
			fos = new FileOutputStream(reportPath);
			outputStream = new BufferedOutputStream(fos);
			outputStream.write(reportContent);
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
		
		String commandString = "[" + timestamp + "] ";
		commandString += SERVICE_CMD + DELIM;
		commandString += hostname + DELIM;
		commandString += "diagnostics" + DELIM;
		commandString += "3" + DELIM;
		commandString += "Report" + " = " + reportName + "|";
		commandString += "Appeared" + "=" + "1";
		
		wtriteToCmdPipe(commandString);

		return "SUCCESS";
	}

	private void wtriteToCmdPipe(String command) throws FileNotFoundException
	{
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream("/var/run/icinga2/cmd/icinga2.cmd")));
			pw.println(command);
		} finally {
			if (pw != null)
				pw.close();
		}
	}
}
