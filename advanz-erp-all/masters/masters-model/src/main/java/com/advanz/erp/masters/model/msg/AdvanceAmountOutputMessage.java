package com.advanz.erp.masters.model.msg;

import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.AdvanceAmountDTO;
@SuppressWarnings("serial")
public class AdvanceAmountOutputMessage extends AdvanzErpBaseOutputMessage {
	List<AdvanceAmountDTO> advanceAmountDTOList;
	private AdvanceAmountDTO advanceAmountDTO;
	private Integer transactionId;

	public List<AdvanceAmountDTO> getAdvanceAmountDTOList() {
		return advanceAmountDTOList;
	}

	public void setAdvanceAmountDTOList(
			List<AdvanceAmountDTO> advanceAmountDTOList) {
		this.advanceAmountDTOList = advanceAmountDTOList;
	}

	public AdvanceAmountDTO getAdvanceAmountDTO() {
		return advanceAmountDTO;
	}

	public void setAdvanceAmountDTO(AdvanceAmountDTO advanceAmountDTO) {
		this.advanceAmountDTO = advanceAmountDTO;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

}
