package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.AreaDTO;


public class AreaForm {

	private AreaDTO areaDTO;
	private List<AreaDTO> rows;
	private String succ;
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public AreaDTO getAreaDTO() {
		return areaDTO;
	}
	public void setAreaDTO( AreaDTO areaDTO) {
		this.areaDTO = areaDTO;
	}
	public List<AreaDTO> getRows() {
		return rows;
	}
	public void setRows(List<AreaDTO> listArea) {
		this.rows = listArea;
	}
}
