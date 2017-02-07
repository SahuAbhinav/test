package com.advanz.erp.masters.entity.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "m_leave_type_mast")

public class LeaveTypeMastEntity extends BaseEntity {
	
	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="leave_id")
	private Integer leaveId;
	
	@Column(name="leave_code")
	private String leaveCode;
	
	@Column(name="leave_name")
	private String leaveName;
	
	@Column(name="leave_type")
	private String leaveType;
	
//	@Column(name="salary_head_id")
//	private String salaryHeadId;
//	
//	
//	public String getSalaryHeadId() {
//		return salaryHeadId;
//	}
//
//	public void setSalaryHeadId(String salaryHeadId) {
//		this.salaryHeadId = salaryHeadId;
//	}

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="m_leave_encashment", 
	joinColumns = {@JoinColumn(name="leave_id")},
	inverseJoinColumns={@JoinColumn(name="salary_head_id")})
	
	public List<SalaryHeadEntity> encashmentList;
	
	
//	public Boolean getLeaveCarryForwardFlag() {
//		return leaveCarryForwardFlag;
//	}
//
//	public void setLeaveCarryForwardFlag(Boolean leaveCarryForwardFlag) {
//		this.leaveCarryForwardFlag = leaveCarryForwardFlag;
//	}



	@Column(name="allow_days")
	private Double allowDays;
	
	

	@Column(name="applicable_days")
	private Integer applicableDays;
	
	@Column(name="leave_carry_forward_flag")
	private Integer leaveCarryForwardFlag;
	
	@Column(name="encashment_flag")
	private Integer encashmentFlag;
	
	@Column(name="description")
	private String description;
	
	

	public Integer getLeaveCarryForwardFlag() {
		return leaveCarryForwardFlag;
	}

	public void setLeaveCarryForwardFlag(Integer leaveCarryForwardFlag) {
		this.leaveCarryForwardFlag = leaveCarryForwardFlag;
	}

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public String getLeaveCode() {
		return leaveCode;
	}

	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}

	public String getLeaveName() {
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	
	public Double getAllowDays() {
		return allowDays;
	}

	public void setAllowDays(Double allowDays) {
		this.allowDays = allowDays;
	}

	public Integer getApplicableDays() {
		return applicableDays;
	}

	public void setApplicableDays(Integer applicableDays) {
		this.applicableDays = applicableDays;
	}

	public Integer getEncashmentFlag() {
		return encashmentFlag;
	}

	public void setEncashmentFlag(Integer encashmentFlag) {
		this.encashmentFlag = encashmentFlag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SalaryHeadEntity> getEncashmentList() {
		return encashmentList;
	}

	public void setEncashmentList(List<SalaryHeadEntity> encashmentList) {
		this.encashmentList = encashmentList;
	}

}
