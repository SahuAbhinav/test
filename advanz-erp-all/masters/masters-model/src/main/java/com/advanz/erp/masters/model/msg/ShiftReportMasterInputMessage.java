package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;

import com.advanz.erp.masters.model.ShiftReportMasterDTO;

import com.advanz.erp.masters.model.criteria.ShiftReportMasterSearchCriteriaDTO;

@SuppressWarnings("serial")
public class ShiftReportMasterInputMessage extends AdvanzErpBaseInputMessage{
	
	
	private ShiftReportMasterDTO shiftReportMasterDTO;
	private ShiftReportMasterSearchCriteriaDTO searchCriteria;
	private Integer next;
	public ShiftReportMasterDTO getShiftReportMasterDTO() {
		return shiftReportMasterDTO;
	}
	public void setShiftReportMasterDTO(ShiftReportMasterDTO shiftReportMasterDTO) {
		this.shiftReportMasterDTO = shiftReportMasterDTO;
	}
	public ShiftReportMasterSearchCriteriaDTO getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(ShiftReportMasterSearchCriteriaDTO searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	public Integer getNext() {
		return next;
	}
	public void setNext(Integer next) {
		this.next = next;
	}
	
	

}
