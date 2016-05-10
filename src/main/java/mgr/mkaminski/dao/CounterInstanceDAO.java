package mgr.mkaminski.dao;

import java.util.List;

import mgr.mkaminski.model.CounterInstance;

public interface CounterInstanceDAO {
	public List<CounterInstance> getInstancesForCategory(int categoryId);
}
