package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.PartyTypeDTO;

public class PartyTypeForm {
	
	private PartyTypeDTO partyTypeDTO;
	private List<PartyTypeDTO> rows;
	private String succ;
		
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public PartyTypeDTO getPartyTypeDTO() {
		return partyTypeDTO;
	}
	public void setPartyTypeDTO(PartyTypeDTO partyTypeDTO) {
		this.partyTypeDTO = partyTypeDTO;
	}
	public List<PartyTypeDTO> getRows() {
		return rows;
	}
	public void setRows(List<PartyTypeDTO> rows) {
		this.rows = rows;
	}
	
	
}
