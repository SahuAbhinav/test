package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.ProfessionalTaxDTO;

public class ProfessionalTaxForm {
	private ProfessionalTaxDTO professionalTaxDto;
	private List<ProfessionalTaxDTO> rows;
	private String succ;
	
	private String slabName;
	private String deductAmount;
	
	
	public String getSlabName() {
		return slabName;
	}
	public void setSlabName(String slabName) {
		this.slabName = slabName;
	}
	public String getDeductAmount() {
		return deductAmount;
	}
	public void setDeductAmount(String deductAmount) {
		this.deductAmount = deductAmount;
	}
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public ProfessionalTaxDTO getProfessionalTaxDto() {
		return professionalTaxDto;
	}
	public void setProfessionalTaxDto(ProfessionalTaxDTO professionalTaxDto) {
		this.professionalTaxDto = professionalTaxDto;
	}
	public List<ProfessionalTaxDTO> getRows() {
		return rows;
	}
	public void setRows(List<ProfessionalTaxDTO> rows) {
		this.rows = rows;
	}
	
}
