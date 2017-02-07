package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.CompanyDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;

@SuppressWarnings("serial")
public class ItemGroupInputMessage extends AdvanzErpBaseInputMessage {

	private ItemGroupDTO itemGroupDTO;
	
	private boolean deleteFlag;

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public ItemGroupDTO getItemGroupDTO() {
		return itemGroupDTO;
	}

	public void setItemGroupDTO(ItemGroupDTO itemGroupDTO) {
		this.itemGroupDTO = itemGroupDTO;
	}
	
	

}
