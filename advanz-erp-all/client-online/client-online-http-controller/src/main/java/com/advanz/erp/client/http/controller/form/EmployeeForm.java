package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.EmployeeDTO;

public class EmployeeForm {

	private EmployeeDTO employeeDTO;
	private List<Integer> leavesIdList;
	private List<String> leavesDaysList;
	
	private String script;
	
	private String employeeCode;
	private String employeeName;
	private String empliyeeCity;
	private String succ;
	

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmpliyeeCity() {
		return empliyeeCity;
	}

	public void setEmpliyeeCity(String empliyeeCity) {
		this.empliyeeCity = empliyeeCity;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public EmployeeDTO getEmployeeDTO() {
		return employeeDTO;
	}

	public void setEmployeeDTO(EmployeeDTO employeeDTO) {
		this.employeeDTO = employeeDTO;
	}

	public List<Integer> getLeavesIdList() {
		return leavesIdList;
	}

	public void setLeavesIdList(List<Integer> leavesIdList) {
		this.leavesIdList = leavesIdList;
	}

	public List<String> getLeavesDaysList() {
		return leavesDaysList;
	}

	public void setLeavesDaysList(List<String> leavesDaysList) {
		this.leavesDaysList = leavesDaysList;
	}

	public String getSucc() {
		return succ;
	}

	public void setSucc(String succ) {
		this.succ = succ;
	}

}
