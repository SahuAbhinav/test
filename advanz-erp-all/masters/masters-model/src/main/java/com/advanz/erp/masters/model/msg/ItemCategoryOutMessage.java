package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.ItemCategoryDTO;

public class ItemCategoryOutMessage extends AdvanzErpBaseOutputMessage{



	private ItemCategoryDTO itemCategoryDTO;
	
	private List<ItemCategoryDTO> itemCategoryDTOList;

	/**
	 * @return the itemCategoryDTO
	 */
	public ItemCategoryDTO getItemCategoryDTO() {
		return itemCategoryDTO;
	}

	/**
	 * @param itemCategoryDTO the itemCategoryDTO to set
	 */
	public void setItemCategoryDTO(ItemCategoryDTO itemCategoryDTO) {
		this.itemCategoryDTO = itemCategoryDTO;
	}

	/**
	 * @return the itemCategoryDTOList
	 */
	public List<ItemCategoryDTO> getItemCategoryDTOList() {
		return itemCategoryDTOList;
	}

	/**
	 * @param itemCategoryDTOList the itemCategoryDTOList to set
	 */
	public void setItemCategoryDTOList(List<ItemCategoryDTO> itemCategoryDTOList) {
		this.itemCategoryDTOList = itemCategoryDTOList;
	}
	
	
}
