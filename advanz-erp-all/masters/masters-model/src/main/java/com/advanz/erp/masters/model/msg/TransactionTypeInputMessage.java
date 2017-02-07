package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.TransactionTypeDTO;

public class TransactionTypeInputMessage extends AdvanzErpBaseInputMessage{

	private TransactionTypeDTO transactionTypeDTO;

	public TransactionTypeDTO getTransactionTypeDTO() {
		return transactionTypeDTO;
	}

	public void setTransactionTypeDTO(TransactionTypeDTO transactionTypeDTO) {
		this.transactionTypeDTO = transactionTypeDTO;
	}
	

	
	
	
	
	
}
