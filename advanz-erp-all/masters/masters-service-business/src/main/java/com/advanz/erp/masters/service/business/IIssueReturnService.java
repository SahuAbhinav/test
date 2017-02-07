package com.advanz.erp.masters.service.business;

import java.util.List;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.IssueReturnMasterDTO;
import com.advanz.erp.masters.model.msg.IssueReturnMasterInputMessage;
import com.advanz.erp.masters.model.msg.IssueReturnMasterOutputMessage;

public interface IIssueReturnService extends IAdvanzErpBaseSerivce{
	public IssueReturnMasterOutputMessage findAllIssueReturn();
	public IssueReturnMasterOutputMessage createIssueReturn(IssueReturnMasterInputMessage issueReturnMasterInputMessage);
	public IssueReturnMasterOutputMessage updateIssueReturn(IssueReturnMasterInputMessage issueReturnMasterInputMessage);
	public IssueReturnMasterOutputMessage deleteIssueReturn(IssueReturnMasterInputMessage issueReturnMasterInputMessage);
	public IssueReturnMasterOutputMessage search(IssueReturnMasterInputMessage issueReturnMasterInputMessage);
	public IssueReturnMasterOutputMessage findById(IssueReturnMasterInputMessage issueReturnMasterInputMessage);
	public IssueReturnMasterOutputMessage newIssueReturnSeriesNo(IssueReturnMasterInputMessage issueReturnMasterInputMessage);
	public List<Double> findReturnQuantity(String issueNumber, Integer itemId);
	public List getIssueList(IssueReturnMasterDTO dto);
	public List searchIssueList(IssueReturnMasterDTO dto);
	public Boolean getIssueRetunDeatilByIssueNumberAndItemId(String issueNumber,
			Integer itemId);
	public IssueReturnMasterOutputMessage findIssueReturnPagination(IssueReturnMasterInputMessage issueReturnMasterInputMessage);
	
}
