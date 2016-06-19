package mgr.mkaminski.dao;

import java.util.List;

import mgr.mkaminski.model.Counter;

public interface CounterDAO {
	public List<Counter> getCountersForCategory(int categoryId);
	public void createCounter(Counter counter);
}
