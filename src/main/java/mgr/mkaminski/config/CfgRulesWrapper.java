package mgr.mkaminski.config;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CfgRulesWrapper {
	private ArrayList<CfgCounterDetails> cfgCounters;
	private List<CfgRule> cfgRule;
	
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
}
