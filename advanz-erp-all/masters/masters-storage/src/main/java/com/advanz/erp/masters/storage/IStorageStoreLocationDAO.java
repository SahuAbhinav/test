package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.StoreLocationEntity;

public interface IStorageStoreLocationDAO extends IStorageDAO<StoreLocationEntity>{
	public List<StoreLocationEntity> load();
    public List<StoreLocationEntity> findById(Integer uid);
    public List<StoreLocationEntity> search(String storeLocation, String locationCode); 
    public List<StoreLocationEntity> findByName(String storeLocation);
    public List<StoreLocationEntity> findByCode(String locationCode);
    public boolean isInUsed(Integer id);
}
