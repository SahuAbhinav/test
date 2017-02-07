package com.advanz.erp.masters.storage;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.MelterLogSummaryEntity;


public interface IStorageMelterLogSummaryDAO extends IStorageDAO<MelterLogSummaryEntity> 
{
  public List<MelterLogSummaryEntity> load();
  public List<MelterLogSummaryEntity> findBySno(Integer sno);
  public List<MelterLogSummaryEntity> searchMelterSummaryRecordByDate(Date fromDate,Date toDate);
  public List<MelterLogSummaryEntity> checkDuplicateSummary(Date logDate,Time logTime);
  public Timestamp getLastMelterLogSummaryDate();

}

