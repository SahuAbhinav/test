package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class SalaryLeaveDTO extends BaseDTO{
	private Integer sno;
	private Integer salaryTranAutoNo;
	private Integer employeeId;
	private Integer departmentId;
	private Integer leaveId;
	private Double openingLeaveBalance;
	private Double closingLeaveBalance;
	private Double clAllotedInMonth;
	private Double availedLeave;
	private String salaryMonth;
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
		
	public Double getOpeningLeaveBalance() {
		return openingLeaveBalance;
	}
	public void setOpeningLeaveBalance(Double openingLeaveBalance) {
		this.openingLeaveBalance = openingLeaveBalance;
	}
	public Double getClosingLeaveBalance() {
		return closingLeaveBalance;
	}
	public void setClosingLeaveBalance(Double closingLeaveBalance) {
		this.closingLeaveBalance = closingLeaveBalance;
	}
	
	public Double getAvailedLeave() {
		return availedLeave;
	}
	public void setAvailedLeave(Double availedLeave) {
		this.availedLeave = availedLeave;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getSalaryMonth() {
		return salaryMonth;
	}
	public void setSalaryMonth(String salaryMonth) {
		this.salaryMonth = salaryMonth;
	}
	public Double getClAllotedInMonth() {
		return clAllotedInMonth;
	}
	public void setClAllotedInMonth(Double clAllotedInMonth) {
		this.clAllotedInMonth = clAllotedInMonth;
	}
		
}
