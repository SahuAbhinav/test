package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.ItemCategoryInputMessage;
import com.advanz.erp.masters.model.msg.ItemCategoryOutMessage;

public interface IItemCategoryService extends IAdvanzErpBaseSerivce{

	
	public ItemCategoryOutMessage createItemCategory(ItemCategoryInputMessage itemCategoryInputMessage);
	
	public ItemCategoryOutMessage updateItemCategory(ItemCategoryInputMessage itemCategoryInputMessage);
	
	public ItemCategoryOutMessage deleteItemCategory(ItemCategoryInputMessage itemCategoryInputMessage);
	
	//public ItemCategoryOutMessage findItemCategoryByCriteria(ItemCategoryInputMessage itemCategoryInputMessage);
	
	public ItemCategoryOutMessage findItemCategoryByItemCategoryId(ItemCategoryInputMessage itemCategoryInputMessage);
	
	public ItemCategoryOutMessage findAllItemCategories();
	
	public ItemCategoryOutMessage findItemCategory(ItemCategoryInputMessage itemCategoryInputMessage);
	public ItemCategoryOutMessage findItemCategoryByItemCategoryName(ItemCategoryInputMessage itemCategoryInputMessage);
	
	public ItemCategoryOutMessage checkBeforeRemove(ItemCategoryInputMessage ItemCategoryInputMessage);
	
	public ItemCategoryOutMessage findItemCategoryByItemGroup(ItemCategoryInputMessage ItemCategoryInputMessage);

}
