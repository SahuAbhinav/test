package com.advanz.erp.masters.service.business;
import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.ProformaDetailInputMessage;
import com.advanz.erp.masters.model.msg.ProformaDetailOutMessage;


public interface IProformaDetailService extends IAdvanzErpBaseSerivce{
	
    public ProformaDetailOutMessage createBillDetail(ProformaDetailInputMessage proformaDetailInputMessage);
	public ProformaDetailOutMessage updateBillDetail(ProformaDetailInputMessage proformaDetailInputMessage);
	public ProformaDetailOutMessage deleteBillDetail(ProformaDetailInputMessage proformaDetailInputMessage);
	//public ItemCategoryOutMessage findItemCategoryByCriteria(ItemCategoryInputMessage itemCategoryInputMessage);
	public ProformaDetailOutMessage findBillByBillId(ProformaDetailInputMessage proformaDetailInputMessage);
	public ProformaDetailOutMessage findAllBillDetail();
	public ProformaDetailOutMessage findBillDetail(ProformaDetailInputMessage proformaDetailInputMessage);
	public ProformaDetailOutMessage findBillByBillName(ProformaDetailInputMessage proformaDetailInputMessage);
	
	public ProformaDetailOutMessage findBillByItemId(ProformaDetailInputMessage proformaDetailInputMessage);

}
