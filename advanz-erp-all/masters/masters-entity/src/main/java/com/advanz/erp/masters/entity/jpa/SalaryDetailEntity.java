package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "t_salary_detail")
public class SalaryDetailEntity extends BaseEntity{

	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="sno")
	private Integer sno;
	
	@Column(name="salary_tran_auto_no")
	private Integer salaryTranAutoNo;
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_ID")
	private EmployeeEntity employeeEntity;
	
	
	@Column(name = "department_id")
	private Integer departmentId;
	
	@Column(name = "salary_head_id")
	private Integer headId;
	
	
	@Column(name="head_amount")
	private Double headAmount;
	
	@Column(name="head_amount_payable")
	private Double headAmountPayable;
	
	@Column(name="head_type")
	private String headType;
	
	
	@Column(name="ded_advance_amt")
	private Double dedAdvanceAmt;
	
	@Column(name="bal_advance_amt")
	private Double balAdvanceAmt;
	
	@Column(name="net_salary")
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

	public EmployeeEntity getEmployeeEntity() {
		return employeeEntity;
	}

	public void setEmployeeEntity(EmployeeEntity employeeEntity) {
		this.employeeEntity = employeeEntity;
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

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Double getHeadAmountPayable() {
		return headAmountPayable;
	}

	public void setHeadAmountPayable(Double headAmountPayable) {
		this.headAmountPayable = headAmountPayable;
	}
	
}
