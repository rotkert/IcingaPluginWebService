package mgr.mkaminski.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="workstation")
public class Workstation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Expose
	private int workstationId;
	@Column
	@Type(type="uuid-char")
	private UUID token;
	@Column
	@Expose
	private String name;
	@Column
	@Expose
	private String name2;
	@Column
	@Expose
	private String description;
	@Column
	@Expose
	private String description2;
	@Column
	@Expose
	private int groupId;
	@Column
	@Expose
	private boolean requestedCounters;
	
	@ManyToOne
	@JoinColumn(name="id", nullable=false)
	private WorkstationsGroup workstationsGroup;
	
	public Workstation() {
		
	}
	
	public Workstation(String name, String description) {
		token = UUID.randomUUID();
		groupId = 0;
		requestedCounters = false;
		this.name = name;
		this.description = description;
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
		return workstationId;
	}

	public void setId(int id) {
		this.workstationId = id;
	}

	public WorkstationsGroup getWorkstationsGroup() {
		return workstationsGroup;
	}

	public void setWorkstationsGroup(WorkstationsGroup workstationsGroup) {
		this.workstationsGroup = workstationsGroup;
	}
	
	
}
