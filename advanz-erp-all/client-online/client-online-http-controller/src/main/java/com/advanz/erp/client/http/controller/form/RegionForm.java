package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.RegionDTO;

public class RegionForm {
	private RegionDTO regionDTO;
	private List<RegionDTO> rows;
	private String succ;
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public RegionDTO getRegionDTO() {
		return regionDTO;
	}
	public void setRegionDTO(RegionDTO regionDTO) {
		this.regionDTO = regionDTO;
	}
	public List<RegionDTO> getRows() {
		return rows;
	}
	public void setRows(List<RegionDTO> listRegion) {
		this.rows = listRegion;
	}
}
