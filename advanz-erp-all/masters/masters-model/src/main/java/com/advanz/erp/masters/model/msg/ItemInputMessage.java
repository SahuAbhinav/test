package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.ItemDTO;

@SuppressWarnings("serial")
public class ItemInputMessage extends AdvanzErpBaseInputMessage {
	
	
	boolean deletedFlag;

	public boolean isDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	private ItemDTO itemDTO;
private String itemOperation;
	public ItemDTO getItemDTO() {
		return itemDTO;
	}

	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}

	public String getItemOperation() {
		return itemOperation;
	}

	public void setItemOperation(String itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	

}
