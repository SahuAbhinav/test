package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.BatchInputMessage;
import com.advanz.erp.masters.model.msg.BatchOutMessage;

public interface IBatchService extends IAdvanzErpBaseSerivce{

	
	public BatchOutMessage createBatch(BatchInputMessage batchInputMessage);
	
	public BatchOutMessage updateBatch(BatchInputMessage batchInputMessage);
	
	public BatchOutMessage deleteBatch(BatchInputMessage batchInputMessage);
	
	//public BatchOutMessage findBatchByCriteria(BatchInputMessage batchInputMessage);
	
	public BatchOutMessage findBatchByBatchNo(BatchInputMessage batchInputMessage);
	
	public BatchOutMessage findBatchByBatchItemNo(BatchInputMessage batchInputMessage);
	
	public BatchOutMessage findAllBatchByItemId(BatchInputMessage batchInputMessage);
	
	public BatchOutMessage findBatchById(BatchInputMessage batchInputMessage);
	
	public BatchOutMessage findAllBatches();
	public BatchOutMessage searchBatch(BatchInputMessage batchInputMessage);
	
	public BatchOutMessage checkBeforeRemove(BatchInputMessage batchInputMessage);
	public BatchOutMessage findBySno(BatchInputMessage batchInputMessage);
}
