package com.advanz.erp.client.http.controller.form;

import java.util.Date;

import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.MastersDTO;

public class ReorderReportFrom {

	private ItemGroupFlagDTO flagDTO;
	private MastersDTO mastersDTO;
	private ItemDTO itemDTO;
	private String name;
	private Date toDate;
	
	public ItemDTO getItemDTO() {
		return itemDTO;
	}
	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}
	private String itemGroupFlagName;
	//private String itemName;
	
	
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

	
	
}
