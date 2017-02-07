package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.StoreLocationInputMessage;
import com.advanz.erp.masters.model.msg.StoreLocationOutMessage;

public interface IStoreLocationService extends IAdvanzErpBaseSerivce {
public StoreLocationOutMessage createStoreLocation(StoreLocationInputMessage storeLocationInputMessage);
	
	public StoreLocationOutMessage updateStoreLocation(StoreLocationInputMessage storeLocationInputMessage);
	
	public StoreLocationOutMessage deleteStoreLocation(StoreLocationInputMessage storeLocationInputMessage);
	
	public StoreLocationOutMessage findStoreLocationById(StoreLocationInputMessage storeLocationInputMessage);
	
	public StoreLocationOutMessage findAllStoreLocations();
	
	public StoreLocationOutMessage findStoreLocation(StoreLocationInputMessage storeLocationInputMessage);
	public StoreLocationOutMessage checkBeforeRemove(StoreLocationInputMessage StoreLocationInputMessage);
}
