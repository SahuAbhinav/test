package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.ProfessionalTaxInputMessage;
import com.advanz.erp.masters.model.msg.ProfessionalTaxOutputMessage;

public interface IProfessionalTaxService extends IAdvanzErpBaseSerivce
{ 
	public ProfessionalTaxOutputMessage createProfessionalTax(
			ProfessionalTaxInputMessage ProfessionalTaxInputMessage);

	public ProfessionalTaxOutputMessage updateProfessionalTax(
			ProfessionalTaxInputMessage ProfessionalTaxInputMessage);

	public ProfessionalTaxOutputMessage deleteProfessionalTax(
			ProfessionalTaxInputMessage ProfessionalTaxInputMessage);

	public ProfessionalTaxOutputMessage findProfessionalTaxById(
			ProfessionalTaxInputMessage ProfessionalTaxInputMessage);

	public ProfessionalTaxOutputMessage findAllProfessionalTax();
	
	public ProfessionalTaxOutputMessage findProfessionalTax(ProfessionalTaxInputMessage professionalTaxInputMessage);
	
	

}