package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.IndentInputMessage;
import com.advanz.erp.masters.model.msg.IndentOutputMessage;

public interface IIndentMasterService extends IAdvanzErpBaseSerivce{

	
	public IndentOutputMessage createIndentMaster(IndentInputMessage indentInputMessage);
	
	public IndentOutputMessage updateIndentMaster(IndentInputMessage indentInputMessage);
	
	public IndentOutputMessage deleteIndentMaster(IndentInputMessage indentInputMessage);
	
	public IndentOutputMessage findIndentMasterById(IndentInputMessage indentInputMessage);
	public IndentOutputMessage findIndentMasterByIndentNumber(IndentInputMessage indentInputMessage);
	public IndentOutputMessage findAllIndentMasters();
	
	public IndentOutputMessage search(IndentInputMessage indentInputMessage);
	
	public IndentOutputMessage getNewIndentSeriesNo(IndentInputMessage indentInputMessage);
	public IndentOutputMessage findIndentMasterPagination(IndentInputMessage indentInputMessage);
}
