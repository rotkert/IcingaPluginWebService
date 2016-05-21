package mgr.mkaminski.dao;

import java.util.List;

import mgr.mkaminski.model.WorkstationsGroup;

public interface WorkstationsGroupDAO {
	public void createWorkstationsGroup(WorkstationsGroup workstationsGroup);
	public WorkstationsGroup getWorkstationsGroupById(int id);
	public void updateWorkstationsGroup(WorkstationsGroup workstationsGroup);
	public List<WorkstationsGroup> getWorkstationsGroups();
}
