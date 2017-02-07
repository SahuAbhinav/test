package com.advanz.erp.masters.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;
@SuppressWarnings("serial")
@Entity
@Table(name = "t_leave_application")
public class LeaveApplicationEntity extends BaseEntity{
	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="sno")
	private Integer sno;
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_ID")
	private EmployeeEntity employeeEntity;
	
	@ManyToOne
	@JoinColumn(name = "leave_id")
	private LeaveTypeMastEntity leaveTypeMastEntity;
	
	@Column(name = "from_date")
	private Date fromDate;
	
	@Column(name = "to_date")
	private Date toDate;
	
	@Column(name = "approve_flag")
	private Boolean approveFlag;
	
	@Column(name = "remark")
	private String remark;

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	

	public EmployeeEntity getEmployeeEntity() {
		return employeeEntity;
	}

	public void setEmployeeEntity(EmployeeEntity employeeEntity) {
		this.employeeEntity = employeeEntity;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	

	

	public Boolean getApproveFlag() {
		return approveFlag;
	}

	public void setApproveFlag(Boolean approveFlag) {
		this.approveFlag = approveFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public LeaveTypeMastEntity getLeaveTypeMastEntity() {
		return leaveTypeMastEntity;
	}

	public void setLeaveTypeMastEntity(LeaveTypeMastEntity leaveTypeMastEntity) {
		this.leaveTypeMastEntity = leaveTypeMastEntity;
	}
	
}
