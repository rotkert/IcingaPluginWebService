package mgr.mkaminski.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mgr.mkaminski.dao.WorkstationsGroupDAO;
import mgr.mkaminski.model.Workstation;
import mgr.mkaminski.model.WorkstationsGroup;
import mgr.mkaminski.service.WorkstationService;
import mgr.mkaminski.service.WorkstationsGroupService;

@Service
@Transactional
public class WorkstationsGroupServiceImpl implements WorkstationsGroupService{
	
	@Autowired
	private WorkstationService workstationService;
	
	@Autowired
	private WorkstationsGroupDAO workstationsGroupDao;

	@Override
	public int createWorkstationsGroup(int workstationId, String name, String desc) {
		WorkstationsGroup workstationsGroup = new WorkstationsGroup();
		workstationsGroup.setName(name);
		workstationsGroup.setDescription(desc);
		workstationsGroupDao.createWorkstationsGroup(workstationsGroup);
		
		Workstation workstation = workstationService.getWorkstationById(workstationId);
		workstation.setGroupId(workstationsGroup.getId());
		workstation.setRequestedCounters(true);
		workstationService.updateWorkstation(workstation);
		return workstationsGroup.getId();
	}

	@Override
	public WorkstationsGroup getWorkstationsGroupById(int id) {
		return workstationsGroupDao.getWorkstationsGroupById(id);
	}

	@Override
	public void updateWorkstationsGroup(WorkstationsGroup workstationsGroup) {
		workstationsGroupDao.createWorkstationsGroup(workstationsGroup);
	}

	@Override
	public List<WorkstationsGroup> getWorkstationsGroups() {
		return workstationsGroupDao.getWorkstationsGroups();
	}
}
