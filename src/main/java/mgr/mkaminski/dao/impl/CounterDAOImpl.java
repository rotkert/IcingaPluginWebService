package mgr.mkaminski.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mgr.mkaminski.dao.CounterDAO;
import mgr.mkaminski.model.Counter;
import mgr.mkaminski.util.HibernateUtil;

@Repository
public class CounterDAOImpl implements CounterDAO{
	
	@Autowired
	private HibernateUtil hibernateUtil;

	@SuppressWarnings("unchecked")
	@Override
	public List<Counter> getCountersForCategory(int categoryId) {
		String query = "select * from counter where counterObjectId = " + categoryId;
		List<Object[]> counterObjects = hibernateUtil.fetchAll(query);
		List<Counter> counters = new ArrayList<>();
		
		for (Object[] counterObject : counterObjects) {
			Counter counter = new Counter();
			counter.setId((int)counterObject[0]);
			counter.setCounterObjectId((int)counterObject[1]);
			counter.setName((String)counterObject[2]);
			counters.add(counter);
		}
		return counters;
	}
}
