package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.SalesOrderInputMessage;
import com.advanz.erp.masters.model.msg.SalesorderOutputMessage;

public interface ISalesOrderService extends IAdvanzErpBaseSerivce{

	
	public SalesorderOutputMessage createSalesOrder(SalesOrderInputMessage salesOrderInputMessage);
	
	public SalesorderOutputMessage updateSalesOrder(SalesOrderInputMessage salesOrderInputMessage);
	
	public SalesorderOutputMessage deleteSalesOrder(SalesOrderInputMessage salesOrderInputMessage);
	
	public SalesorderOutputMessage findSalesOrderById(SalesOrderInputMessage salesOrderInputMessage);
	
	public SalesorderOutputMessage findAllSalesOrder();
	
	public SalesorderOutputMessage search(SalesOrderInputMessage salesOrderInputMessage);

	
}
