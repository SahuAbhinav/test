package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.ItemCategoryDTO;


@SuppressWarnings("serial")
public class ItemCategoryInputMessage extends AdvanzErpBaseInputMessage{

	private ItemCategoryDTO itemCategoryDTO;

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
	

}
