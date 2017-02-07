package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.StoreLocationDTO;

public class StoreLocationForm {
	
	private StoreLocationDTO storeLocationDTO;
	private List<StoreLocationDTO> rows;
	private String succ;
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	/**
	 * @return the storeLocationDTO
	 */
	public StoreLocationDTO getStoreLocationDTO() {
		return storeLocationDTO;
	}
	/**
	 * @param storeLocationDTO the storeLocationDTO to set
	 */
	public void setStoreLocationDTO(StoreLocationDTO storeLocationDTO) {
		this.storeLocationDTO = storeLocationDTO;
	}
	/**
	 * @return the rows
	 */
	public List<StoreLocationDTO> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<StoreLocationDTO> rows) {
		this.rows = rows;
	}
	
}
