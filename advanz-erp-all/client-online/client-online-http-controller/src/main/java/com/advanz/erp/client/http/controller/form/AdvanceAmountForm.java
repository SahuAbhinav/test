package com.advanz.erp.client.http.controller.form;

import java.util.Date;

import com.advanz.erp.masters.model.AdvanceAmountDTO;

public class AdvanceAmountForm {
	private Date fromDate;
	private Date toDate;
	private String employeeName;
	private String transactionType;
	private String designation;
	private String succ="";
	private AdvanceAmountDTO advanceAmountDTO;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public AdvanceAmountDTO getAdvanceAmountDTO() {
		return advanceAmountDTO;
	}

	public void setAdvanceAmountDTO(AdvanceAmountDTO advanceAmountDTO) {
		this.advanceAmountDTO = advanceAmountDTO;
	}

	public String getSucc() {
		return succ;
	}

	public void setSucc(String succ) {
		this.succ = succ;
	}
   
}
