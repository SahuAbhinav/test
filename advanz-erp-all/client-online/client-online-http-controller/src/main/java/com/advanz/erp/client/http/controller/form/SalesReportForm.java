package com.advanz.erp.client.http.controller.form;

import java.util.Date;


public class SalesReportForm {
	private String itemName;
	private String partyName;
	private String itemGroupFlagName; 
	private String itemCategory;
	private String activeStatus;
	private String orderType;
	private Date fromDate;
	private String departmentName;
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getItemGroupFlagName() {
		return itemGroupFlagName;
	}
	public void setItemGroupFlagName(String itemGroupFlagName) {
		this.itemGroupFlagName = itemGroupFlagName;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	

	
	
}
