package mgr.mkaminski.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mgr.mkaminski.dao.WorkstationsGroupDAO;
import mgr.mkaminski.model.WorkstationsGroup;
import mgr.mkaminski.util.HibernateUtil;

@Repository
public class WorkstationsGroupDAOImpl implements WorkstationsGroupDAO{
	
	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public void createWorkstationsGroup(WorkstationsGroup workstationsGroup) {
		hibernateUtil.create(workstationsGroup);
	}

	@Override
	public WorkstationsGroup getWorkstationsGroupById(int id) {
		return hibernateUtil.fetchById(id, WorkstationsGroup.class);
	}

	@Override
	public void updateWorkstationsGroup(WorkstationsGroup workstationsGroup) {
		hibernateUtil.update(workstationsGroup);
	}

	@Override
	public List<WorkstationsGroup> getWorkstationsGroups() {
		return hibernateUtil.fetchAll(WorkstationsGroup.class);
	}
}
