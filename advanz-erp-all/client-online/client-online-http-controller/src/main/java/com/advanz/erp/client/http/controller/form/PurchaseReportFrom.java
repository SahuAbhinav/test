package com.advanz.erp.client.http.controller.form;

import java.util.Date;

import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.MastersDTO;

public class PurchaseReportFrom {

	private ItemGroupFlagDTO flagDTO;
	private MastersDTO mastersDTO;
	private String itemGroupFlagName;
	private String name;
	private Date fromDate;
	private Date toDate;
	private String itemName;
	private String itemCategory;
	private String itemGroupName;
	public String getItemGroupFlagName() {
		return itemGroupFlagName;
	}
	public void setItemGroupFlagName(String itemGroupFlagName) {
		this.itemGroupFlagName = itemGroupFlagName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
	public ItemGroupFlagDTO getFlagDTO() {
		return flagDTO;
	}
	public void setFlagDTO(ItemGroupFlagDTO flagDTO) {
		this.flagDTO = flagDTO;
	}
	public MastersDTO getMastersDTO() {
		return mastersDTO;
	}
	public void setMastersDTO(MastersDTO mastersDTO) {
	this.mastersDTO = mastersDTO;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public String getItemGroupName() {
		return itemGroupName;
	}
	public void setItemGroupName(String itemGroupName) {
		this.itemGroupName = itemGroupName;
	}

	
	
}
