package com.advanz.erp.client.http.controller.form;

import java.util.Date;

import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.MastersDTO;

public class IssueReportFrom {

	private ItemGroupFlagDTO flagDTO;
	private MastersDTO mastersDTO;
	private String itemGroupFlagName;
	private String itemGroupName;
	private String itemName;
	private String itemCategory;
	private String activeStatus;
	private String headName;
	private String sectionName;
	private String rateType;
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	public String getItemGroupName() {
		return itemGroupName;
	}
	public void setItemGroupName(String itemGroupName) {
		this.itemGroupName = itemGroupName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	private String name;
	private Date fromDate;
	private Date toDate;
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
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getRateType() {
		return rateType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	
	
}
