package com.advanz.erp.masters.model;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class AttandanceMasterDTO extends BaseDTO{
	
	private String dayOfDate;
	private Integer sno;
	private Date date;
	private String employeeName;
	private String employeeCode;
	private String weakOff;
	private String attandanceFlag;
	private String dayStatus;
	private String orderBy;
	private Integer employeeId;
	private String tempDate;
	private String month;
	private Integer year;
	private List<LeaveTypeMastDTO> leaveTypeMastDTO;
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getDayOfDate() {
		return dayOfDate;
	}
	public void setDayOfDate(String dayOfDate) {
		this.dayOfDate = dayOfDate;
	}
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getAttandanceFlag() {
		return attandanceFlag;
	}
	public void setAttandanceFlag(String attandanceFlag) {
		this.attandanceFlag = attandanceFlag;
	}
	public String getDayStatus() {
		return dayStatus;
	}
	public void setDayStatus(String dayStatus) {
		this.dayStatus = dayStatus;
	}
	public String getWeakOff() {
		return weakOff;
	}
	public void setWeakOff(String weakOff) {
		this.weakOff = weakOff;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public List<LeaveTypeMastDTO> getLeaveTypeMastDTO() {
		return leaveTypeMastDTO;
	}
	public void setLeaveTypeMastDTO(List<LeaveTypeMastDTO> leaveTypeMastDTO) {
		this.leaveTypeMastDTO = leaveTypeMastDTO;
	}
	public String getTempDate() {
		return tempDate;
	}
	public void setTempDate(String tempDate) {
		this.tempDate = tempDate;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	
}
