package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.CityDTO;

public class CityForm {
	private CityDTO cityDTO;
	private List<CityDTO> rows;
	private String succ;
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public CityDTO getCityDTO() {
		return cityDTO;
	}
	public void setCityDTO(CityDTO cityDTO) {
		this.cityDTO = cityDTO;
	}
	public List<CityDTO> getRows() {
		return rows;
	}
	public void setRows(List<CityDTO> listCity) {
		this.rows = listCity;
	}
}
