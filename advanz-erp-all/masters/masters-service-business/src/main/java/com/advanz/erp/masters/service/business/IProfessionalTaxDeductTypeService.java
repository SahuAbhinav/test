package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.ProfessionalTaxDeductTypeOutputMessage;


public interface IProfessionalTaxDeductTypeService extends IAdvanzErpBaseSerivce {
	public  ProfessionalTaxDeductTypeOutputMessage findAllDeductType();
}
