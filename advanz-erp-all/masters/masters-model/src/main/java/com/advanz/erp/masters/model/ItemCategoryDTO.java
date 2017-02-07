package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class ItemCategoryDTO extends BaseDTO {
	
	private Integer itemCategoryId;
	
	
	private String itemCategoryCode;
	
	
	private String itemCategoryName;
	
	
	//private Integer itemGroupId;

	private ItemGroupDTO itemGroupDTO;

	public ItemGroupDTO getItemGroupDTO() {
		return itemGroupDTO;
	}


	public void setItemGroupDTO(ItemGroupDTO itemGroupDTO) {
		this.itemGroupDTO = itemGroupDTO;
	}


	public Integer getItemCategoryId() {
		return itemCategoryId;
	}


	public void setItemCategoryId(Integer itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}


	public String getItemCategoryCode() {
		return itemCategoryCode;
	}


	public void setItemCategoryCode(String itemCategoryCode) {
		this.itemCategoryCode = itemCategoryCode;
	}


	public String getItemCategoryName() {
		return itemCategoryName;
	}


	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}


	
	

}
