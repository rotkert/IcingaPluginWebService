package mgr.mkaminski.service;

import java.util.List;

import mgr.mkaminski.model.CounterCategory;

public interface CounterCategoryService {
	public List<CounterCategory> getCounterCategories(int groupId);
	public void createCounterCategory(CounterCategory counterCategory);
}
