package mgr.mkaminski.api.config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CfgRulesWrapper {
	private ArrayList<CfgCounterDetails> cfgCounters;
	private List<CfgRule> cfgRule;
	private boolean requestedCounters;
	private UUID token;
	
	@XmlElement
	public ArrayList<CfgCounterDetails> getCfgCounters() {
		return cfgCounters;
	}

	public void setCfgCounters(ArrayList<CfgCounterDetails> cfgCounters) {
		this.cfgCounters = cfgCounters;
	}
	
	@XmlElement
	public List<CfgRule> getCfgRules() {
		return cfgRule;
	}
	
	public void setCfgRules(List<CfgRule> cfgRule) {
		this.cfgRule = cfgRule;
	}
	
	@XmlElement
	public boolean isRequestedCounters() {
		return requestedCounters;
	}

	public void setRequestedCounters(boolean requestedCounters) {
		this.requestedCounters = requestedCounters;
	}
	
	@XmlElement
	public UUID isToken() {
		return token;
	}

	public void setToken(UUID token) {
		this.token = token;
	}
}
