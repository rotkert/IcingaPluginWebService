package mgr.mkaminski.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="workstation")
public class Workstation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	@Type(type="uuid-char")
	private UUID token;
	@Column
	private String name;
	@Column
	private String name2;
	@Column
	private String description;
	@Column
	private String description2;
	@Column
	private int groupId;
	@Column
	private boolean requestedCounters;
	
	public Workstation() {
		token = UUID.randomUUID();
		groupId = 0;
		requestedCounters = false;
	}
	
	public UUID getToken() {
		return token;
	}
	public void setToken(UUID token) {
		this.token = token;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription2() {
		return description2;
	}
	public void setDescription2(String description2) {
		this.description2 = description2;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public boolean getRequestedCounters() {
		return requestedCounters;
	}
	public void setRequestedCounters(boolean requestedCounters) {
		this.requestedCounters = requestedCounters;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
