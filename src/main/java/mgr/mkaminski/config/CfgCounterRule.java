package mgr.mkaminski.config;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType
@XmlType(name = "CfgCounterRule")
public class CfgCounterRule {
	private int counterId;
	private double criticalValue;
	private boolean isAbove;

	@XmlElement
	public int getCounterId() {
		return counterId;
	}

	public void setCounterId(int counterId) {
		this.counterId = counterId;
	}

	@XmlElement
	public double getCriticalValue() {
		return criticalValue;
	}

	public void setCriticalValue(double criticalValue) {
		this.criticalValue = criticalValue;
	}

	@XmlElement
	public boolean isAbove() {
		return isAbove;
	}

	public void setAbove(boolean isAbove) {
		this.isAbove = isAbove;
	}
}
