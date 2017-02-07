package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.FinishedGoodsDetailEntity;
import com.advanz.erp.masters.entity.jpa.GetPassDetailEntity;
import com.advanz.erp.masters.entity.jpa.GetPassMasterEntity;
import com.advanz.erp.masters.entity.jpa.GrnDetailEntity;
import com.advanz.erp.masters.entity.jpa.GrnMasterEntity;
import com.advanz.erp.masters.entity.jpa.IssueMasterEntity;

public interface IStorageGetPassMasterDAO extends IStorageDAO<GetPassMasterEntity>{
    public List<GetPassMasterEntity> load();
    public <E> List<GetPassMasterEntity> findById(E id);
    public List<GetPassMasterEntity> search(String passNumber,Date date,String partyName,String itemName,String gatePassType,Date toDate);
    public List getNewSeriesNo(String finYear);
   
    public<E> List<GetPassMasterEntity> findByGetPassNumber(E id);
    List<GetPassMasterEntity> findForGetPassNumber(Integer getPassAutoId);
    public List<GetPassDetailEntity> findByPassNo(String getPassAutoNo);

    public List<GetPassMasterEntity> findByGatePassType(String gatePassType,Integer next);
    public List<GetPassMasterEntity> findGatePassPagination(Integer next);
    
    public GetPassDetailEntity updateGatePassDetail(GetPassDetailEntity entity);
    public List<GetPassDetailEntity> findByGatePassNoAndItemId(String gatePassNumber, Integer itemId);
}
