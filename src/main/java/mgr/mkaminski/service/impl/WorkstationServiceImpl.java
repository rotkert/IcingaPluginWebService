package mgr.mkaminski.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mgr.mkaminski.dao.WorkstationDAO;
import mgr.mkaminski.dao.WorkstationsGroupDAO;
import mgr.mkaminski.model.Workstation;
import mgr.mkaminski.model.WorkstationsGroup;
import mgr.mkaminski.service.WorkstationService;
import mgr.mkaminski.service.WorkstationsGroupService;

@Service
@Transactional
public class WorkstationServiceImpl implements WorkstationService{
	
	@Autowired
	private WorkstationsGroupDAO wsGroupDAO;
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
	public void deleteWorkstation(Workstation workstation) {
		WorkstationsGroup wsGroup = workstation.getWorkstationsGroup();
		wsGroup.removeWorkstation(workstation);
		workstation.setWorkstationsGroup(null);
		wsGroupDAO.updateWorkstationsGroup(wsGroup);
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
	public Workstation getWorkstationById(int id) {
		return workstationDAO.getWorkstationById(id);
	}
	
}
