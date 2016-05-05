package mgr.mkaminski.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "counter_rule")
public class CounterRule implements Serializable{

	private static final long serialVersionUID = -2281522643963232500L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private int ruleId;
	
	@Column
	private String category;
	
	@Column
	private String name;
	
	@Column
	private String instance;
	
	@Column
	private double threshold;
	
	@Column
	private int minPeriod;
	
	@Column
	private boolean isAbove;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public int getMinPeriod() {
		return minPeriod;
	}

	public void setMinPeriod(int minPeriod) {
		this.minPeriod = minPeriod;
	}

	public boolean isAbove() {
		return isAbove;
	}

	public void setAbove(boolean isAbove) {
		this.isAbove = isAbove;
	}
}
