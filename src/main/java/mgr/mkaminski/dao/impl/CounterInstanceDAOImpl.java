package mgr.mkaminski.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mgr.mkaminski.dao.CounterInstanceDAO;
import mgr.mkaminski.model.CounterInstance;
import mgr.mkaminski.util.HibernateUtil;

@Repository
public class CounterInstanceDAOImpl implements CounterInstanceDAO{
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CounterInstance> getInstancesForCategory(int categoryId) {
		return hibernateUtil.fetchByColumn("counterCategoryId", categoryId, CounterInstance.class);
	}

	@Override
	public void createCounterInstance(CounterInstance counterInstance) {
		hibernateUtil.create(counterInstance);
	}

}
