package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.ExciseLedgerDTO;

@SuppressWarnings("serial")
public class ExciseLedgerInputMessage extends AdvanzErpBaseInputMessage {
	private ExciseLedgerDTO exciseLedgerDTO;

	public ExciseLedgerDTO getExciseLedgerDTO() {
		return exciseLedgerDTO;
	}

	public void setExciseLedgerDTO(ExciseLedgerDTO exciseLedgerDTO) {
		this.exciseLedgerDTO = exciseLedgerDTO;
	}

}
