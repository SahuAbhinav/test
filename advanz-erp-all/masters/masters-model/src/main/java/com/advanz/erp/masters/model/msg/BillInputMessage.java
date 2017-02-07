package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.BillDTO;

@SuppressWarnings("serial")
public class BillInputMessage extends AdvanzErpBaseInputMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BillDTO billDTO;
	
	
	public BillDTO getBillDTO() {
		return billDTO;
	}
	public void setBillDTO(BillDTO billDTO) {
		this.billDTO = billDTO;
	}

	
	
	
}
