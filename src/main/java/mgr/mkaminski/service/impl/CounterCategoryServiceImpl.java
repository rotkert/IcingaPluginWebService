package mgr.mkaminski.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mgr.mkaminski.dao.impl.CounterCategoryDAO;
import mgr.mkaminski.model.CounterCategory;
import mgr.mkaminski.service.CounterCategoryService;

@Service
@Transactional
public class CounterCategoryServiceImpl implements CounterCategoryService{

	@Autowired
	private CounterCategoryDAO counterCategoryDAO;
	
	@Override
	public List<CounterCategory> getCounterCategories(int groupId) {
		List<CounterCategory> counterObjects = counterCategoryDAO.getCounterCategories(groupId);
		counterObjects.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		return counterObjects;
	}

	@Override
	public void createCounterCategory(CounterCategory counterCategory) {
		counterCategoryDAO.createCounterCategory(counterCategory);
	}

}
