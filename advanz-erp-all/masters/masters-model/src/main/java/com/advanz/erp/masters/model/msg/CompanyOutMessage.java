package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.CompanyDTO;

public class CompanyOutMessage extends AdvanzErpBaseOutputMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7652311966136682774L;
	
	private CompanyDTO companyDTO;
	
	private List<CompanyDTO> companyDTOList;
	
	public List<CompanyDTO> getCompanyDTOList() {
		return companyDTOList;
	}

	public void setCompanyDTOList(List<CompanyDTO> companyDTOList) {
		this.companyDTOList = companyDTOList;
	}

	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}

	public void setCompanyDTO(CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}
}
