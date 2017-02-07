package com.advanz.erp.common.service.template;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;

public class AdvanzErpServiceTemplate implements IAdvanzErpServiceTemplate{

	IAdvanzErpBaseSerivce advanzErpBaseSerivce ;
	
	public void execute(IAdvanzErpBaseSerivce advanzErpBaseSerivce){
		this.advanzErpBaseSerivce = advanzErpBaseSerivce;
		
			if(advanzErpBaseSerivce.validateInput()){
				advanzErpBaseSerivce.performBusinessLogic();
			}
			advanzErpBaseSerivce.formatOutput();
	}
}