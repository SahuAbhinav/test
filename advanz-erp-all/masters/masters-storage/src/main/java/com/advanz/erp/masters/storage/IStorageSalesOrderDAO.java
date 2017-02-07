package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.SalesOrderEntity;
import com.advanz.erp.masters.entity.jpa.StateEntity;

public interface IStorageSalesOrderDAO extends IStorageDAO<SalesOrderEntity>{
	 public List<SalesOrderEntity> load();
	 public List<SalesOrderEntity> search(String salesOrderNumber, Date salesOrderDate, Integer partyId);
	 
}
