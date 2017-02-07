package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.StockLedgerEntity;

public interface IStorageStockLedgerDAO extends IStorageDAO<StockLedgerEntity> {
	 public List<StockLedgerEntity> load();
	 public List<StockLedgerEntity> findById(Integer itemId);
	 
	 public List countStockByItemId(Integer itemId);
	 public List<Double> countStockByBatchNo(String batchNo);
	 public List<Double> countStockForItemByItemId(Integer itemId);
	 
	 public List findStockByTransactionId(String transactionNumber);
	 
	  public List<StockLedgerEntity> findByItemId(Integer itemId);
	  //added by kamal on 8-2-13 for remove finishgoods 
	  public List<StockLedgerEntity> deleteByTransactionNumber(String transactionNumber);
	 
	// public List<BillDetailEntity> findByNameAndCode(String areaName,String areaCode);
	//public List<BillDetailEntity> search(String areaName,String areaCode,String regionName);
	  
	  public List ItemCountDateWise(String date);
	  public void createEmailDetal(String emailDetail,Date date,Integer userId);
	  public String getEmailByDate(String date);
	  public void updateEmailDetal(String date,String status);
	  public List getEmailDetal(String date,String status);
	  public List getLastSalesRate(Integer itemId, Date transactionDate,
			String transactionCodeRequired);
}
