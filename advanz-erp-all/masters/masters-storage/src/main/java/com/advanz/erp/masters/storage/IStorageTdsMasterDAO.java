package com.advanz.erp.masters.storage;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.TdsMasterEntity;

public interface IStorageTdsMasterDAO  extends IStorageDAO<TdsMasterEntity>{
	    public List<TdsMasterEntity> load();
	    public  List<TdsMasterEntity> findById(Integer id);
	    public List<TdsMasterEntity> findByCode(String tdsCode);
	    public List<TdsMasterEntity> findBySlabName(String slabName);
	}
