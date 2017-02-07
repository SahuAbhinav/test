package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.ProfessionalTaxDeductTypeDTO;
@SuppressWarnings("serial")
public class ProfessionalTaxDeductTypeOutputMessage extends AdvanzErpBaseOutputMessage {


private ProfessionalTaxDeductTypeDTO professionalTaxDeductTypeDTO;
	
private List<ProfessionalTaxDeductTypeDTO> professionalTaxDeductTypeDTOList;

public ProfessionalTaxDeductTypeDTO getProfessionalTaxDeductTypeDTO() {
	return professionalTaxDeductTypeDTO;
}

public void setProfessionalTaxDeductTypeDTO(
		ProfessionalTaxDeductTypeDTO professionalTaxDeductTypeDTO) {
	this.professionalTaxDeductTypeDTO = professionalTaxDeductTypeDTO;
}

public List<ProfessionalTaxDeductTypeDTO> getProfessionalTaxDeductTypeDTOList() {
	return professionalTaxDeductTypeDTOList;
}

public void setProfessionalTaxDeductTypeDTOList(
		List<ProfessionalTaxDeductTypeDTO> professionalTaxDeductTypeDTOList) {
	this.professionalTaxDeductTypeDTOList = professionalTaxDeductTypeDTOList;
}

	
}
