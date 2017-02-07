package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.GrnDetailEntity;
import com.advanz.erp.masters.entity.jpa.PurchaseOrderDetailEntity;
import com.advanz.erp.masters.entity.jpa.PurchaseOrderMasterEntity;
import com.advanz.erp.masters.entity.jpa.SalesOrderMasterEntity;

public interface IStoragePurchaseOrderMasterDAO extends IStorageDAO<PurchaseOrderMasterEntity>{
    public List<PurchaseOrderMasterEntity> load();
    public <E> List<PurchaseOrderMasterEntity> findById(E id);
    public List<PurchaseOrderMasterEntity> search(String purchaseOrderNumber,Date fromDate,Date toDate,String partyName,String itemName);
    public List getNewSeriesNo(String finYear);
    public List<PurchaseOrderDetailEntity> findByItemId(Integer itemId);
    public<E> List<PurchaseOrderMasterEntity> findByPurchaseOrderNumber(E id) ;
    public List<PurchaseOrderMasterEntity> loadPoAccordingGrn();
    public List<PurchaseOrderDetailEntity> findByItemIdAndPo(Integer itemId,Integer poId);

    public <E> List<PurchaseOrderMasterEntity> findBySupplierId(E id);
    
    public List<PurchaseOrderDetailEntity> findByItemIdAndPoNumber(Integer itemId,String poNumber);
    public List findByPurchaseOrderByIndentNumber(String indentNumber);
    
    public List<Double> findPurchaseQtyByItemIdAndIndentNumber(Integer itemId,String indentNumber);
    public List findByPurchaseOrderDetailByIndentNumberAndItemId(String indentNumber,Integer itemId); 
    public List<PurchaseOrderMasterEntity> FindPurchaseOrderPagination(Integer next);
}
