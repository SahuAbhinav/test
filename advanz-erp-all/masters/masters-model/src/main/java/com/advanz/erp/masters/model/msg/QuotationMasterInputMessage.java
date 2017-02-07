package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.QuotationMasterDTO;
import com.advanz.erp.masters.model.criteria.QuotationMasterSearchCriteriaDTO;

@SuppressWarnings("serial")
public class QuotationMasterInputMessage extends AdvanzErpBaseInputMessage{
	
	private QuotationMasterDTO quotationMasterDTO;
	private QuotationMasterSearchCriteriaDTO searchCriteria;
	private Integer next;
	public QuotationMasterDTO getQuotationMasterDTO() {
		return quotationMasterDTO;
	}

	public void setQuotationMasterDTO(QuotationMasterDTO quotationMasterDTO) {
		this.quotationMasterDTO = quotationMasterDTO;
	}

	public QuotationMasterSearchCriteriaDTO getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(QuotationMasterSearchCriteriaDTO searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}

	
	
}
