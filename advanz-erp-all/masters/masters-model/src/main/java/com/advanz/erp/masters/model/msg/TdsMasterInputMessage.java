package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.ProfessionalTaxDTO;
import com.advanz.erp.masters.model.TdsMasterDTO;
@SuppressWarnings("serial")
public class TdsMasterInputMessage extends AdvanzErpBaseInputMessage {
	
	TdsMasterDTO tdsMasterDTO;
	
	
	public TdsMasterDTO getTdsMasterDTO() {
		return tdsMasterDTO;
	}
	public void setTdsMasterDTO(TdsMasterDTO tdsMasterDTO) {
		this.tdsMasterDTO = tdsMasterDTO;
	}
	
	
	
}
