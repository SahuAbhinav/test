package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.ItemGroupFlagInputMessage;
import com.advanz.erp.masters.model.msg.ItemGroupFlagOutMessage;

public interface IItemGroupFlagService extends IAdvanzErpBaseSerivce{
   
	public ItemGroupFlagOutMessage findAllItemGroupFlag();
	public ItemGroupFlagOutMessage findItemGroupFlagByName(ItemGroupFlagInputMessage flagInputMessage);
	public ItemGroupFlagOutMessage findItemGroupFlagById(ItemGroupFlagInputMessage flagInputMessage);
	
}
