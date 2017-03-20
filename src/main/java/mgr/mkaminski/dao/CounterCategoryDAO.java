package mgr.mkaminski.dao;

import java.util.List;

import mgr.mkaminski.model.CounterCategory;

public interface CounterCategoryDAO {
	public List<CounterCategory> getCounterCategories(int groupId);
	public void createCounterCategory(CounterCategory counterCategory);
}
