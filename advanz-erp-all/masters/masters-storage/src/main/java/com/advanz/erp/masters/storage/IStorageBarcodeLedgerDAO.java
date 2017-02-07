package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.BarcodeLedgerEntity;

public interface IStorageBarcodeLedgerDAO extends
		IStorageDAO<BarcodeLedgerEntity> {
	
	public BarcodeLedgerEntity createBarcode(BarcodeLedgerEntity entity);
	
	public List<BarcodeLedgerEntity> load();

	public <E> List<BarcodeLedgerEntity> findById(E id);

	public List<BarcodeLedgerEntity> findByTranasactionType(
			String tranasactionType);

	public List<BarcodeLedgerEntity> search(Integer itemId, String barcode,
			String transactionType, Integer transactionId);

}
