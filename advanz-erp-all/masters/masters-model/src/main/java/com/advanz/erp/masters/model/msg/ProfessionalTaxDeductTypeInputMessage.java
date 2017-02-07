package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.ProfessionalTaxDeductTypeDTO;

@SuppressWarnings("serial")
public class ProfessionalTaxDeductTypeInputMessage extends AdvanzErpBaseInputMessage{
	private ProfessionalTaxDeductTypeDTO professionalTaxDeductTypeDTO;

	public ProfessionalTaxDeductTypeDTO getProfessionalTaxDeductTypeDTO() {
		return professionalTaxDeductTypeDTO;
	}

	public void setProfessionalTaxDeductTypeDTO(
			ProfessionalTaxDeductTypeDTO professionalTaxDeductTypeDTO) {
		this.professionalTaxDeductTypeDTO = professionalTaxDeductTypeDTO;
	}
	
}
