package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.IssueTypeMasterDTO;

public class IssueTypeMasterOutputMessage extends AdvanzErpBaseOutputMessage{
	
	private List<IssueTypeMasterDTO> issueTypeMasterDTOList;	

	public List<IssueTypeMasterDTO> getIssueTypeMasterDTOList() {
		return issueTypeMasterDTOList;
	}

	public void setIssueTypeMasterDTOList(
			List<IssueTypeMasterDTO> issueTypeMasterDTOList) {
		this.issueTypeMasterDTOList = issueTypeMasterDTOList;
	}

	

}
