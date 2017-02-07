package com.advanz.erp.masters.entity.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_employee_leaves")
public class EmployeeLeavesEntity  implements Serializable{
	
	@EmbeddedId
    private EmployeeLeaveKey id;
	
	
	public EmployeeLeaveKey getId() {
		return id;
	}

	public void setId(EmployeeLeaveKey id) {
		this.id = id;
	}
	
	

	@ManyToOne
    @JoinColumn(name = "employee_id" , nullable = true,insertable = false, updatable = false)
	private EmployeeEntity employee;
	

	
	@ManyToOne
    @JoinColumn(name = "leave_id" , nullable = true,insertable = false, updatable = false)
	private LeaveTypeMastEntity leaveTypeMastEntity;
	
	@Column(name="allow_days")
	private Double allowDays;


	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public LeaveTypeMastEntity getLeaveTypeMastEntity() {
		return leaveTypeMastEntity;
	}

	public void setLeaveTypeMastEntity(LeaveTypeMastEntity leaveTypeMastEntity) {
		this.leaveTypeMastEntity = leaveTypeMastEntity;
	}

	public Double getAllowDays() {
		return allowDays;
	}

	public void setAllowDays(Double allowDays) {
		this.allowDays = allowDays;
	}

	
}
