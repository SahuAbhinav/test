package com.advanz.erp.masters.service.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.SalaryMasterDTO;
import com.advanz.erp.masters.model.msg.SalaryMasterInputMessage;
import com.advanz.erp.masters.model.msg.SalaryMasterOutputMessage;

public interface ISalaryMasterService extends IAdvanzErpBaseSerivce{

	
	public SalaryMasterOutputMessage createSalaryMaster(SalaryMasterInputMessage salaryMasterInputMessage);
	
	public SalaryMasterOutputMessage updateSalaryMaster(SalaryMasterInputMessage salaryMasterInputMessage);
	
	public SalaryMasterOutputMessage deleteSalaryMaster(SalaryMasterInputMessage salaryMasterInputMessage);
	
	public SalaryMasterOutputMessage findSalaryMasterById(SalaryMasterInputMessage salaryMasterInputMessage);
	public SalaryMasterOutputMessage findAllSalaryMasters();
	public SalaryMasterOutputMessage search(SalaryMasterInputMessage salaryMasterInputMessage);
    public Map getEmployeeList();
    public List<SalaryMasterDTO> getSalaryList();
    public Boolean getSalaryByDepartmentIdAndMonth(Integer deptId, String month);
    public Map getEmployeeListByDepartment(Integer deptId,String mothName);
    public Integer getApprovedFlag(String monthName);
	public Map getEmployeeListByMonthName(String month,Integer year);
}
