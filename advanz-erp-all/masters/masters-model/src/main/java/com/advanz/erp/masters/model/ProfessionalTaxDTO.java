package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;
@SuppressWarnings("serial")
public class ProfessionalTaxDTO extends BaseDTO {
	private Integer ptaxId;
	private String slabName;
	private String ptaxCode;
	private Double fromAmount;
	private Double toAmount;
	private String deductAmount;
	
	private ProfessionalTaxDeductTypeDTO professionalTaxDeductTypeDto;
	
	
	public ProfessionalTaxDeductTypeDTO getProfessionalTaxDeductTypeDto() {
		return professionalTaxDeductTypeDto;
	}
	public void setProfessionalTaxDeductTypeDto(
			ProfessionalTaxDeductTypeDTO professionalTaxDeductTypeDto) {
		this.professionalTaxDeductTypeDto = professionalTaxDeductTypeDto;
	}
	
	
	public Integer getPtaxId() {
		return ptaxId;
	}
	public void setPtaxId(Integer ptaxId) {
		this.ptaxId = ptaxId;
	}
	public String getSlabName() {
		return slabName;
	}
	public void setSlabName(String slabName) {
		this.slabName = slabName;
	}
	
	public String getPtaxCode() {
		return ptaxCode;
	}
	public void setPtaxCode(String ptaxCode) {
		this.ptaxCode = ptaxCode;
	}
	public Double getFromAmount() {
		return fromAmount;
	}
	public void setFromAmount(Double fromAmount) {
		this.fromAmount = fromAmount;
	}
	public Double getToAmount() {
		return toAmount;
	}
	public void setToAmount(Double toAmount) {
		this.toAmount = toAmount;
	}
	
	public String getDeductAmount() {
		return deductAmount;
	}
	public void setDeductAmount(String deductAmount) {
		this.deductAmount = deductAmount;
	}

	@Override
	public String toString() {
		return "ProfessionalTaxDTO [ptaxId=" + ptaxId + ", slabName=" + slabName + ", ptaxDeductType=" + ptaxCode + ", fromAmount=" + fromAmount + ", toAmount=" + toAmount + ",professionalTaxDeductTypeDto="+professionalTaxDeductTypeDto+"]";
	}
}