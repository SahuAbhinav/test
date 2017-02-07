package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.BatchEntity;

public interface IStorageBatchDAO extends IStorageDAO<BatchEntity>{
    public List<BatchEntity> load();
    public <E> List<BatchEntity> findById(E id);
    public List<BatchEntity> getLastByItemId(Integer itemId);
	public<E> List<BatchEntity> findByBatchNo(String batchNo);
	public List<BatchEntity> search(String batchNo, String itemName, String itemCategory);
    public boolean isInUsed(String batchNo);
    public <E> List<BatchEntity> findBySno(Integer sno);
}
