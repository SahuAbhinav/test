package com.advanz.erp.masters.storage;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.AnnealingOvenMasterEntity;

public interface IStorageAnnealingOvenDAO extends IStorageDAO<AnnealingOvenMasterEntity>{
	public List<AnnealingOvenMasterEntity> load();
	public List<AnnealingOvenMasterEntity> findById(Integer id);
	public List<AnnealingOvenMasterEntity> search(Date fromDate,Date toDate,String shift);
	public List<AnnealingOvenMasterEntity> checkDuplicateEntry(Date date,Integer shift);
	public Timestamp lastAnnealingOvenDate(); 
	}

