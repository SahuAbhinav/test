package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.ProfessionalTaxDeductTypeDTO;



public class ProfessionalTaxDeductTypeForm {
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
