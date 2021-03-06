package mgr.mkaminski.service;

import java.util.List;
import java.util.UUID;

import mgr.mkaminski.model.Workstation;

public interface WorkstationService {
	public void createWorkstation(Workstation workstation);
	public void updateWorkstation(Workstation workstation);
	public void deleteWorkstation(Workstation workstation);
	public List<Workstation> getWorkstatinons();
	public Workstation getWorkstationByToken(UUID token);
	public Workstation getWorkstationById(int id);
}
