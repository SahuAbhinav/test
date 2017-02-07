package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.PurchaseOrderMasterDTO;

public class PurchaseOrderMasterForm {
	private Integer next;
	private Integer previous;
	private String succ;
	private String lastPurchaseOrderDate;

	public String getLastPurchaseOrderDate() {
		return lastPurchaseOrderDate;
	}

	public void setLastPurchaseOrderDate(String lastPurchaseOrderDate) {
		this.lastPurchaseOrderDate = lastPurchaseOrderDate;
	}

	public String getSucc() {
		return succ;
	}

	public void setSucc(String succ) {
		this.succ = succ;
	}

	private List<PurchaseOrderMasterDTO> purchaseOrderMasterList;
	private PurchaseOrderMasterDTO purchaseOrderMasterDTO;

	public List<PurchaseOrderMasterDTO> getPurchaseOrderMasterList() {
		return purchaseOrderMasterList;
	}

	public void setPurchaseOrderMasterList(
			List<PurchaseOrderMasterDTO> purchaseOrderMasterList) {
		this.purchaseOrderMasterList = purchaseOrderMasterList;
	}

	public PurchaseOrderMasterDTO getPurchaseOrderMasterDTO() {
		return purchaseOrderMasterDTO;
	}

	public void setPurchaseOrderMasterDTO(
			PurchaseOrderMasterDTO purchaseOrderMasterDTO) {
		this.purchaseOrderMasterDTO = purchaseOrderMasterDTO;
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

	@Override
	public String toString() {
		return "PurchaseOrderMasterForm [purchaseOrderMasterDTO="
				+ purchaseOrderMasterDTO + "]";
	}

}
