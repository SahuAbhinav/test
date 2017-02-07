package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.PartyDTO;

public class PartyForm {
	
	private PartyDTO partyDTO;
	private List<PartyDTO> rows;
	private String succ;
	
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public PartyDTO getPartyDTO() {
		return partyDTO;
	}
	public void setPartyDTO(PartyDTO partyDTO) {
		this.partyDTO = partyDTO;
	}
	public List<PartyDTO> getRows() {
		return rows;
	}
	public void setRows(List<PartyDTO> rows) {
		this.rows = rows;
	}
	
	
}
