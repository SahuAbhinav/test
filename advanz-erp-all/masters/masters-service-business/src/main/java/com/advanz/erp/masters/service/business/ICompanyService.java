package com.advanz.erp.masters.service.business;

import java.util.Date;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.BlanketWeightRecordDTO;
import com.advanz.erp.masters.model.LoggerDTO;
import com.advanz.erp.masters.model.msg.CompanyInputMessage;
import com.advanz.erp.masters.model.msg.CompanyOutMessage;

public interface ICompanyService extends IAdvanzErpBaseSerivce{

	
	public CompanyOutMessage createCompany(CompanyInputMessage companyInputMessage);
	
	public CompanyOutMessage updateCompany(CompanyInputMessage companyInputMessage);
	
	public CompanyOutMessage deleteCompany(CompanyInputMessage companyInputMessage);
	
	public CompanyOutMessage findCompanyById(CompanyInputMessage companyInputMessage);
	
	public CompanyOutMessage findAllCompanies();
	
	public CompanyOutMessage findCompany(CompanyInputMessage companyInputMessage);
	public Boolean getEmailFlag();
	public void sendEmail(Integer userId,Date date);
	public Date getSalaryGenaratingDate();
	public void createBlanketWeightRecord(BlanketWeightRecordDTO blanketWeightRecordDTO);
	public void createLoggerRecord(LoggerDTO loggerDTO);
	public void checkSendEmail(Integer userId,Date date);
	
}
