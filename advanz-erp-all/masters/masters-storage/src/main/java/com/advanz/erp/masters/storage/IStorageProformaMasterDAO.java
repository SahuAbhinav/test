package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.BillEntity;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.ProformaMasterEntity;

public interface IStorageProformaMasterDAO extends IStorageDAO<ProformaMasterEntity>{
	 	public List<ProformaMasterEntity> load();
	    public <E> List<ProformaMasterEntity> findById(Integer invoiceAutoId);
	    public List<ProformaMasterEntity> getLastByInvoiceId();
	    
	    
	    
	    public <E> List<ProformaMasterEntity> findByInvoiceSeries(String invoiceSeries);
	    public List<ProformaMasterEntity> search(Date fromDate,Date toDate,  String salesOrderNumber,String invoiceNumber,String itemName);
	    
	    public List<ProformaMasterEntity> findByEmployeeId(Integer empId);
	    
	    public List<ProformaMasterEntity> getMaxInvoiceId();
	    public <E> List<ProformaMasterEntity> findByExsiceInvoiceNo(String exciseInvoiceSeries);
	    public List<ProformaMasterEntity> getFinacialYear();
	    
	    public List getNewSeriesNo(String finYear);
	    public List<ProformaMasterEntity> getProformaListWithPagination(Integer index);
}
