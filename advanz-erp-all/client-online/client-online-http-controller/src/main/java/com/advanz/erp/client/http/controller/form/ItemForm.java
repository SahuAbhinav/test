package com.advanz.erp.client.http.controller.form;

import java.util.ArrayList;
import java.util.List;

import com.advanz.erp.masters.model.ItemDTO;

public class ItemForm {
	//private List<ItemDTO> items;
	private ItemDTO itemDTO;
	private List<ItemDTO> rows;
	private ArrayList<ItemDTO> itemList = null ;
	private Integer uid;
	private Integer itemId;
	private String succ;
	
	private String batchFlag;
	
	public String getBatchFlag() {
		return batchFlag;
	}
	public void setBatchFlag(String batchFlag) {
		this.batchFlag = batchFlag;
	}
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public ArrayList<ItemDTO> getItemList() {
		return itemList;
	}
	public void setItemList(ArrayList<ItemDTO> itemList) {
		this.itemList = itemList;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public ItemDTO getItemDTO() {
		return itemDTO;
	}
	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}
	public List<ItemDTO> getRows() {
		return rows;
	}
	public void setRows(List<ItemDTO> listItemDTO) {
		this.rows = listItemDTO;
	}
//	public List<ItemDTO> getItems() {
//		return items;
//	}
//	public void setItems(List<ItemDTO> items) {
//		this.items = items;
//	}
}
