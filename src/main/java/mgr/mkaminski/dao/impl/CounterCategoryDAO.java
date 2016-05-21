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
	
	@Override
	public List<CounterCategory> getCounterCategories() {
		return hibernateUtil.fetchAll(CounterCategory.class);
	}

	@Override
	public void createCounterCategory(CounterCategory counterCategory) {
		hibernateUtil.create(counterCategory);
	}

}
