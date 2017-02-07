package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.BillDTO;
import com.advanz.erp.masters.model.ProformaMasterDTO;

@SuppressWarnings("serial")
public class ProformaMasterInputMessage extends AdvanzErpBaseInputMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProformaMasterDTO proformaMasterDTO;

	public ProformaMasterDTO getProformaMasterDTO() {
		return proformaMasterDTO;
	}

	public void setProformaMasterDTO(ProformaMasterDTO proformaMasterDTO) {
		this.proformaMasterDTO = proformaMasterDTO;
	}
	
	
	
	
	
	
}
