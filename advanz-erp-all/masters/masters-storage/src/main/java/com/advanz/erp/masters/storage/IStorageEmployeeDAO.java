package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.EmployeeEntity;
import com.advanz.erp.masters.entity.jpa.EmployeeLeavesEntity;
import com.advanz.erp.masters.entity.jpa.EmployeeSalaryDetEntity;

public interface IStorageEmployeeDAO extends IStorageDAO<EmployeeEntity>{
    public List<EmployeeEntity> load();
    public List<EmployeeEntity> findById(Integer id);
    public List<EmployeeEntity> search(String employeeName,String employeeCode,String employeeCity , int pmFlag);
    public List<EmployeeLeavesEntity> findLeaveByEmployeeId(Integer empID);
    public List<EmployeeSalaryDetEntity> findSalaryByEmployeeId(Integer empID);
    public List preLoad();
    public List<EmployeeEntity> findAllActivatedEmployee();
    public List<EmployeeEntity> findAllActivatedEmployeeByDeptId(Integer deptId);
    public List<EmployeeEntity> findAllActivatedEmployeeByJoinDate(String joinDate);
}
