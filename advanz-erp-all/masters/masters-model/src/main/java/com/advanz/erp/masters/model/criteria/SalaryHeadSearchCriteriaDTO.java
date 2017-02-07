package com.advanz.erp.masters.model.criteria;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class SalaryHeadSearchCriteriaDTO extends BaseDTO {
private String salaryHeadName;
private String salaryHeadCode;
private String headType;
private Integer currentSalaryHeadId;




public Integer getCurrentSalaryHeadId() {
	return currentSalaryHeadId;
}

public void setCurrentSalaryHeadId(Integer currentSalaryHeadId) {
	this.currentSalaryHeadId = currentSalaryHeadId;
}

public SalaryHeadSearchCriteriaDTO() {
	super();
}

public SalaryHeadSearchCriteriaDTO(String salaryHeadName, String salaryHeadCode, String headType) {
	super();
	this.salaryHeadName = salaryHeadName;
	this.salaryHeadCode = salaryHeadCode;
	this.headType = headType;
}

public String getSalaryHeadName() {
	return salaryHeadName;
}

public void setSalaryHeadName(String salaryHeadName) {
	this.salaryHeadName = salaryHeadName;
}

public String getSalaryHeadCode() {
	return salaryHeadCode;
}

public void setSalaryHeadCode(String salaryHeadCode) {
	this.salaryHeadCode = salaryHeadCode;
}

public String getHeadType() {
	return headType;
}

public void setHeadType(String headType) {
	this.headType = headType;
}



}