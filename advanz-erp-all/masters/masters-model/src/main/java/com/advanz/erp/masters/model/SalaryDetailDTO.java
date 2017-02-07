package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class SalaryDetailDTO extends BaseDTO {

	private Integer sno;
	private Integer salaryTranAutoNo;
	private EmployeeDTO employeeDTO;
	private Integer headId;
	private Double headAmount;
	private Double headAmountPayable;
	private String headType;
	private Double dedAdvanceAmt;
	private Double balAdvanceAmt;
	private Double netSalary;
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public Integer getSalaryTranAutoNo() {
		return salaryTranAutoNo;
	}
	public void setSalaryTranAutoNo(Integer salaryTranAutoNo) {
		this.salaryTranAutoNo = salaryTranAutoNo;
	}
	public EmployeeDTO getEmployeeDTO() {
		return employeeDTO;
	}
	public void setEmployeeDTO(EmployeeDTO employeeDTO) {
		this.employeeDTO = employeeDTO;
	}
	
	public Integer getHeadId() {
		return headId;
	}
	public void setHeadId(Integer headId) {
		this.headId = headId;
	}
	public Double getHeadAmount() {
		return headAmount;
	}
	public void setHeadAmount(Double headAmount) {
		this.headAmount = headAmount;
	}
	public String getHeadType() {
		return headType;
	}
	public void setHeadType(String headType) {
		this.headType = headType;
	}
	public Double getDedAdvanceAmt() {
		return dedAdvanceAmt;
	}
	public void setDedAdvanceAmt(Double dedAdvanceAmt) {
		this.dedAdvanceAmt = dedAdvanceAmt;
	}
	public Double getBalAdvanceAmt() {
		return balAdvanceAmt;
	}
	public void setBalAdvanceAmt(Double balAdvanceAmt) {
		this.balAdvanceAmt = balAdvanceAmt;
	}
	public Double getNetSalary() {
		return netSalary;
	}
	public void setNetSalary(Double netSalary) {
		this.netSalary = netSalary;
	}
	public Double getHeadAmountPayable() {
		return headAmountPayable;
	}
	public void setHeadAmountPayable(Double headAmountPayable) {
		this.headAmountPayable = headAmountPayable;
	}
	
}
