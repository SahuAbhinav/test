package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;

public class ItemGroupFlagOutMessage extends AdvanzErpBaseOutputMessage{
	
     private ItemGroupFlagDTO itemGroupFlagDTO;
	
	private List<ItemGroupFlagDTO> itemGroupFlagDTOList;

	public ItemGroupFlagDTO getItemGroupFlagDTO() {
		return itemGroupFlagDTO;
	}

	public void setItemGroupFlagDTO(ItemGroupFlagDTO itemGroupFlagDTO) {
		this.itemGroupFlagDTO = itemGroupFlagDTO;
	}

	public List<ItemGroupFlagDTO> getItemGroupFlagDTOList() {
		return itemGroupFlagDTOList;
	}

	public void setItemGroupFlagDTOList(List<ItemGroupFlagDTO> itemGroupFlagDTOList) {
		this.itemGroupFlagDTOList = itemGroupFlagDTOList;
	}

}
