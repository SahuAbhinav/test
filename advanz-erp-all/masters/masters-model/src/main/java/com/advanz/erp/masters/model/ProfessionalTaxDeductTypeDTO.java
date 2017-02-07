package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;
@SuppressWarnings("serial")
public class ProfessionalTaxDeductTypeDTO extends BaseDTO {

	private Integer ptaxDeductTypeId;
	
	private String ptaxDeductType;

	public Integer getPtaxDeductTypeId() {
		return ptaxDeductTypeId;
	}

	public void setPtaxDeductTypeId(Integer ptaxDeductTypeId) {
		this.ptaxDeductTypeId = ptaxDeductTypeId;
	}

	public String getPtaxDeductType() {
		return ptaxDeductType;
	}

	public void setPtaxDeductType(String ptaxDeductType) {
		this.ptaxDeductType = ptaxDeductType;
	}
	
	@Override
	public String toString() {
		return "ProfessionalTaxDeductTypeDTO [ptaxDeductTypeId=" + ptaxDeductTypeId + ", ptaxDeductType=" + ptaxDeductType + "]";
	}
}
