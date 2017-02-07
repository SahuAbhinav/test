package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.IndentMasterDTO;
import com.advanz.erp.masters.model.SalaryMasterDTO;


public class SalaryMasterForm {
	private List<SalaryMasterDTO> salaryMasterList;
	private SalaryMasterDTO salaryMasterDTO;
	private String succ;
	private String operation;
	
	public List<SalaryMasterDTO> getSalaryMasterList() {
		return salaryMasterList;
	}
	public void setSalaryMasterList(List<SalaryMasterDTO> salaryMasterList) {
		this.salaryMasterList = salaryMasterList;
	}
	public SalaryMasterDTO getSalaryMasterDTO() {
		return salaryMasterDTO;
	}
	public void setSalaryMasterDTO(SalaryMasterDTO salaryMasterDTO) {
		this.salaryMasterDTO = salaryMasterDTO;
	}
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
}
