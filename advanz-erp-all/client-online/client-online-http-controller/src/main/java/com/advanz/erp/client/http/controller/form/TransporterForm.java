package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.TransporterDTO;

public class TransporterForm {
	
	private TransporterDTO transporterDTO;
	private List<TransporterDTO> rows;
	private String succ;
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	/**
	 * @return the transporterDTO
	 */
	public TransporterDTO getTransporterDTO() {
		return transporterDTO;
	}
	/**
	 * @param transporterDTO the transporterDTO to set
	 */
	public void setTransporterDTO(TransporterDTO transporterDTO) {
		this.transporterDTO = transporterDTO;
	}
	/**
	 * @return the rows
	 */
	public List<TransporterDTO> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<TransporterDTO> rows) {
		this.rows = rows;
	}
	
	
}
