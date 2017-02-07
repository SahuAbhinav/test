package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.GetPassMasterDTO;
import com.advanz.erp.masters.model.ReturnGetPassMasterDTO;

@SuppressWarnings("serial")
public class ReturnGetPassMasterInputMessage extends AdvanzErpBaseInputMessage{

	private ReturnGetPassMasterDTO returnGetPassMasterDTO;
private Integer next;
	public ReturnGetPassMasterDTO getReturnGetPassMasterDTO() {
		return returnGetPassMasterDTO;
	}

	public void setReturnGetPassMasterDTO(
			ReturnGetPassMasterDTO returnGetPassMasterDTO) {
		this.returnGetPassMasterDTO = returnGetPassMasterDTO;
	}

	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}

	
	

	
	
}
