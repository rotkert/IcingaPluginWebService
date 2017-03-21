package mgr.mkaminski.api.sending;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import mgr.mkaminski.api.sending.config.CfgCheckedCounter;
import mgr.mkaminski.api.sending.config.CfgRule;

@XmlRootElement
public class GetConfigResponse {
	private ArrayList<CfgCheckedCounter> cfgCounters;
	private List<CfgRule> cfgRules;
	private boolean requestedCounters;
	
	public GetConfigResponse() {
		requestedCounters = false;
	}
	
	@XmlElement
	public ArrayList<CfgCheckedCounter> getCfgCounters() {
		return cfgCounters;
	}

	public void setCfgCounters(ArrayList<CfgCheckedCounter> cfgCounters) {
		this.cfgCounters = cfgCounters;
	}
	
	@XmlElement
	public List<CfgRule> getCfgRules() {
		return cfgRules;
	}
	
	public void setCfgRules(List<CfgRule> cfgRule) {
		this.cfgRules = cfgRule;
	}
	
	@XmlElement
	public boolean isRequestedCounters() {
		return requestedCounters;
	}

	public void setRequestedCounters(boolean requestedCounters) {
		this.requestedCounters = requestedCounters;
	}
}
