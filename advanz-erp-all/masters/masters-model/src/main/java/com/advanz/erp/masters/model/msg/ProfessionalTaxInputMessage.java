package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.ProfessionalTaxDTO;
@SuppressWarnings("serial")
public class ProfessionalTaxInputMessage extends AdvanzErpBaseInputMessage {
	
	ProfessionalTaxDTO professionalTaxDTO;
	
	public ProfessionalTaxDTO getProfessionalTaxDTO() {
		return professionalTaxDTO;
	}
	public void setProfessionalTaxDTO(ProfessionalTaxDTO professionalTaxDTO) {
		this.professionalTaxDTO = professionalTaxDTO;
	}
	
	
}
