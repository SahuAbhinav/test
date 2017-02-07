package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.LeaveApplicationEntity;

public interface IStorageLeaveApplicationDAO extends IStorageDAO<LeaveApplicationEntity>{
    public List<LeaveApplicationEntity> load();
    public <E> List<LeaveApplicationEntity> findById(E id);
    public List<LeaveApplicationEntity> findLeaveByEmployeeIdAndDate(Integer employeeId,Date date);

}
