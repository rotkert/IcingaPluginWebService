package mgr.mkaminski.service;

import java.util.ArrayList;
import java.util.List;

import mgr.mkaminski.model.Workstation;
import mgr.mkaminski.model.WorkstationsGroup;


public interface WorkstationsGroupService {
	public int createWorkstationsGroup(int workstationId, String name, String desc);
	public WorkstationsGroup getWorkstationsGroupById(int id);
	public void updateWorkstationsGroup(WorkstationsGroup workstationsGroup);
	public List<WorkstationsGroup> getWorkstationsGroups();
	public ArrayList<Workstation> getWorkstations(int groupId);
	public void addWorkstation(Workstation workstation, int groupId);
	void changeWorkstationGroup(int workstationId, int groupId);
}
