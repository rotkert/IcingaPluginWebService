package mgr.mkaminski.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mgr.mkaminski.dao.WorkstationDAO;
import mgr.mkaminski.model.Workstation;
import mgr.mkaminski.service.WorkstationService;

@Service
@Transactional
public class WorkstationServiceImpl implements WorkstationService{
	
	@Autowired
	private WorkstationDAO workstationDAO;

	@Override
	public void createWorkstation(Workstation workstation) {
		workstationDAO.createWorkstation(workstation);
	}

	@Override
	public void updateWorkstation(Workstation workstation) {
		workstationDAO.updateWorkstation(workstation);
	}

	@Override
	public List<Workstation> getWorkstatinons() {
		return workstationDAO.getWorkstatinons();
	}

	@Override
	public Workstation getWorkstationByToken(UUID token) {
		return workstationDAO.getWorkstationByToken(token);
	}

	@Override
	public List<Workstation> getWorkStationsByGroupId(int groupId) {
		return workstationDAO.getWorkstationByGroupId(groupId);
	}
	
}
