package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.BillDTO;
import com.advanz.erp.masters.model.DispatchDetailDTO;

@SuppressWarnings("serial")
public class DispatchDetailInputMessage extends AdvanzErpBaseInputMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DispatchDetailDTO dispatchDetailDTO;

	public DispatchDetailDTO getDispatchDetailDTO() {
		return dispatchDetailDTO;
	}

	public void setDispatchDetailDTO(DispatchDetailDTO dispatchDetailDTO) {
		this.dispatchDetailDTO = dispatchDetailDTO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
