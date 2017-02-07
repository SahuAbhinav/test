package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_salary_leaves")
public class SalaryLeaveEntity extends BaseEntity{

	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="sno")
	private Integer sno;
	
	@Column(name="salary_tran_auto_no")
	private Integer salaryTranAutoNo;
		
	@Column(name = "EMPLOYEE_ID")
	private Integer employeeId;

	@Column(name = "department_id")
	private Integer departmentId;
	
	@Column(name = "leave_id")
	private Integer leaveId;
	
	@Column(name = "opening_leave_balance")
	private Double openingLeaveBalance;
	
	@Column(name = "closing_leave_balance")
	private Double closingLeaveBalance;
	
	@Column(name = "availed_leave")
	private Double availedLeave;
	
	@Column(name = "month_name")
	private String salaryMonth;
	
	@Column(name = "cl_alloted_in_month")
	private Double clAllotedInMonth;
	
	public String getSalaryMonth() {
		return salaryMonth;
	}
	public void setSalaryMonth(String salaryMonth) {
		this.salaryMonth = salaryMonth;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
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
	public Integer getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}
	public Double getClAllotedInMonth() {
		return clAllotedInMonth;
	}
	public void setClAllotedInMonth(Double clAllotedInMonth) {
		this.clAllotedInMonth = clAllotedInMonth;
	}
	
	
}
