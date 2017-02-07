package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.ItemDTO;

@SuppressWarnings("serial")
public class ItemOutMessage extends AdvanzErpBaseOutputMessage {
private ItemDTO itemDTO;
	
	private List<ItemDTO> itemDTOList;

	/**
	 * @return the batchDTO
	 */
	public ItemDTO getItemDTO() {
		return itemDTO;
	}

	/**
	 * @param batchDTO the batchDTO to set
	 */
	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}

	/**
	 * @return the batchDTOList
	 */
	public List<ItemDTO> getItemDTOList() {
		return itemDTOList;
	}

	/**
	 * @param batchDTOList the batchDTOList to set
	 */
	public void setItemDTOList(List<ItemDTO> itemDTOList) {
		this.itemDTOList = itemDTOList;
	}

}
