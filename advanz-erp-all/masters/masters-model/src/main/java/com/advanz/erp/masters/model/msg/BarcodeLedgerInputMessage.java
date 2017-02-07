package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.BarcodeLedgerDTO;
import com.advanz.erp.masters.model.criteria.BarcodeLedgerSearchCriteriaDTO;

public class BarcodeLedgerInputMessage extends AdvanzErpBaseInputMessage {

	private BarcodeLedgerDTO barcodeLedgerDTO;
	private BarcodeLedgerSearchCriteriaDTO barcodeLedgerSearchCriteriaDTO;

	public BarcodeLedgerSearchCriteriaDTO getBarcodeLedgerSearchCriteriaDTO() {
		return barcodeLedgerSearchCriteriaDTO;
	}

	public void setBarcodeLedgerSearchCriteriaDTO(
			BarcodeLedgerSearchCriteriaDTO barcodeLedgerSearchCriteriaDTO) {
		this.barcodeLedgerSearchCriteriaDTO = barcodeLedgerSearchCriteriaDTO;
	}

	public BarcodeLedgerDTO getBarcodeLedgerDTO() {
		return barcodeLedgerDTO;
	}

	public void setBarcodeLedgerDTO(BarcodeLedgerDTO barcodeLedgerDTO) {
		this.barcodeLedgerDTO = barcodeLedgerDTO;
	}

}
