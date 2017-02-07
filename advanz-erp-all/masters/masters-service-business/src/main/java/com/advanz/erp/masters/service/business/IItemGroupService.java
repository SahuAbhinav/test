package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.ItemGroupInputMessage;
import com.advanz.erp.masters.model.msg.ItemGroupOutMessage;

public interface IItemGroupService extends IAdvanzErpBaseSerivce {

	public ItemGroupOutMessage createItemGroup(
			ItemGroupInputMessage itemGroupInputMessage);

	public ItemGroupOutMessage updateItemGroup(
			ItemGroupInputMessage itemGroupInputMessage);

	public ItemGroupOutMessage deleteItemGroup(
			ItemGroupInputMessage itemGroupInputMessage);

	public ItemGroupOutMessage findItemGroupById(
			ItemGroupInputMessage itemGroupInputMessage);

	public ItemGroupOutMessage findAllItemGroup();
	
	public ItemGroupOutMessage findItemGroup(ItemGroupInputMessage itemGroupInputMessage);

	public ItemGroupOutMessage findItemGroupForItemCategory();
	
	public ItemGroupOutMessage findItemGroupByFlagId(ItemGroupInputMessage itemGroupInputMessage);
}
