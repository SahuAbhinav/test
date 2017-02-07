package com.advanz.erp.masters.model.criteria;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class IssueMasterSearchCriteriaDTO extends BaseDTO {
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	private String issueNumber;
	private Date issueDate; 
	private String issueType;
	private String itemName;
	private String raisedBy;
	private Date fromDate;
	private Date toDate;
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
	public String getIssueNumber() {
		return issueNumber;
	}
	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	
	public String getRaisedBy() {
		return raisedBy;
	}
	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
	}
	@Override
	public String toString() {
		return "IssueMasterSearchCriteriaDTO [issueNumber=" + issueNumber
				+ ", issueDate=" + issueDate + ", issueType=" + issueType + "]";
	}
	
	

}
