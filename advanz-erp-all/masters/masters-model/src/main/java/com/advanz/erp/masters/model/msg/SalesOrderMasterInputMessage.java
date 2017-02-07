package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.SalesOrderMasterDTO;
import com.advanz.erp.masters.model.criteria.SalesOrderMasterSearchCriteriaDTO;

@SuppressWarnings("serial")
public class SalesOrderMasterInputMessage extends AdvanzErpBaseInputMessage{

	private SalesOrderMasterDTO salesOrderMasterDTO;
	
	

	private SalesOrderMasterSearchCriteriaDTO searchCriteria;
	private Integer next;
	
	public SalesOrderMasterDTO getSalesOrderMasterDTO() {
		return salesOrderMasterDTO;
	}

	public void setSalesOrderMasterDTO(SalesOrderMasterDTO salesOrderMasterDTO) {
		this.salesOrderMasterDTO = salesOrderMasterDTO;
	}

	public SalesOrderMasterSearchCriteriaDTO getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(SalesOrderMasterSearchCriteriaDTO searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}

	
	
	
	
}
