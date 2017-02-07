package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.AdvanceAmountDTO;
@SuppressWarnings("serial")
public class AdvanceAmountInputMessage extends AdvanzErpBaseInputMessage{
     private AdvanceAmountDTO advanceAmountDTO;

	public AdvanceAmountDTO getAdvanceAmountDTO() {
		return advanceAmountDTO;
	}

	public void setAdvanceAmountDTO(AdvanceAmountDTO advanceAmountDTO) {
		this.advanceAmountDTO = advanceAmountDTO;
	}
     
}
