package com.advanz.erp.masters.storage;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.MelterTrollyLogEntity;

public interface IStorageMelterTrollyLogDAO extends IStorageDAO<MelterTrollyLogEntity>
{
  public List<MelterTrollyLogEntity> load();
  public List<MelterTrollyLogEntity> search(Date fromDate,Date toDate,String trollyNO);
  public List<MelterTrollyLogEntity>  findBySno(Integer sno);
  public List<MelterTrollyLogEntity> checkDuplicateTrolly(Date logDate,Time logTime,String trollyNo);
  }
