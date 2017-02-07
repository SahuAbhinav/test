package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.PurchaseOrderMasterDTO;

@SuppressWarnings("serial")
public class PurchaseOrderMasterInputMessage extends AdvanzErpBaseInputMessage {

	private PurchaseOrderMasterDTO purchaseOrderMasterDTO;

	private boolean deletedFlag;
    private Integer itemId;
    private Integer next;
	public boolean isDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	// private PurchaseOrderMasterSearchCriteriaDTO searchCriteria;

	public PurchaseOrderMasterDTO getPurchaseOrderMasterDTO() {
		return purchaseOrderMasterDTO;
	}

	public void setPurchaseOrderMasterDTO(
			PurchaseOrderMasterDTO purchaseOrderMasterDTO) {
		this.purchaseOrderMasterDTO = purchaseOrderMasterDTO;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}

}
