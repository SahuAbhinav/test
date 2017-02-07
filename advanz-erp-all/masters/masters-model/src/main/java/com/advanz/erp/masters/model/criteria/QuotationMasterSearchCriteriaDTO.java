package com.advanz.erp.masters.model.criteria;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class QuotationMasterSearchCriteriaDTO extends BaseDTO {
	private String qoNumber;
	private Date fromDate;
	private Date toDate;
	private Date qoDate;
	private String partyName;
	private String itemName;
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getQoNumber() {
		return qoNumber;
	}
	public void setQoNumber(String qoNumber) {
		this.qoNumber = qoNumber;
	}
	public Date getQoDate() {
		return qoDate;
	}
	public void setQoDate(Date qoDate) {
		this.qoDate = qoDate;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
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
	
	
	

}
