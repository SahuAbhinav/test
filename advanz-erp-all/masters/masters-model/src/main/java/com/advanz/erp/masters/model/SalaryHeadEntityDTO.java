package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class SalaryHeadEntityDTO extends BaseDTO {

	private Integer salaryHeadId;

	private String salaryHeadName;

	private String salaryHeadCode;

	private Integer headType;

	private String type;

	private String payableType;

	private String payableMonth;

	private Integer professionalTaxFlag;

	private Integer basicSalaryFlag;

	private String description;

	public Integer getSalaryHeadId() {
		return salaryHeadId;
	}

	public void setSalaryHeadId(Integer salaryHeadId) {
		this.salaryHeadId = salaryHeadId;
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

	public Integer getHeadType() {
		return headType;
	}

	public void setHeadType(Integer headType) {
		this.headType = headType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPayableType() {
		return payableType;
	}

	public void setPayableType(String payableType) {
		this.payableType = payableType;
	}

	public String getPayableMonth() {
		return payableMonth;
	}

	public void setPayableMonth(String payableMonth) {
		this.payableMonth = payableMonth;
	}

	public Integer getProfessionalTaxFlag() {
		return professionalTaxFlag;
	}

	public void setProfessionalTaxFlag(Integer professionalTaxFlag) {
		this.professionalTaxFlag = professionalTaxFlag;
	}

	public Integer getBasicSalaryFlag() {
		return basicSalaryFlag;
	}

	public void setBasicSalaryFlag(Integer basicSalaryFlag) {
		this.basicSalaryFlag = basicSalaryFlag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SalaryHeadEntityDTO [salaryHeadId=" + salaryHeadId
				+ ", salaryHeadName=" + salaryHeadName + ", salaryHeadCode="
				+ salaryHeadCode + ", headType=" + headType + ", type=" + type
				+ ", payableType=" + payableType + ", payableMonth="
				+ payableMonth + ", professionalTaxFlag=" + professionalTaxFlag
				+ ", basicSalaryFlag=" + basicSalaryFlag + ", description="
				+ description + "]";
	}

}
