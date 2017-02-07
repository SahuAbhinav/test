package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.TransactionTypeDTO;

public class TransactionTypeOutputMessage extends AdvanzErpBaseOutputMessage{

	private TransactionTypeDTO transactionTypeDTO;
	
	private List<TransactionTypeDTO> transactionTypeDTOList;

	public TransactionTypeDTO getTransactionTypeDTO() {
		return transactionTypeDTO;
	}

	public void setTransactionTypeDTO(TransactionTypeDTO transactionTypeDTO) {
		this.transactionTypeDTO = transactionTypeDTO;
	}

	public List<TransactionTypeDTO> getTransactionTypeDTOList() {
		return transactionTypeDTOList;
	}

	public void setTransactionTypeDTOList(
			List<TransactionTypeDTO> transactionTypeDTOList) {
		this.transactionTypeDTOList = transactionTypeDTOList;
	}

	

}
