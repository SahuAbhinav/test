package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.ProfessionalTaxDTO;
@SuppressWarnings("serial")
public class ProfessionalTaxOutputMessage extends AdvanzErpBaseOutputMessage{
	ProfessionalTaxDTO professionalTaxDTO;
	List<ProfessionalTaxDTO> ProfessionalTaxDTOList;

	public ProfessionalTaxDTO getProfessionalTaxDTO() {
		return professionalTaxDTO;
	}

	public void setProfessionalTaxDTO(ProfessionalTaxDTO professionalTaxDTO) {
		this.professionalTaxDTO = professionalTaxDTO;
	}

	public List<ProfessionalTaxDTO> getProfessionalTaxDTOList() {
		return ProfessionalTaxDTOList;
	}

	public void setProfessionalTaxDTOList(
			List<ProfessionalTaxDTO> professionalTaxDTOList) {
		ProfessionalTaxDTOList = professionalTaxDTOList;
	}

	

}
