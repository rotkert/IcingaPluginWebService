package mgr.mkaminski.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mgr.mkaminski.dao.CounterObjectDAO;
import mgr.mkaminski.model.CounterObject;
import mgr.mkaminski.service.CounterObjectService;

@Service
@Transactional
public class CounterObjectServiceImpl implements CounterObjectService{

	@Autowired
	private CounterObjectDAO counterRuleDAO;
	
	@Override
	public List<CounterObject> getCounterObjects() {
		List<CounterObject> counterObjects = counterRuleDAO.getCounterObjects();
		counterObjects.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		return counterObjects;
	}

}
