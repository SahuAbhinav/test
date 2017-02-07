package com.advanz.erp.masters.model;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;


@SuppressWarnings("serial")
public class LeaveApplicationDTO extends BaseDTO{
	
	private static final long serialVersionUID = 1L;
    private Integer sno;
	private EmployeeDTO employeeDTO;
	private LeaveTypeMastDTO leaveTypeMastDTO;
	private Date fromDate;
	private Date toDate;
	private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	private Boolean approveFlag;
	private String remark;
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	
	
	public EmployeeDTO getEmployeeDTO() {
		return employeeDTO;
	}
	public void setEmployeeDTO(EmployeeDTO employeeDTO) {
		this.employeeDTO = employeeDTO;
	}
	public LeaveTypeMastDTO getLeaveTypeMastDTO() {
		return leaveTypeMastDTO;
	}
	public void setLeaveTypeMastDTO(LeaveTypeMastDTO leaveTypeMastDTO) {
		this.leaveTypeMastDTO = leaveTypeMastDTO;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
