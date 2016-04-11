package mgr.mkaminski.config;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType
@XmlType(name = "CfgRule")
public class CfgRule {
	private CfgCounterRulesWrapper cfgCounterRuleWrapper = new CfgCounterRulesWrapper();
	
	@XmlElement
	public CfgCounterRulesWrapper getCfgCounterRulesWrapper() {
		return cfgCounterRuleWrapper;
	}

	public void setCfgCounterRulesWrapper(CfgCounterRulesWrapper cfgCounterRuleWrapper) {
		this.cfgCounterRuleWrapper = cfgCounterRuleWrapper;
	}
}
