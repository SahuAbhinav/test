package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.IssueMasterDTO;
import com.advanz.erp.masters.model.criteria.IssueMasterSearchCriteriaDTO;

@SuppressWarnings("serial")
public class IssueInputMessage extends AdvanzErpBaseInputMessage {

	private IssueMasterDTO issueMasterDTO;

	private IssueMasterSearchCriteriaDTO searchCriteria;

	public IssueMasterDTO getIssueMasterDTO() {
		return issueMasterDTO;
	}

	public void setIssueMasterDTO(IssueMasterDTO issueMasterDTO) {
		this.issueMasterDTO = issueMasterDTO;
	}

	public IssueMasterSearchCriteriaDTO getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(IssueMasterSearchCriteriaDTO searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	
	

}
