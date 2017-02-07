package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.FinishedGoodsDetailEntity;
import com.advanz.erp.masters.entity.jpa.GrnDetailEntity;
import com.advanz.erp.masters.entity.jpa.GrnMasterEntity;

public interface IStorageGrnMasterDAO extends IStorageDAO<GrnMasterEntity>{
    public List<GrnMasterEntity> load();
    public <E> List<GrnMasterEntity> findById(E id);
    public List<GrnMasterEntity> search(String grnNumber,Date fromDate,Date toDate,String partyName,String itemName,String supplierBillNo);
    public List getNewSeriesNo(String finYear);
    public List<GrnDetailEntity> findByItemId(Integer itemId);
    public List<GrnMasterEntity> findByPoId(Integer poId);
    public<E> List<GrnMasterEntity> findByGrnNumber(E id);
    public Date getMaxDate();
    List<GrnMasterEntity> findForGrnNumber(Integer grnAutoId);
    public List<GrnMasterEntity> findGrnByPoId(Integer poId);
    public List<GrnMasterEntity> findAllPoNo();
    public List getEmailAlertData(String date);
    public List<GrnMasterEntity> FindGrnMasterPagination(Integer next);
    public Double getPuchaseRateByIssueItemId(Integer itemId);
    public List getLastGrnRateForWeightedAvg(Date date ,Integer itemId);
}
