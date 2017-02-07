package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.IndentDetailEntity;
import com.advanz.erp.masters.entity.jpa.IndentMasterEntity;


public interface IStorageIndentDAO extends IStorageDAO<IndentMasterEntity>{
    public List<IndentMasterEntity> load();
    public <E> List<IndentMasterEntity> findById(E id);
    public List<IndentMasterEntity> search(String indentNumber,Date fromDate,Date toDate,String itemName,String raisedBy,String deoatementName);
    public List getNewSeriesNo(String finYear);
    
	public<E> List<IndentMasterEntity> findByIndentNumber(E id);
	public List<IndentMasterEntity> findIndentNumber(Integer issueAutoId);
	public List<IndentMasterEntity> findIndentPagination(Integer next);
	
	public List<IndentDetailEntity> updatePendingQtyInIndentDetail(Integer itemId,String indentNumber,Double quantity,String operation);
}
