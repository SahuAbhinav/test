package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.BlanketProductionDetailNewDTO;
import com.advanz.erp.masters.model.BlanketProductionMasterNewDTO;
import com.advanz.erp.masters.model.criteria.BlanketProductionSearchCriteriaDTO;

@SuppressWarnings("serial")
public class BlanketProductionMasterNewInputMessage extends
		AdvanzErpBaseInputMessage {
	private BlanketProductionMasterNewDTO blanketProductionMasterNewDTO;
	private BlanketProductionSearchCriteriaDTO searchCriteria;
	private BlanketProductionDetailNewDTO blanketProductionDetailNewDTO;
	private Integer next;

	public BlanketProductionMasterNewDTO getBlanketProductionMasterNewDTO() {
		return blanketProductionMasterNewDTO;
	}

	public void setBlanketProductionMasterNewDTO(
			BlanketProductionMasterNewDTO blanketProductionMasterNewDTO) {
		this.blanketProductionMasterNewDTO = blanketProductionMasterNewDTO;
	}

	public BlanketProductionSearchCriteriaDTO getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(
			BlanketProductionSearchCriteriaDTO searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public BlanketProductionDetailNewDTO getBlanketProductionDetailNewDTO() {
		return blanketProductionDetailNewDTO;
	}

	public void setBlanketProductionDetailNewDTO(
			BlanketProductionDetailNewDTO blanketProductionDetailNewDTO) {
		this.blanketProductionDetailNewDTO = blanketProductionDetailNewDTO;
	}

	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}

}
