package mgr.mkaminski.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mgr.mkaminski.dao.CounterInstanceDAO;
import mgr.mkaminski.model.CounterInstance;
import mgr.mkaminski.util.HibernateUtil;

@Repository
public class CounterInstanceDAOImpl implements CounterInstanceDAO{
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CounterInstance> getInstancesForCategory(int categoryId) {
		String query = "select * from counter_instance where counterObjectId = " + categoryId;
		List<Object[]> counterInstanceObjects = hibernateUtil.fetchAll(query);
		List<CounterInstance> countersInstances = new ArrayList<>();
		
		for (Object[] counterInstanceObject : counterInstanceObjects) {
			CounterInstance counterInstance = new CounterInstance();
			counterInstance.setId((int)counterInstanceObject[0]);
			counterInstance.setCounterObjectId((int)counterInstanceObject[1]);
			counterInstance.setName((String)counterInstanceObject[2]);
			countersInstances.add(counterInstance);
		}
		return countersInstances;
	}

}
