package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.GrnMasterDTO;

@SuppressWarnings("serial")
public class GrnMasterInputMessage extends AdvanzErpBaseInputMessage{

	private GrnMasterDTO grnMasterDTO;
	
	private Integer next;
	private Integer previous;

	public Integer getNext() {
	return next;
	}
	public void setNext(Integer next) {
	this.next = next;
	}
	public Integer getPrevious() {
	return previous;
	}
	public void setPrevious(Integer previous) {
	this.previous = previous;
	}

//	private GrnMasterSearchCriteriaDTO searchCriteria;
	
	
	public GrnMasterDTO getGrnMasterDTO() {
		return grnMasterDTO;
	}

	public void setGrnMasterDTO(GrnMasterDTO grnMasterDTO) {
		this.grnMasterDTO = grnMasterDTO;
	}

	
	
	
	
}
