package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.BillEntity;
import com.advanz.erp.masters.entity.jpa.DebitDutyMasterEntity;

public interface IStorageDebitDutyMasterDAO extends IStorageDAO<DebitDutyMasterEntity>
{
	public List<DebitDutyMasterEntity> load();
    public <E> List<DebitDutyMasterEntity> findById(E id);
    public List<DebitDutyMasterEntity> search(Date fromDate,Date toDate,Integer approvedStatus);
    public List getNewSeriesNo(String finYear);
    public List<DebitDutyMasterEntity> getMaxDebitId();
    public List<DebitDutyMasterEntity> getLastByDebitDutyId();
    public List<DebitDutyMasterEntity> getFinacialYear();
}
