package com.advanz.erp.masters.entity.jpa;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EmployeeSalaryDepKey  implements Serializable{
	
	private Integer employee_id;
	
	private Integer salary_head_id;

	public Integer getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}

	public Integer getSalary_head_id() {
		return salary_head_id;
	}

	public void setSalary_head_id(Integer salary_head_id) {
		this.salary_head_id = salary_head_id;
	}

	

}
