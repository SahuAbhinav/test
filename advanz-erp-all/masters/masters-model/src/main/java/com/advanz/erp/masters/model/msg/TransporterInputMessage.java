package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.TransporterDTO;

public class TransporterInputMessage  extends AdvanzErpBaseInputMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6010217276398805424L;
	
	private TransporterDTO transporterDTO;
	
	public TransporterDTO getTransporterDTO() {
		return transporterDTO;
	}

	public void setTransporterDTO(TransporterDTO transporterDTO) {
		this.transporterDTO = transporterDTO;
	}	
}
