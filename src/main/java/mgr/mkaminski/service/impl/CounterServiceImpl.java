package mgr.mkaminski.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mgr.mkaminski.dao.CounterDAO;
import mgr.mkaminski.model.Counter;
import mgr.mkaminski.service.CounterService;

@Service
@Transactional
public class CounterServiceImpl implements CounterService{
	
	@Autowired
	private CounterDAO counterDAO;
	
	@Override
	public List<Counter> getCountersForCategory(int categoryId) {
		return counterDAO.getCountersForCategory(categoryId);
	}

	@Override
	public void createCounter(Counter counter) {
		counterDAO.createCounter(counter);
	}

}
