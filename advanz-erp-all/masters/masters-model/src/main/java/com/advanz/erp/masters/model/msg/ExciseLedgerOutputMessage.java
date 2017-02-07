package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.ExciseLedgerDTO;
@SuppressWarnings("serial")
public class ExciseLedgerOutputMessage extends AdvanzErpBaseOutputMessage {
	private ExciseLedgerDTO exciseLedgerDTO;
	private List<ExciseLedgerDTO> exciseLedgerDTOList;

	public ExciseLedgerDTO getExciseLedgerDTO() {
		return exciseLedgerDTO;
	}

	public void setExciseLedgerDTO(ExciseLedgerDTO exciseLedgerDTO) {
		this.exciseLedgerDTO = exciseLedgerDTO;
	}

	public List<ExciseLedgerDTO> getExciseLedgerDTOList() {
		return exciseLedgerDTOList;
	}

	public void setExciseLedgerDTOList(List<ExciseLedgerDTO> exciseLedgerDTOList) {
		this.exciseLedgerDTOList = exciseLedgerDTOList;
	}

}
