package mgr.mkaminski.service;

import java.util.List;

import mgr.mkaminski.model.WorkstationsGroup;


public interface WorkstationsGroupService {
	public int createWorkstationsGroup(int workstationId, String name, String desc);
	public WorkstationsGroup getWorkstationsGroupById(int id);
	public void updateWorkstationsGroup(WorkstationsGroup workstationsGroup);
	public List<WorkstationsGroup> getWorkstationsGroups();
}
