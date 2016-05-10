package mgr.mkaminski.service;

import java.util.List;

import mgr.mkaminski.model.Counter;

public interface CounterService {
	public List<Counter> getCountersForCategory(int categoryId);
}
