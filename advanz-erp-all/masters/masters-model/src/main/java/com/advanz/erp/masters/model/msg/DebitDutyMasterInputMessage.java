package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.BillDTO;
import com.advanz.erp.masters.model.DebitDutyMasterDTO;

@SuppressWarnings("serial")
public class DebitDutyMasterInputMessage extends AdvanzErpBaseInputMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DebitDutyMasterDTO debitDutyMasterDTO;

	public DebitDutyMasterDTO getDebitDutyMasterDTO() {
		return debitDutyMasterDTO;
	}

	public void setDebitDutyMasterDTO(DebitDutyMasterDTO debitDutyMasterDTO) {
		this.debitDutyMasterDTO = debitDutyMasterDTO;
	}
	
	
	

	
	
	
}
