package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.CompanyDTO;

public class CompanyForm {
	
	private CompanyDTO companyDto;
	private List<CompanyDTO> rows;
	private String succ;
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public CompanyDTO getCompanyDto() {
		return companyDto;
	}
	public void setCompanyDto(CompanyDTO companyDto) {
		this.companyDto = companyDto;
	}
	public List<CompanyDTO> getRows() {
		return rows;
	}
	public void setRows(List<CompanyDTO> listCompany) {
		this.rows = listCompany;
	}
	
}
