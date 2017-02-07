package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;

public class ItemGroupFlagInputMessage extends AdvanzErpBaseInputMessage {
	
	private ItemGroupFlagDTO itemGroupFlagDTO;

	public ItemGroupFlagDTO getItemGroupFlagDTO() {
		return itemGroupFlagDTO;
	}

	public void setItemGroupFlagDTO(ItemGroupFlagDTO itemGroupFlagDTO) {
		this.itemGroupFlagDTO = itemGroupFlagDTO;
	}

}
