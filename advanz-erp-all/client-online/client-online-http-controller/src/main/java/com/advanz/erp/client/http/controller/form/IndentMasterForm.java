package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.IndentMasterDTO;

public class IndentMasterForm {
	private List<IndentMasterDTO> indentMasterList;
	private IndentMasterDTO indentMasterDTO;
	private String succ;
	private String lastIndentDate;

	public String getLastIndentDate() {
		return lastIndentDate;
	}

	public void setLastIndentDate(String lastIndentDate) {
		this.lastIndentDate = lastIndentDate;
	}

	public List<IndentMasterDTO> getIndentMasterList() {
		return indentMasterList;
	}

	public void setIndentMasterList(List<IndentMasterDTO> indentMasterList) {
		this.indentMasterList = indentMasterList;
	}

	public IndentMasterDTO getIndentMasterDTO() {
		return indentMasterDTO;
	}

	public void setIndentMasterDTO(IndentMasterDTO indentMasterDTO) {
		this.indentMasterDTO = indentMasterDTO;
	}

	public String getSucc() {
		return succ;
	}

	public void setSucc(String succ) {
		this.succ = succ;
	}

}
