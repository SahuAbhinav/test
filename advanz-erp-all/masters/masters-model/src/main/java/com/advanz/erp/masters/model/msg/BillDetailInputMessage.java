package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.BillDetailDTO;

public class BillDetailInputMessage extends AdvanzErpBaseInputMessage{
	private BillDetailDTO billDetailDTO;

	public BillDetailDTO getBillDetailDTO() {
		return billDetailDTO;
	}

	public void setBillDetailDTO(BillDetailDTO billDetailDTO) {
		this.billDetailDTO = billDetailDTO;
	}

}
