package com.advanz.erp.masters.service.business;
import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.DispatchDetailInputMessage;
import com.advanz.erp.masters.model.msg.DispatchDetailOutMessage;


public interface IDispatchDetailService extends IAdvanzErpBaseSerivce{
	
    public DispatchDetailOutMessage createDispatchDetail(DispatchDetailInputMessage dispatchDetailInputMessage);
	public DispatchDetailOutMessage updateDispatchDetail(DispatchDetailInputMessage dispatchDetailInputMessage);
	public DispatchDetailOutMessage deleteDispatchDetail(DispatchDetailInputMessage dispatchDetailInputMessage);
	//public ItemCategoryOutMessage findItemCategoryByCriteria(ItemCategoryInputMessage itemCategoryInputMessage);
	public DispatchDetailOutMessage findById(DispatchDetailInputMessage dispatchDetailInputMessage);
	public DispatchDetailOutMessage findAllDispatchDetail();
	public DispatchDetailOutMessage findDispatchDetail(DispatchDetailInputMessage dispatchDetailInputMessage);
	public DispatchDetailOutMessage findDispatchByBillName(DispatchDetailInputMessage dispatchDetailInputMessage);
   
	public DispatchDetailOutMessage findByDispatchId(DispatchDetailInputMessage dispatchDetailInputMessage);
}
