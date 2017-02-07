package com.advanz.erp.masters.model;


import com.advanz.erp.common.model.BaseDTO;

public class SalaryAttandanceDTO extends BaseDTO
{
	private Integer sno;
	private Integer salaryTranAutoNo;
	private Integer employeeId;
	private Integer departmentId;
	private Double totalDayInMonth;
	private Double hollydayWeekOff;
	private Double workingDays;
	private Double presentDays;
	private Double absentDays;
	private Double lossOfPay;
	private Double extraWork;
	private Double payableDays;
	private Double monthlySalary;
	private Double otherAllowance;
	private Double netAmount;
	private Double netPayable;
	private Double baseSalaryForPf;
	private Double pfFlag;
	private Double employeeComponent;
	private Double employerComponent;

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

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Double getHollydayWeekOff() {
		return hollydayWeekOff;
	}

	public void setHollydayWeekOff(Double hollydayWeekOff) {
		this.hollydayWeekOff = hollydayWeekOff;
	}

	public Double getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(Double workingDays) {
		this.workingDays = workingDays;
	}

	public Double getPresentDays() {
		return presentDays;
	}

	public void setPresentDays(Double presentDays) {
		this.presentDays = presentDays;
	}

	public Double getAbsentDays() {
		return absentDays;
	}

	public void setAbsentDays(Double absentDays) {
		this.absentDays = absentDays;
	}

	public Double getLossOfPay() {
		return lossOfPay;
	}

	public void setLossOfPay(Double lossOfPay) {
		this.lossOfPay = lossOfPay;
	}

	public Double getExtraWork() {
		return extraWork;
	}

	public void setExtraWork(Double extraWork) {
		this.extraWork = extraWork;
	}

	

	public Double getPayableDays() {
		return payableDays;
	}

	public void setPayableDays(Double payableDays) {
		this.payableDays = payableDays;
	}

	public Double getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(Double monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public Double getNetPayable() {
		return netPayable;
	}

	public void setNetPayable(Double netPayable) {
		this.netPayable = netPayable;
	}

	public Double getOtherAllowance() {
		return otherAllowance;
	}

	public void setOtherAllowance(Double otherAllowance) {
		this.otherAllowance = otherAllowance;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Double getBaseSalaryForPf() {
		return baseSalaryForPf;
	}

	public void setBaseSalaryForPf(Double baseSalaryForPf) {
		this.baseSalaryForPf = baseSalaryForPf;
	}

	public Double getEmployeeComponent() {
		return employeeComponent;
	}

	public void setEmployeeComponent(Double employeeComponent) {
		this.employeeComponent = employeeComponent;
	}

	public Double getEmployerComponent() {
		return employerComponent;
	}

	public void setEmployerComponent(Double employerComponent) {
		this.employerComponent = employerComponent;
	}

	public Double getPfFlag() {
		return pfFlag;
	}

	public void setPfFlag(Double pfFlag) {
		this.pfFlag = pfFlag;
	}

	public Double getTotalDayInMonth() {
		return totalDayInMonth;
	}

	public void setTotalDayInMonth(Double totalDayInMonth) {
		this.totalDayInMonth = totalDayInMonth;
	}

	
}
