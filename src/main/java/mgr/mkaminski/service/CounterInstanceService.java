package mgr.mkaminski.service;

import java.util.List;

import mgr.mkaminski.model.CounterInstance;

public interface CounterInstanceService {
	public List<CounterInstance> getCounterInstancesForCategory(int categoryId);
}
