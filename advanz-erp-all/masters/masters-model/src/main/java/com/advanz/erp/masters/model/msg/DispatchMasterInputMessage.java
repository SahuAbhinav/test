package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.BillDTO;
import com.advanz.erp.masters.model.DispatchDetailDTO;
import com.advanz.erp.masters.model.DispatchMasterDTO;

@SuppressWarnings("serial")
public class DispatchMasterInputMessage extends AdvanzErpBaseInputMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DispatchMasterDTO dispatchMasterDTO;


	public DispatchMasterDTO getDispatchMasterDTO() {
		return dispatchMasterDTO;
	}


	public void setDispatchMasterDTO(DispatchMasterDTO dispatchMasterDTO) {
		this.dispatchMasterDTO = dispatchMasterDTO;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
