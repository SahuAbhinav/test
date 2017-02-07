package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.ItemGroupDTO;

@SuppressWarnings("serial")
public class ItemGroupOutMessage extends AdvanzErpBaseOutputMessage {
private ItemGroupDTO itemGroupDTO;
	
	private List<ItemGroupDTO> itemGroupDTOList;

	/**
	 * @return the batchDTO
	 */
	public ItemGroupDTO getItemGroupDTO() {
		return itemGroupDTO;
	}

	/**
	 * @param batchDTO the batchDTO to set
	 */
	public void setItemGroupDTO(ItemGroupDTO itemGroupDTO) {
		this.itemGroupDTO = itemGroupDTO;
	}

	/**
	 * @return the batchDTOList
	 */
	public List<ItemGroupDTO> getItemGroupDTOList() {
		return itemGroupDTOList;
	}

	/**
	 * @param batchDTOList the batchDTOList to set
	 */
	public void setItemGroupDTOList(List<ItemGroupDTO> itemGroupDTOList) {
		this.itemGroupDTOList = itemGroupDTOList;
	}

}
