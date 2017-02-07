package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.BillDetailEntity;

public interface IStorageBillDetailDAO extends IStorageDAO<BillDetailEntity> {
	 public List<BillDetailEntity> load();
	 public List<BillDetailEntity> findById(String invoiceNumber);
	 public List<BillDetailEntity> findByItemId(Integer itemId);
	// public List<BillDetailEntity> findByNameAndCode(String areaName,String areaCode);
	 //public List<BillDetailEntity> search(String areaName,String areaCode,String regionName);
}
