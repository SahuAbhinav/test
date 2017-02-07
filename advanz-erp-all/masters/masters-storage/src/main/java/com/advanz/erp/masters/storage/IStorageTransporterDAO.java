package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.TransporterEntity;

public interface IStorageTransporterDAO extends IStorageDAO<TransporterEntity>{
	public List<TransporterEntity> load();
    public List<TransporterEntity> findById(Integer uid);
    public List<TransporterEntity> search(String companyName, String companyCity, String ieCode); 
    public List<TransporterEntity> findByName(String companyName);
    public List<TransporterEntity> findByCode(String companyCode);
    public boolean isInUsed(Integer id);
    public List preload();
}
