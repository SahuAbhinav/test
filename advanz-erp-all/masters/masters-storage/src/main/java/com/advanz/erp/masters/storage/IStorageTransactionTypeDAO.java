package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.TransactionTypeEntity;

public interface IStorageTransactionTypeDAO extends IStorageDAO<TransactionTypeEntity>{
	public <E> List<TransactionTypeEntity> findById(E id);
	public  List<TransactionTypeEntity> findByName(String name);
	public  List<TransactionTypeEntity> findBySeries(String series);
	
}
