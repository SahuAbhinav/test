package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.AttandanceMasterEntity;
import com.advanz.erp.masters.entity.jpa.BulkFiberMasterEntity;

public interface IStorageAttandanceMasterDAO extends IStorageDAO<AttandanceMasterEntity>{
    public List<AttandanceMasterEntity> load();
    public <E> List<AttandanceMasterEntity> findById(E id);
	public List<AttandanceMasterEntity> search(Date date,String ordrBy);
	public Double coutLeaves(Integer employeeId,String leaveType,Date fromDate,Date toDate);
	public Double countByHalfDay(Integer employeeId,String leaveType,Date fromDate,Date toDate);
	public Double countByFullDay(Integer employeeId,String leaveType,Date fromDate,Date toDate);
	public void deleteAttendaceByEmp(Integer empId,Date date);
	public List<AttandanceMasterEntity> getAttandanceByMonthYearEmpId(String month,int year,int empid);
}
