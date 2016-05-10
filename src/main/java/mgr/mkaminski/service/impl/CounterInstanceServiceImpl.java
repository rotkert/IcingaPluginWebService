package mgr.mkaminski.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mgr.mkaminski.dao.CounterInstanceDAO;
import mgr.mkaminski.model.CounterInstance;
import mgr.mkaminski.service.CounterInstanceService;

@Service
@Transactional
public class CounterInstanceServiceImpl implements CounterInstanceService{

	@Autowired
	private CounterInstanceDAO counterInstanceDAO;
	
	@Override
	public List<CounterInstance> getCounterInstancesForCategory(int categoryId) {
		return counterInstanceDAO.getInstancesForCategory(categoryId);
	}

}
