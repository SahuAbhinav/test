package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.GrnMasterDTO;
import com.advanz.erp.masters.model.ItemDTO;

public class GrnMasterForm {
	
	
	private List<GrnMasterDTO> grnMasterList;
	private GrnMasterDTO grnMasterDTO;
	private String succ;
	private String printView;
	private List<ItemDTO> itemList;
	private Integer next;
	private Integer previous;
	private String lastGrnDate;
	
	public String getLastGrnDate() {
		return lastGrnDate;
	}
	public void setLastGrnDate(String lastGrnDate) {
		this.lastGrnDate = lastGrnDate;
	}
	public Integer getNext() {
	return next;
	}
	public void setNext(Integer next) {
	this.next = next;
	}
	public Integer getPrevious() {
	return previous;
	}
	public void setPrevious(Integer previous) {
	this.previous = previous;
	}
	public String getPrintView() {
		return printView;
	}
	public void setPrintView(String printView) {
		this.printView = printView;
	}
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public List<GrnMasterDTO> getGrnMasterList() {
		return grnMasterList;
	}
	public void setGrnMasterList(
			List<GrnMasterDTO> grnMasterList) {
		this.grnMasterList = grnMasterList;
	}
	public GrnMasterDTO getGrnMasterDTO() {
		return grnMasterDTO;
	}
	public void setGrnMasterDTO(GrnMasterDTO grnMasterDTO) {
		this.grnMasterDTO = grnMasterDTO;
	}
	@Override
	public String toString() {
		return "GrnMasterForm [grnMasterDTO="
				+ grnMasterDTO + "]";
	}
	public List<ItemDTO> getItemList() {
		return itemList;
	}
	public void setItemList(List<ItemDTO> itemList) {
		this.itemList = itemList;
	}
	
	
	
}
