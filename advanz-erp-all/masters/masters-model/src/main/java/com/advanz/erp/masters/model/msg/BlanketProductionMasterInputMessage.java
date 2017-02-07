package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.BlanketProductionDetailLeftDTO;
import com.advanz.erp.masters.model.BlanketProductionDetailRightDTO;
import com.advanz.erp.masters.model.BlanketProductionMasterDTO;
import com.advanz.erp.masters.model.criteria.BlanketProductionSearchCriteriaDTO;

@SuppressWarnings("serial")
public class BlanketProductionMasterInputMessage extends AdvanzErpBaseInputMessage{
	private BlanketProductionMasterDTO blanketProductionMasterDTO;
	private BlanketProductionSearchCriteriaDTO searchCriteria;
	
	private BlanketProductionDetailLeftDTO blanketProductionDetailLeftDTO;
	private BlanketProductionDetailRightDTO blanketProductionDetailRightDTO;
	private Integer next;
	public BlanketProductionMasterDTO getBlanketProductionMasterDTO() {
		return blanketProductionMasterDTO;
	}
	public void setBlanketProductionMasterDTO(
			BlanketProductionMasterDTO blanketProductionMasterDTO) {
		this.blanketProductionMasterDTO = blanketProductionMasterDTO;
	}
	public BlanketProductionSearchCriteriaDTO getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(BlanketProductionSearchCriteriaDTO searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	public BlanketProductionDetailLeftDTO getBlanketProductionDetailLeftDTO() {
		return blanketProductionDetailLeftDTO;
	}
	public void setBlanketProductionDetailLeftDTO(
			BlanketProductionDetailLeftDTO blanketProductionDetailLeftDTO) {
		this.blanketProductionDetailLeftDTO = blanketProductionDetailLeftDTO;
	}
	public BlanketProductionDetailRightDTO getBlanketProductionDetailRightDTO() {
		return blanketProductionDetailRightDTO;
	}
	public void setBlanketProductionDetailRightDTO(
			BlanketProductionDetailRightDTO blanketProductionDetailRightDTO) {
		this.blanketProductionDetailRightDTO = blanketProductionDetailRightDTO;
	}
	public Integer getNext() {
		return next;
	}
	public void setNext(Integer next) {
		this.next = next;
	}
	
	

}
