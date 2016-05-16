package mgr.mkaminski.dao;

import java.util.List;
import java.util.UUID;

import mgr.mkaminski.model.Workstation;

public interface WorkstationDAO {
	public void createWorkstation(Workstation workstation);
	public void updateWorkstation(Workstation workstation);
	public List<Workstation> getWorkstatinons();
	public Workstation getWorkstationByToken(UUID token);
	public List<Workstation> getWorkstationByGroupId(int groupId);
 }
