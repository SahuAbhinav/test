package com.advanz.erp.masters.service.business;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.IssueDetailMasterDTO;
import com.advanz.erp.masters.model.msg.IssueInputMessage;
import com.advanz.erp.masters.model.msg.IssueOutputMessage;

public interface IIssueMasterService extends IAdvanzErpBaseSerivce{

	
	public IssueOutputMessage createIssueMaster(IssueInputMessage issueInputMessage);
	
	public IssueOutputMessage updateIssueMaster(IssueInputMessage issueInputMessage);
	
	public IssueOutputMessage deleteIssueMaster(IssueInputMessage issueInputMessage);
	
	public IssueOutputMessage findIssueMasterById(IssueInputMessage issueInputMessage);
	
	public IssueOutputMessage findAllIssueMasters();
	
	public IssueOutputMessage search(IssueInputMessage issueInputMessage);
	
	public IssueOutputMessage getNewIssueSeriesNo(IssueInputMessage issueInputMessage);
	public IssueOutputMessage findIssueMasterPagination(IssueInputMessage issueInputMessage);
	
	public List findByIssueNoAndItemId(String issueNumber,Integer itemId);
	public IssueDetailMasterDTO updateIssueDetail(IssueDetailMasterDTO detailMasterDTO);
	public Date findIssueDateByIssueNo(String issueNumber);
}
