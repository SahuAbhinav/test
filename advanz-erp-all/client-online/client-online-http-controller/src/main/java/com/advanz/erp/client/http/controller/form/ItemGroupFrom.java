package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;

public class ItemGroupFrom {

	
	
	//private List<ItemDTO> items;
	private ItemGroupDTO itemGroupDTO;
	private List<ItemGroupDTO> rows;
	private String succ;
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public ItemGroupDTO getItemGroupDTO() {
		return itemGroupDTO;
	}
	public void setItemGroupDTO(ItemGroupDTO itemGroupDTO) {
		this.itemGroupDTO = itemGroupDTO;
	}
	public List<ItemGroupDTO> getRows() {
		return rows;
	}
	public void setRows(List<ItemGroupDTO> listItemGroupDTO) {
		this.rows = listItemGroupDTO;
	}
//	public List<ItemGroupDTO> getItems() {
//		return items;
//	}
//	public void setItems(List<ItemGroupDTO> items) {
//		this.items = items;
//	}
}
