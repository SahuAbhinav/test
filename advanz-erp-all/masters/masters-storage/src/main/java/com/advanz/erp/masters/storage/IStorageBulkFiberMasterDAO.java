package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.BulkFiberMasterEntity;

public interface IStorageBulkFiberMasterDAO extends IStorageDAO<BulkFiberMasterEntity>{
    public List<BulkFiberMasterEntity> load();
    public <E> List<BulkFiberMasterEntity> findById(E id);
	public List<BulkFiberMasterEntity> search(Date fromDate,Date toDate,String runNo,Integer gradeId);
	public List<BulkFiberMasterEntity> findByDateAndRunNo(Date date,String runNo,Integer shiftName);
	public List<BulkFiberMasterEntity> getListWithPagination(Integer index);

}
