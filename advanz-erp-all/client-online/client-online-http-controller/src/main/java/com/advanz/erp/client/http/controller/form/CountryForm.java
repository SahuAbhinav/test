package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.CountryDTO;

public class CountryForm {
	
	private CountryDTO countryDTO;
	private List<CountryDTO> rows;
	private String succ;
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public CountryDTO getCountryDTO() {
		return countryDTO;
	}
	public void setCountryDTO(CountryDTO countryDTO) {
		this.countryDTO = countryDTO;
	}
	public List<CountryDTO> getRows() {
		return rows;
	}
	public void setRows(List<CountryDTO> listCountry) {
		this.rows = listCountry;
	}
	
}
