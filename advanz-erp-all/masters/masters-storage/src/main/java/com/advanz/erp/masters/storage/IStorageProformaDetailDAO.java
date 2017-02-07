package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.BillDetailEntity;
import com.advanz.erp.masters.entity.jpa.ProformaDetailEntity;

public interface IStorageProformaDetailDAO extends IStorageDAO<ProformaDetailEntity> {
	 public List<ProformaDetailEntity> load();
	 public List<ProformaDetailEntity> findById(String invoiceNumber);
	 public List<ProformaDetailEntity> findByItemId(Integer itemId);
	// public List<BillDetailEntity> findByNameAndCode(String areaName,String areaCode);
	 //public List<BillDetailEntity> search(String areaName,String areaCode,String regionName);
}
