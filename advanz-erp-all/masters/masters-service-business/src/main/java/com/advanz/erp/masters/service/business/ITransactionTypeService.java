package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.TransactionTypeInputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeOutputMessage;

public interface ITransactionTypeService extends IAdvanzErpBaseSerivce{
	

	public TransactionTypeOutputMessage findTransactionTypeById(TransactionTypeInputMessage transactionTypeInputMessage);
	
	public TransactionTypeOutputMessage findTransactionTypeByName(TransactionTypeInputMessage transactionTypeInputMessage);
	
	public TransactionTypeOutputMessage findTransactionTypeBySeries(TransactionTypeInputMessage transactionTypeInputMessage);
}
