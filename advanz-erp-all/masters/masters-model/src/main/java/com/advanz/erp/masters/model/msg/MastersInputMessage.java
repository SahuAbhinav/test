package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.criteria.MastersSearchCriteriaDTO;

public class MastersInputMessage extends AdvanzErpBaseInputMessage{

	private MastersDTO mastersDTO;

	private MastersSearchCriteriaDTO searchCriteria;
	
	private Integer formId;	

	
	public MastersDTO getMastersDTO() {
		return mastersDTO;
	}

	public void setMastersDTO(MastersDTO mastersDTO) {
		this.mastersDTO = mastersDTO;
	}

	public MastersSearchCriteriaDTO getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(MastersSearchCriteriaDTO searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	/**
	 * @return the formId
	 */
	public Integer getFormId() {
		return formId;
	}

	/**
	 * @param formId the formId to set
	 */
	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	
	
	
}
