package mgr.mkaminski.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mgr.mkaminski.model.RuleIdSeq;
import mgr.mkaminski.util.HibernateUtil;

@Repository
public class RuleIdSeqDAOImpl implements RuleIdSeqDAO {
	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public long createRuleIdSeq(RuleIdSeq ruleIdSeq ) {
		hibernateUtil.create(ruleIdSeq);
		return ruleIdSeq.getId();
	}

}
