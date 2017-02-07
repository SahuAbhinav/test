package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;

import com.advanz.erp.masters.model.SalaryHeadDTO;

import com.advanz.erp.masters.model.criteria.SalaryHeadSearchCriteriaDTO;

public class SalaryHeadInputMessage extends AdvanzErpBaseInputMessage{

	private SalaryHeadDTO salaryHeadDTO;

	private SalaryHeadSearchCriteriaDTO searchCriteria;
	
	public SalaryHeadDTO getSalaryHeadDTO() {
		return salaryHeadDTO;
	}

	public void setSalaryHeadDTO(SalaryHeadDTO salaryHeadDTO) {
		this.salaryHeadDTO = salaryHeadDTO;
	}

	public SalaryHeadSearchCriteriaDTO getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(SalaryHeadSearchCriteriaDTO searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	
	
	
}
