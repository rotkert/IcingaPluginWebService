package mgr.mkaminski.dao.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mgr.mkaminski.dao.WorkstationDAO;
import mgr.mkaminski.model.Workstation;
import mgr.mkaminski.util.HibernateUtil;

@Repository
public class WorkstationDAOImpl implements WorkstationDAO {

	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public void createWorkstation(Workstation workstation) {
		hibernateUtil.create(workstation);
	}

	@Override
	public void updateWorkstation(Workstation workstation) {
		hibernateUtil.update(workstation);
	}
	
	@Override
	public void deleteWorkstation(Workstation workstation) {
		hibernateUtil.delete(workstation);
	}

	@Override
	public List<Workstation> getWorkstatinons() {
		return hibernateUtil.fetchAll(Workstation.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Workstation getWorkstationByToken(UUID token) {
		List<Workstation> list = hibernateUtil.fetchByColumn("token", token, Workstation.class);
		Workstation workstation = null;
		if(list.size() > 0) {
			workstation = list.get(0);
		}
		return workstation;
	}

	@Override
	public Workstation getWorkstationById(int id) {
		return hibernateUtil.fetchById(id, Workstation.class);
	}
}
	
