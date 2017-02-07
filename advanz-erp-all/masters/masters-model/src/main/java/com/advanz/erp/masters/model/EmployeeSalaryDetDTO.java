package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class EmployeeSalaryDetDTO extends BaseDTO{
	
    private Integer employeeId;
	
	private String salaryId;
	
	private EmployeeDTO employeeDTO;
	
	private SalaryHeadDTO salaryHeadDTO;
	
	private Double baseHeadPer;
	
	private String baseHeadCombination;
	
	private String headType;
	
	private String payableMonth;
	
    private Double amount =0.0;
    private String payableType;
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public SalaryHeadDTO getSalaryHeadDTO() {
		return salaryHeadDTO;
	}

	public void setSalaryHeadDTO(SalaryHeadDTO salaryHeadDTO) {
		this.salaryHeadDTO = salaryHeadDTO;
	}

	public EmployeeDTO getEmployeeDTO() {
		return employeeDTO;
	}

	public void setEmployeeDTO(EmployeeDTO employeeDTO) {
		this.employeeDTO = employeeDTO;
	}


	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(String salaryId) {
		this.salaryId = salaryId;
	}

	public Double getBaseHeadPer() {
		return baseHeadPer;
	}

	public void setBaseHeadPer(Double baseHeadPer) {
		this.baseHeadPer = baseHeadPer;
	}

	public String getBaseHeadCombination() {
		return baseHeadCombination;
	}

	public void setBaseHeadCombination(String baseHeadCombination) {
		this.baseHeadCombination = baseHeadCombination;
	}

	public String getHeadType() {
		return headType;
	}

	public void setHeadType(String headType) {
		this.headType = headType;
	}

	public String getPayableMonth() {
		return payableMonth;
	}

	public void setPayableMonth(String payableMonth) {
		this.payableMonth = payableMonth;
	}

	public String getPayableType() {
		return payableType;
	}

	public void setPayableType(String payableType) {
		this.payableType = payableType;
	}

}
