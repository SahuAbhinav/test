package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;


@SuppressWarnings("serial")
public class EmployeeLeavesDTO extends BaseDTO{
	
   private Integer employeeId;
	
	private Integer leaveId;
	
    private EmployeeDTO employeeDTO;
	
	private LeaveTypeMastDTO LeaveTypeMastDTO;
	
	private Double allowDays;

	public EmployeeDTO getEmployeeDTO() {
		return employeeDTO;
	}

	public void setEmployeeDTO(EmployeeDTO employeeDTO) {
		this.employeeDTO = employeeDTO;
	}

	public LeaveTypeMastDTO getLeaveTypeMastDTO() {
		return LeaveTypeMastDTO;
	}

	public void setLeaveTypeMastDTO(LeaveTypeMastDTO leaveTypeMastDTO) {
		LeaveTypeMastDTO = leaveTypeMastDTO;
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

	public Double getAllowDays() {
		return allowDays;
	}

	public void setAllowDays(Double allowDays) {
		this.allowDays = allowDays;
	}

}
