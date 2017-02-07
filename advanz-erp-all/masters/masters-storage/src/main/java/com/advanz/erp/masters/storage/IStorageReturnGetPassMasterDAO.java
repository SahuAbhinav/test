package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.FinishedGoodsDetailEntity;
import com.advanz.erp.masters.entity.jpa.GetPassMasterEntity;
import com.advanz.erp.masters.entity.jpa.GrnDetailEntity;
import com.advanz.erp.masters.entity.jpa.GrnMasterEntity;
import com.advanz.erp.masters.entity.jpa.ReturnGetPassMasterEntity;

public interface IStorageReturnGetPassMasterDAO extends IStorageDAO<ReturnGetPassMasterEntity>{
    public List<ReturnGetPassMasterEntity> load();
    public <E> List<ReturnGetPassMasterEntity> findById(E id);
    public List<ReturnGetPassMasterEntity> search(String returnPassNumber,Date date,String partyName,String itemName);
    public List getNewSeriesNo(String finYear);
   
    public<E> List<ReturnGetPassMasterEntity> findByReturnGetPassNumber(E id);
    List<ReturnGetPassMasterEntity> findForReturnGetPassNumber(Integer returnGetPassAutoId);
    public List<ReturnGetPassMasterEntity> findReturnGetPassPagination(Integer next);

    public List getPendingQty(String passNumber,Integer itemId);
    public List sumPendingQty(String passNumber, Integer itemId);
}
