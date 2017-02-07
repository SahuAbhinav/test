package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.IssueReturnMasterDTO;

@SuppressWarnings("serial")
public class IssueReturnMasterInputMessage extends AdvanzErpBaseInputMessage{
	
	private IssueReturnMasterDTO issueReturnMasterDTO;
	private Integer next;
	public IssueReturnMasterDTO getIssueReturnMasterDTO() {
		return issueReturnMasterDTO;
	}
	public void setIssueReturnMasterDTO(IssueReturnMasterDTO issueReturnMasterDTO) {
		this.issueReturnMasterDTO = issueReturnMasterDTO;
	}
	public Integer getNext() {
		return next;
	}
	public void setNext(Integer next) {
		this.next = next;
	}
	
}
