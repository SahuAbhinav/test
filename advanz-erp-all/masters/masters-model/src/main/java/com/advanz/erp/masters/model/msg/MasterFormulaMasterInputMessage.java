package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.MasterFormulaMasterDTO;
@SuppressWarnings("serial")
public class MasterFormulaMasterInputMessage extends AdvanzErpBaseInputMessage{
	private MasterFormulaMasterDTO masterFormulaMasterDTO;

	public MasterFormulaMasterDTO getMasterFormulaMasterDTO() {
		return masterFormulaMasterDTO;
	}

	public void setMasterFormulaMasterDTO(
			MasterFormulaMasterDTO masterFormulaMasterDTO) {
		this.masterFormulaMasterDTO = masterFormulaMasterDTO;
	}
	

}
