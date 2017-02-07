package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.BranchDTO;

public class BranchForm {
	
	private BranchDTO branchDto;
	private List<BranchDTO> rows;
	private String succ;
	
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public BranchDTO getBranchDto() {
		return branchDto;
	}
	public void setBranchDto(BranchDTO branchDto) {
		this.branchDto = branchDto;
	}
	public List<BranchDTO> getRows() {
		return rows;
	}
	public void setRows(List<BranchDTO> listBranch) {
		this.rows = listBranch;
	}
	
	
}
