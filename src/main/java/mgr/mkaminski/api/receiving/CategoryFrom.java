package mgr.mkaminski.api.receiving;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType
@XmlType(name="CategoryFrom")
public class CategoryFrom {
	
	private String name;
	private List<String> instances;
	private List<String> counters;
	
	@XmlElement
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement
	public List<String> getInstances() {
		return instances;
	}
	
	public void setInstances(List<String> instances) {
		this.instances = instances;
	}
	
	public List<String> getCounters() {
		return counters;
	}
	
	public void setCounters(List<String> counters) {
		this.counters = counters;
	}
}
