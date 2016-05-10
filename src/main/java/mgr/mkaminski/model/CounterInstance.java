package mgr.mkaminski.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CounterInstance {
	
	@Id
	private int id;
	
	private int counterObjectId;
	
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCounterObjectId() {
		return counterObjectId;
	}

	public void setCounterObjectId(int counterObjectId) {
		this.counterObjectId = counterObjectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
