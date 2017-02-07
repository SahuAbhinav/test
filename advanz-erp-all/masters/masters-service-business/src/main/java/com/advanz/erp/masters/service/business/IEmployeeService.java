package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.EmployeeInputMessage;
import com.advanz.erp.masters.model.msg.EmployeeOutputMessage;

public interface IEmployeeService extends IAdvanzErpBaseSerivce {
	
	public EmployeeOutputMessage createEmployee(
			EmployeeInputMessage employeeInputMessage);

	public EmployeeOutputMessage updateEmployee(
			EmployeeInputMessage employeeInputMessage);

	public EmployeeOutputMessage deleteEmployee(
			EmployeeInputMessage employeeInputMessage);

	public EmployeeOutputMessage findEmployeeById(
			EmployeeInputMessage employeeInputMessage);

	public EmployeeOutputMessage findAllEmployee();
	
	public EmployeeOutputMessage findEmployee(EmployeeInputMessage employeeInputMessage);
	public EmployeeOutputMessage preLoad();
	public EmployeeOutputMessage findAllActivatedEmployee();
	public EmployeeOutputMessage findAllActivatedEmployeeByDeptId(EmployeeInputMessage employeeInputMessage);
	public EmployeeOutputMessage findAllActivatedEmployeeByJoinDate(EmployeeInputMessage employeeInputMessage);
}
