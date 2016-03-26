package mgr.mkaminski;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType
@XmlType(name = "Check")
public class Check {
	@XmlElement(name = "hostName")
	private String hostName;
	@XmlElement(name = "serviceName")
	private String serviceName;
	@XmlElement(name = "result")
	private double result;
	@XmlElement(name = "timestamp")
	private long timestamp;
	@XmlElement(name = "critical")
	private double critical;
	@XmlElement(name = "hasExceeded")
	private boolean hasExceeded;
	
	String getHostName() {
		return hostName;
	}
	void setHostName(String hostName) {
		this.hostName = hostName;
	}
	String getServiceName() {
		return serviceName;
	}
	void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	double getResult() {
		return result;
	}
	void setResult(double result) {
		this.result = result;
	}
	long getTimestamp() {
		return timestamp;
	}
	void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	double getCritical() {
		return critical;
	}
	void setCritical(double critical) {
		this.critical = critical;
	}
	boolean isHasExceeded() {
		return hasExceeded;
	}
	void setHasExceeded(boolean hasExceeded) {
		this.hasExceeded = hasExceeded;
	}
}
