package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.GrnDetailEntity;
import com.advanz.erp.masters.entity.jpa.QuotationDetailEntity;
import com.advanz.erp.masters.entity.jpa.QuotationMasterEntity;

public interface IStorageQuotationMasterDAO extends IStorageDAO<QuotationMasterEntity>{
    public List<QuotationMasterEntity> load();
    public <E> List<QuotationMasterEntity> findById(E id);
    public List<QuotationMasterEntity> search(String quotationNumber,Date fromDate,Date toDate,String partyName,String itemName);
    public List getNewSeriesNo(String finYear);
    public boolean isInUsed(Integer id);
    public List<QuotationDetailEntity> findByItemId(Integer itemId);
	public<E> List<QuotationMasterEntity> findByQutationNumber(E id);
	public List<QuotationMasterEntity> FindQuotationPagination(Integer next);
}
