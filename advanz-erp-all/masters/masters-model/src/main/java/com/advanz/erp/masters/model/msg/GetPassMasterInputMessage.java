package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.GetPassMasterDTO;

@SuppressWarnings("serial")
public class GetPassMasterInputMessage extends AdvanzErpBaseInputMessage{

	private GetPassMasterDTO getPassMasterDTO;
private Integer next;
	public GetPassMasterDTO getGetPassMasterDTO() {
		return getPassMasterDTO;
	}

	public void setGetPassMasterDTO(GetPassMasterDTO getPassMasterDTO) {
		this.getPassMasterDTO = getPassMasterDTO;
	}

	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}
	

	
	
}
