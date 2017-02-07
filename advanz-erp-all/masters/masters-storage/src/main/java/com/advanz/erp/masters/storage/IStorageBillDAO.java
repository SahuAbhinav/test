package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.BillEntity;

public interface IStorageBillDAO extends IStorageDAO<BillEntity>{
	 	public List<BillEntity> load();
	    public <E> List<BillEntity> findById(Integer invoiceAutoId);
	    public List<BillEntity> getLastByInvoiceId();
	    
	    
	    
	    public <E> List<BillEntity> findByInvoiceSeries(String invoiceSeries);
	    public List<BillEntity> search(Date fromDate,Date toDate,  String salesOrderNumber,String invoiceNumber,String itemName);
	    
	    public List<BillEntity> findByEmployeeId(Integer empId);
	    
	    public List<BillEntity> getMaxInvoiceId();
	    public List<BillEntity> getFinacialYear();
	    public Integer getNewSeriesNo(String finYear);
	    public List<BillEntity> loadInvoicePagination(Integer index);
	    public List getCountInvoiceToEmail(String startDate,String endDate);
	    
	    public String checkDuplicateProformaNumber(String proformaNumber);
}
