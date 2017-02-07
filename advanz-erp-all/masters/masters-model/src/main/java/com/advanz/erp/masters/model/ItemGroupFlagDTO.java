package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;


@SuppressWarnings("serial")
public class ItemGroupFlagDTO extends BaseDTO{

	
	private Integer itemGroupFlagId;
	
	private String itemGroupFlagName;
	
	
	public Integer getItemGroupFlagId() {
		return itemGroupFlagId;
	}


	public void setItemGroupFlagId(Integer itemGroupFlagId) {
		this.itemGroupFlagId = itemGroupFlagId;
	}


	public String getItemGroupFlagName() {
		return itemGroupFlagName;
	}


	public void setItemGroupFlagName(String itemGroupFlagName) {
		this.itemGroupFlagName = itemGroupFlagName;
	}


	
}
