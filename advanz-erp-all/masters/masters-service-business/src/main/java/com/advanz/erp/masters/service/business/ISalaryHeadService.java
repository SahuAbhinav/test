package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;

import com.advanz.erp.masters.model.msg.LeaveTypeMastOutputMessage;
import com.advanz.erp.masters.model.msg.SalaryHeadInputMessage;
import com.advanz.erp.masters.model.msg.SalaryHeadOutputMessage;

public interface ISalaryHeadService extends IAdvanzErpBaseSerivce{

	
	public SalaryHeadOutputMessage createSalaryHead(SalaryHeadInputMessage salaryHeadInputMessage);
	
	public SalaryHeadOutputMessage updateSalaryHead(SalaryHeadInputMessage salaryHeadInputMessage);
	
	public SalaryHeadOutputMessage deleteSalaryHead(SalaryHeadInputMessage salaryHeadInputMessage);
	
	public SalaryHeadOutputMessage findSalaryHeadById(SalaryHeadInputMessage salaryHeadInputMessage);
	
	public SalaryHeadOutputMessage findAllSalaryHeads();
	
	public SalaryHeadOutputMessage search(SalaryHeadInputMessage salaryHeadInputMessage);
	
	public SalaryHeadOutputMessage getHeadType();
	public Integer findSalaryHeadByType(String headType);
}
