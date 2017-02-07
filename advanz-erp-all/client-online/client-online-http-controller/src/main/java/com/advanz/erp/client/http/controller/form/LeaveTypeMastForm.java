package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.LeaveTypeMastDTO;


public class LeaveTypeMastForm {

	private LeaveTypeMastDTO leaveTypeMastDTO;
	private List<LeaveTypeMastDTO> rows;
	private String succ;
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public LeaveTypeMastDTO getLeaveTypeMastDTO() {
		return leaveTypeMastDTO;
	}
	public void setLeaveTypeMastDTO( LeaveTypeMastDTO leaveTypeMastDTO) {
		this.leaveTypeMastDTO = leaveTypeMastDTO;
	}
	public List<LeaveTypeMastDTO> getRows() {
		return rows;
	}
	public void setRows(List<LeaveTypeMastDTO> listleaveTypeMast) {
		this.rows = listleaveTypeMast;
	}
}
