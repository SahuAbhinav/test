package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.AttandanceMasterDTO;

@SuppressWarnings("serial")
public class AttandanceMasterInputMessage extends AdvanzErpBaseInputMessage{
	private AttandanceMasterDTO attandanceMasterDTO;

	public AttandanceMasterDTO getAttandanceMasterDTO() {
		return attandanceMasterDTO;
	}

	public void setAttandanceMasterDTO(AttandanceMasterDTO attandanceMasterDTO) {
		this.attandanceMasterDTO = attandanceMasterDTO;
	}

	
	

}
