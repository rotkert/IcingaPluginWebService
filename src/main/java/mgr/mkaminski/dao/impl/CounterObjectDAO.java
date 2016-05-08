package mgr.mkaminski.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mgr.mkaminski.model.CounterObject;
import mgr.mkaminski.util.HibernateUtil;

@Repository
public class CounterObjectDAO implements mgr.mkaminski.dao.CounterObjectDAO {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Override
	public List<CounterObject> getCounterObjects() {
		return hibernateUtil.fetchAll(CounterObject.class);
	}

}
