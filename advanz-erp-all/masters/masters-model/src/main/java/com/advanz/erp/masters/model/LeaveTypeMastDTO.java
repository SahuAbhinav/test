package com.advanz.erp.masters.model;

import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class LeaveTypeMastDTO extends BaseDTO {

	private Integer leaveId;
	
	private Integer salaryHeadId;

	public Integer getSalaryHeadId() {
		return salaryHeadId;
	}

	public void setSalaryHeadId(Integer salaryHeadId) {
		this.salaryHeadId = salaryHeadId;
	}

	private String leaveName;

	private String leaveCode;	

	private String leaveType;
	
	private Double allowDays;

	private Integer applicableDays;

	private Integer leaveCarryForwardFlag;

	private Integer encashmentFlag;	
	
	private String description;
	
	private List<Integer> encashmentIds;
	
	public List<SalaryHeadDTO> encashments;
	
	

	

	public List<SalaryHeadDTO> getEncashments() {
		return encashments;
	}

	public void setEncashments(List<SalaryHeadDTO> encashments) {
		this.encashments = encashments;
	}

	public List<Integer> getEncashmentIds() {
		return encashmentIds;
	}

	public void setEncashmentIds(List<Integer> encashmentIds) {
		this.encashmentIds = encashmentIds;
	}

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public String getLeaveName() {
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public String getLeaveCode() {
		return leaveCode;
	}

	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
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

	public Integer getLeaveCarryForwardFlag() {
		return leaveCarryForwardFlag;
	}

	public void setLeaveCarryForwardFlag(Integer leaveCarryForwardFlag) {
		this.leaveCarryForwardFlag = leaveCarryForwardFlag;
	}

	

	

}
