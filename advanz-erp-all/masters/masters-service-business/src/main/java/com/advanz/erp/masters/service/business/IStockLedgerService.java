package com.advanz.erp.masters.service.business;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.StockLedgerInputMessage;
import com.advanz.erp.masters.model.msg.StockLedgerOutMessage;

public interface IStockLedgerService extends IAdvanzErpBaseSerivce{
	
public StockLedgerOutMessage createStockLedger(StockLedgerInputMessage stockLedgerInputMessage);
	
	public StockLedgerOutMessage updateStockLedger(StockLedgerInputMessage stockLedgerInputMessage);
	
	public StockLedgerOutMessage deleteStockLedger(StockLedgerInputMessage stockLedgerInputMessage);
	
	//public ItemCategoryOutMessage findItemCategoryByCriteria(ItemCategoryInputMessage itemCategoryInputMessage);
	
	public StockLedgerOutMessage findStockLedgerByItemId(StockLedgerInputMessage stockLedgerInputMessage);
	
	public StockLedgerOutMessage findAllStockLedger();
	
	public StockLedgerOutMessage findStockLedger(StockLedgerInputMessage stockLedgerInputMessage);
	public StockLedgerOutMessage findStockLedgerByName(StockLedgerInputMessage stockLedgerInputMessage);
	
	public StockLedgerOutMessage countStockByItemId(StockLedgerInputMessage stockLedgerInputMessage);
	public StockLedgerOutMessage countStockByBatchNo(StockLedgerInputMessage stockLedgerInputMessage);

	public StockLedgerOutMessage findStockLedgerByTransactionId(StockLedgerInputMessage stockLedgerInputMessage);
	 //added by kamal on 8-2-13 for remove finishgoods 
	public StockLedgerOutMessage deleteByTransactionNumber(StockLedgerInputMessage stockLedgerInputMessage);

    public StockLedgerOutMessage findQuantityByItemId(StockLedgerInputMessage stockLedgerInputMessage);
    
    public List ItemCountDateWise(String date);
    public void createEmailDetal(String emailDetail, Date date,Integer userId);
    public String getEmailByDate(String date);
    public void updateEmailDetal(String date,String status);
    public List getEmailDetal(String date,String status);
}
