package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;

public interface IItemService extends IAdvanzErpBaseSerivce {
	
	public ItemOutMessage createItem(
			ItemInputMessage itemInputMessage);

	public ItemOutMessage updateItem(
			ItemInputMessage itemInputMessage);

	public ItemOutMessage deleteItem(
			ItemInputMessage itemInputMessage);

	public ItemOutMessage findItemById(
			ItemInputMessage itemInputMessage);

	public ItemOutMessage findAllItem();
	
	public ItemOutMessage findItemsForBatch();
	
	public ItemOutMessage findItem(ItemInputMessage itemInputMessage);

	
	public ItemOutMessage searchFinishedFoodItems(ItemInputMessage itemInputMessage);
	
	public ItemOutMessage  searchMaterialIssuesItems(ItemInputMessage itemInputMessage);
	
	public ItemOutMessage finishGoodItemList();
	
	public ItemOutMessage findItemForReportByGroupName(ItemInputMessage itemInputMessage);
	public ItemOutMessage findItemGroupNameForAllReports(ItemInputMessage itemInputMessage);
	public ItemOutMessage getItemIdAndItemNameList();
	
	public ItemOutMessage findItemForPagination(ItemInputMessage itemInputMessage);
	public ItemOutMessage finishGoodItemListWithPagination(ItemInputMessage itemInputMessage);
	
	public ItemOutMessage getListByItemGroupName(ItemInputMessage itemInputMessage);
	
	public ItemOutMessage findItemListByItemCode(ItemInputMessage itemInputMessage);
	public ItemOutMessage findtemGroupFlagNameByItemCode(ItemInputMessage itemInputMessage);
	public ItemOutMessage getListByItemCategory(ItemInputMessage itemInputMessage);
	public ItemDTO getItemIdAndDencity(ItemDTO itemDTO);
	public ItemOutMessage getItemNameAndId(ItemInputMessage itemInputMessage);
}
