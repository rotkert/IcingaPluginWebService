package mgr.mkaminski.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "workstations_group")
public class WorkstationsGroup {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private Date lastModification;
	
	@Column
	private boolean containCounters;
	
	@OneToMany(mappedBy = "workstationsGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Workstation> workstations;

	public WorkstationsGroup() {
		
	}
	
	public WorkstationsGroup(String name, String desc) {
		this.name = name;
		this.description = desc;
		lastModification = new Date();
		containCounters = false;
		workstations = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getLastModification() {
		return lastModification;
	}

	public void setLastModification(Date lastModification) {
		this.lastModification = lastModification;
	}
	
	public boolean isContainCounters() {
		return containCounters;
	}

	public void setContainCounters() {
		this.containCounters = true;
	}

	public List<Workstation> getWorkstations() {
		return workstations;
	}

	public void setWorkstations(ArrayList<Workstation> workstations) {
		this.workstations = workstations;
	}
	
	public void addWorkstation(Workstation workstation) {
		workstations.add(workstation);
	}
	
	public void removeWorkstation(Workstation workstation) {
		workstations.remove(workstation);
		if(workstation != null) {
			workstation.setWorkstationsGroup(null);
		}
	}
}
