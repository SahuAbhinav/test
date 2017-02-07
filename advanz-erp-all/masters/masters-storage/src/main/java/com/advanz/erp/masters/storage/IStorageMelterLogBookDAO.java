package com.advanz.erp.masters.storage;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.MelterLogBookEntity;

public interface IStorageMelterLogBookDAO extends IStorageDAO<MelterLogBookEntity>
{
  List<MelterLogBookEntity> load();
  List<MelterLogBookEntity> search(Date fromDate,Date toDate,String runNo,String operator);
  List<MelterLogBookEntity> findById(Integer sno);
  List<MelterLogBookEntity> findByDateRunNoShiftTime(Date logDate,String runNo,Integer masterId,Time logTime);
  List getLastRecordDate();
}
