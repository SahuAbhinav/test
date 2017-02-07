package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.FinishedGoodsMasterDTO;
import com.advanz.erp.masters.model.SalesOrderMasterDTO;
import com.advanz.erp.masters.model.criteria.FinishedGoodsMasterSearchCriteriaDTO;
import com.advanz.erp.masters.model.criteria.SalesOrderMasterSearchCriteriaDTO;

@SuppressWarnings("serial")
public class FinishedGoodsMasterInputMessage extends AdvanzErpBaseInputMessage{
private Integer next;
	public FinishedGoodsMasterSearchCriteriaDTO getSearchCriteria() {
		return searchCriteria;
	}

	private FinishedGoodsMasterDTO finishedGoodsMasterDTO;
	
	

	private FinishedGoodsMasterSearchCriteriaDTO searchCriteria;
	
	
	

	

	public FinishedGoodsMasterDTO getFinishedGoodsMasterDTO() {
		return finishedGoodsMasterDTO;
	}

	public void setFinishedGoodsMasterDTO(
			FinishedGoodsMasterDTO finishedGoodsMasterDTO) {
		this.finishedGoodsMasterDTO = finishedGoodsMasterDTO;
	}

	public void setSearchCriteria(
			FinishedGoodsMasterSearchCriteriaDTO searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}

	
	
	
	
}
