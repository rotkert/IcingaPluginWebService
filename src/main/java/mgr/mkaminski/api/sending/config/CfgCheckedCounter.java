package mgr.mkaminski.api.sending.config;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType
@XmlType(name = "CfgCounterDetails")
public class CfgCheckedCounter {
	private final int id;
	private final String category;
	private final String instance;
	private final String name;
	
	public CfgCheckedCounter(int id, String category, String instance, String counter) {
		super();
		this.id = id;
		this.category = category;
		this.instance = instance;
		this.name = counter;
	}

	@XmlElement
	public int getId() {
		return id;
	}
	
	@XmlElement
	public String getCategory() {
		return category;
	}
	
	@XmlElement
	public String getInstance() {
		return instance;
	}
	
	@XmlElement
	public String getCounter() {
		return name;
	}	
}
