package com.advanz.erp.masters.storage;


import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.AnnealingOvenMasterEntity;
import com.advanz.erp.masters.entity.jpa.CapativeConsuptionEntity;

public interface IStorageCapativeConsuptionDAO extends IStorageDAO<CapativeConsuptionEntity>{
	public List<CapativeConsuptionEntity> load();
	public List<CapativeConsuptionEntity> findById(Integer id);
	public List<CapativeConsuptionEntity> search(Date fromDate,String sourceItemCode,String targetItemCode);
	public List<CapativeConsuptionEntity> checkDuplicateEntry(Date date,Integer shift);
	public Integer getNewSeriesNo();
	public List<CapativeConsuptionEntity> FindCapativeConsumptionPagination(Integer next);
}

