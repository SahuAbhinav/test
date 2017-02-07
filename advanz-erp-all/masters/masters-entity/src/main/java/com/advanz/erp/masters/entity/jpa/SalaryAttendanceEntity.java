package com.advanz.erp.masters.entity.jpa;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "t_salary_attandance")
public class SalaryAttendanceEntity extends BaseEntity {
	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name = "sno")
	private Integer sno;

	@Column(name = "salary_tran_auto_no")
	private Integer salaryTranAutoNo;
	@Column(name = "employee_id")
	private Integer employeeId;
	@Column(name = "department_id")
	private Integer departmentId;
	@Column(name = "total_day_in_month")
	private Double totalDayInMonth;
	@Column(name = "hollyd_weekOff")
	private Double hollydayWeekOff;
	@Column(name = "working_days")
	private Double workingDays;
	@Column(name = "present_days")
	private Double presentDays;
	@Column(name = "absent_days")
	private Double absentDays;
	@Column(name = "loss_of_pay")
	private Double lossOfPay;
	@Column(name = "extra_work_day")
	private Double extraWork;
	@Column(name = "payable_day")
	private Double payableDays;
	@Column(name = "monthly_salary")
	private Double monthlySalary;
	@Column(name = "other_allowance")
	private Double otherAllowance;
	@Column(name = "net_amount")
	private Double netAmount;
	@Column(name = "net_payable")
	private Double netPayable;
	@Column(name = "base_salary_for_pf")
	private Double baseSalaryForPf;
	@Column(name = "pf_flag")
	private Double pfFlag;
	
	@Column(name = "employee_component")
	private Double employeeComponent;
	@Column(name = "employer_component")
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
