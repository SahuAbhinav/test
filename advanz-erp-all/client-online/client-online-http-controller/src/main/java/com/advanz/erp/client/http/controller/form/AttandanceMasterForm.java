package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.AttandanceMasterDTO;
import com.advanz.erp.masters.model.EmployeeDTO;

public class AttandanceMasterForm {
	private List<AttandanceMasterDTO> attandanceMasterList;
	private AttandanceMasterDTO attandanceMasterDTO;
	private List<EmployeeDTO> employeeDTOList;
	private String succ;
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public List<AttandanceMasterDTO> getAttandanceMasterList() {
		return attandanceMasterList;
	}
	public void setAttandanceMasterList(
			List<AttandanceMasterDTO> attandanceMasterList) {
		this.attandanceMasterList = attandanceMasterList;
	}
	public AttandanceMasterDTO getAttandanceMasterDTO() {
		return attandanceMasterDTO;
	}
	public void setAttandanceMasterDTO(AttandanceMasterDTO attandanceMasterDTO) {
		this.attandanceMasterDTO = attandanceMasterDTO;
	}
	public List<EmployeeDTO> getEmployeeDTOList() {
		return employeeDTOList;
	}
	public void setEmployeeDTOList(List<EmployeeDTO> employeeDTOList) {
		this.employeeDTOList = employeeDTOList;
	}
	
}
