package mgr.mkaminski.config;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType
@XmlType(name = "CfgCounterDetails")
public class CfgCounterDetails {
	private String category;
	private String instance;
	private String counter;
	private String serviceName;
	private int maxChecks;
	private int minChecks;
	
	@XmlElement
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@XmlElement
	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	@XmlElement
	public String getCounter() {
		return counter;
	}

	public void setCounter(String counter) {
		this.counter = counter;
	}

	@XmlElement
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@XmlElement
	public int getMaxChecks() {
		return maxChecks;
	}

	public void setMaxChecks(int maxChecks) {
		this.maxChecks = maxChecks;
	}

	@XmlElement
	public int getMinChecks() {
		return minChecks;
	}

	public void setMinChecks(int minChecks) {
		this.minChecks = minChecks;
	}	
}
