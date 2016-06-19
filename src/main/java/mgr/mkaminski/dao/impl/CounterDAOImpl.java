package mgr.mkaminski.dao.impl;

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
		return hibernateUtil.fetchByColumn("counterCategoryId", categoryId, Counter.class);
	}

	@Override
	public void createCounter(Counter counter) {
		hibernateUtil.create(counter);
	}
}
