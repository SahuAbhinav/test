package com.advanz.erp.masters.model;

import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class SalaryHeadDTO extends BaseDTO {

	private Integer salaryHeadId;

	private String salaryHeadName;

	private String salaryHeadCode;

	private String headType;

	private String type;

	private List<Integer> baseHeadIds;
	
	private Double baseHeadPer;
	private Integer salaryHeadSequence;
	
	private Integer pfEmployeeFlag;
	private Integer esiEmployeeFlag;
	
	public Double getBaseHeadPer() {
		return baseHeadPer;
	}

	public void setBaseHeadPer(Double baseHeadPer) {
		this.baseHeadPer = baseHeadPer;
	}

	public List<Integer> getBaseHeadIds() {
		return baseHeadIds;
	}

	public void setBaseHeadIds(List<Integer> baseHeadIds) {
		this.baseHeadIds = baseHeadIds;
	}

	private String payableType;

	private String payableMonth;

	private Integer professionalTaxFlag;

	private Integer basicSalaryFlag;

	private String description;
	
	
	public List<SalaryHeadDTO> baseHeads;
	
	private SalaryHeadDTO salaryHeadDTO;


	public SalaryHeadDTO getSalaryHeadDTO() {
		return salaryHeadDTO;
	}

	public void setSalaryHeadDTO(SalaryHeadDTO salaryHeadDTO) {
		this.salaryHeadDTO = salaryHeadDTO;
	}

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

	public String getHeadType() {
		return headType;
	}

	public void setHeadType(String headType) {
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

	
	
//	public List<String> getBaseHeads() {
//		return baseHeads;
//	}
//
//	public void setBaseHeads(List<String> baseHeads) {
//		this.baseHeads = baseHeads;
//	}

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

	public List<SalaryHeadDTO> getBaseHeads() {
		return baseHeads;
	}

	public void setBaseHeads(List<SalaryHeadDTO> baseHeads) {
		this.baseHeads = baseHeads;
	}

	public Integer getSalaryHeadSequence() {
		return salaryHeadSequence;
	}

	public void setSalaryHeadSequence(Integer salaryHeadSequence) {
		this.salaryHeadSequence = salaryHeadSequence;
	}

	public Integer getPfEmployeeFlag() {
		return pfEmployeeFlag;
	}

	public void setPfEmployeeFlag(Integer pfEmployeeFlag) {
		this.pfEmployeeFlag = pfEmployeeFlag;
	}

	public Integer getEsiEmployeeFlag() {
		return esiEmployeeFlag;
	}

	public void setEsiEmployeeFlag(Integer esiEmployeeFlag) {
		this.esiEmployeeFlag = esiEmployeeFlag;
	}

}
