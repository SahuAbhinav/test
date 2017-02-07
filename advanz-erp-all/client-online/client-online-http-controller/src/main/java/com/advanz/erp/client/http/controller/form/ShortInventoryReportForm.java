package com.advanz.erp.client.http.controller.form;

import java.util.Date;

public class ShortInventoryReportForm {
	private Date datePrompt;
	private String itemGroupName;
	private String reorderName;
	private String itemClass;
	private String status;
	private String itemCategoryName;
	public Date getDatePrompt() {
		return datePrompt;
	}

	public void setDatePrompt(Date datePrompt) {
		this.datePrompt = datePrompt;
	}

	public String getItemGroupName() {
		return itemGroupName;
	}

	public void setItemGroupName(String itemGroupName) {
		this.itemGroupName = itemGroupName;
	}

	public String getReorderName() {
		return reorderName;
	}

	public void setReorderName(String reorderName) {
		this.reorderName = reorderName;
	}

	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getItemCategoryName() {
		return itemCategoryName;
	}

	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}

}
