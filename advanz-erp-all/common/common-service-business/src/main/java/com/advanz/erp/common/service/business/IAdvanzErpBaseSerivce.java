package com.advanz.erp.common.service.business;


public interface IAdvanzErpBaseSerivce {

	public boolean validateInput() ;//throws AdvanzErpValidationException ;
	
	public void performBusinessLogic();// throws AdvanzErpGenericException;
	
	public void formatOutput();// throws AdvanzErpGenericException;
}
