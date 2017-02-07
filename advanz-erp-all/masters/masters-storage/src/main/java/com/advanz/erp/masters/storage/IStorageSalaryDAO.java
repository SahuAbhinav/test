package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.SalaryDetailEntity;
import com.advanz.erp.masters.entity.jpa.SalaryLeaveEntity;
import com.advanz.erp.masters.entity.jpa.SalaryMasterEntity;


public interface IStorageSalaryDAO extends IStorageDAO<SalaryMasterEntity>{
    public List<SalaryMasterEntity> load();
    public <E> List<SalaryMasterEntity> findById(E id);
    public List<SalaryMasterEntity> search(Date fromDate,Date toDate,String employeeName);
	public<E> List<SalaryMasterEntity> findBySalayrNumber(E id);
	public List getSalaryList();
	public List getSalaryByDepartmentIdAndMonth(Integer deptId,String month);
	public<E> List getAdvanceAmount(E id);
	public<E> List getAdvanceAmountByMonthNameAndDeartment(String monthName,Integer departmentId);
	public Double getHeadAmount(Integer transactionAutoId,Integer headId,Integer employeeId);

	public<E> List getNetSalary(E id);
	public Integer getApprovedFlag(String monthName);
	//List<SalaryMasterEntity> findByMonthEmpIdLeaveId(String monthName,Integer empId,Integer leaveId);
	List<SalaryLeaveEntity> findByMonthEmpIdLeaveId(String monthName,Integer empId,Integer leaveId);
	
}

