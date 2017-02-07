package com.advanz.erp.masters.model.criteria;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class LeaveTypeMastSearchCriteriaDTO extends BaseDTO {
private String leaveName;
private String leaveCode;
private String leaveType;

public LeaveTypeMastSearchCriteriaDTO() {
	super();
}

public LeaveTypeMastSearchCriteriaDTO(String leaveName, String leaveCode,
		String leaveType) {
	super();
	this.leaveName = leaveName;
	this.leaveCode = leaveCode;
	this.leaveType = leaveType;
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






}