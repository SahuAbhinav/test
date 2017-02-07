package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.BulkFiberMasterInputMessage;
import com.advanz.erp.masters.model.msg.BulkFiberMasterOutputMessage;

public interface IBulkFiberMasterService extends IAdvanzErpBaseSerivce{	
	public BulkFiberMasterOutputMessage createBulkFiberMaster(BulkFiberMasterInputMessage bulkFiberMasterInputMessage);
	
	public BulkFiberMasterOutputMessage updateBulkFiberMaster(BulkFiberMasterInputMessage bulkFiberMasterInputMessage);
	
	public BulkFiberMasterOutputMessage deleteBulkFiberMaster(BulkFiberMasterInputMessage bulkFiberMasterInputMessage);
	
	public BulkFiberMasterOutputMessage findBulkFiberMasterById(BulkFiberMasterInputMessage bulkFiberMasterInputMessage);
	
	public BulkFiberMasterOutputMessage findAllBulkFiberMasters();
	
	public BulkFiberMasterOutputMessage search(BulkFiberMasterInputMessage bulkFiberMasterInputMessage);
	public BulkFiberMasterOutputMessage getListWithPagination(BulkFiberMasterInputMessage bulkFiberMasterInputMessage);
}
