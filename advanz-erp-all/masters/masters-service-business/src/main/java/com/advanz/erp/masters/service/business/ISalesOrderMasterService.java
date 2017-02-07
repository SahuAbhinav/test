package com.advanz.erp.masters.service.business;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.SalesOrderMasterInputMessage;
import com.advanz.erp.masters.model.msg.SalesOrderMasterOutputMessage;

public interface ISalesOrderMasterService extends IAdvanzErpBaseSerivce{

	
	public SalesOrderMasterOutputMessage createSalesOrderMaster(SalesOrderMasterInputMessage salesOrderMasterInputMessage);
	
	public SalesOrderMasterOutputMessage updateSalesOrderMaster(SalesOrderMasterInputMessage salesOrderMasterInputMessage);
	
	public SalesOrderMasterOutputMessage deleteSalesOrderMaster(SalesOrderMasterInputMessage salesOrderMasterInputMessage);
	
	public SalesOrderMasterOutputMessage findSalesOrderMasterById(SalesOrderMasterInputMessage salesOrderMasterInputMessage);
	
	public SalesOrderMasterOutputMessage findAllSalesOrderMasters();
	
	public SalesOrderMasterOutputMessage search(SalesOrderMasterInputMessage salesOrderMasterInputMessage);
	
	public SalesOrderMasterOutputMessage getNewSalesOrderSeriesNo(SalesOrderMasterInputMessage salesOrderMasterInputMessage);
	
	public SalesOrderMasterOutputMessage findBySalesOrderNo(SalesOrderMasterInputMessage salesOrderMasterInputMessage);
	
	public SalesOrderMasterOutputMessage checkBeforeRemove(SalesOrderMasterInputMessage salesOrderMasterInputMessage);
	public List getSalesDetailToEmail(String date);
	public SalesOrderMasterOutputMessage findSalesOrderMasterPagination(SalesOrderMasterInputMessage salesOrderMasterInputMessage);
	public List getBookedSalesDetailToEmail(String date);
	public Date getMinimumPendingDate();
	
}
