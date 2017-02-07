package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.MastersDTO;


public class MastersForm {

	private MastersDTO mastersDTO;
	private List<MastersDTO> rows;
	private String succ;
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public MastersDTO getMastersDTO() {
		return mastersDTO;
	}
	public void setMastersDTO( MastersDTO mastersDTO) {
		this.mastersDTO = mastersDTO;
	}
	public List<MastersDTO> getRows() {
		return rows;
	}
	public void setRows(List<MastersDTO> listMasters) {
		this.rows = listMasters;
	}
}
