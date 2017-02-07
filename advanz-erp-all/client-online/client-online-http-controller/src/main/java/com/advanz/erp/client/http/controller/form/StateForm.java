package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.StateDTO;

public class StateForm {
	private StateDTO stateDTO;
	private List<StateDTO> rows;
	private String succ;
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public StateDTO getStateDTO() {
		return stateDTO;
	}
	public void setStateDTO(StateDTO stateDTO) {
		this.stateDTO = stateDTO;
	}
	public List<StateDTO> getRows() {
		return rows;
	}
	public void setRows(List<StateDTO> listState) {
		this.rows = listState;
	}
}
