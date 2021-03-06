package mgr.mkaminski.api.sending.config;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType
@XmlType(name = "CfgCounterRule")
public class CfgCounterRule {
	private int counterId;
	private double criticalValue;
	private boolean isAbove;
	private int minChecks;
	
	public CfgCounterRule(int counterId, double criticalValue, boolean isAbove, int minChecks) {
		super();
		this.counterId = counterId;
		this.criticalValue = criticalValue;
		this.isAbove = isAbove;
		this.minChecks = minChecks;
	}

	@XmlElement
	public int getCounterId() {
		return counterId;
	}
	
	@XmlElement
	public double getCriticalValue() {
		return criticalValue;
	}

	@XmlElement
	public boolean isAbove() {
		return isAbove;
	}

	@XmlElement
	public int getMinChecks() {
		return minChecks;
	}
}
