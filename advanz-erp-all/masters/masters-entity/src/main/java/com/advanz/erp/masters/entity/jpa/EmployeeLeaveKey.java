package com.advanz.erp.masters.entity.jpa;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EmployeeLeaveKey  implements Serializable{
	
	public Integer employee_id;
	
	public Integer leave_id;

	public Integer getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}

	public Integer getLeave_id() {
		return leave_id;
	}

	public void setLeave_id(Integer leave_id) {
		this.leave_id = leave_id;
	}

}
