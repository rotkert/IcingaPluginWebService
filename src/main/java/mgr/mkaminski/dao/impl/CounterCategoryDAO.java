package mgr.mkaminski.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mgr.mkaminski.model.CounterCategory;
import mgr.mkaminski.util.HibernateUtil;

@Repository
public class CounterCategoryDAO implements mgr.mkaminski.dao.CounterCategoryDAO {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CounterCategory> getCounterCategories(int groupId) {
		return hibernateUtil.fetchByColumn("groupId", groupId, CounterCategory.class);
	}

	@Override
	public void createCounterCategory(CounterCategory counterCategory) {
		hibernateUtil.create(counterCategory);
	}

}
