package com.advanz.erp.masters.storage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.BlanketProductionDetailLeftEntity;
import com.advanz.erp.masters.entity.jpa.BlanketProductionDetailRightEntity;
import com.advanz.erp.masters.entity.jpa.BlanketProductionMasterEntity;
import com.advanz.erp.masters.entity.jpa.BlanketWeightRecordEntity;
import com.advanz.erp.masters.entity.jpa.IssueReturnMasterEntity;

public interface IStorageBlanketProductionMasterDAO extends IStorageDAO<BlanketProductionMasterEntity>{
    public List<BlanketProductionMasterEntity> load();
    public <E> List<BlanketProductionMasterEntity> findById(E id);
	public List<BlanketProductionMasterEntity> search(Date fromDate,Date toDate,String runNo,Integer gradeId);
	public List<BlanketProductionMasterEntity> findByDateAndRunNo(Date date,String runNo,Integer shiftName,String batchNo,Integer status);
    public void createLeftBlanketProduction(BlanketProductionDetailLeftEntity blanketProductionDetailLeftEntity); 
    public void createRightBlanketProduction(BlanketProductionDetailRightEntity blanketProductionDetailRightEntity);
   
    public Boolean deleteLeftBlanketProduction(BlanketProductionDetailLeftEntity blanketProductionDetailLeftEntity);
    public Boolean deleteRightBlanketProduction(BlanketProductionDetailRightEntity blanketProductionDetailRightEntity);
    
    
    public Integer getMaxBlanketProdId();
    public ArrayList<Integer> getMaxRollNoL(String batchNo);
    public ArrayList<Integer> getMaxRollNoR(String batchNo);
    
    
    public List getBlanketProductionLeft(Date date,Integer shiftId);
    public List getBlanketProductionRight(Date date,Integer shiftId);
    
    public void updateBlanketProductionLeft(BlanketProductionDetailLeftEntity leftEntity,String flag);
    public void updateBlanketProductionRight(BlanketProductionDetailRightEntity rightEntity,String flag);

    public List<BlanketProductionDetailLeftEntity> getRejBlanketProductionLeft(String status,Date date,Integer shiftId);
    public List<BlanketProductionDetailRightEntity> getRejBlanketProductionRight(String status,Date date,Integer shiftId);

    public List<BlanketProductionMasterEntity> findBlanketProductionPagination(Integer next);
    
    public List<BlanketProductionMasterEntity> getDataForShiftReport(Date date,String runNo,Integer shiftName);
    
    public List getCheckDuplicatRollNoL(String batchNo,Integer rollNoL);
    public List getCheckDuplicatRollNoR(String batchNo,Integer rollNoR);
    
    public void updateBlanket(Date date,String runNo,Integer shiftName,Integer status);
    public Boolean checkFinishedGood(String finishedGoodNumber);
    public void cleanDuplicateEntry();
    public Double getBlanketWeightRecord(Character position,Integer createdUserId) throws Exception;
    public List getLastBlanketEntryDate();
}
