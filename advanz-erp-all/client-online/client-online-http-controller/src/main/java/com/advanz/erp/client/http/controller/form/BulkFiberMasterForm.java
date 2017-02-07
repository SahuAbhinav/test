package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.BulkFiberMasterDTO;

public class BulkFiberMasterForm {
	private List<BulkFiberMasterDTO> bulkFiberMasterList;
	private BulkFiberMasterDTO bulkFiberMasterDTO;
	private String succ;
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public List<BulkFiberMasterDTO> getBulkFiberMasterList() {
		return bulkFiberMasterList;
	}
	public void setBulkFiberMasterList(List<BulkFiberMasterDTO> bulkFiberMasterList) {
		this.bulkFiberMasterList = bulkFiberMasterList;
	}
	public BulkFiberMasterDTO getBulkFiberMasterDTO() {
		return bulkFiberMasterDTO;
	}
	public void setBulkFiberMasterDTO(BulkFiberMasterDTO bulkFiberMasterDTO) {
		this.bulkFiberMasterDTO = bulkFiberMasterDTO;
	}
	
	
	
}
