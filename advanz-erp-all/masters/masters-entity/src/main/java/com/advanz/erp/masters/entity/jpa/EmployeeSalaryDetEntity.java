package com.advanz.erp.masters.entity.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_employee_salary_det")
public class EmployeeSalaryDetEntity extends BaseEntity implements Serializable{
	
	
	@EmbeddedId
    private EmployeeSalaryDepKey id;
	
	public EmployeeSalaryDepKey getId() {
		return id;
	}

	public void setId(EmployeeSalaryDepKey id) {
		this.id = id;
	}


	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_ID" , nullable = true,insertable = false, updatable = false)
	private EmployeeEntity employee;
	
	
	@ManyToOne
	@JoinColumn(name = "salary_head_id" , nullable = true,insertable = false, updatable = false)
	private SalaryHeadEntity salaryHeadEntity;
		
	@Column(name="base_head_per")
	private Double baseHeadPer;
	
	@Column(name="base_head_combination")
	private String baseHeadCombination;
	
	@Column(name="head_type")
	private String headType;
	
	@Column(name="payable_month")
	private String payableMonth;
	
	@Column(name="payable_type")
	private String payableType;
	
	
	@Column(name="amount")
	private Double amount;
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	
	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public SalaryHeadEntity getSalaryHeadEntity() {
		return salaryHeadEntity;
	}

	public void setSalaryHeadEntity(SalaryHeadEntity salaryHeadEntity) {
		this.salaryHeadEntity = salaryHeadEntity;
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
