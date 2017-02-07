package com.advanz.erp.masters.service.business;
import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.BillDetailInputMessage;
import com.advanz.erp.masters.model.msg.BillDetailOutMessage;


public interface IBillDetailService extends IAdvanzErpBaseSerivce{
	
    public BillDetailOutMessage createBillDetail(BillDetailInputMessage billDetailInputMessage);
	public BillDetailOutMessage updateBillDetail(BillDetailInputMessage billDetailInputMessage);
	public BillDetailOutMessage deleteBillDetail(BillDetailInputMessage billDetailInputMessage);
	//public ItemCategoryOutMessage findItemCategoryByCriteria(ItemCategoryInputMessage itemCategoryInputMessage);
	public BillDetailOutMessage findBillByBillId(BillDetailInputMessage billDetailInputMessage);
	public BillDetailOutMessage findAllBillDetail();
	public BillDetailOutMessage findBillDetail(BillDetailInputMessage billDetailInputMessage);
	public BillDetailOutMessage findBillByBillName(BillDetailInputMessage billDetailInputMessage);
	
	public BillDetailOutMessage findBillByItemId(BillDetailInputMessage billDetailInputMessage);

}
