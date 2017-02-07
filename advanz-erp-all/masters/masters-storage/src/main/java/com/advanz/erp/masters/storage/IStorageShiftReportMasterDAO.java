package com.advanz.erp.masters.storage;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.IssueReturnMasterEntity;
import com.advanz.erp.masters.entity.jpa.ShiftConsumedDetailEntity;
import com.advanz.erp.masters.entity.jpa.ShiftEngInterruptionDetailEntity;
import com.advanz.erp.masters.entity.jpa.ShiftReportMasterEntity;
import com.advanz.erp.masters.entity.jpa.ShiftSpinInterruptionDetailEntity;


public interface IStorageShiftReportMasterDAO extends IStorageDAO<ShiftReportMasterEntity>{
    public List<ShiftReportMasterEntity> load();
    public <E> List<ShiftReportMasterEntity> findById(E id);
    public List<ShiftReportMasterEntity> search(String runNo,Date fromDate,Date toDate);
  //public Integer getNewSeriesNo(String finYear);
     public void deleteShiftEng(ShiftEngInterruptionDetailEntity entity);

    public void deleteShiftSpin(ShiftSpinInterruptionDetailEntity entity);
    public void deleteConsumedDetail(ShiftConsumedDetailEntity entity);
    public List<ShiftReportMasterEntity> getDuplicateCheckList();
    public List<ShiftReportMasterEntity> checkDuplicateEntry(Date date,String runNo, Integer shiftId);
    
    public List<ShiftReportMasterEntity> findIShiftReportPagination(Integer next);
    public Timestamp getLastShiftDate();
}
