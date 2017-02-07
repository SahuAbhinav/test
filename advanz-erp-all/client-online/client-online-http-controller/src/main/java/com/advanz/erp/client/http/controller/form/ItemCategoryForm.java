package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;

public class ItemCategoryForm {
	private ItemCategoryDTO itemCategoryDTO;
	private List<ItemCategoryDTO> rows;
	private String succ;
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public ItemCategoryDTO getItemCategoryDTO() {
		return itemCategoryDTO;
	}
	public void setItemCategoryDTO(ItemCategoryDTO itemCategoryDTO) {
		this.itemCategoryDTO = itemCategoryDTO;
	}
	public List<ItemCategoryDTO> getRows() {
		return rows;
	}
	public void setRows(List<ItemCategoryDTO> rows) {
		this.rows = rows;
	}
	
	
	
}
