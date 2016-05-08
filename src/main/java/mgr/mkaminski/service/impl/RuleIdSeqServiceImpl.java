package mgr.mkaminski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mgr.mkaminski.dao.RuleIdSeqDAO;
import mgr.mkaminski.model.RuleIdSeq;
import mgr.mkaminski.service.RuleIdSeqService;

@Service
@Transactional
public class RuleIdSeqServiceImpl implements RuleIdSeqService{

	@Autowired
	private RuleIdSeqDAO ruleIdSeqDAO;
	
	@Override
	public long nextValue() {
		return ruleIdSeqDAO.createRuleIdSeq(new RuleIdSeq());
	}

}
