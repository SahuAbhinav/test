package com.advanz.erp.client.http.controller.form;

import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;


public class ItemReportFrom {

	private ItemGroupDTO itemgroupDTO;
	private ItemCategoryDTO itemcategoryDTO;
	private String itemGroupFlag;
	private String itemGroup;
	private String itemCategory;
	
	private String grade;
	private String classType;
	private String activeStatus;
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public String getItemGroup() {
		return itemGroup;
	}
	public void setItemGroup(String itemGroup) {
		this.itemGroup = itemGroup;
	}
	public String getItemGroupFlag() {
		return itemGroupFlag;
	}
	
	public ItemCategoryDTO getItemcategoryDTO() {
		return itemcategoryDTO;
	}
	public void setItemcategoryDTO(ItemCategoryDTO itemcategoryDTO) {
		this.itemcategoryDTO = itemcategoryDTO;
	}
	public void setItemgroupDTO(ItemGroupDTO itemgroupDTO) {
		this.itemgroupDTO = itemgroupDTO;
	}
	public void setItemGroupFlag(String itemGroupFlag) {
		this.itemGroupFlag = itemGroupFlag;
	}
	public ItemGroupDTO getItemgroupDTO() {
		return itemgroupDTO;

	}
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

}
