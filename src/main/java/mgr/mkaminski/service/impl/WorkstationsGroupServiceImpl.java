package mgr.mkaminski.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mgr.mkaminski.dao.WorkstationsGroupDAO;
import mgr.mkaminski.model.Workstation;
import mgr.mkaminski.model.WorkstationsGroup;
import mgr.mkaminski.service.WorkstationService;
import mgr.mkaminski.service.WorkstationsGroupService;
import mgr.mkaminski.util.HibernateUtil;

@Service
@Transactional
public class WorkstationsGroupServiceImpl implements WorkstationsGroupService{
	
	@Autowired
	private WorkstationService wsService;
	
	@Autowired
	private WorkstationsGroupDAO wsGroupDao;

	@Override
	public int createWorkstationsGroup(int workstationId, String name, String desc) {
		WorkstationsGroup workstationsGroup = new WorkstationsGroup(name, desc);
		wsGroupDao.createWorkstationsGroup(workstationsGroup);
		int groupId = workstationsGroup.getId();
		
		changeWorkstationGroup(workstationId, groupId);
		Workstation workstation = wsService.getWorkstationById(workstationId);
		workstation.setRequestedCounters(true);
		wsService.updateWorkstation(workstation);
		return groupId;
	}

	@Override
	public WorkstationsGroup getWorkstationsGroupById(int id) {
		return wsGroupDao.getWorkstationsGroupById(id);
	}

	@Override
	public void updateWorkstationsGroup(WorkstationsGroup workstationsGroup) {
		wsGroupDao.updateWorkstationsGroup(workstationsGroup);
	}

	@Override
	public List<WorkstationsGroup> getWorkstationsGroups() {
		return wsGroupDao.getWorkstationsGroups();
	}

	@Override
	public ArrayList<Workstation> getWorkstations(int groupId) {
		WorkstationsGroup workstationsGroup = getWorkstationsGroupById(groupId);
		Hibernate.initialize(workstationsGroup.getWorkstations());
		return workstationsGroup.getWorkstations();
	}
	
	@Override
	public void changeWorkstationGroup(int workstationId, int groupId) {
		Workstation ws = wsService.getWorkstationById(workstationId);
		WorkstationsGroup wsGroupOld = getWorkstationsGroupById(ws.getGroupId());
		WorkstationsGroup wsGroupNew = getWorkstationsGroupById(groupId);
		wsGroupOld.removeWorkstation(ws);
		wsGroupNew.addWorkstation(ws);
		ws.setWorkstationsGroup(wsGroupNew);
		wsGroupDao.updateWorkstationsGroup(wsGroupOld);
		wsGroupDao.updateWorkstationsGroup(wsGroupNew);
	}

	@Override
	public void addWorkstation(Workstation workstation, int groupId) {
		WorkstationsGroup wsGroup = getWorkstationsGroupById(groupId);;
		wsGroup.addWorkstation(workstation);
		workstation.setWorkstationsGroup(wsGroup);
		wsGroupDao.updateWorkstationsGroup(wsGroup);
	}
}
