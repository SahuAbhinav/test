package com.advanz.erp.masters.model.criteria;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;
@SuppressWarnings("serial")
public class IssueReturnSearchCriteriaDTO extends BaseDTO{
	private String returnNumber;
	private Date toDate;
	private Date fromDate;
	private Integer departmentId;

	public String getReturnNumber() {
		return returnNumber;
	}
	public void setReturnNumber(String returnNumber) {
		this.returnNumber = returnNumber;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	

}
