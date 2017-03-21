package mgr.mkaminski.api.sending.config;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType
@XmlType(name = "CfgCounterRules")
public class CfgCounterRulesWrapper {
	private List<CfgCounterRule> cfgCounterRules;
	
	@XmlElement
	public List<CfgCounterRule> getCfgCounterRules() {
		return cfgCounterRules;
	}

	public void setCfgCounterRules(List<CfgCounterRule> cfgCounterRules) {
		this.cfgCounterRules = cfgCounterRules;
	}
}
