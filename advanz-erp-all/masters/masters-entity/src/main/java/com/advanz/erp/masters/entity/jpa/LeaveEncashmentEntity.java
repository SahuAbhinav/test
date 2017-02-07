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


@Table(name = "m_leave_encashment")

public class LeaveEncashmentEntity extends BaseEntity {
	
	

	@Column(name="leave_id")
	private String leaveId;

	@Column(name="salary_head_id")
	private String salaryHeadId;



	


	public String getLeaveId() {
		return leaveId;
	}



	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}



	public String getSalaryHeadId() {
		return salaryHeadId;
	}



	public void setSalaryHeadId(String salaryHeadId) {
		this.salaryHeadId = salaryHeadId;
	}
	
	
	


	

	



	
}
