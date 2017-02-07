package com.advanz.erp.masters.storage;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.GrnMasterEntity;
import com.advanz.erp.masters.entity.jpa.SalesOrderDetailEntity;
import com.advanz.erp.masters.entity.jpa.SalesOrderMasterEntity;

public interface IStorageSalesOrderMasterDAO extends IStorageDAO<SalesOrderMasterEntity>{
	public String save(SalesOrderMasterEntity entity);
    public List<SalesOrderMasterEntity> load();
    public <E> List<SalesOrderMasterEntity> findById(E id);
    public List<SalesOrderMasterEntity> search(String salesOrderNumber,Date fromDate,Date toDate,String partyName,String itemName);
    public Integer getNewSeriesNo(String finYear);    
    public  List<SalesOrderMasterEntity> findBySalesOrderNo(String salesOrderNo);
    public boolean isInUsed(Integer id);
    public List<SalesOrderDetailEntity> findByItemId(Integer itemId);
	public<E> List<SalesOrderMasterEntity> findBySalesOrderNumber(E id);
	public<E> List<SalesOrderMasterEntity> findBySalesOrderNo(E id);
	public List getSalesDetailToEmail(String date);
	public List<SalesOrderMasterEntity> findSalesOrderMasterPagination(Integer next);
	public List getBookedSalesDetailToEmail(String date);
	public Date getMinimumPendingDate();
	 public Timestamp getNewSeriesDate(String finYear);  
}
