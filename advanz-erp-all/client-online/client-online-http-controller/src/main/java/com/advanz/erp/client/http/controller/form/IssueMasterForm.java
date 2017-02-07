package com.advanz.erp.client.http.controller.form;

import java.util.Date;
import java.util.List;

import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.IssueMasterDTO;

public class IssueMasterForm {
	private List<IssueMasterDTO> issueMasterList;
	private IssueMasterDTO issueMasterDTO;
	private String succ;

	private String printView;
	private Date issueDate;
	private String lastIssueDate;

	public String getLastIssueDate() {
		return lastIssueDate;
	}

	public void setLastIssueDate(String lastIssueDate) {
		this.lastIssueDate = lastIssueDate;
	}

	public String getSucc() {
		return succ;
	}

	public void setSucc(String succ) {
		this.succ = succ;
	}

	public List<IssueMasterDTO> getIssueMasterList() {
		return issueMasterList;
	}

	public void setIssueMasterList(List<IssueMasterDTO> issueMasterList) {
		this.issueMasterList = issueMasterList;
	}

	public IssueMasterDTO getIssueMasterDTO() {
		return issueMasterDTO;
	}

	public void setIssueMasterDTO(IssueMasterDTO issueMasterDTO) {
		this.issueMasterDTO = issueMasterDTO;
	}

	@Override
	public String toString() {
		return "IssueMasterForm [issueMasterList=" + issueMasterList
				+ ", issueMasterDTO=" + issueMasterDTO + "]";
	}

	public String getPrintView() {
		return printView;
	}

	public void setPrintView(String printView) {
		this.printView = printView;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

}
