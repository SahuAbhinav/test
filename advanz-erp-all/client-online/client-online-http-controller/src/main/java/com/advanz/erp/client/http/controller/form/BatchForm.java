package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.ItemDTO;

public class BatchForm {
	private List<ItemDTO> items;
	private BatchDTO batchDTO;
	private Integer itemId;
	private String succ;
	private Integer id;
	private List<ItemDTO> selectItemList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	private int indexNo;
	public int getIndexNo() {
		return indexNo;
	}
	public void setIndexNo(int indexNo) {
		this.indexNo = indexNo;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	private List<BatchDTO> rows;
	public BatchDTO getBatchDTO() {
		return batchDTO;
	}
	public void setBatchDTO(BatchDTO batchDTO) {
		this.batchDTO = batchDTO;
	}
	public List<BatchDTO> getRows() {
		return rows;
	}
	public void setRows(List<BatchDTO> listBatch) {
		this.rows = listBatch;
	}
	public List<ItemDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}
	public List<ItemDTO> getSelectItemList() {
		return selectItemList;
	}
	public void setSelectItemList(List<ItemDTO> selectItemList) {
		this.selectItemList = selectItemList;
	}
	
	
	
}
